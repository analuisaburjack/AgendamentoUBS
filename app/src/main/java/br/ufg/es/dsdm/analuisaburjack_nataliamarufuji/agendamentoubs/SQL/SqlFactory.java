package br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.SQL;


import android.content.Context;
import br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.SQL.modelDAO.*;

public final class SqlFactory {

    private final Context context;
    private ConsultDAOImpl consultDaoImpl;
    private UserDAOImpl userDaoImpl;
    public SqlFactory(Context context) {
        this.context = context;
    }

    public ConsultDAOImpl getConsultDAO(){
        if (consultDaoImpl == null) {
            consultDaoImpl = new ConsultDAOImpl(context);
        }
        return consultDaoImpl;
    }

    public UserDAOImpl getClientDAO(){

        if (userDaoImpl == null) {
            userDaoImpl = new UserDAOImpl(context);
        }
        return userDaoImpl;
    }

    public void close(){
        consultDaoImpl.close();
        userDaoImpl.close();
    }
}
