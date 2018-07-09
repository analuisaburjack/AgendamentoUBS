/*package br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.web;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.R;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public abstract class WebTaskBase extends AsyncTask<Void, Void, Void> {

    private static int TIMEOUT = 20;
    private static String BASE_URL = "http://private-11b6d8-sus1.apiary-mock.com/";

    public AsyncResponse delegate = null;
    private String serviceURL;
    private Context context;
    private WebError error;
    private String responseString;
    private int responseHttpStatus;

    public static MediaType JSON
            = MediaType.parse("application/json");

    public WebTaskBase(Context context, String serviceURL) {
        this.context = context;
        this.serviceURL = serviceURL;
    }

    @Override
    protected <T> T doInBackground(Void... voids) {

        if(!isOnline()){
            error = new WebError(context.getString(R.string.error_connection), getUrl());
            responseString = "";
            return null;
        }

        doRegularCall();

        return null;
    }

    private void doRegularCall() {
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
            //handleResponse(responseString, responseHttpStatus);
        } catch (IOException e) {
            if(e.getClass() == SocketTimeoutException.class){
                error = new WebError("Servidor não responde. Tente mais tarde.", getUrl());
            }else{
                error = new WebError("Erro no servidor: " + serviceURL
                        + ":" + e.getMessage(), getUrl());
            }

        }
    }

    protected void onPostExecute(Void avoid) {
        super.onPostExecute(avoid);
        if(error!= null){
           handleError();
        }else{
            try {
                JSONObject responseJSON = new JSONObject(responseString);
                String errorMessage = responseJSON.getString("error");
                EventBus.getDefault().post(new WebError(errorMessage, getUrl()));

            } catch (JSONException e) {
                handleResponse(responseString, responseHttpStatus);
                delegate.processFinish(Collections.singletonList(avoid));
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


    abstract String getRequestBody();

    abstract void handleResponse(String response, int code);

    abstract HttpMethod getMethod();

    public Context getContext() {
        return context;
    }

    public int getResponseHttpStatus() {
        return responseHttpStatus;
    }

    public Error getError() {
        return error;
    }

    public String getUrl(){
        return BASE_URL + serviceURL;
    }


    protected enum HttpMethod {
        GET,POST,PATCH,DELETE,PUT;
    }
}*/

