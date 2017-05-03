package eni.baptistedixneuf.fr.lokacarproject.BDDHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by pcormier2015 on 03/05/2017.
 **/
public class BDDHelper extends SQLiteOpenHelper {
    public BDDHelper(Context context) {
        super(context, "infos.db", null, 1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE voitures (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                            "modele TEXT NOT NULL, " +
                                            "couleur TEXT NOT NULL, " +
                                            "marque TEXT NOT NULL, " +
                                            "immatriculation TEXT NOT NULL, " +
                                            "prix REAL NOT NULL, " +
                                            "categorie INTEGER NOT NULL)");

        db.execSQL("CREATE TABLE clients (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                            "nom TEXT NOT NULL, " +
                                            "prenom TEXT NOT NULL, " +
                                            "adresse TEXT NOT NULL, " +
                                            "telephone TEXT NOT NULL, " +
                                            "mail TEXT NOT NULL)");

        db.execSQL("CREATE TABLE contrats (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                            "dateDebut DATETIME NOT NULL, " +
                                            "dateFinPrevue DATETIME NOT NULL, " +
                                            "dateFinReel DATETIME NOT NULL, " +
                                            "rendu BOOLEAN NOT NULL, " +
                                            "client INTEGER NOT NULL, " +
                                            "voiture INTEGER NOT NULL)");

        db.execSQL("ALTER TABLE contrats ADD CONSTRAINT FOREIGN KEY (client) REFERENCES clients(_id)");
        db.execSQL("ALTER TABLE contrats ADD CONSTRAINT FOREIGN KEY (voiture) REFERENCES voitures(_id)");

        db.execSQL("CREATE TABLE photos_voitures (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                                "chemin TEXT NOT NULL, " +
                                                "voiture INTEGER NOT NULL)");

        db.execSQL("ALTER TABLE photos_voitures ADD CONSTRAINT FOREIGN KEY (voiture) REFERENCES voitures(_id)");

        db.execSQL("CREATE TABLE categories (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                                "nom TEXT NOT NULL");

        db.execSQL("ALTER TABLE voitures ADD CONSTRAINT FOREIGN KEY (categorie) REFERENCES categories(_id)");

        db.execSQL("CREATE TABLE etats_lieux (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                                "chemin TEXT NOT NULL, " +
                                                "contrat INTEGER NOT NULL)");

        db.execSQL("ALTER TABLE etats_lieux ADD CONSTRAINT FOREIGN KEY (contrat) REFERENCES contrats(_id)");


    }
}
