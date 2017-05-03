package eni.baptistedixneuf.fr.lokacarproject.dao;

import android.app.Activity;
import android.database.Cursor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import eni.baptistedixneuf.fr.lokacarproject.BDDHelper.BDD;
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
        BDD bdd = new BDD();
        List<EtatLieu> etatLieux = new ArrayList<>();
        try {
            bdd.open(context);
            Cursor cursor = bdd.getCategories();

            while(cursor.moveToNext()){
                EtatLieu etatLieu = new EtatLieu();
                etatLieu.setId(cursor.getInt(cursor.getColumnIndex("_id")));
                etatLieu.setChemin(cursor.getString(cursor.getColumnIndex("chemin")));
                boolean avant = cursor.getInt(cursor.getColumnIndex("avant")) > 0;
                etatLieu.setAvant(avant);
                ContratDao dao = new ContratDao(this.context);
                etatLieu.setContrat(dao.get(cursor.getInt(cursor.getColumnIndex("contrat"))));

                etatLieux.add(etatLieu);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return etatLieux;
    }

    @Override
    public EtatLieu get(int id) {
        BDD bdd = new BDD();
        EtatLieu etatLieu = new EtatLieu();
        try {
            bdd.open(context);
            Cursor cursor = bdd.getCategorie(id);

            while(cursor.moveToNext()){
                etatLieu.setId(cursor.getInt(cursor.getColumnIndex("_id")));
                etatLieu.setChemin(cursor.getString(cursor.getColumnIndex("chemin")));
                boolean avant = cursor.getInt(cursor.getColumnIndex("avant")) > 0;
                etatLieu.setAvant(avant);
                ContratDao dao = new ContratDao(this.context);
                etatLieu.setContrat(dao.get(cursor.getInt(cursor.getColumnIndex("contrat"))));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return etatLieu;
    }

    @Override
    public void add(EtatLieu entity) {
        BDD bdd = new BDD();
        try {
            bdd.open(context);
            bdd.addEtatLieu(entity);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(EtatLieu entity) {
        BDD bdd = new BDD();
        try {
            bdd.open(context);
            bdd.updateEtatLieu(entity);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void remove(int id) {
        BDD bdd = new BDD();
        try {
            bdd.open(context);
            bdd.removeEtatLieu(id);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
