package app.daaziv2.appclientevipdb.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import app.daaziv2.appclientevipdb.R;
import app.daaziv2.appclientevipdb.api.AppUtil;
import app.daaziv2.appclientevipdb.model.Cliente;
import app.daaziv2.appclientevipdb.model.ClientePF;

public class ClientePessoaFisicaActivity extends AppCompatActivity {

    // Declarar Objetos
    Cliente novoVip;
    ClientePF novoClientePF;

    private SharedPreferences preferences;

    EditText editCPF, editNomeCompleto;
    Button btnSalvarContinuar, btnVoltar, btnCancelar;

    boolean isFormularioOk, isPessoaFisica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_pessoa_fisica);

        initFormulario();

        btnSalvarContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isFormularioOk = validarFormulario()) {

                    novoClientePF.setCpf(editCPF.getText().toString());
                    novoClientePF.setNomeCompleto(editNomeCompleto.getText().toString());

                    salvarSharedPreferences();

                    Intent intent;

                    if (isPessoaFisica)

                        intent = new Intent(ClientePessoaFisicaActivity.this, CredencialDeAcessoActivity.class);

                    else

                        intent = new Intent(ClientePessoaFisicaActivity.this, ClientePessoaJuridicaActivity.class);

                    startActivity(intent);

                }
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ClientePessoaFisicaActivity.this, ClienteVipActivity.class);
                startActivity(intent);
                finish();

            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(ClientePessoaFisicaActivity.this)
                        .setIcon(R.mipmap.ic_launcher_round)
                        .setTitle("Confirme o Cancelamento")
                        .setMessage("Deseja realmente Cancelar ?")
                        .setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(ClientePessoaFisicaActivity.this, "Continue seu Cadastro...",
                                        Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(ClientePessoaFisicaActivity.this, "Cancelado com sucesso...", Toast.LENGTH_SHORT).show();
                                dialog.dismiss(); // Fecha o diálogo
                            }
                        })
                        .create().show();

            }
        });
    }

    private void initFormulario() {

        editCPF = findViewById(R.id.editCPF);
        editNomeCompleto = findViewById(R.id.editNomeCompleto);
        btnSalvarContinuar = findViewById(R.id.btnSalvarContinuarPF);
        btnVoltar = findViewById(R.id.btnVoltar);
        btnCancelar = findViewById(R.id.btnCancelar);

        isFormularioOk = false;

        novoClientePF = new ClientePF();
        novoVip = new Cliente();

        restaurarSharedPreferences();

    }

    public boolean validarFormulario() {

        boolean retorno = true;

        if (TextUtils.isEmpty(editCPF.getText().toString())) {
            editCPF.setError("*");
            editCPF.requestFocus();
            retorno = false;
        }

        if (TextUtils.isEmpty(editNomeCompleto.getText().toString())) {
            editNomeCompleto.setError("*");
            editNomeCompleto.requestFocus();
            retorno = false;
        }

        return retorno;

    }

    private void salvarSharedPreferences() {

        preferences = getSharedPreferences(AppUtil.PRE_APP, MODE_PRIVATE);
        SharedPreferences.Editor dados = preferences.edit();

        // Adicionando dados no SharedPreferences
        dados.putString("cpf", editCPF.getText().toString());
        dados.putString("nomeCompleto", editNomeCompleto.getText().toString());
        dados.apply();

    }

    private void restaurarSharedPreferences() {

        preferences = getSharedPreferences(AppUtil.PRE_APP, MODE_PRIVATE);
        isPessoaFisica = preferences.getBoolean("pessoaFisica", true);

    }

}