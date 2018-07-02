package br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.Models.Consult;
import br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.Models.Day;

public class ListOfConsults_GsonJsonParsing extends AppCompatActivity {

    private String TAG = ListOfConsults_GsonJsonParsing.class.getSimpleName();

    private RecyclerView rv;
    private DividerItemDecoration mDividerItemDecoration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consults_list);
        Log.d(TAG, "content view is set in onCreate method of activity");

        rv = (RecyclerView) findViewById(R.id.list_of_consults);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(mLayoutManager);

        mDividerItemDecoration = new DividerItemDecoration(rv.getContext(),
                mLayoutManager.getOrientation());
        rv.addItemDecoration(mDividerItemDecoration);

        new ListOfConsults_GsonJsonParsing.GetConsultsAync().execute(this);
    }

    private class GetConsultsAync extends AsyncTask<Context, Void, ArrayList<Consult>> {

        private String TAG = GetConsultsAync.class.getSimpleName();
        private Context contx;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ArrayList<Consult> doInBackground(Context... params) {
            contx = params[0];
            Log.e(TAG, "start aynctask to get consults");
            return getConsultsFromServer();
        }

        @Override
        protected void onPostExecute(ArrayList<Consult> result) {
            super.onPostExecute(result);

            if(result != null){
                Log.e(TAG, "populate UI recycler view with gson converted data");

                ConsultsRecyclerViewAdapter ConsultsRecyclerViewAdapter = new ConsultsRecyclerViewAdapter(result,contx);
                rv.setAdapter(ConsultsRecyclerViewAdapter);
            }
        }
    }

    public ArrayList<Consult> getConsultsFromServer() {
        String serviceUrl = "https://agendamentoubs-25ed.restdb.io/rest/consults";
        URL url = null;
        try {
            Log.d(TAG, "call rest service to get json response");
            url = new URL(serviceUrl);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.setConnectTimeout(4000);
            connection.setReadTimeout(4000);
            connection.connect();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            //pass buffered reader to convert json to java object using gson
            return convertJsonToObject(bufferedReader);

        } catch (Exception e) {
            Log.e(TAG, "error in getting and parsing response");
        }
        return null;
    }

    public ArrayList<Consult> convertJsonToObject(BufferedReader bufferedReader){
        //instantiate Gson
        final Gson gson = new Gson();

        //pass root element type to fromJson method along with input stream
        Day day= gson.fromJson(bufferedReader, Day.class);

        ArrayList<Consult> cpnlst = day.getConsults();
        Log.e(TAG, "number of coupons from json response after gson parsing"+cpnlst.size());

        return cpnlst;
    }
}

