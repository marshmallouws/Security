/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.Request;
import java.util.Date;

/**
 *
 * @author Annika
 */

public class RequestDTO {
    private Long id;
    private Date date;
    private String movieName;
    private String username;
    
    public RequestDTO() {}
    
    public RequestDTO(Request req) {
        this.id = req.getId();
        this.date = req.getRequestTime();
        this.movieName = req.getMovie().getTitle();
        this.username = req.getUser().getUserName();
    }

    public Long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}