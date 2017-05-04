package eni.baptistedixneuf.fr.lokacarproject.bo;

import java.io.Serializable;

/**
 * Created by mmarin2015 on 03/05/2017.
 */
public class PhotosVoiture implements Serializable{
    private int id;
    private String chemin;
    private Voiture voiture;

    public PhotosVoiture() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getChemin() {
        return chemin;
    }

    public void setChemin(String chemin) {
        this.chemin = chemin;
    }

    public Voiture getVoiture() {
        return voiture;
    }

    public void setVoiture(Voiture voiture) {
        this.voiture = voiture;
    }
}
