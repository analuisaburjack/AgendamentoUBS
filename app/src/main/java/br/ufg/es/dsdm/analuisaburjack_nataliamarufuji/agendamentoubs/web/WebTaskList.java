package br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.web;

import android.content.Context;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.models.Consult;
import br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.models.Day;
import br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.models.Pacient;

public class WebTaskList extends WebTaskBase{
    private static String URL = "getBook";
    private String FIELD_DATE;

    private String date;
    
    private Day day;

    public String getFIELD_DATE() {
        return FIELD_DATE;
    }

    public void setFIELD_DATE(Day day) {
        this.FIELD_DATE = day.getDate();
    }

    public WebTaskList(Context context, Day day) {
        super(context, URL);
        this.day = day;
        setFIELD_DATE(day);
    }

    @Override
    String getRequestBody() {
        Map<String,Object> requestMap = new HashMap<>();
        requestMap.put(FIELD_DATE, date );

        JSONObject json = new JSONObject(requestMap);
        return json.toString();

    }

    @Override
    void handleResponse(String response) {

        try {
            JSONObject requestAsJSON = new JSONObject(response);
            String status = requestAsJSON.getString("status");
            JSONArray dayAsJSON = requestAsJSON.getJSONArray("consults");


            //List of day's consults
            List<Consult> dayConsults = new ArrayList<Consult>();

            for(int index = 0; index < dayAsJSON.length(); index++){
                JSONObject consultAsJSON = dayAsJSON.getJSONObject(index);

                String hourAsJSON = consultAsJSON.getString("hour");
                Boolean bookedAsJSON = consultAsJSON.getBoolean("booked");
                JSONObject pacientAsJSON = consultAsJSON.getJSONObject("pacient");
                String pacientNameAsJSON = pacientAsJSON.getString("name");

                Pacient pacient = new Pacient(pacientNameAsJSON);
                Consult consult = new Consult(bookedAsJSON, hourAsJSON, pacient);

                dayConsults.add(consult);
            }

            day.setConsults(dayConsults);
        } catch (JSONException e) {
            EventBus.getDefault().post(
                    new WebError(
                            "Resposta inválida do servidor", URL));
        }


        try {
            JSONObject nameAsJSON = new JSONObject(response);
            String name = nameAsJSON.getString("name");
            EventBus.getDefault().post(name);
        } catch (JSONException e) {
            EventBus.getDefault().post(
                    new WebError(
                            "Resposta inválida do servidor", URL));
        }
    }

    @Override
    WebTaskBase.HttpMethod getMethod() {
        return WebTaskBase.HttpMethod.PUT;
    }
}
