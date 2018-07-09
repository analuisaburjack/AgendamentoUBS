package br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.auth;

/*import android.content.Intent;
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

import br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.BookingActivity;
import br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.R;
import br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.web.WebTaskLogin;
import br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.web.WebError;

public class LoginActivity extends AppCompatActivity {

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

        setupButtonRememberPassword();
        setupLogin();
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    private void setupButtonRememberPassword() {
        Button buttonRememberPassword =
                findViewById(R.id.button_forgot_password);
        buttonRememberPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPassword = new Intent(getApplicationContext(),
                        RememberPassword.class);
                startActivity(intentPassword);
            }
        });
    }

    private void setupLogin() {
        Button buttonLogin =
                findViewById(R.id.button_login);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //tryLogin();
                Intent intent = new Intent(LoginActivity.this, BookingActivity.class);
                startActivity(intent);
            }
        });
    }

    private void tryLogin() {
        EditText editTextEmail = findViewById(R.id.input_cpf);
        EditText editTextPassword = findViewById(R.id.input_password);

        if(!"".equals(editTextEmail.getText().toString())){
            showLoading();
            sendCredentials(editTextEmail.getText().toString(),
                    editTextPassword.getText().toString());
        }else{
            editTextEmail.setError("Preencha o campo cpf");
        }

    }

    private void sendCredentials(String email, String pass) {
        WebTaskLogin taskLogin = new WebTaskLogin(this,
                email, pass);
        taskLogin.execute();
    }

    private void showLoading(){
        dialog = new MaterialDialog.Builder(this)
                .content(R.string.label_wait)
                .progress(true,0)
                .cancelable(false)
                .show();
    }

    @Subscribe
    public void onEvent(String response){
        hideLoading();
        Intent openUrlIntent = new Intent(Intent.ACTION_VIEW);
        openUrlIntent.setData(
                Uri.parse("http://www.freescreencleaner.com/"));
        startActivity(openUrlIntent);
    }

    @Subscribe
    public void onEvent(WebError error){
        hideLoading();
        Snackbar.make(findViewById(R.id.container),
                        error.getMessage(),
                        Snackbar.LENGTH_LONG).show();
    }

    private void hideLoading(){
        if(dialog != null && dialog.isShowing()){
            dialog.hide();
            dialog = null;
        }
    }
}*/
