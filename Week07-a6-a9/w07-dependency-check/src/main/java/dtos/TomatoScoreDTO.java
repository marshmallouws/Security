/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import java.util.Map;

/**
 *
 * @author Annika
 */
public class TomatoScoreDTO {

    private Map<String, Double> viewer;
    private Map<String, Double> critic;

    public TomatoScoreDTO() {
    }

    public TomatoScoreDTO(Map<String, Double> viewer, Map<String, Double> critic) {
        this.viewer = viewer;
        this.critic = critic;
    }

    public Map<String, Double> getViewer() {
        return viewer;
    }

    public void setViewer(Map<String, Double> viewer) {
        this.viewer = viewer;
    }

    public Map<String, Double> getCritic() {
        return critic;
    }

    public void setCritic(Map<String, Double> critic) {
        this.critic = critic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        
        TomatoScoreDTO dto = (TomatoScoreDTO) o;
        return viewer.equals(dto.getViewer()) && critic.equals(dto.getCritic());
    }
}
