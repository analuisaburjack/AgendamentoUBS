package br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.models;

import com.google.gson.annotations.SerializedName;

public class Consult {
    //@SerializedName("list_hour")
    private String hour;
    private Boolean booked;
    private String pacientName;
    private String pacientBirth;
    private String pacientPhone;
    private String pacientSus;

    public Consult(){
        setBooked(false);
    }

    public Consult(String hour, Boolean booked, String name, String birth,
                   String phone, String sus){
        setBooked(booked);
        setHour(hour);
        setPacientName(name);
        setPacientBirth(birth);
        setPacientPhone(phone);
        setPacientSus(sus);
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getPacientName() {
        return pacientName;
    }

    public void setPacientName(String pacientName) {
        this.pacientName = pacientName;
    }

    public String getPacientBirth() {
        return pacientBirth;
    }

    public void setPacientBirth(String pacientBirth) {
        this.pacientBirth = pacientBirth;
    }

    public String getPacientPhone() {
        return pacientPhone;
    }

    public void setPacientPhone(String pacientPhone) {
        this.pacientPhone = pacientPhone;
    }

    public String getPacientSus() {
        return pacientSus;
    }

    public void setPacientSus(String pacientSus) {
        this.pacientSus = pacientSus;
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
}
