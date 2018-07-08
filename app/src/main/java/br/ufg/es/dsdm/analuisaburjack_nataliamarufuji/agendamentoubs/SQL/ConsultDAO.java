package br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.SQL;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.models.Consult;

@Dao
public interface ConsultDAO {
    @Query("SELECT * FROM consults WHERE consult_date IN (:date) ORDER BY consult_hour ASC")
    List<Consult> getAll(String date);

    @Query("SELECT * FROM consults WHERE consult_dateHour LIKE (:dateHour)")
    Consult loadByHour(String dateHour);

    @Insert
    void insertConsult (Consult consult);
}

