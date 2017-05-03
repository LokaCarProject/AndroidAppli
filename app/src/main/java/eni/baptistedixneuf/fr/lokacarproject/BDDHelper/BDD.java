package eni.baptistedixneuf.fr.lokacarproject.BDDHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;

import eni.baptistedixneuf.fr.lokacarproject.bo.Categorie;
import eni.baptistedixneuf.fr.lokacarproject.bo.Client;
import eni.baptistedixneuf.fr.lokacarproject.bo.Contrat;
import eni.baptistedixneuf.fr.lokacarproject.bo.EtatLieu;
import eni.baptistedixneuf.fr.lokacarproject.bo.PhotosVoiture;
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

    public Cursor getPhotosVoitures(){
        return base.rawQuery("SELECT _id, chemin, voiture " +
                "FROM photos_voitures", null);
    }

    public Cursor getPhotosVoiture(int id){
        return base.rawQuery("SELECT _id, chemin, voiture " +
                "FROM photos_voitures where _id = " + id, null);
    }

    public void addPhotosVoiture(PhotosVoiture photosVoiture){
        base.execSQL("INSERT INTO photos_voitures (chemin, voiture) VALUES " +
                "('" + photosVoiture.getChemin() + "'," + photosVoiture.getVoiture().getId() + ")");
    }

    public void removePhotosVoiture(int id){
        remove("photos_voitures", id);
    }

    public void updatePhotosVoiture(PhotosVoiture photosVoiture){
        ContentValues args = new ContentValues();
        args.put("chemin", photosVoiture.getChemin());
        args.put("voiture", photosVoiture.getVoiture().getId());

        update("photos_voitures", photosVoiture.getId(), args);
    }

    public Cursor getEtatsLieux(){
        return base.rawQuery("SELECT _id, chemin, avant, categorie " +
                "FROM etats_lieux", null);
    }

    public Cursor getEtatLieu(int id){
        return base.rawQuery("SELECT _id, chemin, avant, categorie " +
                "FROM etats_lieux where _id = " + id, null);
    }

    public void addEtatLieu(EtatLieu etatLieu){
        base.execSQL("INSERT INTO etats_lieux (chemin, avant, contrat) VALUES " +
                "('" + etatLieu.getChemin() + "'," + etatLieu.isAvant() + ", " + etatLieu.getContrat().getId() + ")");
    }

    public void removeEtatLieu(int id){
        remove("etats_lieux", id);
    }

    public void updateEtatLieu(EtatLieu etatLieu){
        ContentValues args = new ContentValues();
        args.put("chemin", etatLieu.getChemin());
        args.put("avant", etatLieu.isAvant());
        args.put("contrat", etatLieu.getContrat().getId());

        update("etats_lieux", etatLieu.getId(), args);
    }

    public Cursor getContrats(){
        return base.rawQuery("SELECT _id, dateDebut, dateFinPrevue, dateFinReel, rendu, client, voiture " +
                "FROM contrats", null);
    }

    public Cursor getContrat(int id){
        return base.rawQuery("SELECT _id, dateDebut, dateFinPrevue, dateFinReel, rendu, client, voiture " +
                "FROM contrats where _id = " + id, null);
    }

    public void addContrat(Contrat contrat){
        base.execSQL("INSERT INTO contrats (dateDebut, dateFinPrevue, dateFinReel, rendu, client, voiture) VALUES " +
                "(" + contrat.getDebut().getTime() + "," + contrat.getFinPrevue().getTime() + ", "
                    + contrat.getFinReel().getTime() + ", " + contrat.isRendu() + ","
                    + contrat.getClient().getId() + "," + contrat.getVoiture().getId() + ",)");
    }

    public void removeContrat(int id){
        remove("contrats", id);
    }

    public void updateContrat(Contrat contrat){
        ContentValues args = new ContentValues();
        args.put("dateDebut", contrat.getDebut().getTime());
        args.put("dateFinPrevue", contrat.getFinPrevue().getTime());
        args.put("dateFinReel", contrat.getFinReel().getTime());
        args.put("rendu", contrat.isRendu());
        args.put("client", contrat.getClient().getId());
        args.put("voiture", contrat.getVoiture().getId());

        update("contrats", contrat.getId(), args);
    }
}
