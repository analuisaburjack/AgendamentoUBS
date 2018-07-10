package br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.SQL.modelDAO;

import br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.models.User;

import java.util.List;


public interface UserDAO {
    boolean contains(User user);

    List<User> getUsers();
}
