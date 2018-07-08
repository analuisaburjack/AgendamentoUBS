package br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.models;

import java.util.ArrayList;
import java.util.List;

public class ConsultList_Test {
    private List<Consult> consults = new ArrayList<Consult>();
    int length = 9;



    public void setListData(){
        for (int i = 0; i < length; i++){
            Consult consult = new Consult("21/07/2018", "09h", true, "Ana",
                    "23/04/1998", "62982521113", "70215896345");
            this.consults.add(consult);
        }
    }

    public List<Consult> getList(){
        setListData();
        return consults;
    }
}
