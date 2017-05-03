package eni.baptistedixneuf.fr.lokacarproject.dao;

import android.app.Activity;
import android.database.Cursor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import eni.baptistedixneuf.fr.lokacarproject.BDDHelper.BDD;
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
        BDD bdd = new BDD();
        List<PhotosVoiture> photosVoitures = new ArrayList<>();
        try {
            bdd.open(context);
            Cursor cursor = bdd.getPhotosVoitures();

            while(cursor.moveToNext()){
                PhotosVoiture photosVoiture = new PhotosVoiture();
                photosVoiture.setId(cursor.getInt(cursor.getColumnIndex("_id")));
                photosVoiture.setChemin(cursor.getString(cursor.getColumnIndex("chemin")));

                VoitureDao dao = new VoitureDao(this.context);
                photosVoiture.setVoiture(dao.get(cursor.getInt(cursor.getColumnIndex("voiture"))));

                photosVoitures.add(photosVoiture);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return photosVoitures;
    }

    @Override
    public PhotosVoiture get(int id) {
        BDD bdd = new BDD();
        PhotosVoiture photosVoiture = new PhotosVoiture();
        try {
            bdd.open(context);
            Cursor cursor = bdd.getPhotosVoiture(id);

            while(cursor.moveToNext()){
                photosVoiture.setId(cursor.getInt(cursor.getColumnIndex("_id")));
                photosVoiture.setChemin(cursor.getString(cursor.getColumnIndex("chemin")));

                VoitureDao dao = new VoitureDao(this.context);
                photosVoiture.setVoiture(dao.get(cursor.getInt(cursor.getColumnIndex("voiture"))));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return photosVoiture;
    }

    @Override
    public void add(PhotosVoiture entity) {
        BDD bdd = new BDD();
        try {
            bdd.open(context);
            bdd.addPhotosVoiture(entity);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(PhotosVoiture entity) {
        BDD bdd = new BDD();
        try {
            bdd.open(context);
            bdd.updatePhotosVoiture(entity);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void remove(int id) {
        BDD bdd = new BDD();
        try {
            bdd.open(context);
            bdd.removePhotosVoiture(id);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
