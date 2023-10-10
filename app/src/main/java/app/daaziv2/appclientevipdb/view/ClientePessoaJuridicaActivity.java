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
import app.daaziv2.appclientevipdb.model.ClientePJ;

public class ClientePessoaJuridicaActivity extends AppCompatActivity {

    Cliente novoVip;
    ClientePJ novoClientePJ;

    private SharedPreferences preferences;

    EditText editCNPJ, editRazaoSocial, editDataAbertura;
    CheckBox ckSimpleNacional, ckMEI;
    Button btnSalvarContinuar, btnVoltar, btnCancelar;

    boolean isFormularioOk, isSimplesNacional, isMEI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_pessoa_juridica);

        initFormulario();

        btnSalvarContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isFormularioOk = validarFormulario()) {

                    novoClientePJ.setCnpj(editCNPJ.getText().toString());
                    novoClientePJ.setRazaoSocial(editRazaoSocial.getText().toString());
                    novoClientePJ.setDataAbertura(editDataAbertura.getText().toString());
                    novoClientePJ.setSimplesNacional(isSimplesNacional);
                    novoClientePJ.setMei(isMEI);

                    salvarSharedPreferences();

                    Intent intent = new Intent(ClientePessoaJuridicaActivity.this, CredencialDeAcessoActivity.class);
                    startActivity(intent);

                }

            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ClientePessoaJuridicaActivity.this, ClienteVipActivity.class);
                startActivity(intent);
                finish();

            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(ClientePessoaJuridicaActivity.this)
                        .setIcon(R.mipmap.ic_launcher_round)
                        .setTitle("Confirme o Cancelamento")
                        .setMessage("Deseja realmente Cancelar ?")
                        .setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(ClientePessoaJuridicaActivity.this, "Continue seu Cadastro...",
                                        Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(ClientePessoaJuridicaActivity.this, "Cancelado com sucesso...", Toast.LENGTH_SHORT).show();
                                dialog.dismiss(); // Fecha o diálogo
                            }
                        })
                        .create().show();

            }
        });

    }

    public void simplesNacional(View view) {

        isSimplesNacional = ckSimpleNacional.isChecked();

    }

    public void mei(View view) {

        isMEI = ckMEI.isChecked();

    }

    private void initFormulario() {

        editCNPJ = findViewById(R.id.editCNPJ);
        editRazaoSocial = findViewById(R.id.editRazaoSocial);
        editDataAbertura = findViewById(R.id.editDataAbertura);
        ckSimpleNacional = findViewById(R.id.ckSimplesNaciona);
        ckMEI = findViewById(R.id.ckMEI);
        btnSalvarContinuar = findViewById(R.id.btnSalvarContinuar);
        btnVoltar = findViewById(R.id.btnVoltar);
        btnCancelar = findViewById(R.id.btnCancelar);

        isFormularioOk = false;

        novoClientePJ = new ClientePJ();
        novoVip = new Cliente();

        restaurarSharedPreferences();

    }

    public boolean validarFormulario() {

        boolean retorno = true;

        if (TextUtils.isEmpty(editCNPJ.getText().toString())) {
            editCNPJ.setError("*");
            editCNPJ.requestFocus();
            retorno = false;
        }

        if (TextUtils.isEmpty(editRazaoSocial.getText().toString())) {
            editRazaoSocial.setError("*");
            editRazaoSocial.requestFocus();
            retorno = false;
        }

        if (TextUtils.isEmpty(editDataAbertura.getText().toString())) {
            editDataAbertura.setError("*");
            editDataAbertura.requestFocus();
            retorno = false;
        }

        return retorno;

    }

    private void salvarSharedPreferences() {

        preferences = getSharedPreferences(AppUtil.PRE_APP, MODE_PRIVATE);
        SharedPreferences.Editor dados = preferences.edit();

        // Adicionando dados no SharedPreferences
        dados.putString("cnpj", editCNPJ.getText().toString());
        dados.putString("razaoSocial", editRazaoSocial.getText().toString());
        dados.putString("dataAbertura", editDataAbertura.getText().toString());
        dados.putBoolean("simplesNacional", isSimplesNacional);
        dados.putBoolean("mei", isMEI);
        dados.apply();


    }

    private void restaurarSharedPreferences() {

        preferences = getSharedPreferences(AppUtil.PRE_APP, MODE_PRIVATE);

    }

}