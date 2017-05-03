package eni.baptistedixneuf.fr.lokacarproject.dao;

import android.app.Activity;
import android.database.Cursor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import eni.baptistedixneuf.fr.lokacarproject.BDDHelper.BDD;
import eni.baptistedixneuf.fr.lokacarproject.bo.Voiture;

/**
 * Created by pcormier2015 on 03/05/2017.
 **/
public class VoitureDao extends Dao<Voiture>{
    public VoitureDao (Activity context){
        super(context);
    }

    @Override
    public List<Voiture> getAll(){
        BDD bdd = new BDD();
        List<Voiture> voitures = new ArrayList<>();
        try {
            bdd.open(context);
            Cursor cursor = bdd.getVoitures();

            while(cursor.moveToNext()){
                Voiture voiture = new Voiture();
                voiture.setId(cursor.getInt(cursor.getColumnIndex("_id")));
                voiture.setModele(cursor.getString(cursor.getColumnIndex("modele")));
                voiture.setCouleur(cursor.getString(cursor.getColumnIndex("couleur")));
                voiture.setMarque(cursor.getString(cursor.getColumnIndex("marque")));
                voiture.setImmatriculation(cursor.getString(cursor.getColumnIndex("immatriculation")));
                voiture.setPrix(cursor.getDouble(cursor.getColumnIndex("prix")));

                CategorieDao dao = new CategorieDao(this.context);
                voiture.setCategorie(dao.get(cursor.getColumnIndex("categorie")));

                voitures.add(voiture);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return voitures;
    }

    @Override
    public Voiture get(int id) {
        BDD bdd = new BDD();
        Voiture voiture = new Voiture();
        try {
            bdd.open(context);
            Cursor cursor = bdd.getVoiture(id);

            while(cursor.moveToNext()){
                voiture.setId(cursor.getInt(cursor.getColumnIndex("_id")));
                voiture.setModele(cursor.getString(cursor.getColumnIndex("modele")));
                voiture.setCouleur(cursor.getString(cursor.getColumnIndex("couleur")));
                voiture.setMarque(cursor.getString(cursor.getColumnIndex("marque")));
                voiture.setImmatriculation(cursor.getString(cursor.getColumnIndex("immatriculation")));
                voiture.setPrix(cursor.getDouble(cursor.getColumnIndex("prix")));

                CategorieDao dao = new CategorieDao(this.context);
                voiture.setCategorie(dao.get(cursor.getColumnIndex("categorie")));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return voiture;
    }

    @Override
    public void add(Voiture entity) {
        BDD bdd = new BDD();
        try {
            bdd.open(context);
            bdd.addVoiture(entity);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(Voiture entity) {
        BDD bdd = new BDD();
        try {
            bdd.open(context);
            bdd.updateVoiture(entity);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void remove(int id) {
        BDD bdd = new BDD();
        try {
            bdd.open(context);
            bdd.removeVoiture(id);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
