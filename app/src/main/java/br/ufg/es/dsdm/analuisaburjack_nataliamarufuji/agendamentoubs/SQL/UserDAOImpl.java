package br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.SQL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.SQL.modelDAO.*;
import br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.models.User;

import static br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.models.User.Entries.*;
import static br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.models.User.Entries.COL_NAME;
import static br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.models.User.Entries.COL_DATE_BIRTH;
import static br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.models.User.Entries.ID;
import static br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.models.User.Entries.TABLE_NAME;


public class UserDAOImpl extends SqlDAO implements UserDAO {

    public UserDAOImpl(Context context) {
        super(context);
    }


    @Override
    public boolean contains(User user) {
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COL_NAME + " = '" + user.getName() + " AND " + COL_DATE_BIRTH + " = '" + user.getDate_of_birth() + "'", null);
        c.moveToFirst();
        if (c.getCount()==0) return false;
        if (c.getLong(c.getColumnIndexOrThrow(ID)) == user.getId()) return true;
        Log.d("VCO-DB", "contains user");
        return false;
    }

    @Override
    public List<User> getUsers() {

        String[] projectionIn = new String[]{
                ID, COL_NAME, COL_DATE_BIRTH
        };
        String order = COL_NAME + " ASC";
        Cursor cursor = db.query(TABLE_NAME,projectionIn,null,null,null,null,order);
        List<User> users = new ArrayList<>();
        if (cursor.getCount() >=0) {
            cursor.moveToFirst();
            do {
                User user = new User();

                user.setId(cursor.getLong(cursor.getColumnIndexOrThrow(User.Entries.ID)));
                user.setName(cursor.getString(cursor.getColumnIndexOrThrow(User.Entries.COL_NAME)));
                user.setDate_of_birth(cursor.getString(cursor.getColumnIndexOrThrow(User.Entries.COL_DATE_BIRTH)));
                user.setEmail(cursor.getString(cursor.getColumnIndexOrThrow(User.Entries.COL_EMAIL)));
                user.setJob(cursor.getString(cursor.getColumnIndexOrThrow(User.Entries.COL_JOB)));

                user.setId(cursor.getLong(cursor.getColumnIndexOrThrow(ID)));
                users.add(user);

            } while (cursor.moveToNext());
            Log.d("VCO-DB", "list cities");
        }

        return users;
    }
}
