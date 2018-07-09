package br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.web;

import java.util.List;

import br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.models.Consult;

public interface AsyncResponse {
    void processFinish(List<Consult> output);
}