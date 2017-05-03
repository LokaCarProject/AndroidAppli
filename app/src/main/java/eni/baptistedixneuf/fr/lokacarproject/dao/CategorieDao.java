package eni.baptistedixneuf.fr.lokacarproject.dao;

import android.app.Activity;

import java.util.List;

import eni.baptistedixneuf.fr.lokacarproject.bo.Categorie;

/**
 * Created by pcormier2015 on 03/05/2017.
 **/
public class CategorieDao extends Dao<Categorie>{

    public CategorieDao (Activity context){
        super(context);
    }

    @Override
    public List<Categorie> getAll() {
        return null;
    }

    @Override
    public Categorie get(int id) {
        return null;
    }

    @Override
    public void add(Categorie entity) {

    }

    @Override
    public void update(Categorie entity) {

    }

    @Override
    public void remove(int id) {

    }
}
