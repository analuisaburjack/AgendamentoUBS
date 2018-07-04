package br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.models;

import java.util.ArrayList;
import java.util.List;


public class Day {

    private String date;

    public List<String> listaTeste;

    private List<Consult> consults= new ArrayList<>();

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Consult> getConsults() {return this.consults; }

    public void setConsults(List<Consult> consults) {
        this.consults = consults;
    }

}
