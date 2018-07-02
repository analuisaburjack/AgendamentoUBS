package br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.Models;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class Consult {

    private String bookedPhrase;
    private String hour;
    
    private Boolean booked;

    private Pacient pacient = new Pacient();

    Consult(){
        setBooked(false);
    }

    Consult(String hour, Pacient pacient){
        setbookedPhrase(true);
        sethour(hour);
        setPacient(pacient);
    }

    public Boolean isBooked() {
        return booked;
    }

    public void setBooked(Boolean booked) {
        this.booked = booked;
    }

    public String getBookedPhrase() {
        return bookedPhrase;
    }

    public void setbookedPhrase(boolean booked) {
        if (booked) {
            this.bookedPhrase = "Paciente:";

        } else if (!booked) {
            this.bookedPhrase = "Horário Disponível";

        }
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
