package br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.SQL;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import java.text.MessageFormat;

import br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.models.Consult;
import br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.models.Consult.Entries;
import br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.models.User;

/**
 * Created by cedric on 22-05-15.
 */
public class ClientHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "clients.db";
    public static final int DB_VERSION = 1;

    private static ClientHelper INSTANCE = null;

    private ClientHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public static ClientHelper getInstance(Context context){
        if (INSTANCE == null) {
            INSTANCE = new ClientHelper(context);
        }
        return INSTANCE;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String  createCity = MessageFormat.format("CREATE TABLE {0} ( {1} INTEGER PRIMARY KEY, {2} TEXT, {3} TEXT )",
                User.Entries.TABLE_NAME, User.Entries.ID, User.Entries.COL_DATE_BIRTH, User.Entries.COL_NAME, User.Entries.COL_JOB, User.Entries.COL_EMAIL);
        db.execSQL(createCity);

        String createClient = MessageFormat.format("CREATE TABLE {0} ( {1} INTEGER PRIMARY KEY, {2} TEXT, {3} TEXT, {4} TEXT, {5} INTEGER, FOREIGN KEY({5}) REFERENCES {6}( {7} ))",
                Consult.Entries.TABLE_NAME, Consult.Entries.ID, Entries.COL_DATE_HOUR, Entries.COL_HOUR, Entries.COL_DATE, Entries.COL_BOOKED, Entries.COL_PACIENT_NAME, Entries.COL_PACIENT_BIRTH, Entries.COL_PACIENT_PHONE, Entries.COL_PACIENT_SUS
        );

        db.execSQL(createClient);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        SQLiteStatement delete = db.compileStatement("DROP TABLE?");
        delete.bindString(1, Consult.Entries.TABLE_NAME);
        delete.execute();
        delete.bindString(1, Consult.Entries.TABLE_NAME);
        delete.execute();
        onCreate(db);
    }

}
