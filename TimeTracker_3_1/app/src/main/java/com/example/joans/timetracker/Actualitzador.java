package com.example.joans.timetracker;

import android.os.Handler;
import android.util.Log;

/**
 * Periòdicament invoca el mètode <code>actualitza</code> d'un objecte que es
 * subscriu a un objecte d'aquesta classe. Té un propòsit semblant doncs al que
 * es podria aconseguir amb <code>Timer</code> combinat amb
 * <code>TimerTask</code>, però que no són compatibles amb l'Android.
 * <p>
 * El mecanisme per aconseguir-ho està molt ben explicat a l'article
 * <em>Updating the UI from a Timer</em> que es troba a la documentació oficial
 * d'Android Resources -> Articles,
 * <p>
 * {@link http://developer.android.com/resources/articles/timed-ui-updates.html}
 * <p>
 * Una extensió al respecte és la possibilitat de parar i engegar la crida
 * periòdica al mètode <code>actualitza</code>.
 * <p>
 * El fem servir per dues coses:
 * <ul>
 * <li>per comptar el temps a la classe <code>Rellotge_Actualitzable</code></li>
 * <li>per actualitzar la interfase d'usuari a les classes
 * <code>LlistaActivitatsActivity</code> i
 * <code>LlistaIntervalsActivity</code></li>
 * </ul>
 *
 * @author joans
 * @version 6 febrer 2012
 */
public class Actualitzador extends Handler {

    /**
     * Nom de la classe per fer aparèixer als missatges de logging del LogCat.
     *
     * @see Log
     */
    private final String tag = this.getClass().getSimpleName();

    /**
     * Període d'actualització, en milisegons.
     */
    private long delayMillis;

    /**
     * Només quan és <code>true</code> funciona el mecanisme d'actualització, és
     * a dir, s'invoca el mètode <code>actualitza</code> de l'objecte
     * <code>actualitzable</code>. Els métodes <code>engega</code> i
     * <code>para</code> alternen el seu valor.
     */
    private boolean enMarxa;

    /**
     * Consultar l'article <em>Updating the UI from a Timer</em> referenciat a
     * {@link Actualitzador}.
     */
    private Handler mHandler = new Handler();

    /**
     * Objecte del que invocarem periòdicament el mètode <code>actualitza</code>
     * . La seva classe ha d'implementar la interfase {@link Actualitzable}.
     */
    private Actualitzable actualitzable;

    /**
     * Serveix per identificar l'objecte actualitzat en els missatges de
     * logging.
     */
    private String propietari;

    /**
     * Crea l'objecte actualitzador però encara no el posa en marxa.
     *
     * @param ac
     *            objecte a actualitzar periòdicament
     * @param d
     *            període en milisegons
     * @param str
     *            identificador de l'objecte actualitzat pels missatges de
     *            logging
     */
    public Actualitzador(final Actualitzable ac, final long d,
            final String str) {
        super();
        enMarxa = false;
        delayMillis = d;
        actualitzable = ac;
        propietari = str;
    }

    /**
     * Inicia el cicle d'actualitzacions al cap de {@link #delayMilis}
     * milisegons i amb aquest període.
     */
    public final void engega() {
        if (!enMarxa) { // si no estava ja engegat
            enMarxa = true;
            mHandler.postDelayed(mUpdateTimeTask, delayMillis);
            // així comença el cicle d'enviar i gestionar callbacks
            Log.d(tag, "handler de " + propietari + " engegat");
        } else {
            Log.d(tag, "engegat handler de " + propietari
                    + " que ja ho estava");
        }
    }

    /**
     * Consultar l'article <em>Updating the UI from a Timer</em> referenciat a
     * {@link Actualitzador}.
     */
     private Runnable mUpdateTimeTask = new Runnable() {
        public void run() {
            Log.d(tag, "entro a run() d'actualitzador de " + propietari);
            // continuem el cicle
            if (enMarxa) {
                Log.d(tag, "i actualitzo");
                mHandler.removeCallbacks(mUpdateTimeTask);
                actualitzable.actualitza();
                mHandler.postDelayed(mUpdateTimeTask, delayMillis);
            } else {
                Log.d(tag, "pero no faig res");
            }
        }
    };

    /**
      * Momentàniament atura el cicle d'actualitzacions, fins a una nova
      * invocació de {@link #engega}.
      */
    public final void para() {
        if (enMarxa) { // si ja estava engegat
            enMarxa = false;
            mHandler.removeCallbacks(mUpdateTimeTask);
            Log.d(tag, "handler pausat");
        } else {
            Log.d(tag, "pausat el handler que ja ho estava");
        }
    }

};
