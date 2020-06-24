/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dtos.ImdbScoreDTO;
import dtos.MetacriticDTO;
import dtos.MovieDTO;
import dtos.TomatoScoreDTO;
import errorhandling.NotFoundException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import utils.EMF_Creator;

/**
 *
 * @author Annika
 */
public class MovieApiFacade {

    private static MovieApiFacade instance;
    private static EntityManagerFactory emf;
    private static MovieDBFacade movieDBFacade;
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private MovieApiFacade() {
    }

    public static MovieApiFacade getMovieApiFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MovieApiFacade();
            movieDBFacade = MovieDBFacade.getMovieDBFacade(emf);
        }
        return instance;
    }

    private List<Future<String>> submitTasks(String moviename, String[] endpoints) {
        ExecutorService executor
                = Executors.newFixedThreadPool(16); //Numbers of cores *2
        List<Future<String>> futures = new ArrayList<>();

        for (String s : endpoints) {
            Future<String> future = executor.submit(new Movies(s, moviename));
            futures.add(future);
        }

        executor.shutdown();
        return futures;
    }

    public MovieDTO getMoviesSimple(String moviename) throws InterruptedException, NotFoundException {
        moviename = moviename.replace(" ", "%20");
        String[] endpoints = {"movieInfo", "moviePoster"};
        List<Future<String>> futures = submitTasks(moviename, endpoints);
        MovieDTO m = new MovieDTO();

        try {
            for (Future<String> fut : futures) {
                MovieDTO newM = GSON.fromJson(fut.get(), MovieDTO.class);
                m.merge(newM); // merging the new DTO so the new future doesn't overwrite fields
            }
        } catch (ExecutionException e) {
            //Throw custom exception
        }

        if (m.getTitle() == null) {
            throw new NotFoundException(moviename + " is not found");
        }

        return m;
    }

    public MovieDTO getMoviesAll(String moviename, String username) throws InterruptedException, NotFoundException {
        String[] endpoints = {"movieInfo", "moviePoster", "imdbScore",
            "tomatoesScore", "metacriticScore"};

        MovieDTO dbDTO = movieDBFacade.findMovie(moviename);

        if (dbDTO != null) {
            movieDBFacade.saveRequest(username, dbDTO, null);
            return dbDTO;
        }

        moviename = moviename.replace(" ", "%20");
        List<Future<String>> futures = submitTasks(moviename, endpoints);
        MovieDTO m = new MovieDTO();
        ImdbScoreDTO i = null;
        TomatoScoreDTO t = null;
        MetacriticDTO mc = null;

        try {
            for (Future<String> fut : futures) {
                MovieDTO newM = GSON.fromJson(fut.get(), MovieDTO.class);
                i = GSON.fromJson(fut.get(), ImdbScoreDTO.class);
                t = GSON.fromJson(fut.get(), TomatoScoreDTO.class);
//                mc = GSON.fromJson(fut.get(), MetacriticDTO.class);

                if (i.getImdbRating() != 0) {
                    newM.setImdb(i);
                }

                if (t.getCritic() != null) {
                    newM.setTomato(t);
                }

//                if(mc.getSource() != null) {
//                    newM.setMetacritic(mc);
//                }
                m.merge(newM);
            }
        } catch (ExecutionException e) {
            //Throw custom exception
        }

        if (m.getTitle() == null) {
            throw new NotFoundException(moviename + " is not found");
        }

        movieDBFacade.saveRequest(username, m, t);
        return m;
    }

    class Movies implements Callable<String> {

        String endpoint;
        String title;

        Movies(String call, String title) {
            this.endpoint = call;
            this.title = title;
        }

        @Override
        public String call() throws Exception {
            URL url = new URL("http://exam-1187.mydemos.dk/" + endpoint + "/" + title);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/json;charset=UTF-8");
            Scanner scan = new Scanner(con.getInputStream());
            String jsonStr = null;
            if (scan.hasNext()) {
                jsonStr = scan.nextLine();
            }
            scan.close();
            return jsonStr;
        }
    }
}
