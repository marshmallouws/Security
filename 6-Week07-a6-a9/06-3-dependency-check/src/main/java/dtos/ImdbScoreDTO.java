/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

/**
 *
 * @author Annika
 */
public class ImdbScoreDTO {

    private double imdbRating;
    private int imdbVotes;

    public ImdbScoreDTO() {
    }

    public ImdbScoreDTO(double imdbRating, int imdbVotes) {
        this.imdbRating = imdbRating;
        this.imdbVotes = imdbVotes;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ImdbScoreDTO i = (ImdbScoreDTO) o;
        return imdbRating == i.getImdbRating() && imdbVotes == i.getImdbVotes();
    }

}
