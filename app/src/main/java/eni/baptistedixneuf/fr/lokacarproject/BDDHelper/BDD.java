package eni.baptistedixneuf.fr.lokacarproject.BDDHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;

import eni.baptistedixneuf.fr.lokacarproject.bo.Categorie;
import eni.baptistedixneuf.fr.lokacarproject.bo.Client;
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

    public Cursor getCategorie(int id){
        return base.rawQuery("SELECT _id, nom FROM categories where _id = " + id, null);
    }

    public void addCategorie(Categorie categorie){
        base.execSQL("INSERT INTO categories (nom) VALUES ('" + categorie.getNom() + "')");
    }

    public void removeCategorie(int id){
        remove("voitures", id);
    }

    public void updateCategorie(Categorie categorie){
        ContentValues args = new ContentValues();
        args.put("nom", categorie.getNom());

        update("categories", categorie.getId(), args);
    }


    public Cursor getVoitures(){
        return base.rawQuery("SELECT _id, modele, couleur, marque, immatriculation, prix, categorie " +
                                "FROM voitures", null);
    }

    public Cursor getVoiture(int id){
        return base.rawQuery("SELECT _id, modele, couleur, marque, immatriculation, prix, categorie " +
                "FROM voitures where _id = " + id, null);
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


    public Cursor getClients(){
        return base.rawQuery("SELECT _id, nom, prenom, adresse, telephone, email " +
                "FROM clients", null);
    }

    public Cursor getClient(int id){
        return base.rawQuery("SELECT _id, nom, prenom, adresse, telephone, email " +
                "FROM clients where _id = " + id, null);
    }

    public void addClient(Client client){
        base.execSQL("INSERT INTO clients (nom, prenom, adresse, telephone, email) VALUES " +
                "('" + client.getNom() + "','" + client.getPrenom() + "','" + client.getAdresse()
                + "','" + client.getTel() + "','" + client.getEmail() + "')");
    }

    public void removeClient(int id){
        remove("clients", id);
    }

    public void updateClient(Client client){
        ContentValues args = new ContentValues();
        args.put("nom", client.getNom());
        args.put("prenom", client.getPrenom());
        args.put("adresse", client.getAdresse());
        args.put("telephone", client.getTel());
        args.put("email", client.getEmail());

        update("clients", client.getId(), args);
    }
}
