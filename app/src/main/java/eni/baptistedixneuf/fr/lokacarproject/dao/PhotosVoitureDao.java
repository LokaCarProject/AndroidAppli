package eni.baptistedixneuf.fr.lokacarproject.dao;

import android.app.Activity;

import java.util.List;

import eni.baptistedixneuf.fr.lokacarproject.bo.PhotosVoiture;

/**
 * Created by pcormier2015 on 03/05/2017.
 **/
public class PhotosVoitureDao extends Dao<PhotosVoiture>{

    public PhotosVoitureDao (Activity context){
        super(context);
    }

    @Override
    public List<PhotosVoiture> getAll() {
        return null;
    }

    @Override
    public PhotosVoiture get(int id) {
        return null;
    }

    @Override
    public void add(PhotosVoiture entity) {

    }

    @Override
    public void update(PhotosVoiture entity) {

    }

    @Override
    public void remove(int id) {

    }
}
