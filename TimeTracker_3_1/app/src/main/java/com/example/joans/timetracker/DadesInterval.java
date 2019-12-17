package com.example.joans.timetracker;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import nucli.Interval;



/**
 * Conté les dades d'un interval que poden ser mostrades per la interfase
 * d'usuari. <code>GestorArbreActivitats</code> en fa una llista dels de la
 * tasca pare actual i l'envia a la Activity
 * <code>LlistaIntervalsActivity</code> per que la mostri.
 * <p>
 * Aquesta classe simplifica el passar les dades d'interval a la Activity
 * corresponent que les visualitza. Veure {@link DadesActivitat} per saber
 * perquè.
 *
 * @author joans
 * @version 6 febrer 2012
 */
public class DadesInterval implements Serializable {

    /**
     * Necessari segons checkstyle.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Data inicial d'un interval.
     * @see Interval
     */
    private Date dataInicial;

    /**
     * Data final d'un interval.
     * @see Interval
     */
    private Date dataFinal;

    /**
     * Durada d'un interval.
     * @see Interval
     */
    private long durada;

    /**
     * Extreu les dades de l'interval passat per paràmetre i les copia als
     * atributs propis.
     *
     * @param inter
     *            l'interval
     */
    public DadesInterval(final Interval inter) {
        dataInicial = inter.getDataInicial();
        dataFinal = inter.getDataFinal();
        durada = inter.getDurada();
    }

    /**
     * Converteix una part de les dades d'un objecte Interval a un String,
     * que serà el que es mostrarà a la interfase d'usuari, ja que els
     * <code>ListView</code> mostren el que retorna aquest mètode per a cada un
     * dels seus elements. Veure
     * {@link LlistaIntervalsActivity.Receptor#onReceive}.
     *
     * @return nom i durada de la activitat, en format hores, minuts i segons.
     */
    @Override
    public final String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        String strdi = sdf.format(dataInicial);
        String strdf = sdf.format(dataFinal);

        // TODO : aquest codi de conversió de durada en segons a
        // hores, minuts i segons, és redundant amb DadesActivitat.
        // Fer un mètode estàtic en alguna classe a l'efecte.

        /**
         * Factor de conversió
         */
        final long segonsPerHora = 3600;

        /**
         * Factor de conversió
         */
        final long segonsPerMinut = 60;

        long hores = (long) (durada / segonsPerHora);
        long minuts = (long) ((durada - hores * segonsPerHora)
                / segonsPerMinut);
        long segons = (long) (durada - segonsPerHora * hores
                - segonsPerMinut * minuts);
        // String strdurada = Long.toString(durada);
        String strdurada = hores + "h " + minuts + "m " + segons + "s";
        return strdi + "-->" + strdf + " = " + strdurada;
    }

    // Getters

    /**
     * Getter de <code>dataInicial</code>.
     * @return {@link #dataInicial}.
     */
    public final Date getDataInicial() {
        return dataInicial;
    }

    /**
     * Getter de <code>dataFinal</code>.
     * @return {@link #dataFinal}.
     */
    public final Date getDataFinal() {
        return dataFinal;
    }

    /**
     * Getter de <code>durada</code>.
     * @return {@link #durada}.
     */
    public final long getDurada() {
        return durada;
    }
}
