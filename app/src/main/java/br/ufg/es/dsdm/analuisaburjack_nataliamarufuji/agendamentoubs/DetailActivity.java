/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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

import br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.models.Consult;

/**
 * Provides UI for the Detail page with Collapsing Toolbar.
 */
public class DetailActivity extends AppCompatActivity {

    private ArrayList<Consult> mConsults;

    public static final String EXTRA_POSITION = "position";

    public DetailActivity(){}

    public DetailActivity(ArrayList<Consult> consults){
        this.mConsults = consults;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Set Collapsing Toolbar layout to the screen
        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        // Set title of Detail page
        // collapsingToolbar.setTitle(getString(R.string.item_title));

        int postion = getIntent().getIntExtra(EXTRA_POSITION, 0);
        String hour = mConsults.get(postion).getHour();
        collapsingToolbar.setTitle(hour);

        String pacientName = mConsults.get(postion).getPacientName();
        TextView pacientDetail = (TextView) findViewById(R.id.details_pacient);
        pacientDetail.setText(pacientName);

        String pacientBirth = mConsults.get(postion).getPacientBirth();
        TextView birthDetail = (TextView) findViewById(R.id.details_birth);
        birthDetail.setText(pacientBirth);

        String pacientPhone = mConsults.get(postion).getPacientPhone();
        TextView phoneDetail = (TextView) findViewById(R.id.details_phone);
        phoneDetail.setText(pacientPhone);

        String pacientSUS = mConsults.get(postion).getPacientName();
        TextView susDetail = (TextView) findViewById(R.id.details_sus);
        susDetail.setText(pacientSUS);
    }
}
