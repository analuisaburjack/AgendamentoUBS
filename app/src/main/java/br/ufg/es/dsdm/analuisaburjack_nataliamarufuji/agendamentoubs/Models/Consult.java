package br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.Models;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class Consult {

    private boolean booked;
    private String time;

    private Pacient pacient = new Pacient();

    Consult(){
        setBooked(false);
    }

    Consult(String time, Pacient pacient){
        setBooked(true);
        setTime(time);
        setPacient(pacient);
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Pacient getPacient() {
        return pacient;
    }

    public void setPacient(Pacient pacient) {
        this.pacient = pacient;
    }
}
