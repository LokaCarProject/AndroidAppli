package eni.baptistedixneuf.fr.lokacarproject.dao;

import android.app.Activity;

import java.util.List;

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
}
