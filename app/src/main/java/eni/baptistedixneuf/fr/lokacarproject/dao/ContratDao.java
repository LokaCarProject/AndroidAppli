package eni.baptistedixneuf.fr.lokacarproject.dao;

import android.app.Activity;
import android.database.Cursor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import eni.baptistedixneuf.fr.lokacarproject.BDDHelper.BDD;
import eni.baptistedixneuf.fr.lokacarproject.bo.Contrat;

/**
 * Created by pcormier2015 on 03/05/2017.
 **/
public class ContratDao extends Dao<Contrat>{

    public ContratDao (Activity context){
        super(context);
    }

    @Override
    public List<Contrat> getAll() {
        BDD bdd = new BDD();
        List<Contrat> contrats = new ArrayList<>();
        try {
            bdd.open(context);
            Cursor cursor = bdd.getContrats();

            while(cursor.moveToNext()){
                Contrat contrat = new Contrat();
                contrat.setId(cursor.getInt(cursor.getColumnIndex("_id")));
                contrat.setDebut(new Date(cursor.getLong(cursor.getColumnIndex("dateDebut"))));
                contrat.setFinPrevue(new Date(cursor.getLong(cursor.getColumnIndex("dateFinPrevue"))));
                if(cursor.getLong(cursor.getColumnIndex("dateFinReel")) > 0){
                    contrat.setFinReel(new Date(cursor.getLong(cursor.getColumnIndex("dateFinReel"))));
                }
                boolean rendu = cursor.getInt(cursor.getColumnIndex("rendu")) > 0;
                contrat.setRendu(rendu);
                ClientDao cDao = new ClientDao(this.context);
                contrat.setClient(cDao.get(cursor.getInt(cursor.getColumnIndex("client"))));
                VoitureDao vDao = new VoitureDao(this.context);
                contrat.setVoiture(vDao.get(cursor.getInt(cursor.getColumnIndex("voiture"))));

                contrats.add(contrat);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return contrats;
    }

    @Override
    public Contrat get(int id) {
        BDD bdd = new BDD();
        Contrat contrat = new Contrat();
        try {
            bdd.open(context);
            Cursor cursor = bdd.getContrat(id);

            while(cursor.moveToNext()){
                contrat.setId(cursor.getInt(cursor.getColumnIndex("_id")));
                contrat.setDebut(new Date(cursor.getLong(cursor.getColumnIndex("dateDebut"))));
                contrat.setFinPrevue(new Date(cursor.getLong(cursor.getColumnIndex("dateFinPrevue"))));
                if(cursor.getLong(cursor.getColumnIndex("dateFinReel")) > 0){
                    contrat.setFinReel(new Date(cursor.getLong(cursor.getColumnIndex("dateFinReel"))));
                }                boolean rendu = cursor.getInt(cursor.getColumnIndex("rendu")) > 0;
                contrat.setRendu(rendu);
                ClientDao cDao = new ClientDao(this.context);
                contrat.setClient(cDao.get(cursor.getInt(cursor.getColumnIndex("client"))));
                VoitureDao vDao = new VoitureDao(this.context);
                contrat.setVoiture(vDao.get(cursor.getInt(cursor.getColumnIndex("voiture"))));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return contrat;
    }

    @Override
    public void add(Contrat entity) {
        BDD bdd = new BDD();
        try {
            bdd.open(context);
            bdd.addContrat(entity);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(Contrat entity) {
        BDD bdd = new BDD();
        try {
            bdd.open(context);
            bdd.updateContrat(entity);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void remove(int id) {
        BDD bdd = new BDD();
        try {
            bdd.open(context);
            bdd.removeContrat(id);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
