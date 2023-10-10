package app.daaziv2.appclientevipdb.datamodel;

// MOR - Modelo Objeto Relacional - SQLServer, Oracle, Postgress
public class ClienteDataModel {

    /**
     *     private int id;
     *     private String primeiroNome;
     *     private String sobreNome;
     *     private String email;
     *     private String senha;
     *     private boolean pessoaFisica;
     */

    public static final String TABELA = "cliente";
    public static final String ID = "id";
    public static final String PRIMEIRO_NOME = "primeiroNome";
    public static final String SOBRE_NOME = "sobreNome";
    public static final String EMAIL = "email";
    public static final String SENHA = "senha";
    public static final String PESSOA_FISICA = "pessoaFisica";
    public static final String DATA_INC = "datainc";
    public static final String DATA_ALT = "dataalt";

    public static String query;

    /**
     * CREATE TABLE cliente (
     *     id      INTEGER PRIMARY KEY AUTOINCREMENT,
     *     nome    TEXT,
     *     email   TEXT,
     *     status  INTEGER,
     *     datainc TEXT,
     *     dataalt TEXT
     * );
     */

    public static String gerarTabela(){

        query = "CREATE TABLE "+TABELA+" ( ";
        query += ID+" INTEGER PRIMARY KEY AUTOINCREMENT, ";
        query += PRIMEIRO_NOME+" TEXT, ";
        query += SOBRE_NOME+" TEXT, ";
        query += EMAIL+" TEXT, ";
        query += SENHA+" TEXT, ";
        query += PESSOA_FISICA+" INTEGER, ";
        query += DATA_INC+" TEXT, ";
        query += DATA_ALT+" TEXT ";

        query += ")";

        return query;
    }


}
