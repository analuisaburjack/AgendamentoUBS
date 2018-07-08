package br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.SQL;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.models.Consult;
import br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.models.Day;

@Database(entities = {Consult.class, Day.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public abstract ConsultDAO consultDao();
    public abstract DayDAO dayDao();
}

