package app.daaziv2.appclientevipdb.controler;


import android.content.ContentValues;
import android.content.Context;

import androidx.annotation.Nullable;

import java.util.List;

import app.daaziv2.appclientevipdb.api.AppDataBase;
import app.daaziv2.appclientevipdb.datamodel.ClienteDataModel;
import app.daaziv2.appclientevipdb.model.Cliente;

public class ClienteController extends AppDataBase {

    private static final String TABELA = ClienteDataModel.TABELA;
    private ContentValues dados;

    public ClienteController(@Nullable Context context) {
        super(context);
    }

    public boolean incluir(Cliente obj){

        dados = new ContentValues();

        dados.put(ClienteDataModel.PRIMEIRO_NOME, obj.getPrimeiroNome());
        dados.put(ClienteDataModel.SOBRE_NOME, obj.getSobreNome());
        dados.put(ClienteDataModel.EMAIL, obj.getEmail());
        dados.put(ClienteDataModel.SENHA, obj.getSenha());
        dados.put(ClienteDataModel.PESSOA_FISICA, obj.isPessoaFisica());

        return insert(TABELA, dados);

    }

    public boolean alterar(Cliente obj){

        dados = new ContentValues();

        dados.put(ClienteDataModel.ID, obj.getId());
        dados.put(ClienteDataModel.PRIMEIRO_NOME, obj.getPrimeiroNome());
        dados.put(ClienteDataModel.SOBRE_NOME, obj.getSobreNome());
        dados.put(ClienteDataModel.EMAIL, obj.getEmail());
        dados.put(ClienteDataModel.SENHA, obj.getSenha());
        dados.put(ClienteDataModel.PESSOA_FISICA, obj.isPessoaFisica());

        return update(TABELA, dados);

    }

    public boolean deletar(Cliente obj){

        return delete(TABELA, obj.getId());

    }

    public List<Cliente> listar(){


        return list(TABELA);

    }
}
