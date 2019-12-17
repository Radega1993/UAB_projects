package nucli;
// import java.io.*;
import com.example.joans.timetracker.Actualitzable;
import com.example.joans.timetracker.Actualitzador;

import java.util.Date;
import java.util.Observable;

/**
 * Actualitzacio Setembre 2016 :
 *
 * He provat d'utilitzar un rellotge "normal i corrent", el que fem per la fita 1 i
 * que esta basat o conte un Thread de Java, ja sigui en la classe Timer, TimerTask,
 * o en la versio més simple, que es tenir un rellotge sense practicament funcionalitat
 * i alhora una classe GeneradorTicks que es la que realment es un Thread i va
 * actualitzant el rellotge cada segon.
 *
 * Amb aquest tipus de rellotge la aplicacio Android segueix funcionant en l'emulador
 * de l'Android Studio.
 *
 * No obstant, prefereixo la implementacio original perque evitar que el Thread de
 * GeneradorTicks estigui viu sempre. Amb la implementacio original podem parar
 * el mecanisme d'actualitzacio (de fet ho fem). No obstant, deixo incloses al
 * projecte el codi de les classes Rellotge i GeneradorTicks per si cas, nomes
 * que excloses del path de compilacio (arxiu build.gradle).
 *
 */
public class Rellotge_Actualitzable extends Observable implements Actualitzable {

    private Actualitzador actualitzadorRellotge;
    private final int delayMillis = 1000;
    
	private static Rellotge_Actualitzable theInstance = null;

	private Rellotge_Actualitzable() {
		setHora(new Date());
		actualitzadorRellotge = new Actualitzador(this,delayMillis,"rellotge");
	}
	
	public static Rellotge_Actualitzable Instance() {
		if (theInstance==null) {
			theInstance = new Rellotge_Actualitzable();
		}
		return theInstance;
	}

	private Date hora;

	public Date getHora() {
		return hora;
	}

	public void setHora(Date hora) {
		this.hora = hora;
	}

	private void tick() {
		setHora(new Date());
		setChanged();
		notifyObservers(this);
	}

    @Override
    public void actualitza() {
        tick();
    }
    
    public void para() {
        actualitzadorRellotge.para();
    }

    public void engega() {
        actualitzadorRellotge.engega();
    }

}
