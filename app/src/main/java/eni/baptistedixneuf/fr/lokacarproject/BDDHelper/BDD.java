package eni.baptistedixneuf.fr.lokacarproject.BDDHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;

import eni.baptistedixneuf.fr.lokacarproject.bo.Voiture;

/**
 * Created by pcormier2015 on 03/05/2017.
 **/
public class BDD {
    BDDHelper helper;
    SQLiteDatabase base;

    public BDD() {
    }

    public void open(Context activity) throws SQLException {
        helper = new BDDHelper(activity);
        base = helper.getWritableDatabase();
    }

    public void remove(String table, int id){
        base.delete(table, "_id=" + id, null);
    }

    public void update(String table, int id, ContentValues args){
        String strFilter = "_id=" + id;
        base.update(table, args, strFilter, null);
    }

    public Cursor getCategories(){
        return base.rawQuery("SELECT _id, nom FROM categories", null);
    }

    public Cursor getVoitures(){
        return base.rawQuery("SELECT _id, modele, couleur, marque, immatriculation, prix, categorie " +
                                "FROM voitures", null);
    }

    public Cursor getVoiture(int id){
        return base.rawQuery("SELECT _id, modele, couleur, marque, immatriculation, prix, categorie " +
                "FROM voitures whete _id = " + id, null);
    }

    public void addVoiture(Voiture voiture){
        base.execSQL("INSERT INTO voitures (modele, couleur, marque, immatriculation, prix, categorie) VALUES " +
                "('" + voiture.getModele() + "','" + voiture.getCouleur() + "','" + voiture.getMarque()
                + "','" + voiture.getImmatriculation() + "'," + voiture.getPrix()
                + "," + voiture.getCategorie().getId() + ")");
    }

    public void removeVoiture(int id){
        remove("voitures", id);
    }

    public void updateVoiture(Voiture voiture){
        ContentValues args = new ContentValues();
        args.put("modele", voiture.getModele());
        args.put("couleur", voiture.getCouleur());
        args.put("marque", voiture.getMarque());
        args.put("immatriculation", voiture.getImmatriculation());
        args.put("prix", voiture.getPrix());
        args.put("categorie", voiture.getCategorie().getId());

        update("voitures", voiture.getId(), args);
    }
}
