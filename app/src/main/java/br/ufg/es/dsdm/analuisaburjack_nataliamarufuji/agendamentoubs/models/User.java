package br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.models;

/**
 * Created by analu on 25/06/2018.
 */

public class User extends Person{
    private String job;
    private String email;

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
