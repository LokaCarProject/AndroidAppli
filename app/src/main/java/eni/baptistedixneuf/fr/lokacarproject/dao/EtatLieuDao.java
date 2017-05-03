package eni.baptistedixneuf.fr.lokacarproject.dao;

import android.app.Activity;

import java.util.List;

import eni.baptistedixneuf.fr.lokacarproject.bo.EtatLieu;

/**
 * Created by pcormier2015 on 03/05/2017.
 **/
public class EtatLieuDao extends Dao<EtatLieu>{

    public EtatLieuDao (Activity context){
        super(context);
    }

    @Override
    public List<EtatLieu> getAll() {
        return null;
    }

    @Override
    public EtatLieu get(int id) {
        return null;
    }

    @Override
    public void add(EtatLieu entity) {

    }

    @Override
    public void update(EtatLieu entity) {

    }

    @Override
    public void remove(int id) {

    }
}
