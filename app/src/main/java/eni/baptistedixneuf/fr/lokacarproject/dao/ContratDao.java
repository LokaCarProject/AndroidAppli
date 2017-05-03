package eni.baptistedixneuf.fr.lokacarproject.dao;

import android.app.Activity;

import java.util.List;

import eni.baptistedixneuf.fr.lokacarproject.bo.Contrat;

/**
 * Created by pcormier2015 on 03/05/2017.
 **/
public class ContratDao extends Dao<Contrat>{

    public ContratDao (Activity context){
        super(context);
    }

    @Override
    public List<Contrat> getAll() {
        return null;
    }

    @Override
    public Contrat get(int id) {
        return null;
    }

    @Override
    public void add(Contrat entity) {

    }

    @Override
    public void update(Contrat entity) {

    }

    @Override
    public void remove(int id) {

    }
}
