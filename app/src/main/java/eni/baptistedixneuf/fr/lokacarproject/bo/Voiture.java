package eni.baptistedixneuf.fr.lokacarproject.bo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mmarin2015 on 03/05/2017.
 */
public class Voiture {
    private int id;
    private String marque;
    private String modele;
    private String couleur;
    private String immatriculation;
    private double prix;
    private Categorie categorie;
    private List<PhotosVoiture> photos;

    public Voiture() {
        photos = new ArrayList<PhotosVoiture>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }
}
