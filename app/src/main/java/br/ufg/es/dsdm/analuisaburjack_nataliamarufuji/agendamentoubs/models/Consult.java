package br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.models;

import com.google.gson.annotations.SerializedName;

public class Consult {
    @SerializedName("list_hour")
    private String hour;
    private Boolean booked;
    private Pacient pacient;

    public Consult(){
        setBooked(false);
    }

    public Consult(Boolean booked, String hour, Pacient pacient){
        sethour(hour);
        setPacient(pacient);
        setBooked(booked);
    }

    public Boolean isBooked() {
        return booked;
    }

    public void setBooked(Boolean booked) {
        this.booked = booked;
    }

    public String getHour() {
        return hour;
    }

    public void sethour(String hour) {
        this.hour = hour;
    }

    public Pacient getPacient() {
        return pacient;
    }

    public void setPacient(Pacient pacient) {
        this.pacient = pacient;
    }
}
