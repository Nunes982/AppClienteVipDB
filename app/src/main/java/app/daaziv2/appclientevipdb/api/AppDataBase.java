package app.daaziv2.appclientevipdb.api;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import app.daaziv2.appclientevipdb.datamodel.ClienteDataModel;
import app.daaziv2.appclientevipdb.datamodel.ClientePFDataModel;
import app.daaziv2.appclientevipdb.datamodel.ClientePJDataModel;
import app.daaziv2.appclientevipdb.model.Cliente;

public class AppDataBase extends SQLiteOpenHelper {

    public static final String DB_NAME = "clienteDB.sqlite";
    public static final int DB_VERSION = 1;

    Cursor cursor;

    SQLiteDatabase db;

    public AppDataBase(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);

        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Criar as tabelas

        try {
            // Executar o desejado
            // Tentar

            db.execSQL(ClienteDataModel.gerarTabela());

            Log.i(AppUtil.LOG_APP, "TB Cliente: " + ClienteDataModel.gerarTabela());

        } catch (SQLException e) {

            // Capturar erro

            Log.e(AppUtil.LOG_APP, "Erro TB Cliente: " + e.getMessage());
        }

        try {
            // Executar o desejado
            // Tentar

            db.execSQL(ClientePFDataModel.gerarTabela());

            Log.i(AppUtil.LOG_APP, "TB ClientePF: " + ClientePFDataModel.gerarTabela());

        } catch (SQLException e) {

            // Capturar erro

            Log.e(AppUtil.LOG_APP, "Erro TB ClientePF: " + e.getMessage());
        }

        try {
            // Executar o desejado
            // Tentar

            db.execSQL(ClientePJDataModel.gerarTabela());

            Log.i(AppUtil.LOG_APP, "TB ClientePJ: " + ClientePJDataModel.gerarTabela());

        } catch (SQLException e) {

            // Capturar erro

            Log.e(AppUtil.LOG_APP, "Erro TB ClientePJ: " + e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Atualizar o banco de dados
        // tabelas
    }

    /**
     * Incluir dados no banco de dados
     *
     * @return
     */
    public boolean insert(String tabela, ContentValues dados) {

        boolean sucesso = true;

        try {

            Log.e(AppUtil.LOG_APP, tabela + " insert() executado com sucesso. ");
            sucesso = db.insert(tabela, null, dados) > 0;

        } catch (SQLException e) {

            Log.e(AppUtil.LOG_APP, tabela + " falhou ao executar o insert(): " + e.getMessage());
        }

        return sucesso;
    }

    /**
     * Deletar dados no banco de dados
     *
     * @return
     */
    public boolean delete(String tabela, int id) {

        boolean sucesso = true;

        try {

            Log.e(AppUtil.LOG_APP, tabela + " delete() executado com sucesso. ");
            sucesso = db.delete(tabela, "id=?", new String[]{Integer.toString(id)}) > 0;

        } catch (SQLException e) {

            Log.e(AppUtil.LOG_APP, tabela + " falhou ao executar o delete(): " + e.getMessage());
        }

        return sucesso;

    }

    /**
     * Atualizar dados no banco de dados
     *
     * @return
     */
    public boolean update(String tabela, ContentValues dados) {

        boolean sucesso = true;

        try {

            int id = dados.getAsInteger("id");

            Log.e(AppUtil.LOG_APP, tabela + " update() executado com sucesso. ");
            sucesso = db.update(tabela, dados, "id=?", new String[]{Integer.toString(id)}) > 0;

        } catch (SQLException e) {

            Log.e(AppUtil.LOG_APP, tabela + " falhou ao executar o update(): " + e.getMessage());
        }

        return sucesso;

    }

    /**
     * Listar dados no banco de dados
     *
     * @return
     */
    public List<Cliente> list(String tabela) {

        List<Cliente> list = new ArrayList<>();

        Cliente cliente;

        // Select no banco de dados
        // SELECT * FROM tabela

        String sql = "SELECT * FROM " + tabela;

        try {

            cursor = db.rawQuery(sql, null);

            if (cursor.moveToFirst()) {

                do {

                    cliente = new Cliente();

                    cliente.setId(cursor.getInt(cursor.getColumnIndexOrThrow(ClienteDataModel.ID)));
                    cliente.setPrimeiroNome(cursor.getString(cursor.getColumnIndexOrThrow(ClienteDataModel.PRIMEIRO_NOME)));
                    cliente.setSobreNome(cursor.getString(cursor.getColumnIndexOrThrow(ClienteDataModel.SOBRE_NOME)));
                    cliente.setEmail(cursor.getString(cursor.getColumnIndexOrThrow(ClienteDataModel.EMAIL)));
                    cliente.setSenha(cursor.getString(cursor.getColumnIndexOrThrow(ClienteDataModel.SENHA)));
                    cliente.setPessoaFisica(cursor.getInt(cursor.getColumnIndexOrThrow(ClienteDataModel.PESSOA_FISICA)) == 1);

                    list.add(cliente);

                } while (cursor.moveToNext());

                Log.i(AppUtil.LOG_APP, tabela + " lista gerado com sucesso.");

            }
        } catch (SQLException e) {
            Log.e(AppUtil.LOG_APP, "Erro ao listar os dados: " + tabela);
            Log.e(AppUtil.LOG_APP, "Erro: " + e.getMessage());
        }
        return list;
    }

}
