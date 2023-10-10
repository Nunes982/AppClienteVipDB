package app.daaziv2.appclientevipdb.datamodel;

// MOR - Modelo Objeto Relacional - SQLServer, Oracle, Postgress
public class ClientePFDataModel {

    /**
     *     private int fk;
     *     private String cpf;
     *     private String nomeCompleto;
     */

    public static final String TABELA = "clientePF";
    public static final String ID = "id";
    public static final String FK = "clienteID";
    public static final String CPF = "cpf";
    public static final String NOME_COMPLETO = "NomeCompleto";
    public static final String DATA_INC = "datainc";
    public static final String DATA_ALT = "dataalt";

    public static String query;

    public static String gerarTabela(){

        query = "CREATE TABLE "+TABELA+" ( ";
        query += ID+" INTEGER PRIMARY KEY AUTOINCREMENT, ";
        query += FK+" INTEGER, ";
        query += CPF+" TEXT, ";
        query += NOME_COMPLETO+" TEXT, ";
        query += DATA_INC+" TEXT, ";
        query += DATA_ALT+" TEXT, ";
        query += "FOREIGN KEY("+FK+") REFERENCES cliente(id) ";

        query += ")";

        return query;
    }


}
