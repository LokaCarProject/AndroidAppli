package eni.baptistedixneuf.fr.lokacarproject.bo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mmarin2015 on 03/05/2017.
 */
public class EtatLieu implements Serializable{

    private int id;
    private String chemin;
    private boolean avant;
    private Contrat contrat;

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

    public String getChemin() {
        return chemin;
    }

    public void setChemin(String chemin) {
        this.chemin = chemin;
    }

    public boolean isAvant() {
        return avant;
    }

    public void setAvant(boolean avant) {
        this.avant = avant;
    }

    public Contrat getContrat() {
        return contrat;
    }

    public void setContrat(Contrat contrat) {
        this.contrat = contrat;
    }
}
