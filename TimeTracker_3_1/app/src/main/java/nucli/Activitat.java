package nucli;
import java.io.Serializable;
import java.util.Date;

public abstract class Activitat implements Serializable {
	private Informacio info = null;
	public Informacio getInfo() {
		return info;
	}
	public void setInfo(Informacio info) {
		this.info = info;
	}
	private Periode periode = null;
	protected Periode getPeriode() {
	// per saber si un projecte te periode creat i associat o es la
	// primera vegada que se'n cronometra una tasca i cal crear-li el
	// periode
		return periode;
	}

	public void setPeriode(Periode periode) {
	// protected perque a engegarCronometre de Tasca fem un setPeriode(new Periode())
	// la primera vegada que cronometrem una tasca. Tambe ho farem aixi a Projecte.
		this.periode = periode;
	}

	private Projecte projectePare;

	public Projecte getProjectePare() {
		return projectePare;
	}

	public void setProjectePare(Projecte projectePare) {
		this.projectePare = projectePare;
	}
	
	// funcions de redireccionament, per ocultar la classe Periode
	// als clients d'activitats
	
	public void setDataInicial(Date di) {
		getPeriode().setDataInicial(di);
	}

	public void setDataFinal(Date di) {
		getPeriode().setDataFinal(di);
	}

	public void setDurada(long d) {
		getPeriode().setDurada(d);
	}

	public Date getDataInicial() {
		return getPeriode().getDataInicial();
	}

	public Date getDataFinal() {
		return getPeriode().getDataFinal();
	}

	public long getDurada() {
		// hem de preveure el cas que encara no haguem cronometrat i
		// per tant no hi hagi cap periode. Aixo afecta a les tasques,
		// no se si tambe als projectes.
		if (periode!=null) {
			return getPeriode().getDurada();
		} else {
			return 0L;
		}
	}
	
	public void incrementaDurada(long increment) {
		getPeriode().incrementaDurada(increment);
	}

	// funcions de redireccionament per ocultar la classe Informacio
	// als clients
	
	public String getNom() {
		return getInfo().getNom();
	}
		
	public String getDescripcio() {
		return getInfo().getDescripcio();
	}

	/**
	 */
	public void creaPeriode(Date horaActual) {
		if (getPeriode()==null) { // encara no hem cronometrat mai cap tasca derivada d'aquest projecte
			setPeriode(new Periode(horaActual)); // aixo ja inicialitza la data inicial, final i durada
			Projecte projectePare = getProjectePare();
			if (projectePare!=null) { // si no som en un projecte arrel ja
				projectePare.creaPeriode(horaActual);
			}
		} 
	}
		
	/**
	 */
	public Activitat(String nom, String descr, Projecte projectePare) {
		this.setInfo(new Informacio(nom, descr));
		this.setProjectePare(projectePare);
		if (projectePare!= null) {
			projectePare.getActivitats().add(this);
		}
	}

		
	/**
	 */
	public void actualitza(Date df, long increment) {
		// actualitza la durada i la data final del projecte o tasca,
		// a consequencia de cronometrar
		incrementaDurada(increment);
		setDataFinal(df);
		
		//System.out.println(this.getClass().getName() + " " + getNom()    + " " + // Projecte o Tasca
		//		           "durada "     + getDurada() + " " + 
		//		           "data final " + getDataFinal());
		// i propaga l'actualitzacio cap al projecte pare, si n'hi ha
		Projecte projectePare = getProjectePare();
		if (projectePare!=null) {
			projectePare.actualitza(df,increment);
		}
	}
}
