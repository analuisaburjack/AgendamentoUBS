package br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.models;

import java.util.ArrayList;
import java.util.List;

import br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.SQL.AppDataBase;
import br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.SQL.ConsultDAO;

public class ConsultList_Test {
    private List<Consult> consultList = new ArrayList<Consult>();
    private Consult consult;
    int length = 9;


    public void setListData(){
        for (int i = 0; i < length; i++){

            switch (i) {
                case 0:
                case 3:
                case 4:
                case 5:
                    consult = new Consult("03/07/2018", "08h", false,
                            "", "", "", "");
                    consultList.add(consult);
                    break;
                case 1:
                case 2:
                case 6:
                case 7:
                case 8:
                    consult = new Consult("03/07/2018", "09h", true,
                            "Lúcia Martins de Oliveira", "19/05/1987",
                            "19/05/1987", "702709651256960");
                    consultList.add(consult);
                    break;
            }

        }
    }

    public List<Consult> getList(){
        setListData();
        return consultList;
    }
}
