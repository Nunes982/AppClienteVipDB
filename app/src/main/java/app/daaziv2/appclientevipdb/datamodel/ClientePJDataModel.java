package app.daaziv2.appclientevipdb.datamodel;

// MOR - Modelo Objeto Relacional - SQLServer, Oracle, Postgress
public class ClientePJDataModel {

    /**
     *     private int fk;
     *     private  String cnpj;
     *     private String razaoSocial;
     *     private String dataAbertura;
     *     private boolean simplesNacional;
     *     private boolean mei;
     */

    public static final String TABELA = "clientePJ";
    public static final String ID = "id";
    public static final String FK = "clientePFID";
    public static final String RAZAO_SOCIAL = "razaoSocial";
    public static final String DATA_ABERTURA = "dataAbertura";
    public static final String SIMPLES_NACIONAL = "simplesNacional";
    public static final String MEI = "mei";
    public static final String DATA_INC = "datainc";
    public static final String DATA_ALT = "dataalt";

    public static String query;

    public static String gerarTabela(){

        query = "CREATE TABLE "+TABELA+" ( ";
        query += ID+" INTEGER PRIMARY KEY AUTOINCREMENT, ";
        query += FK+" INTEGER, ";
        query += RAZAO_SOCIAL+" TEXT, ";
        query += DATA_ABERTURA+" TEXT, ";
        query += SIMPLES_NACIONAL+" INTEGER, ";
        query += MEI+" INTEGER, ";
        query += DATA_INC+" TEXT, ";
        query += DATA_ALT+" TEXT, ";
        query += "FOREIGN KEY("+FK+") REFERENCES clientePF(id) ";

        query += ")";

        return query;
    }


}
