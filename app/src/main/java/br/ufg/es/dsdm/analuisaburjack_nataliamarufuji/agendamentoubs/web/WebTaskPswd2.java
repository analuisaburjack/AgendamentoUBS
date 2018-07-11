package br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.web;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.R;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class WebTaskPswd2 extends AsyncTask<Void, Void, String> {

    private static String BASE_URL = "http://private-11b6d8-sus1.apiary-mock.com/newpswd";
    private String NEWPSWD1 = "pswd1";
    private String NEWPSWD2 = "pswd2";
    private static int TIMEOUT = 20;

    public AsyncResponse delegate = null;
    private Context context;
    private WebError error;
    private String responseString;
    private int responseHttpStatus;

    private String pswd1 = "12345679";
    private String pswd2 = "12345679";
    private String answer;

    public static MediaType JSON
            = MediaType.parse("application/json");

    public WebTaskPswd2(Context context, String pswd1, String pswd2) {
        this.context = context;
        this.pswd1 = pswd1;
        this.pswd2 = pswd2;
    }


    protected String doInBackground(Void... voids) {

        if(!isOnline()){
            error = new WebError(context.getString(R.string.error_connection), getUrl());
            responseString = "";
            return null;
        }

        return doRegularCall();
    }

    private String doRegularCall() {
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

            return handleResponse(responseString);
        } catch (IOException e) {
            if(e.getClass() == SocketTimeoutException.class){
                error = new WebError("Servidor não responde. Tente mais tarde.", getUrl());
            }else{
                error = new WebError("Erro no servidor: "
                        + ":" + e.getMessage(), getUrl());
            }

            return "Erro";
        }
    }

    protected void onPostExecute(String answer) {
        super.onPostExecute(answer);
        if(error!= null){
            handleError();
        }else{
            try {
                JSONObject responseJSON = new JSONObject(responseString);
                String errorMessage = responseJSON.getString("error");
                EventBus.getDefault().post(new WebError(errorMessage, getUrl()));

            } catch (JSONException e) {
                delegate.processFinishString(answer);
            } catch (NullPointerException e) {
                answer = "Erro";
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
        System.out.println("entrou no request body");
        Map<String,Object> requestMap = new HashMap<>();
        requestMap.put(NEWPSWD1, pswd1);
        requestMap.put(NEWPSWD2, pswd2);

        JSONObject json = new JSONObject(requestMap);
        return json.toString();

    }

    String handleResponse(String response) {
        try{
            JSONObject messageAsJSON = new JSONObject(response);
            String msg = messageAsJSON.getString("message");
            this.answer = msg;

            return this.answer;
        }catch (JSONException e) {
            return "Resposta inválida do servidor";
        }
    }

    HttpMethod getMethod() {
        return HttpMethod.PUT;
    }
}
