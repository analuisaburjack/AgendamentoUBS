package br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.Models;

public class Pacient extends Person {

    private String main_phone;
    private String sus_number;

    public String getMain_phone() {
        return main_phone;
    }

    public void setMain_phone(String main_phone) {
        this.main_phone = main_phone;
    }

    public String getSus_number() {
        return sus_number;
    }

    public void setSus_number(String cardSUS) {
        this.sus_number = cardSUS;
    }
}
