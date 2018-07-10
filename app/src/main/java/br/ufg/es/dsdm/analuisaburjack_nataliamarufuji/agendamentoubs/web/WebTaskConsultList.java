package br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.web;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.R;
import br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.models.Consult;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class WebTaskConsultList extends AsyncTask<Void, Void, List<Consult>> {

    private static String BASE_URL = "http://private-11b6d8-sus1.apiary-mock.com/getBook";
    private static String DATE = "date";
    private static int TIMEOUT = 20;

    public AsyncResponse delegate = null;
    private Context context;
    private WebError error;
    private String responseString;
    private int responseHttpStatus;

    private String dateBody = "03/07/2018";
    private List<Consult> mConsults = new ArrayList<Consult>();

    public static MediaType JSON
            = MediaType.parse("application/json");

    public WebTaskConsultList(Context context) {
        this.context = context;
    }

    protected List<Consult> doInBackground(Void... voids) {

        if(!isOnline()){
            error = new WebError(context.getString(R.string.error_connection), getUrl());
            responseString = "";
            return null;
        }

        return doRegularCall();
    }

    private List<Consult> doRegularCall() {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, getRequestBody());

        client = client.newBuilder()
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .build();

        Request.Builder requestBuilder = new Request.Builder()
                .header("Content-Type" ,  "application/json")
                .url(getUrl());

        switch (getMethod()){
            case POST:
                requestBuilder.post(body);
                break;
            case PUT:
                requestBuilder.put(body);
                break;
            case PATCH:
                requestBuilder.patch(body);
                break;
            case DELETE:
                requestBuilder.delete(body);
                break;
            case GET:
                requestBuilder.get();
                break;
        }

        Request request = requestBuilder.build();
        try {
            Response response = client.newCall(request).execute();
            responseString =  response.body().string();
            responseHttpStatus = response.code();

            return handleResponse(responseString, responseHttpStatus);
        } catch (IOException e) {
            if(e.getClass() == SocketTimeoutException.class){
                error = new WebError("Servidor não responde. Tente mais tarde.", getUrl());
            }else{
                error = new WebError("Erro no servidor: "
                        + ":" + e.getMessage(), getUrl());
            }

            List<Consult> erro = new ArrayList<Consult>();
            return erro;
        }
    }

    protected void onPostExecute(List<Consult> consults) {
        super.onPostExecute(consults);
        if(error!= null){
            handleError();
        }else{
            try {
                JSONObject responseJSON = new JSONObject(responseString);
                String errorMessage = responseJSON.getString("error");
                EventBus.getDefault().post(new WebError(errorMessage, getUrl()));

            } catch (JSONException e) {
                delegate.processFinishList(consults);
            } catch (NullPointerException e) {
                handleResponse("", 401);
            }
        }
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public void handleError(){
        EventBus.getDefault().post(error);
    }


    public String getUrl(){
        return BASE_URL;
    }


    protected enum HttpMethod {
        GET,POST,PATCH,DELETE,PUT;
    }

    String getRequestBody() {
        Map<String,Object> requestMap = new HashMap<>();
        requestMap.put(DATE, dateBody);

        JSONObject json = new JSONObject(requestMap);
        return json.toString();

    }

    List<Consult> handleResponse(String response, int code) {
        try {
            JSONObject requestAsJSON = new JSONObject(response);

            if (code == 200){
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
                return getmConsults();
            }
        } catch (JSONException e) {
            EventBus.getDefault().post(
                    new WebError(
                            "Resposta inválida do servidor", BASE_URL));
        }
        return getmConsults();
    }

    HttpMethod getMethod() {
        return HttpMethod.GET;
    }

   public List<Consult> getmConsults() {
        return this.mConsults;
    }
}
