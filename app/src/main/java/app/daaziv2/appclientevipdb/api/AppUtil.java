package app.daaziv2.appclientevipdb.api;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Classe de apoio contendo métodos que podem
 * ser reutilizados em todo o projeto
 *
 * Criada por Anderson Nunes - 09/2023
 *
 * Versão v1
 */
public class AppUtil {

    public static final int TIME_SPLASH = 5 * 1000;
    public static final String PRE_APP = "app_cliente_vip_pref";
    public static final String LOG_APP = "CLIENTE_LOG";

    /**
     *
     * @return devolve a data atual
     */
    public static String getDataAtual(){

       String dataAtual = "00-00-0000";

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
        Date date = new Date();

        dataAtual = dateFormat.format(date);

       return dataAtual;
    }

    /**
     *
     * @return devolve a hora atual
     */
    public static String getHoraAtual(){

        String horaAtual = "00:00:00";

        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date data = new Date();

        horaAtual = dateFormat.format(data);

        return horaAtual;

    }

}
