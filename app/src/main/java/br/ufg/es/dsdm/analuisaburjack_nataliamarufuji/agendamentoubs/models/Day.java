package br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.models;

import local.org.apache.http.HttpResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;


import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;



public class Day {

    public Day(String date){
        // Only one time
        Unirest.setObjectMapper(new ObjectMapper()  {
            private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper
                    = new com.fasterxml.jackson.databind.ObjectMapper();

            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    return jacksonObjectMapper.readValue(value, valueType);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            public String writeValue(Object value) {
                try {
                    return jacksonObjectMapper.writeValueAsString(value);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        setDate(date);
    }

    private String date;

    public List<String> listaTeste;

    private List<Consult> consults= new ArrayList<>();

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Consult> getConsults() {return this.consults; }

    public void setConsults(List<Consult> consults) {
        this.consults = consults;
    }

    public void teste(){
        try{
           /* HttpResponse<Consult> response = Unirest.get("https://agendamentoubs-25ed.restdb.io/rest/days")
                    .header("x-apikey", "6f48329f920b28d735d97ffe92efcc6e0b84f")
                    .header("cache-control", "no-cache")
                    .asObject(Consult.class);*/
        }catch(Exception ex){

        }
    }
}
