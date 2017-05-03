package eni.baptistedixneuf.fr.lokacarproject.bo;

import java.util.List;

/**
 * Created by mmarin2015 on 03/05/2017.
 */
public class EtatLieu {
    private int id;
    private List<String> chemin;
    private boolean avant;

    public EtatLieu() {
    }

    public EtatLieu(boolean avant) {
        this.avant = avant;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getChemin() {
        return chemin;
    }

    public void setChemin(List<String> chemin) {
        this.chemin = chemin;
    }

    public boolean isAvant() {
        return avant;
    }

    public void setAvant(boolean avant) {
        this.avant = avant;
    }
}
