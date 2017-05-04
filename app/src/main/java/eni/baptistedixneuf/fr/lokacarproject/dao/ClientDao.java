package eni.baptistedixneuf.fr.lokacarproject.dao;

import android.app.Activity;
import android.database.Cursor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import eni.baptistedixneuf.fr.lokacarproject.BDDHelper.BDD;
import eni.baptistedixneuf.fr.lokacarproject.bo.Client;

/**
 * Created by pcormier2015 on 03/05/2017.
 **/
public class ClientDao extends Dao<Client>{

    public ClientDao (Activity context){
        super(context);
    }

    @Override
    public List<Client> getAll() {
        BDD bdd = new BDD();
        List<Client> clients = new ArrayList<>();
        try {
            bdd.open(context);
            Cursor cursor = bdd.getClients();

            while(cursor.moveToNext()){
                Client client = new Client();
                client.setId(cursor.getInt(cursor.getColumnIndex("_id")));
                client.setNom(cursor.getString(cursor.getColumnIndex("nom")));
                client.setPrenom(cursor.getString(cursor.getColumnIndex("prenom")));
                client.setAdresse(cursor.getString(cursor.getColumnIndex("adresse")));
                client.setTel(cursor.getString(cursor.getColumnIndex("telephone")));
                client.setEmail(cursor.getString(cursor.getColumnIndex("mail")));

                clients.add(client);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return clients;
    }

    @Override
    public Client get(int id) {
        BDD bdd = new BDD();
        Client client = new Client();
        try {
            bdd.open(context);
            Cursor cursor = bdd.getClient(id);

            while(cursor.moveToNext()){
                client.setId(cursor.getInt(cursor.getColumnIndex("_id")));
                client.setNom(cursor.getString(cursor.getColumnIndex("nom")));
                client.setPrenom(cursor.getString(cursor.getColumnIndex("prenom")));
                client.setAdresse(cursor.getString(cursor.getColumnIndex("adresse")));
                client.setTel(cursor.getString(cursor.getColumnIndex("telephone")));
                client.setEmail(cursor.getString(cursor.getColumnIndex("mail")));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return client;
    }

    @Override
    public void add(Client entity) {
        BDD bdd = new BDD();
        try {
            bdd.open(context);
            bdd.addClient(entity);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(Client entity) {
        BDD bdd = new BDD();
        try {
            bdd.open(context);
            bdd.updateClient(entity);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void remove(int id) {
        BDD bdd = new BDD();
        try {
            bdd.open(context);
            bdd.removeClient(id);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public int getInsertId() {
        return super.getInsertId("clients");
    }
}
