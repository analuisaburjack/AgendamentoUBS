package br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.models;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class Person {

    private String name;
    private String cpf;
    private String date_of_birth;

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}