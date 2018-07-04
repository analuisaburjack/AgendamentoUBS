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

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.List;

import br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.models.Consult;
import br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.models.ConsultList_Test;
import br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.web.WebTaskConsultList;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private RecyclerViewAdapter adapter;
    private DividerItemDecoration mDividerItemDecoration;
    private String Date;
    private ConsultList_Test test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Adding Toolbar to Main screen
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //DatePicker
        this.showDatePickerDialog();

        test = new ConsultList_Test();

        // Setting RecyclerView
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(MainActivity.this);
        adapter = new RecyclerViewAdapter(test.getList()/*requestList(MainActivity.this, getDate())*/);
        mDividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(mDividerItemDecoration);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    public String generateDateString(int year, int month, int day){
        StringBuffer strBuf = new StringBuffer();
        strBuf.append(day);
        strBuf.append("/");
        strBuf.append(month+1);
        strBuf.append("/");
        strBuf.append(year);

        return strBuf.toString();
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public List<Consult> requestList(Context context, String date){
        WebTaskConsultList webTaskList = new WebTaskConsultList(context, date);
        return webTaskList.getConsultList();
    }



    // Create and show a DatePickerDialog when click button.
    private void showDatePickerDialog()
    {
        // Get open DatePickerDialog button.
        final Button btn = (Button)findViewById(R.id.datePickerDialogButton);
        btn.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View view) {
                // Create a new OnDateSetListener instance. This listener will be invoked when user click ok button in DatePickerDialog.
                DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        btn.setText(generateDateString(year, month, dayOfMonth).toString());
                        setDate(generateDateString(year, month, dayOfMonth).toString());
                    }
                };

                // Get current year, month and day.
                Calendar now = Calendar.getInstance();
                int year = now.get(java.util.Calendar.YEAR);
                int month = now.get(java.util.Calendar.MONTH);
                int day = now.get(java.util.Calendar.DAY_OF_MONTH);

                setDate(generateDateString(year, month, day));

                // Create the new DatePickerDialog instance.
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, onDateSetListener, year, month, day);

                // Set dialog icon and title.
                //datePickerDialog.setIcon(R.drawable.if_snowman);
                //datePickerDialog.setTitle("Please select date.");

                // Popup the dialog.
                datePickerDialog.show();
            }
        });
    }
}

