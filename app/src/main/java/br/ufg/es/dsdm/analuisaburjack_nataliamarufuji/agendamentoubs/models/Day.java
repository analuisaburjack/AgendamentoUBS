package br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.models;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Day {

    public Day(String date){
        setDate(date);
    }

    private String date;

    private List<Consult> consults= new ArrayList<>();

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Consult> getConsults() {
        return consults;
    }

    public void setConsults(List<Consult> consults) {
        this.consults = consults;
    }


}
