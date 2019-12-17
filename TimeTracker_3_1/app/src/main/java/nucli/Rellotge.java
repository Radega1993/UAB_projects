package nucli;

// import java.io.*;
import java.util.Date;
import java.util.Observable;

public class Rellotge extends Observable {

	private static Rellotge theInstance = null;

	private Rellotge() {
		setHora(new Date());
	}

	public static Rellotge Instance() {
		if (theInstance==null) {
			theInstance = new Rellotge();
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

	public void tick() {
		setHora(new Date());
		setChanged();
		notifyObservers(this);
	}

}
