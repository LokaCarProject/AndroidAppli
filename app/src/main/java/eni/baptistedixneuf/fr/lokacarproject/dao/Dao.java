package eni.baptistedixneuf.fr.lokacarproject.dao;

import android.app.Activity;

import java.sql.SQLException;
import java.util.List;

import eni.baptistedixneuf.fr.lokacarproject.BDDHelper.BDD;

/**
 * Created by pcormier2015 on 03/05/2017.
 **/
public abstract class Dao<E> {
    protected Activity context;

    public Dao (Activity context){
        this.context = context;
    }

    public abstract List<E> getAll();

    public abstract E get(int id);

    public abstract void add(E entity);

    public abstract void update(E entity);

    public abstract void remove(int id);

    protected int getInsertId(String table){
        int id = 0;
        BDD bdd = new BDD();

        try {
            bdd.open(context);
            id = bdd.getInsertID(table);
        } catch (SQLException e){
            e.printStackTrace();
        }

        return id;
    }
}
