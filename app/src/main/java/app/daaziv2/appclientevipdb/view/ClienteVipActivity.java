package app.daaziv2.appclientevipdb.view;


import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import app.daaziv2.appclientevipdb.R;
import app.daaziv2.appclientevipdb.api.AppUtil;
import app.daaziv2.appclientevipdb.model.Cliente;

public class ClienteVipActivity extends AppCompatActivity {

    // Declarar Objetos
    Cliente novoVip;
    private SharedPreferences preferences;

    // 1º passo apartir do layout
    EditText editPrimeiroNome, editSobreNome;
    CheckBox ckPessofisica;
    Button btnSalvarContinuar, btnCancelar;

    boolean isFormularioOk, isPessoaFisica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_vip);

        initFormulario();


        btnSalvarContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isFormularioOk = validarFormulario()) {

                    novoVip.setPrimeiroNome(editPrimeiroNome.getText().toString());
                    novoVip.setSobreNome(editSobreNome.getText().toString());
                    novoVip.setPessoaFisica(isPessoaFisica);

                    salvarSharedPreferences();

                    // Tela de Cadastro do CPF

                    Intent intent = new Intent(ClienteVipActivity.this, ClientePessoaFisicaActivity.class);
                    startActivity(intent);

                }
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(ClienteVipActivity.this)
                        .setIcon(R.mipmap.ic_launcher_round)
                        .setTitle("Confirme o Cancelamento")
                        .setMessage("Deseja realmente Cancelar ?")
                        .setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(ClienteVipActivity.this, "Continue seu Cadastro...",
                                        Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(ClienteVipActivity.this, "Cancelado com sucesso...", Toast.LENGTH_SHORT).show();
                                dialog.dismiss(); // Fecha o diálogo
                            }
                        })
                        .create().show();


            }
        });
    }

    private void initFormulario() {

        editPrimeiroNome = findViewById(R.id.editPrimeiroNome);
        editSobreNome = findViewById(R.id.editSobreNome);
        ckPessofisica = findViewById(R.id.ckPessofisica);
        btnSalvarContinuar = findViewById(R.id.btnSalvarContinuar);
        btnCancelar = findViewById(R.id.btnCancelar);

        isFormularioOk = false;

        novoVip = new Cliente();

        restaurarSharedPreferences();

    }

    public boolean validarFormulario() {


        boolean isFormularioOk = true;

        if (TextUtils.isEmpty(editPrimeiroNome.getText().toString())) {
            editPrimeiroNome.setError("*");
            editPrimeiroNome.requestFocus();
            isFormularioOk = false;
        }

        if (TextUtils.isEmpty(editSobreNome.getText().toString())) {
            editSobreNome.setError("*");
            editSobreNome.requestFocus();
            isFormularioOk = false;
        }

        return isFormularioOk;

    }

    public void pessoaFisica(View view) {

        isPessoaFisica = ckPessofisica.isChecked();

    }

    private void salvarSharedPreferences() {

        preferences = getSharedPreferences(AppUtil.PRE_APP, MODE_PRIVATE);
        SharedPreferences.Editor dados = preferences.edit();

        // Adicionando dados no SharedPreferences

        dados.putString("primeiroNome", novoVip.getPrimeiroNome());
        dados.putString("sobreNome", novoVip.getSobreNome());
        dados.putBoolean("pessoaFisica", novoVip.isPessoaFisica());
        dados.apply();

    }

    private void restaurarSharedPreferences() {

        preferences = getSharedPreferences(AppUtil.PRE_APP, MODE_PRIVATE);

    }
}