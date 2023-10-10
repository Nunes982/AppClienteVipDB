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

public class CredencialDeAcessoActivity extends AppCompatActivity {

    // 1º Passo
    Button btnCadastrar;
    EditText editNome;
    EditText editEmail;
    EditText editSenhaA;
    EditText editSenhaB;
    CheckBox ckTermo;

    private SharedPreferences preferences;

    boolean isFormularioOK, isPessoaFisica;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        //2º Passo

        initFormulario();

        //3º Passo

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //4º Passo Validar entradas
                isFormularioOK = true;

                if (TextUtils.isEmpty(editNome.getText().toString())){
                    editNome.setError("*");
                    editNome.requestFocus();
                    isFormularioOK = false;
                }

                if (TextUtils.isEmpty(editEmail.getText().toString())){
                    editEmail.setError("*");
                    editEmail.requestFocus();
                    isFormularioOK = false;
                }

                if (TextUtils.isEmpty(editSenhaA.getText().toString())){
                    editSenhaA.setError("*");
                    editSenhaA.requestFocus();
                    isFormularioOK = false;
                }

                if (TextUtils.isEmpty(editSenhaB.getText().toString())){
                    editSenhaB.setError("*");
                    editSenhaB.requestFocus();
                    isFormularioOK = false;
                }

                if (!ckTermo.isChecked()){
                    ckTermo.setError("*");
                    ckTermo.requestFocus();
                    isFormularioOK = false;
                }

                // 5º Passo- Depois de tudo OK, mudar de tela

                if (isFormularioOK) {

                    if (!validarSenha()){

                        editSenhaA.setError("*");
                        editSenhaB.setError("*");
                        editSenhaA.requestFocus();

                        new AlertDialog.Builder(CredencialDeAcessoActivity.this)
                                .setIcon(R.mipmap.ic_launcher_round)
                                .setTitle("ATENÇÃO!!!")
                                .setMessage("As senhas digitadasnão são iguais , por favor tente novamente.")
                                .setPositiveButton("CONTINUAR", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        Toast.makeText(CredencialDeAcessoActivity.this, "Tente novamente.", Toast.LENGTH_SHORT).show();
                                        dialog.dismiss(); // Fecha o diálogo
                                    }
                                })
                                .create().show();


                    }else {

                        salvarSharedPreferences();

                        Intent iMenuPrincipal = new Intent(CredencialDeAcessoActivity.this, LoginActivity.class);
                        startActivity(iMenuPrincipal);
                        finish();

                        Toast.makeText(getApplicationContext(), "Usuário Cadastrado com Sucesso...", Toast.LENGTH_LONG).show();

                    }

                }

            }
        });

    }

    private void initFormulario() {

        btnCadastrar = findViewById(R.id.btnCadastrar);
        editNome = findViewById(R.id.editNome);
        editEmail = findViewById(R.id.editEmail);
        editSenhaA = findViewById(R.id.editSenhaA);
        editSenhaB = findViewById(R.id.editSenhaB);

        ckTermo = findViewById(R.id.ckTermo);

        isFormularioOK = false;

        restaurarSharedPreferences();

    }

    public void validarTermo(View view) {

        if (!ckTermo.isChecked()){

            Toast.makeText(getApplicationContext(),
                    "É necessário aceitar os termos de uso para continuar o cadastro...",
                    Toast.LENGTH_LONG).show();

        }

    }

    public boolean validarSenha(){

        boolean retono;

        int senhaA, senhaB;

        senhaA = Integer.parseInt(editSenhaA.getText().toString());
        senhaB = Integer.parseInt(editSenhaB.getText().toString());

        retono = (senhaA == senhaB);

        return retono;
    }

    private void salvarSharedPreferences() {

        preferences = getSharedPreferences(AppUtil.PRE_APP, MODE_PRIVATE);
        SharedPreferences.Editor dados = preferences.edit();

        dados.putString("email", editEmail.getText().toString());
        dados.putString("senha", editSenhaA.getText().toString());
        dados.apply();

    }

    private void restaurarSharedPreferences() {

        preferences = getSharedPreferences(AppUtil.PRE_APP, MODE_PRIVATE);
        isPessoaFisica = preferences.getBoolean("pessoaFisica", true);
        if (isPessoaFisica)
            editNome.setText(preferences.getString("nomeCompleto", "Verifique os dados"));
        else
            editNome.setText(preferences.getString("razaoSocial", "Verifique os dados"));


    }

}