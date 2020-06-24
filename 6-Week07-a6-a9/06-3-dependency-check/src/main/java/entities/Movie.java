/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Annika
 */
@Entity
@Table(name = "movie")
@NamedQuery(name = "Movie.DeleteAllRows", query = "DELETE FROM Movie")
public class Movie implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private int releaseYear;
    private String plot;
    private String directors;
    private String genre;
    private String movieCast;
    private String poster;
    private double imdbRating;
    private int imdbVotes;

    @ElementCollection
    @MapKeyColumn(name = "name")
    @Column(name = "value")
    @CollectionTable(name = "tomato_critics", joinColumns = @JoinColumn(name = "movie_id"))
    private Map<String, Double> tomatoCritics = new HashMap();

    @ElementCollection
    @MapKeyColumn(name = "name")
    @Column(name = "value")
    @CollectionTable(name = "tomato_viewers", joinColumns = @JoinColumn(name = "movie_id"))
    private Map<String, Double> tomatoViewers = new HashMap();


    @OneToMany(mappedBy = "movie", cascade = CascadeType.MERGE)
    private List<Request> requests;

    public Movie() {
    }
    
    public Movie(String title, int year, String plot, String directors, 
            String genre, String cast, String poster) {
        this.title = title;
        this.releaseYear = year;
        this.plot = plot;
        this.directors = directors;
        this.genre = genre;
        this.movieCast = cast;
        this.poster = poster;
    }

    public Movie(String title, int year, String plot, String directors,
            String genre, String cast, String poster, Map<String, Double> critics, 
            Map<String, Double> viewers, int imdbVotes, double imdbRating) {
        this.title = title;
        this.releaseYear = year;
        this.plot = plot;
        this.directors = directors;
        this.genre = genre;
        this.movieCast = cast;
        this.poster = poster;
        this.tomatoCritics = critics;
        this.tomatoViewers = viewers;
        this.imdbRating = imdbRating;
        this.imdbVotes = imdbVotes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getDirectors() {
        return directors;
    }

    public void setDirectors(String directors) {
        this.directors = directors;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getMovieCast() {
        return movieCast;
    }

    public void setMovieCast(String movieCast) {
        this.movieCast = movieCast;
    }

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }

    public double getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(double imdbRating) {
        this.imdbRating = imdbRating;
    }

    public int getImdbVotes() {
        return imdbVotes;
    }

    public void setImdbVotes(int imdbVotes) {
        this.imdbVotes = imdbVotes;
    }

    public Map<String, Double> getTomatoCritics() {
        return tomatoCritics;
    }

    public void setTomatoCritics(Map<String, Double> tomatoCritics) {
        this.tomatoCritics = tomatoCritics;
    }

    public Map<String, Double> getTomatoViewers() {
        return tomatoViewers;
    }

    public void setTomatoViewers(Map<String, Double> tomatoViewers) {
        this.tomatoViewers = tomatoViewers;
    }
}
