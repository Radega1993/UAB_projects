package nucli;
import java.io.Serializable;
import java.util.Date;

public class Periode implements Serializable {

	private Date dataInicial;

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date di) {
		this.dataInicial = di;
	}

	private Date dataFinal;

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date df) {
		this.dataFinal = df;
	}
	
	private long durada;

	public long getDurada() {
		return durada;
	}

	public void setDurada(long durada) {
		this.durada = durada;
		// System.out.println(this.info.descr + "durada " + getDurada());		
	}
	
	public void incrementaDurada(long increment) {
		durada += increment;
	}
		
	public Periode(Date di) {
		setDataInicial(di);
		setDataFinal(di);
		setDurada(0);
	}
	
	public Periode(Date di, Date df, long d) {
	    dataInicial = di;
	    dataFinal = df;
	    durada = d;
	}

}
