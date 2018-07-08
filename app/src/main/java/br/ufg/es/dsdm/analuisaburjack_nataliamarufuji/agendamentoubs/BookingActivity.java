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
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.models.Consult;
import br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.models.Day;

/**
 * Provides UI for the Detail page with Collapsing Toolbar.
 */
public class BookingActivity extends AppCompatActivity {
    private Day day;

    public static final String EXTRA_POSITION = "position";

    public BookingActivity(){}

    public BookingActivity(Day day){
        this.day = day;
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

        final int postion = getIntent().getIntExtra(EXTRA_POSITION, 0);
        String hour = day.getConsults().get(postion).getHour();
        collapsingToolbar.setTitle(hour);

        EditText nameEdit = (EditText) findViewById(R.id.booking_pacient);
        final String nameValue = nameEdit.getText().toString();

        EditText birthEdit = (EditText) findViewById(R.id.booking_birth);
        final String birthValue = birthEdit.getText().toString();

        EditText phoneEdit = (EditText) findViewById(R.id.booking_phone);
        final String phoneValue = phoneEdit.getText().toString();

        EditText susEdit = (EditText) findViewById(R.id.booking_sus);
        final String susValue = susEdit.getText().toString();

        Button button = (Button) findViewById(R.id.book);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                day.getConsults().get(postion).setPacientName(nameValue);
                day.getConsults().get(postion).setPacientBirth(birthValue);
                day.getConsults().get(postion).setPacientPhone(phoneValue);
                day.getConsults().get(postion).setPacientSus(susValue);

                Snackbar.make(v, "Consulta agendada com sucesso!",
                        Snackbar.LENGTH_LONG).show();
            }
        });
    }

}
