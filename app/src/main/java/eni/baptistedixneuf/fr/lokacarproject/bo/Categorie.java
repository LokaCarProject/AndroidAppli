package eni.baptistedixneuf.fr.lokacarproject.bo;

import java.io.Serializable;

/**
 * Created by mmarin2015 on 03/05/2017.
 */
public class Categorie implements Serializable{

    private int id;
    private String nom;

    public Categorie() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}

