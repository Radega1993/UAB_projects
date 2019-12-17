package nucli;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

public class Tasca extends Activitat {
	private Collection intervals;

	public Collection<Interval> getIntervals() {
		return intervals;
	}

	public Iterator intervalsIterator() {
		return intervals.iterator();
	}

	public boolean isIntervalsEmpty() {
		return intervals.isEmpty();
	}

	public boolean containsIntervals(Interval interval) {
		return intervals.contains(interval);
	}

	public boolean containsAllIntervals(Collection intervals) {
		return this.intervals.containsAll(intervals);
	}

	public int intervalsSize() {
		return intervals.size();
	}

	public Interval[] intervalsToArray() {
		return (Interval[]) intervals.toArray(new Interval[intervals.size()]);
	}

	public Interval[] intervalsToArray(Interval[] intervals) {
		return (Interval[]) this.intervals.toArray(intervals);
	}

	public boolean addIntervals(Interval interval) {
		return intervals.add(interval);
	}

	public void setIntervals(Collection intervals) {
		this.intervals = intervals;
	}

	public boolean removeIntervals(Interval interval) {
		return intervals.remove(interval);
	}

	public void clearIntervals() {
		intervals.clear();
	}

	public void engegaCronometre(Rellotge_Actualitzable rellotge) {
		if (!isCronometreEngegat()) { // pre-condicio: la tasca no es pot estar ja cronometrant
			cronometreEngegat = true;
			Date horaActual = rellotge.getHora();
			// si es la primera vegada que cronometrem la tasca cal crear el seu
			// Periode i el mateix respecte els seus projectes antecessors
			creaPeriode(horaActual);

			Interval nouInterval = new Interval(this,horaActual);
			this.addIntervals(nouInterval);
			Rellotge_Actualitzable.Instance().addObserver(nouInterval);
		}
	}

	public void paraCronometre(Rellotge_Actualitzable rellotge) {
		if (isCronometreEngegat()) { // pre-condicio : la tasca s'esta cronometrant
			cronometreEngegat = false;
			Interval ultimInterval = this.intervalsToArray()[this.intervalsSize() - 1];
			rellotge.deleteObserver(ultimInterval);
			// no cal actualitzar data final i durada de tasca perque ja ho
			// haura ordenat update() d'interval
		}
	}

	public Tasca(String nom, String descr, Projecte projPare) {
		super(nom, descr, projPare);
		setIntervals(new ArrayList());
	}

	private boolean cronometreEngegat;

	public boolean isCronometreEngegat() {
		return cronometreEngegat;
	}

}
