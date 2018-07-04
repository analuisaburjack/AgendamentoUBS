package br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.models;

/**
 * Created by analu on 25/06/2018.
 */

public class User {
    private String job;
    private String email;
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

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
