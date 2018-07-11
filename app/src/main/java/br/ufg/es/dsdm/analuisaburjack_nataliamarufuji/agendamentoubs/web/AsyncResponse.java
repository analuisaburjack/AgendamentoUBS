package br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.web;

import java.util.List;

import br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.models.Consult;
import br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.models.User;

public interface AsyncResponse {
    void processFinishList(List<Consult> output);
    void processFinishInteger(Integer output);
    void processFinishString(String output);
}