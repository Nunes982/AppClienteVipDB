package app.daaziv2.appclientevipdb.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import app.daaziv2.appclientevipdb.R;

public class RecuperarSenhaActivity extends AppCompatActivity {

    EditText editEmailRecuperarSenha;

    Button btnRecuperarSenha, btnVoltar;

    private SharedPreferences preferences;
    boolean isFormularioOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_senha);

        initFormulario();

        btnRecuperarSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isFormularioOk = validarFormulario()){

                    Toast.makeText(getApplicationContext(), "Sua senha foi enviada para o e-mail informado...",
                            Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(RecuperarSenhaActivity.this, LoginActivity.class);
                    startActivity(intent);

                }

            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(RecuperarSenhaActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void initFormulario() {

        editEmailRecuperarSenha = findViewById(R.id.editEmailRecuperarSenha);
        btnRecuperarSenha =findViewById(R.id.btnRecuperarSenha);
        btnVoltar =findViewById(R.id.btnVoltar);

        isFormularioOk = false;

    }

    public boolean validarFormulario(){

        boolean retorno = true;

        if (TextUtils.isEmpty(editEmailRecuperarSenha.getText().toString())){
            editEmailRecuperarSenha.setError("*");
            editEmailRecuperarSenha.requestFocus();
            retorno = false;
        }

        return retorno;

    }

}