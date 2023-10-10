package app.daaziv2.appclientevipdb.view;


import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import app.daaziv2.appclientevipdb.R;
import app.daaziv2.appclientevipdb.api.AppUtil;
import app.daaziv2.appclientevipdb.model.Cliente;
import app.daaziv2.appclientevipdb.model.ClientePF;
import app.daaziv2.appclientevipdb.model.ClientePJ;

public class MainActivity extends AppCompatActivity {

    Cliente cliente;
    ClientePF clientePF;
    ClientePJ clientePJ;

    TextView txtNomeCliente;

    List<Cliente> clientes;

    List<String> cidades;

    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFormulario();

        buscarListaDeClientes();


    }

    private void buscarListaDeClientes() {

        clientes = new ArrayList<>();

        cidades = new ArrayList<>();

        cidades.add("Manaus");
        cidades.add("Campo Grande");
        cidades.add("Brasília");
        cidades.add("São Paulo");
        cidades.add("Diadema");
        cidades.add("Maranhão");

        clientes.add(cliente);

        Cliente novoCliente01 = new Cliente();
        novoCliente01.setPrimeiroNome("Novo cliente 01");

        clientes.add(novoCliente01);

        for (int i = 0; i < 10; i++) {

            cliente = new Cliente();
            cliente.setPrimeiroNome("Cliente nº "+i);

            clientes.add(cliente);
        }

        for (String obj: cidades) {

            Log.i(AppUtil.LOG_APP, "Obj: "+obj);

        }

    }

    private void initFormulario() {

        cliente = new Cliente();
        clientePF = new ClientePF();
        clientePJ = new ClientePJ();

        txtNomeCliente = findViewById(R.id.txtNomeCliente);

        restaurarSharedPreferences();

        txtNomeCliente.setText("Bem Vindo, "+cliente.getPrimeiroNome());

    }

    private void salvarSharedPreferences() {

        preferences = getSharedPreferences(AppUtil.PRE_APP, MODE_PRIVATE);
        SharedPreferences.Editor dados = preferences.edit();

    }

    private void restaurarSharedPreferences() {

        preferences = getSharedPreferences(AppUtil.PRE_APP, MODE_PRIVATE);

        cliente.setPrimeiroNome(preferences.getString("primeiroNome", "NULO"));
        cliente.setSobreNome(preferences.getString("sobreNome", "NULO"));
        cliente.setEmail(preferences.getString("email", "NULO"));
        cliente.setSenha(preferences.getString("senha", "NULO"));
        cliente.setPessoaFisica(preferences.getBoolean("pessoaFisica", true));

        clientePF.setCpf(preferences.getString("cpf", "NULO"));
        clientePF.setNomeCompleto(preferences.getString("nomeCompleto", "NULO"));

        clientePJ.setCnpj(preferences.getString("cnpj", "NULO"));
        clientePJ.setRazaoSocial(preferences.getString("razaoSocial", "NULO"));
        clientePJ.setDataAbertura(preferences.getString("dataAbertura", "NULO"));
        clientePJ.setSimplesNacional(preferences.getBoolean("simplesNacional", false));
        clientePJ.setMei(preferences.getBoolean("mei", false));

    }

    public void meusDados(View view) {

        /**
         *     <boolean name="pessoaFisica" value="false" />
         *     <string name="primeiroNome">Madoo</string>
         *     <boolean name="simplesNacional" value="true" />
         *     <string name="cnpj">11222333000155</string>
         *     <string name="sobreNome">Marco </string>
         *     <string name="dataAbertura">11/01/2020</string>
         *     <boolean name="loginAutomativo" value="true" />
         *     <boolean name="mei" value="false" />
         *     <string name="senha">12345</string>
         *     <string name="cpf">11122233344</string>
         *     <string name="razaoSocial">Marco Madoo ME</string>
         *     <string name="email">madoo@teste.com</string>
         *     <string name="nomeCompleto">Marco A de Oliveira </string>
         * </map>
         */

        Log.i(AppUtil.LOG_APP, "*** DADOS CLIENTE ***");
        Log.i(AppUtil.LOG_APP, "ID "+cliente.getId());
        Log.i(AppUtil.LOG_APP, "Primeiro Nome: "+cliente.getPrimeiroNome());
        Log.i(AppUtil.LOG_APP, "Sobre Nome: "+cliente.getSobreNome());
        Log.i(AppUtil.LOG_APP, "Email: "+cliente.getEmail());
        Log.i(AppUtil.LOG_APP, "Senha: "+cliente.getSenha());
        Log.i(AppUtil.LOG_APP, "*** DADOS CLIENTE PF ***");
        Log.i(AppUtil.LOG_APP, "CPF: "+clientePF.getCpf());
        Log.i(AppUtil.LOG_APP, "Nome Completo: "+clientePF.getNomeCompleto());

        if (!cliente.isPessoaFisica()) {
            Log.i(AppUtil.LOG_APP, "*** DADOS CLIENTE PJ ***");
            Log.i(AppUtil.LOG_APP, "CNPJ: "+clientePJ.getCnpj());
            Log.i(AppUtil.LOG_APP, "Razão Social: "+clientePJ.getRazaoSocial());
            Log.i(AppUtil.LOG_APP, "Data de Abertura: "+clientePJ.getDataAbertura());
            Log.i(AppUtil.LOG_APP, "Simples Nacional: "+clientePJ.isSimplesNacional());
            Log.i(AppUtil.LOG_APP, "MEI: "+clientePJ.isMei());
        }

    }

    public void atualizarMeusDados(View view) {

        if (cliente.isPessoaFisica()){

            cliente.setPrimeiroNome("Marco A");
            cliente.setSobreNome("Oliveira");

            clientePF.setNomeCompleto("Marco A D Oliveira");

            //salvarSharedPreferences();

            Log.i(AppUtil.LOG_APP, "*** ALTERANDO DADOS CLIENTE ***");
            Log.i(AppUtil.LOG_APP, "Primeiro Nome: "+cliente.getPrimeiroNome());
            Log.i(AppUtil.LOG_APP, "Sobre Nome: "+cliente.getSobreNome());
            Log.i(AppUtil.LOG_APP, "*** ALTERANDO DADOS CLIENTE PF ***");
            Log.i(AppUtil.LOG_APP, "Nome Completo: "+clientePF.getNomeCompleto());

        }else{

            clientePJ.setRazaoSocial("MADOO ME");

            Log.i(AppUtil.LOG_APP, "*** ALTERANDO DADOS CLIENTE PJ ***");
            Log.i(AppUtil.LOG_APP, "Razão Social: "+clientePJ.getRazaoSocial());

        }

    }

    public void excluirMinhaConta(View view) {

        new AlertDialog.Builder(MainActivity.this)
                .setIcon(R.mipmap.ic_launcher_round)
                .setTitle("EXCLUIR SUA CONTA")
                .setMessage("Confirma a EXCLUSÃO definitiva da sua conta ?")
                .setNegativeButton("RETORNAR ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, cliente.getPrimeiroNome() + ", divirta-se com as opções do aplicativo.",
                                Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(MainActivity.this, cliente.getPrimeiroNome() + ", sua conta foi excluída, esperamos que retorne em breve.",
                                Toast.LENGTH_LONG).show();

                        cliente = new Cliente();
                        clientePF = new ClientePF();
                        clientePJ = new ClientePJ();

                        //Lembrar Senha para Login Automático , tem que ser resetado
                        //salvarSharedPreferences();

                        finish();
                    }
                })
                .create().show();

    }

    public void consultarClientesVip(View view) {

        Intent intent = new Intent(MainActivity.this, ConsultarClientesActivity.class);
        startActivity(intent);

    }

    public void sairDoAplicativo(View view) {

        new AlertDialog.Builder(MainActivity.this)
                .setIcon(R.mipmap.ic_launcher_round)
                .setTitle("SAIR DO APLICATIVO")
                .setMessage("Confirma a sua saida do aplicativo ?")
                .setNegativeButton("RETORNAR ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, cliente.getPrimeiroNome() + ", divirta-se com as opções do aplicativo.",
                                Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(MainActivity.this, cliente.getPrimeiroNome() + ", volte sempre e obrigado.",
                                Toast.LENGTH_LONG).show();
                        finish();
                    }
                })
                .create().show();

    }

}