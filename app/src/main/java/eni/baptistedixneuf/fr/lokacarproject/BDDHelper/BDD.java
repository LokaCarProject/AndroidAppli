package eni.baptistedixneuf.fr.lokacarproject.BDDHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;

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

    public Cursor getCategories(){
        return base.rawQuery("SELECT _id, nom FROM categories", null);
    }
}
