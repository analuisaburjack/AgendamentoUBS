package br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.SQL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.SQL.modelDAO.*;
import br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.models.Consult;

import static br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.models.Consult.Entries.*;
import static br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.models.Consult.Entries.COL_DATE;
import static br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.models.Consult.Entries.COL_HOUR;
import static br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.models.Consult.Entries.COL_PACIENT_SUS;
import static br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.models.Consult.Entries.ID;
import static br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.models.Consult.Entries.TABLE_NAME;


public class ConsultDAOImpl extends SqlDAO implements ConsultDAO {

    public ConsultDAOImpl(Context context) {
        super(context);
    }


    @Override
    public boolean contains(Consult consult) {
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COL_DATE + " = '" + consult.getDate() + " AND " + COL_HOUR + " = " + consult.getHour() + "'", null);
        c.moveToFirst();
        if (c.getCount()==0) return false;
        if (c.getLong(c.getColumnIndexOrThrow(ID)) == consult.getId()) return true;
        Log.d("VCO-DB", "contains consult");
        return false;
    }

    @Override
    public long addConsult(Consult consult) {

        ContentValues values = new ContentValues();
        values.put(COL_DATE, consult.getDate());
        values.put(COL_HOUR, consult.getHour());
        values.put(COL_PACIENT_NAME, consult.getPacientName());
        values.put(COL_PACIENT_SUS, consult.getPacientSus());
        values.put(COL_PACIENT_PHONE, consult.getPacientPhone());
        values.put(COL_PACIENT_BIRTH, consult.getPacientBirth());
        Log.d("VCO-DB", "add consult");
        return db.insertOrThrow(TABLE_NAME,null,values);
    }

    @Override
    public List<Consult> getConsults() {

        String[] projectionIn = new String[]{
                ID, COL_DATE, COL_HOUR
        };
        Cursor cursor = db.query(TABLE_NAME,projectionIn,null,null,null,null, COL_HOUR);
        List<Consult> consults = new ArrayList<>();
        if (cursor.getCount() >=0) {
            cursor.moveToFirst();
            do {
                Consult consult = new Consult();

                consult.setId(cursor.getLong(cursor.getColumnIndexOrThrow(Consult.Entries.ID)));
                consult.setDate(cursor.getString(cursor.getColumnIndexOrThrow(Consult.Entries.COL_DATE)));
                consult.setHour(cursor.getString(cursor.getColumnIndexOrThrow(Consult.Entries.COL_HOUR)));
                consult.setPacientName(cursor.getString(cursor.getColumnIndexOrThrow(Consult.Entries.COL_PACIENT_NAME)));
                consult.setPacientBirth(cursor.getString(cursor.getColumnIndexOrThrow(Consult.Entries.COL_PACIENT_BIRTH)));
                consult.setPacientPhone(cursor.getString(cursor.getColumnIndexOrThrow(Consult.Entries.COL_PACIENT_PHONE)));
                consult.setPacientSus(cursor.getString(cursor.getColumnIndexOrThrow(Consult.Entries.COL_PACIENT_SUS)));

                consult.setId(cursor.getLong(cursor.getColumnIndexOrThrow(ID)));
                consults.add(consult);

            } while (cursor.moveToNext());
            Log.d("VCO-DB", "list cities");
        }

        return consults;
    }
}
