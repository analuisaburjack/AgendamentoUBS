package br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.SQL.AppDataBase;
import br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.models.Consult;

/**
 * Provides UI for the Detail page with Collapsing Toolbar.
 */
public class DetailActivity extends AppCompatActivity {

    private List<Consult> consults;

    public static final String EXTRA_POSITION = "position";

    public DetailActivity(){}



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Set Collapsing Toolbar layout to the screen
        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        int postion = getIntent().getIntExtra(EXTRA_POSITION, 0);
        List<Consult> list = (List<Consult>) getIntent().getSerializableExtra("LIST");

        Consult mConsult = list.get(postion);

        collapsingToolbar.setTitle(mConsult.getHour());

        TextView pacientDetail = (TextView) findViewById(R.id.details_pacient);
        pacientDetail.setText(mConsult.getPacientName());

        TextView birthDetail = (TextView) findViewById(R.id.details_birth);
        birthDetail.setText(mConsult.getPacientBirth());

        TextView phoneDetail = (TextView) findViewById(R.id.details_phone);
        phoneDetail.setText(mConsult.getPacientPhone());

        TextView susDetail = (TextView) findViewById(R.id.details_sus);
        susDetail.setText(mConsult.getPacientSus());
    }
}
