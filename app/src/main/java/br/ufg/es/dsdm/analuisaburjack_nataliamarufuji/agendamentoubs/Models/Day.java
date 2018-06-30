package br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.Models;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Day {

    private String date;

    private ArrayList<Consult> consults= new ArrayList<>();

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = DateFormat.getDateInstance(DateFormat.SHORT, Locale.ENGLISH)
                .format(date);
    }

    public ArrayList<Consult> getConsults() {
        return consults;
    }

    public void addConsult(Consult consult) {
        this.consults.add(consult);
    }
}
