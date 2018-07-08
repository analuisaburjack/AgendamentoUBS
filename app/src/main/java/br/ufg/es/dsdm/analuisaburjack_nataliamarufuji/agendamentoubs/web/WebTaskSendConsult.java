package br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.web;

import android.content.Context;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.models.Consult;

/*public class WebTaskSendConsult extends WebTaskBase {

    private static String URL = "getBook";
    private String FIELD_DATE = "date";
    private String FIELD_HOUR = "hour";
    private Boolean FIELD_BOOKED = true;
    private String FIELD_NAME = "name";
    private String FIELD_BIRTH = "birth";
    private String FIELD_PHONE = "phone";
    private String FIELD_SUS = "sus";


    private Consult consult;
    private String date;
    private List<Consult> consultList;

        public WebTaskSendConsult(Context context, Consult consult, String date) {
        super(context, URL);
        this.consult = consult;
        this.date = date;
    }

    public List<Consult> getConsultList() {
        return consultList;
    }

    @Override
    String getRequestBody() {
        Map<String,Object> requestMap = new HashMap<>();
        requestMap.put(FIELD_DATE, date );
        requestMap.put(FIELD_HOUR, consult.getHour() );
        requestMap.put(FIELD_BOOKED, date );
        requestMap.put(FIELD_DATE, date );
        requestMap.put(FIELD_DATE, date );
        requestMap.put(FIELD_DATE, date );
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

            for (int index = 0; index < dayAsJSON.length(); index++) {
                JSONObject consultAsJSON = dayAsJSON.getJSONObject(index);
                String hour = consultAsJSON.getString("hour");
                Boolean booked = consultAsJSON.getBoolean("booked");
                String pacientName = consultAsJSON.getString("pacientName");
                String pacientBirth = consultAsJSON.getString("pacientBirth");
                String pacientPhone = consultAsJSON.getString("pacientPhone");
                String pacientSus = consultAsJSON.getString("pacientSUs");

                Consult consult = new Consult(hour, booked, pacientName, pacientBirth,
                        pacientPhone, pacientSus);

                consultList.add(consult);
            }
        } catch (JSONException e) {
            EventBus.getDefault().post(
            new WebError(
            "Resposta inválida do servidor", URL));
            }
    }

    @Override
    HttpMethod getMethod() {
        return HttpMethod.PUT;
    }


}*/
