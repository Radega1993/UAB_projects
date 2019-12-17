package nucli;
public class GeneradorIds {

	private static GeneradorIds theInstance = null;
	/**
	 * Constructor privat per implementar Singleton
	 */
	private GeneradorIds() {
		id = 0;
	}
	
	public static GeneradorIds Instance() {
		if (theInstance==null) {
			theInstance = new GeneradorIds();
		}
		return theInstance;
	}

	private int id;

	public int getId() {
		this.id++ ;
		return id;
	}
	
}
