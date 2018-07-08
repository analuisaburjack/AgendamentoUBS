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
import br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.models.Day;

/**
 * Provides UI for the Detail page with Collapsing Toolbar.
 */
public class DetailActivity extends AppCompatActivity {

    private List<Consult> consult;

    public static final String EXTRA_POSITION = "position";
    public static final String EXTRA_HOUR = "hour";
    public static final String EXTRA_NAME = "name";
    public static final String EXTRA_BIRTH = "birth";
    public static final String EXTRA_PHONE = "phone";
    public static final String EXTRA_SUS = "sus";

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
        // Set title of Detail page
        // collapsingToolbar.setTitle(getString(R.string.item_title));

        int postion = getIntent().getIntExtra(EXTRA_POSITION, 0);
        String hour = getIntent().getStringExtra(EXTRA_HOUR);
        String name = getIntent().getStringExtra(EXTRA_NAME);
        String birth = getIntent().getStringExtra(EXTRA_BIRTH);
        String phone = getIntent().getStringExtra(EXTRA_PHONE);
        String sus = getIntent().getStringExtra(EXTRA_SUS);

        collapsingToolbar.setTitle(hour);

        TextView pacientDetail = (TextView) findViewById(R.id.details_pacient);
        pacientDetail.setText(name);

        TextView birthDetail = (TextView) findViewById(R.id.details_birth);
        birthDetail.setText(birth);

        TextView phoneDetail = (TextView) findViewById(R.id.details_phone);
        phoneDetail.setText(phone);

        TextView susDetail = (TextView) findViewById(R.id.details_sus);
        susDetail.setText(sus);
    }
}
