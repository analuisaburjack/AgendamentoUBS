package br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.models;

import java.util.ArrayList;
import java.util.List;

import br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.SQL.AppDataBase;
import br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.SQL.ConsultDAO;

public class ConsultList_Test {
    private ConsultDAO consultDB;
    int length = 9;

    public ConsultList_Test(AppDataBase db){
        this.consultDB = db.consultDao();
    }


    public void setListData(){
        for (int i = 0; i < length; i++){
            String str = i + "h";
            Consult consult = new Consult("21/07/2018", str, true, "Ana",
                    "23/04/1998", "62982521113", "70215896345");
            consultDB.insertConsult(consult);
        }
    }

    public List<Consult> getList(){
        setListData();
        return consultDB.getAll("21/07/2018");
    }
}
