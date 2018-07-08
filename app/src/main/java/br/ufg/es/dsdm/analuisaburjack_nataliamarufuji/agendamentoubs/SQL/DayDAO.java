package br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.SQL;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

import br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.models.Consult;

@Dao
public interface DayDAO {
    @Query("SELECT * FROM consults WHERE consult_date IN (:date) ORDER BY consult_hour ASC")
    List<Consult> getAll(String date);

    @Query("SELECT * FROM consults WHERE consult_date LIKE (:date) " +
            "AND consult_hour LIKE (:hour)")
    Consult loadByHour(String date, String hour);

    //@Insert
    //void insertConsult (Consult consult);
}

