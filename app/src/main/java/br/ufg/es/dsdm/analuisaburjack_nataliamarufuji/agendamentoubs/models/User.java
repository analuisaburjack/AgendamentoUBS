package br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.models;

/**
 * Created by analu on 25/06/2018.
 */

public class User {
    private String name;
    private String date_of_birth;
    private String job;
    private String email;

    public User(){}

    public User (String name, String date_of_birth, String job, String email){
        setName(name);
        setDate_of_birth(date_of_birth);
        setJob(job);
        setEmail(email);
    }

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
