package nucli;

public class GeneradorTicks extends Thread {

    /**
     */
    public GeneradorTicks(Rellotge r) {
        setDaemon(true); // daemon thread, aixi no impedeix la fi de la aplicacio
        setRellotge(r);
        start();
    }

    /**
     */
    public GeneradorTicks(Rellotge r, long d) {
        setDaemon(true); // daemon thread, aixi no impedeix la fi de la aplicacio
        setRellotge(r);
        setDelay(d);
        start();
    }

    private long delay = 1000;      // Valor per defecte, en milisegons
    // Unitat minima de temps de cronometratge

    public long getDelay() {
        return delay;
    }

    public void setDelay(long delay) {
        this.delay = delay;
    }

    public void run() {
        try {
            while (true) {
                sleep(delay);
                rellotge.tick();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private Rellotge rellotge = null;

    public Rellotge getRellotge() {
        return rellotge;
    }

    public void setRellotge(Rellotge rellotge) {
        this.rellotge = rellotge;
    }
}
