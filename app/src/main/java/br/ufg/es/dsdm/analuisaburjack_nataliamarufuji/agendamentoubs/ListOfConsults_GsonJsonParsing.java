package br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class ListOfConsults_GsonJsonParsing extends AppCompatActivity {

    /*private String TAG = ListOfConsults_GsonJsonParsing.class.getSimpleName();

    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gson);
        Log.d(TAG, "content view is set in onCreate method of activity");

        rv = (RecyclerView) findViewById(R.id.coupons_rv);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llm);

        LinearDividerItemDecoration lddecoration = new LinearDividerItemDecoration(this, Color.BLUE, 10);
        rv.addItemDecoration(lddecoration);

        new ListOfConsults_GsonJsonParsing.GetCouponsAync().execute(this);
    }

    private class GetCouponsAync extends AsyncTask<Context, Void, List<Coupon>> {

        private String TAG = GetCouponsAync.class.getSimpleName();
        private Context contx;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<Coupon> doInBackground(Context... params) {
            contx = params[0];
            Log.e(TAG, "start aynctask to get coupons");
            return getCouponsFromServer();
        }

        @Override
        protected void onPostExecute(List<Coupon> result) {
            super.onPostExecute(result);

            if(result != null){
                Log.e(TAG, "populate UI recycler view with gson converted data");

                CouponsRecyclerViewAdapter couponsRecyclerViewAdapter = new CouponsRecyclerViewAdapter(result,contx);
                rv.setAdapter(couponsRecyclerViewAdapter);
            }
        }
    }

    public List<Coupon> getCouponsFromServer() {
        String serviceUrl = "http://www.zoftino.com/api/coupons";
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

    public List<Coupon> convertJsonToObject(BufferedReader bufferedReader){
        //instantiate Gson
        final Gson gson = new Gson();

        //pass root element type to fromJson method along with input stream
        CouponsWrapper couponsWrapper= gson.fromJson(bufferedReader, CouponsWrapper.class);

        List<Coupon> cpnlst = couponsWrapper.getCouponList();
        Log.e(TAG, "number of coupons from json response after gson parsing"+cpnlst.size());

        return cpnlst;
    }
}*/
}
