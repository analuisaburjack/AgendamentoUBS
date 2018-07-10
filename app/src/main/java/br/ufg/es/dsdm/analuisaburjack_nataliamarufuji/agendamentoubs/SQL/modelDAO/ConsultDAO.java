package br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.SQL.modelDAO;

import br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.models.Consult;

import java.util.List;


public interface ConsultDAO {

   /* long AddConsult(Consult client);

    int updateConsult(Consult client);

    //int deleteConsult(Consult client);

    List<Consult> getConsults();

    int count();*/
    boolean contains(Consult consult);

    long addConsult(Consult consult);

    List<Consult> getConsults();
}
