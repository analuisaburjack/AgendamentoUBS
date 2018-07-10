package br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.auth;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.afollestad.materialdialogs.MaterialDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.BookingActivity;
import br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.R;
import br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.models.Consult;
import br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.models.User;
import br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.web.AsyncResponse;
import br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.web.WebTaskLogin;
import br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.web.WebError;

public class LoginActivity extends AppCompatActivity implements AsyncResponse {

    MaterialDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ImageView buttonClose = findViewById(R.id.button_close);

        buttonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setupLogin();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void setupLogin() {
        Button buttonLogin =
                findViewById(R.id.button_login);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryLogin();
            }
        });
    }

    private void tryLogin() {
        EditText editTextCpf = findViewById(R.id.input_cpf);
        EditText editTextPassword = findViewById(R.id.input_password);

        if(!"".equals(editTextCpf.getText().toString())){
            showLoading();
            sendCredentials(editTextCpf.getText().toString(),
                    editTextPassword.getText().toString());
        }else{
            editTextCpf.setError("Preencha o campo cpf");
        }

    }

    private void sendCredentials(String cpf, String pass) {
        WebTaskLogin asyncTask  = new WebTaskLogin(this,
                cpf, pass);
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
                .content("CPF ou senha inválido(s)")
                .cancelable(false)
                .show();
    }


    @Override
    public void processFinishList(List<Consult> output) {}

    @Override
    public void processFinishLogin(Integer o) {
        if(o == 200){
            hideLoading();
            Intent intent = new Intent(LoginActivity.this, BookingActivity.class);
            startActivity(intent);
        }else{
            hideLoading();

        }

    }

    @Override
    public void processFinishAdd(String output) {}
}
