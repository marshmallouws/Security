/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.MovieDTO;
import dtos.TomatoScoreDTO;
import entities.Movie;
import entities.User;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import utils.EMF_Creator;

/**
 *
 * @author Annika
 */
public class MovieDBFacadeTest {

    private static EntityManagerFactory emf;
    private static MovieDBFacade facade;
    private static String username = "user";
    private static String password = "password";

    private static String title = "Cronos";
    private static int year = 2011;
    private static String plot = "Something";
    private static String director = "Peter";
    private static String genre = "Romance";
    private static String cast = "Peter, Annika";
    private static String poster = "billede";
    private static double imdbRating = 10.0;
    private static int imdbVotes = 1000;

    private static Map<String, Double> critics = new HashMap() {
        {
            put("rating", 100.0);
        }
    };

    private static Map<String, Double> viewers = new HashMap() {
        {
            put("rating", 11.0);
        }
    };

    public MovieDBFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.TEST, EMF_Creator.Strategy.DROP_AND_CREATE);
        facade = MovieDBFacade.getMovieDBFacade(emf);

        Movie movie = new Movie(title, year, plot, director, genre,
                cast, poster, critics, viewers, imdbVotes, imdbRating);

        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(movie);
            em.persist(new User(username, password));
            em.getTransaction().commit();
        } finally {
            em.close();
        }

    }

    @BeforeEach
    public void setUp() {

    }

    @Test
    public void testFindMovie() {
        MovieDTO m = facade.findMovie(title);

        assertEquals(title, m.getTitle());
        assertEquals(year, m.getYear());
        assertEquals(director, m.getDirectors());
        assertEquals(plot, m.getPlot());
        assertEquals(genre, m.getGenres());
        assertEquals(cast, m.getCast());
        assertEquals(poster, m.getPoster());
        assertEquals(imdbRating, m.getImdb().getImdbRating());
        assertEquals(imdbVotes, m.getImdb().getImdbVotes());
        assertEquals(critics, m.getTomato().getCritic());
        assertEquals(viewers, m.getTomato().getViewer());
    }

    @Test
    public void negativeTestFindMovie() {
        MovieDTO m = facade.findMovie("Something");
        assertEquals(null, m);
    }

    @Test
    public void testSaveRequest() {
        Movie movie = new Movie("Hej", year, plot, director, genre,
                cast, poster, critics, viewers, imdbVotes, imdbRating);
        MovieDTO m = new MovieDTO(movie);
        TomatoScoreDTO t = new TomatoScoreDTO(viewers, critics);
        String username = "user";

        facade.saveRequest(username, m, t);
        
        MovieDTO mdto = facade.findMovie("Hej");
        assertTrue(mdto.equals(m));
    }
}
