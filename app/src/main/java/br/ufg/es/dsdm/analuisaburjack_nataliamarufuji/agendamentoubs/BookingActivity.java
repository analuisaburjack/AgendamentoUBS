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
import br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.web.AsyncResponse;
import br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.web.WebTaskAddConsult;
import br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.web.WebTaskLogin;

/**
 * Provides UI for the Detail page with Collapsing Toolbar.
 */

public class BookingActivity extends AppCompatActivity implements AsyncResponse {

    public BookingActivity(){}
    private String msg;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Set Collapsing Toolbar layout to the screen
        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        // Set title of Detail page
        collapsingToolbar.setTitle(getString(R.string.item_title));

        EditText dateEdit = (EditText) findViewById(R.id.booking_date);
        final String dateValue = dateEdit.getText().toString();

        EditText hourEdit = (EditText) findViewById(R.id.booking_hour);
        final String hourValue = hourEdit.getText().toString();

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
                WebTaskAddConsult asyncTask  = new WebTaskAddConsult(BookingActivity.this,
                        dateValue, hourValue, nameValue, birthValue,
                        phoneValue, susValue);
                asyncTask.delegate = BookingActivity.this;
                asyncTask.execute();

                Snackbar.make(v, getMsg(),
                        Snackbar.LENGTH_LONG).show();
            }
        });
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public void processFinishString(String o) {
        setMsg(o);
    }

    @Override
    public void processFinishList(List<Consult> output) {}

    @Override
    public void processFinishInteger(Integer output) {}
}
