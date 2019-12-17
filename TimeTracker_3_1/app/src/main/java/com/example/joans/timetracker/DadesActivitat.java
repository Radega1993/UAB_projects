package com.example.joans.timetracker;

import java.io.Serializable;
import java.util.Date;

import nucli.Activitat;
import nucli.Tasca;



/**
 * Conté les dades d'una activitat (projecte o tasca) que poden ser mostrades
 * per la interfase d'usuari. <code>GestorArbreActivitats</code> en fa una
 * llista amb les dades de les activitats filles del projecte actual, i l'envia
 * a la Activity <code>LlistaActivitatsActivity</code> per que la mostri.
 * <p>
 * Com que és una classe sense funcionalitat, només és una estructura de dades,
 * així que faig els seus atributs públics per simplificar el codi.
 * <p>
 * Aquesta classe simplifica el passar les dades de projectes i tasques a la
 * Activity corresponent que les visualitza. Si passéssim directament la llista
 * d'activitats filles, com que es fa per serialització, s'acaba enviat tot
 * l'arbre, ja que els fills referencien als pares. El problema es tal, que amb
 * un arbre mitjà es perd tota la "responsiveness".
 *
 * @author joans
 * @version 6 febrer 2012
 */
public class DadesActivitat implements Serializable {
    /**
     * Ho demana el Checkstyle, però no he mirat per a què deu servir.
     */
    private static final long serialVersionUID = 1L;

    // TODO : passar tots els atributs a private i fer els corresponents
    // getters.

    /**
     * @see Activitat
     */
    private Date dataInicial;

    /**
     * @see Activitat
     */
    private Date dataFinal;

    /**
     * @see Activitat
     */
    private long durada; // en segons

    /**
     * @see Activitat
     */
    private String nom;

    /**
     * @see Activitat
     */
    private String descripcio;

    /**
     * Hores de durada.
     */
    private long hores;

    /**
     * Minuts de durada.
     */
    private long minuts;

    /**
     * Segons de durada.
     */
    private long segons;

    /**
     * Per tal d'identificar el tipus d'activitat en la interfase d'usuari.
     */
    private boolean isProjecte;

    /**
     * Per tal d'identificar el tipus d'activitat en la interfase d'usuari.
     */
    private boolean isTasca;

    /**
     * La interfase d'usuari ho necessita saber per denotar-ho i també per
     * adequar la interacció (per exemple, no hauria de deixar cronometrar una
     * tasca que ja ho està sent).
     */
    private boolean isCronometreEngegat = false; // nomes te sentit per tasques

    /**
     * Extreu les dades de la activitat passada per paràmetre i les copia als
     * atributs propis.
     *
     * @param act
     *            Tasca o projecte.
     */
    public DadesActivitat(final Activitat act) {
        /**
         * Factor de conversió
         */
        final long segonsPerHora = 3600;

        /**
         * Factor de conversió
         */
        final long segonsPerMinut = 60;

        dataInicial = act.getDataInicial();
        dataFinal = act.getDataFinal();
        durada = act.getDurada();
        nom = act.getNom();
        descripcio = act.getDescripcio();
        hores = (long) (durada / segonsPerHora);
        minuts = (long) ((durada - hores * segonsPerHora) / segonsPerMinut);
        segons = (long) (durada - segonsPerHora * hores
                - segonsPerMinut * minuts);

        if (act.getClass().getName().endsWith("Projecte")) {
            isProjecte = true;
            isTasca = false;
        } else {
            isProjecte = false;
            isTasca = true;
            isCronometreEngegat = ((Tasca) act).isCronometreEngegat();
        }
    }

    /**
     * Converteix una part de les dades d'un objecte DadesActivitat a un String,
     * que serà el que es mostrarà a la interfase d'usuari, ja que els
     * <code>ListView</code> mostren el que retorna aquest mètode per a cada un
     * dels seus elements. Veure
     * {@link LlistaActivitatsActivity.Receptor#onReceive}.
     *
     * @return nom i durada de la activitat, en format hores, minuts i segons.
     */
    @Override
    public final String toString() {
        String str = nom;
        //String strdurada;
        //if (durada > 0) {
        //    strdurada = hores + "h " + minuts + "m " + segons + "s";
        //} else {
        //    strdurada = "0s";
        //}
        //str += " " + strdurada;
        return str;
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

    /**
     * Getter de <code>hores</code>.
     * @return {@link #hores}.
     */
    public final long getHores() {
        return hores;
    }

    /**
     * Getter de <code>minuts</code>.
     * @return {@link #minuts}.
     */
    public final long getMinuts() {
        return minuts;
    }

    /**
     * Getter de <code>segons</code>.
     * @return {@link #segons}.
     */
    public final long getSegons() {
        return segons;
    }

    /**
     * Getter de <code>nom</code>.
     * @return {@link #nom}.
     */
    public final String getNom() {
        return nom;
    }

    /**
     * Getter de <code>descripcio</code>.
     * @return {@link #descripcio}.
     */
    public final String getDescripcio() {
        return descripcio;
    }

    /**
     * Getter de <code>isProjecte</code>.
     * @return {@link #isProjecte}.
     */
    public final boolean isProjecte() {
        return isProjecte;
    }

    /**
     * Getter de <code>isTasca</code>.
     * @return {@link #isTasca}.
     */
    public final boolean isTasca() {
        return isTasca;
    }

    /**
     * Getter de <code>isCronometreEngegat</code>.
     * @return {@link #isCronometreEngegat}.
     */
    public final boolean isCronometreEngegat() {
        return isCronometreEngegat;
    }
}
