package nucli;
import java.io.Serializable;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

public class Interval implements Observer, Serializable {

	//private Long durada;
	//private Date dataInicial;
	//private Date dataFinal;
	
	private Periode periode = null;

	public Periode getPeriode() {
		return periode;
	}

	public void setPeriode(Periode periode) {
		this.periode = periode;
	}
	
	public Interval(Tasca t, Date horaInicial) {
		setTasca(t);
		
		//durada = 0L;
		//dataInicial = horaInicial;
		//dataFinal = dataInicial;
		
		setPeriode(new Periode(horaInicial));
	}

		
	public void update(Observable rellotge, Object ob) {
		Date horaActual = ((Rellotge_Actualitzable) rellotge).getHora();
		Date df = periode.getDataFinal();

		long incrementDurada = calculaDurada(df,horaActual);
		//long incrementDurada = calculaDurada(dataFinal,horaActual);
		periode.incrementaDurada(incrementDurada);
		periode.setDataFinal(horaActual);
		//durada = periode.getDurada();
		
		//durada += incrementDurada;
		//dataFinal = horaActual;
		
		//System.out.println("Durada d'interval " + durada + " s.");
		// propaguem l'increment de la durada de l'interval i data final cap amunt
		// ho fem ara i no en parar el cronometre per que la durada i les dates inicial
		// i final siguin consistents, ja que a mes es mostren per pantalla mentre 
		// cronometrem
		this.getTasca().actualitza(horaActual,incrementDurada);
	}


	private Tasca tasca = null;

	public Tasca getTasca() {
		return tasca;
	}

	public void setTasca(Tasca tasca) {
		this.tasca = tasca;
	}

	public long calculaDurada(Date data1, Date data2) {
		return (long) (data2.getTime() - data1.getTime())/1000; // en segons
	}
	
	public Date getDataInicial() {
	    return periode.getDataInicial();
	}
	
    public Date getDataFinal() {
        return periode.getDataFinal();
    }

    public long getDurada() {
        return periode.getDurada();
    }
}
