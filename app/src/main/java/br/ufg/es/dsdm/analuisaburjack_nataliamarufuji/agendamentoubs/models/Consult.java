package br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "consults")
public class Consult implements Serializable{
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "consult_dateHour")
    private String dateHour;

    @ColumnInfo(name = "consult_hour")
    private String hour;

    @ColumnInfo(name = "consult_date")
    private String date;

    @ColumnInfo(name = "isBooked")
    private Boolean booked;

    @ColumnInfo(name = "pacient_name")
    private String pacientName;

    @ColumnInfo(name = "pacient_birth")
    private String pacientBirth;

    @ColumnInfo(name = "pacient_phone")
    private String pacientPhone;

    @ColumnInfo(name = "pacient_sus")
    private String pacientSus;

    public Consult(){
        setBooked(false);
    }

    public Consult(String date, String hour, Boolean booked, String name, String birth,
                   String phone, String sus){
        StringBuilder str = new StringBuilder();
        str.append(date)
                .append(";")
                .append(hour);

        setDateHour(str.toString());
        setBooked(booked);
        setDate(date);
        setHour(hour);
        setPacientName(name);
        setPacientBirth(birth);
        setPacientPhone(phone);
        setPacientSus(sus);
    }

    public String getDateHour() {
        return dateHour;
    }

    public void setDateHour(String dateHour) {
        this.dateHour = dateHour;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Boolean getBooked() {
        return booked;
    }

    public void setBooked(Boolean booked) {
        this.booked = booked;
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
}
