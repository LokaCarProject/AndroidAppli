package eni.baptistedixneuf.fr.lokacarproject.dao;

import android.app.Activity;

import java.util.List;

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
        return null;
    }

    @Override
    public Client get(int id) {
        return null;
    }

    @Override
    public void add(Client entity) {

    }

    @Override
    public void update(Client entity) {

    }

    @Override
    public void remove(int id) {

    }
}
