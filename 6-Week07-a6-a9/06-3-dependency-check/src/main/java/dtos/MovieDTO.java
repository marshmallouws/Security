/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.Movie;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Annika
 */
public class MovieDTO {

    private String title;
    private int year;
    private String plot;
    private String directors;
    private String genres;
    private String cast;
    private String poster;
    private ImdbScoreDTO imdb;
    private TomatoScoreDTO tomato;
//    private MetacriticDTO metacritic;

    public MovieDTO() {
    }

    public MovieDTO(Movie m) {
        this.title = m.getTitle();
        this.year = m.getReleaseYear();
        this.plot = m.getPlot();
        this.cast = m.getMovieCast();
        this.directors = m.getDirectors();
        this.genres = m.getGenre();
        this.poster = m.getPoster();
        this.imdb = new ImdbScoreDTO(m.getImdbRating(), m.getImdbVotes());
        Map<String, Double> viewer = m.getTomatoViewers();
        Map<String, Double> critics = m.getTomatoCritics();
        this.tomato = new TomatoScoreDTO(viewer, critics);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
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

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public ImdbScoreDTO getImdb() {
        return imdb;
    }

    public void setImdb(ImdbScoreDTO imdb) {
        this.imdb = imdb;
    }

    public TomatoScoreDTO getTomato() {
        return tomato;
    }

    public void setTomato(TomatoScoreDTO tomato) {
        this.tomato = tomato;
    }

    public void merge(MovieDTO other) {
        this.title = other.title == null ? this.title : other.title;
        this.year = other.year == 0 ? this.year : other.year;
        this.plot = other.plot == null ? this.plot : other.plot;
        this.directors = other.directors == null ? this.directors : other.directors;
        this.genres = other.genres == null ? this.genres : other.genres;
        this.cast = other.cast == null ? this.cast : other.cast;
        this.poster = other.poster == null ? this.poster : other.poster;
        this.imdb = other.imdb == null ? this.imdb : other.imdb;
        this.tomato = other.tomato == null ? this.tomato : other.tomato;
//        this.metacritic = other.metacritic == null ? this.metacritic : other.metacritic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MovieDTO dto = (MovieDTO) o;
        return title.equals(dto.getTitle()) 
                && year == dto.getYear()
                && plot.equals(dto.getPlot())
                && directors.equals(dto.getDirectors())
                && genres.equals(dto.getGenres())
                && cast.equals(dto.getCast())
                && poster.equals(dto.getPoster())
                && imdb.equals(dto.getImdb())
                && tomato.equals(dto.getTomato());
    }

}
