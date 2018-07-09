package br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.web;

import android.content.Context;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class WebTaskLogin extends WebTaskBase {

    private static String URL = "login";
    private String FIELD_CPF = "cpf";
    private String FIELD_PASSWORD = "password";

    private String cpf;
    private String password;

    public WebTaskLogin(Context context, String cpf, String password) {
        super(context, URL);
        this.cpf = cpf;
        this.password = password;
    }

    @Override
    String getRequestBody() {
        Map<String,Object> requestMap = new HashMap<>();
        requestMap.put(FIELD_CPF, cpf );
        requestMap.put(FIELD_PASSWORD, password );

        JSONObject json = new JSONObject(requestMap);
        return json.toString();

    }

    @Override
    void handleResponse(String response) {

        try {
            JSONObject userAsJSON = new JSONObject(response);
            String name = userAsJSON.getString("name");
            String job = userAsJSON.getString("job");
            String email = userAsJSON.getString("email");
            String date_of_birth = userAsJSON.getString("date_of_birth");
            EventBus.getDefault().post(name);
        } catch (JSONException e) {
            EventBus.getDefault().post(
                    new WebError(
                            "Resposta inválida do servidor", URL));
        }
    }

    @Override
    HttpMethod getMethod() {
        return HttpMethod.POST;
    }
}
