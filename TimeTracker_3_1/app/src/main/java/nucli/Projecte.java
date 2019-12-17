package nucli;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Projecte extends Activitat {

    private Collection<Activitat> activitats;

    public Collection<Activitat> getActivitats() {
        return activitats;
    }

    public Iterator activitatsIterator() {
        return activitats.iterator();
    }

    public boolean isActivitatsEmpty() {
        return activitats.isEmpty();
    }

    public boolean containsActivitats(Activitat activitat) {
        return activitats.contains(activitat);
    }

    public boolean containsAllActivitats(Collection activitats) {
        return this.activitats.containsAll(activitats);
    }

    public int activitatsSize() {
        return activitats.size();
    }

    public Activitat[] activitatsToArray() {
        return (Activitat[]) activitats.toArray(new Activitat[activitats.size()]);
    }

    public Activitat[] activitatsToArray(Activitat[] activitats) {
        return (Activitat[]) this.activitats.toArray(activitats);
    }

    public boolean addActivitats(Activitat activitat) {
        return activitats.add(activitat);
    }

    public void setActivitats(Collection activitats) {
        this.activitats = activitats;
    }

    public boolean removeActivitats(Activitat activitat) {
        return activitats.remove(activitat);
    }

    public void clearActivitats() {
        activitats.clear();
    }

    public Projecte(String nom, String descr, Projecte projPare){
        super(nom,descr,projPare);
        activitats = new ArrayList<Activitat>();
    }

}
