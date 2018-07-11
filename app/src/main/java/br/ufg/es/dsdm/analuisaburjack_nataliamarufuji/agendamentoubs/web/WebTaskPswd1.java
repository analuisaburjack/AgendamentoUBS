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

public class WebTaskPswd1 extends AsyncTask<Void, Void, Integer> {

    private static String BASE_URL = "http://private-b45e5-sus1.apiary-mock.com//newpswd";
    private String CPF = "cpf";
    private String DATE_OF_BIRTH = "birth";
    private static int TIMEOUT = 20;

    public AsyncResponse delegate = null;
    private Context context;
    private WebError error;
    private String responseString;
    private int responseHttpStatus;

    private String cpf = "05686632170";
    private String birth = "23/04/1998";
    private Integer code;

    public static MediaType JSON
            = MediaType.parse("application/json");

    public WebTaskPswd1(Context context, String cpf, String birth) {
        this.context = context;
        this.cpf = cpf;
        this.birth = birth;
    }


    protected Integer doInBackground(Void... voids) {

        if(!isOnline()){
            error = new WebError(context.getString(R.string.error_connection), getUrl());
            responseString = "";
            return null;
        }

        return doRegularCall();
    }

    private Integer doRegularCall() {
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

            return responseHttpStatus;
        } catch (IOException e) {
            if(e.getClass() == SocketTimeoutException.class){
                error = new WebError("Servidor não responde. Tente mais tarde.", getUrl());
            }else{
                error = new WebError("Erro no servidor: "
                        + ":" + e.getMessage(), getUrl());
            }

            return 401;
        }
    }

    protected void onPostExecute(Integer code) {
        super.onPostExecute(code);
        if(error!= null){
            handleError();
        }else{
            try {
                JSONObject responseJSON = new JSONObject(responseString);
                String errorMessage = responseJSON.getString("error");
                EventBus.getDefault().post(new WebError(errorMessage, getUrl()));

            } catch (JSONException e) {
                delegate.processFinishInteger(code);
            } catch (NullPointerException e) {
                code = 401;
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
        requestMap.put(CPF, cpf);
        requestMap.put(DATE_OF_BIRTH, birth);

        JSONObject json = new JSONObject(requestMap);
        return json.toString();

    }

    HttpMethod getMethod() {
        return HttpMethod.GET;
    }
}
