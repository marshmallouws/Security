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
public class MetacriticDTO {
    
    private String source;
    private double metacritic;
    
    public MetacriticDTO() {
    }
    
    public MetacriticDTO(String source, double metacritic) {
        this.source = source;
        this.metacritic = metacritic;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public double getMetacritic() {
        return metacritic;
    }

    public void setMetacritic(double metacritic) {
        this.metacritic = metacritic;
    }
}
