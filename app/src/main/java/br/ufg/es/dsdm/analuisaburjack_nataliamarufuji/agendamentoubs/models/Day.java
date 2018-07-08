package br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "day")
public class Day {

    @PrimaryKey
    @ColumnInfo(name = "date")
    private String date;

    /*public List<String> listaTeste;

    private List<Consult> consults= new ArrayList<>();*/

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    /*public List<Consult> getConsults() {return this.consults; }

    public void setConsults(List<Consult> consults) {
        this.consults = consults;
    }*/

}
