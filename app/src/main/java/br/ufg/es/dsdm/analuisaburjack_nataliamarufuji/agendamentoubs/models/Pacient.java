package br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.models;

public class Pacient extends Person {

    public Pacient(String name){
        setName(name);
    }

    public Pacient(String name, String phone, String sus, String cpf, String birth){
        setName(name);
        setMain_phone(phone);
        setSus_number(sus);
        setCpf(cpf);
        setDate_of_birth(birth);
    }

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
