package br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.auth;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.List;

import br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.BookingActivity;
import br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.R;
import br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.models.Consult;
import br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.web.AsyncResponse;
import br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.web.WebTaskLogin;
import br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.web.WebTaskPswd1;

public class RememberPswdActivity1 extends AppCompatActivity implements AsyncResponse {

    MaterialDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remember_password1);

        ImageView buttonClose = findViewById(R.id.button_close);

        buttonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setupAuth();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void setupAuth() {
        Button buttonLogin =
                (Button) findViewById(R.id.button_fwd);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryAuth();
            }
        });
    }

    private void tryAuth() {
        EditText editTextCpf = findViewById(R.id.input_cpf);
        EditText editTextDateOfBirth = findViewById(R.id.input_dateofbirth);

        if(!"".equals(editTextCpf.getText().toString())){
            showLoading();
            sendCredentials(editTextCpf.getText().toString(),
                    editTextDateOfBirth.getText().toString());
        }else{
            editTextCpf.setError("Preencha o campo cpf");
        }

    }

    private void sendCredentials(String cpf, String date) {
        WebTaskPswd1 asyncTask  = new WebTaskPswd1(this,
                cpf, date);
        asyncTask.delegate = this;
        asyncTask.execute();
    }

    private void showLoading(){
        dialog = new MaterialDialog.Builder(this)
                .content(R.string.label_wait)
                .progress(true,0)
                .cancelable(false)
                .show();
    }

    private void hideLoading(){
        if(dialog != null && dialog.isShowing()){
            dialog.hide();
            dialog = null;
        }
    }

    private void showError(){
        dialog = new MaterialDialog.Builder(this)
                .content("CPF ou data de nascimento inválido(s)")
                .cancelable(false)
                .show();
    }


    @Override
    public void processFinishList(List<Consult> output) {}

    @Override
    public void processFinishInteger(Integer o) {
        if(o == 200){
            hideLoading();
            Intent intent = new Intent(RememberPswdActivity1.this,
                    RememberPswdActivity2.class);
            startActivity(intent);
        }else{
            hideLoading();
            showError();
        }
    }

    @Override
    public void processFinishString(String output) {}
}

