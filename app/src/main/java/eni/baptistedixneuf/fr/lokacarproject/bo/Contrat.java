package eni.baptistedixneuf.fr.lokacarproject.bo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by mmarin2015 on 03/05/2017.
 */
public class Contrat implements Serializable{

    private int id;
    private Date debut;
    private Date finPrevue;
    private Date finReel;
    private boolean rendu;

    private EtatLieu avant;
    private EtatLieu apres;
    private Voiture voiture;
    private Client client;

    public Contrat() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDebut() {
        return debut;
    }

    public void setDebut(Date debut) {
        this.debut = debut;
    }

    public Date getFinPrevue() {
        return finPrevue;
    }

    public void setFinPrevue(Date finPrevue) {
        this.finPrevue = finPrevue;
    }

    public Date getFinReel() {
        return finReel;
    }

    public void setFinReel(Date finReel) {
        this.finReel = finReel;
    }

    public boolean isRendu() {
        return rendu;
    }

    public void setRendu(boolean rendu) {
        this.rendu = rendu;
    }

    public EtatLieu getAvant() {
        return avant;
    }

    public void setAvant(EtatLieu avant) {
        this.avant = avant;
    }

    public EtatLieu getApres() {
        return apres;
    }

    public void setApres(EtatLieu apres) {
        this.apres = apres;
    }

    public Voiture getVoiture() {
        return voiture;
    }

    public void setVoiture(Voiture voiture) {
        this.voiture = voiture;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
