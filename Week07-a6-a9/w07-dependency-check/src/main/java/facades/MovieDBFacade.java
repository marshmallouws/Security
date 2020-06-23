/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.MovieDTO;
import dtos.TomatoScoreDTO;
import entities.Movie;
import entities.Request;
import entities.User;
import java.awt.BorderLayout;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import utils.EMF_Creator;

/**
 *
 * @author Annika
 */
public class MovieDBFacade {

    private static MovieDBFacade instance;
    private static EntityManagerFactory emf;

    private MovieDBFacade() {
    }

    public static MovieDBFacade getMovieDBFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MovieDBFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public Long getCount(String title) {
        EntityManager em = getEntityManager();
        
        try {
            Query q = em.createQuery("SELECT COUNT(m) FROM Request r JOIN r.movie m WHERE m.title = :title");
            q.setParameter("title", title);
            return (Long) q.getSingleResult();
        } finally {
            em.close();
        }
    }
    
    public static void main(String[] args) {
        EntityManagerFactory emf2 = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE);
        Long count = MovieDBFacade.getMovieDBFacade(emf2).getCount("Grease");
        System.out.println(count);
    }

    public void saveRequest(String username, MovieDTO m, TomatoScoreDTO t) {
        EntityManager em = getEntityManager();
        User user = user = em.find(User.class, username); // Null if not exists
        Movie movie = null;

        //Used to merge movies so that there won't be duplicates in db
        try {
            TypedQuery<Movie> tq
                    = em.createQuery("SELECT m FROM Movie m WHERE m.title = :title", Movie.class);
            tq.setParameter("title", m.getTitle());
            movie = tq.getSingleResult();
        } catch (NoResultException e) {
            double imdbRating = m.getImdb().getImdbRating();
            int imdbVotes = m.getImdb().getImdbVotes();

            // Manually adding to a new Map or else it will throw an exception
            Map<String, Double> tViewers = m.getTomato().getViewer();
            Map<String, Double> viewers = new HashMap();
            tViewers.forEach((k, v) -> {
                viewers.put(k, v);
            });

            // Manually adding to a new Map or else it will throw an exception
            Map<String, Double> tCritics = m.getTomato().getCritic();
            Map<String, Double> critics = new HashMap();
            tCritics.forEach((k, v) -> {
                critics.put(k, v);
            });

            movie = new Movie(m.getTitle(), m.getYear(), m.getPlot(), m.getDirectors(),
                    m.getGenres(), m.getCast(), m.getPoster(), critics, viewers, imdbVotes, imdbRating);
        }

        try {
            Request req = new Request(movie, user);
            em.getTransaction().begin();
            em.merge(req);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public MovieDTO findMovie(String title) {
        EntityManager em = getEntityManager();
        Movie movie = null;
        try {
            TypedQuery<Movie> tq
                    = em.createQuery("SELECT m FROM Movie m WHERE m.title = :title", Movie.class);
            tq.setParameter("title", title);
            movie = tq.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
        return new MovieDTO(movie);
    }
    

}
