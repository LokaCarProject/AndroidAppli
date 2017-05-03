package eni.baptistedixneuf.fr.lokacarproject.dao;

import android.app.Activity;
import android.database.Cursor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import eni.baptistedixneuf.fr.lokacarproject.BDDHelper.BDD;
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
        BDD bdd = new BDD();
        List<Categorie> categories = new ArrayList<>();
        try {
            bdd.open(context);
            Cursor cursor = bdd.getCategories();

            while(cursor.moveToNext()){
                Categorie categorie = new Categorie();
                categorie.setId(cursor.getInt(cursor.getColumnIndex("_id")));
                categorie.setNom(cursor.getString(cursor.getColumnIndex("nom")));

                categories.add(categorie);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return categories;
    }

    @Override
    public Categorie get(int id) {
        BDD bdd = new BDD();
        Categorie categorie = new Categorie();
        try {
            bdd.open(context);
            Cursor cursor = bdd.getCategorie(id);

            while(cursor.moveToNext()){
                categorie.setId(cursor.getInt(cursor.getColumnIndex("_id")));
                categorie.setNom(cursor.getString(cursor.getColumnIndex("nom")));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return categorie;
    }

    @Override
    public void add(Categorie entity) {
        BDD bdd = new BDD();
        try {
            bdd.open(context);
            bdd.addCategorie(entity);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(Categorie entity) {
        BDD bdd = new BDD();
        try {
            bdd.open(context);
            bdd.updateCategorie(entity);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void remove(int id) {
        BDD bdd = new BDD();
        try {
            bdd.open(context);
            bdd.removeCategorie(id);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
