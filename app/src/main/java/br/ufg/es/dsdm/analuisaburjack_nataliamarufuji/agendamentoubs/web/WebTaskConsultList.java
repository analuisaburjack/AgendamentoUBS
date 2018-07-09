package br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.web;

import android.content.Context;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.SQL.AppDataBase;
import br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.SQL.ConsultDAO;
import br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.models.Consult;

public class WebTaskConsultList extends WebTaskBase {

    private static String URL = "getBook";
    private static String DATE = "date";

    private String dateBody;
    private List<Consult> mConsults;

    public WebTaskConsultList(Context context, String date) {
        super(context, URL);
        this.dateBody = date;
    }

    @Override
    String getRequestBody() {
        Map<String,Object> requestMap = new HashMap<>();
        requestMap.put(DATE, dateBody );

        JSONObject json = new JSONObject(requestMap);
        return json.toString();

    }

    @Override
    void handleResponse(String response) {

        try {

            JSONObject requestAsJSON = new JSONObject(response);
            String status = requestAsJSON.getString("status");
            JSONArray dayAsJSON = requestAsJSON.getJSONArray("consults");

            for (int index = 0; index < dayAsJSON.length(); index++) {
                JSONObject consultAsJSON = dayAsJSON.getJSONObject(index);
                String date = consultAsJSON.getString("date");
                String hour = consultAsJSON.getString("hour");
                Boolean booked = consultAsJSON.getBoolean("booked");
                String pacientName = consultAsJSON.getString("pacientName");
                String pacientBirth = consultAsJSON.getString("pacientBirth");
                String pacientPhone = consultAsJSON.getString("pacientPhone");
                String pacientSus = consultAsJSON.getString("pacientSUs");

                Consult consult = new Consult(date, hour, booked, pacientName, pacientBirth,
                        pacientPhone, pacientSus);

                this.mConsults.add(consult);
            }
        } catch (JSONException e) {
            EventBus.getDefault().post(
            new WebError(
            "Resposta inválida do servidor", URL));
            }
    }

    @Override
    HttpMethod getMethod() {
        return HttpMethod.GET;
    }

    public List<Consult> getmConsults() {
        return mConsults;
    }
}
