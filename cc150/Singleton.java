public class Logging {

	private static final Logging singletonInstance = new Logging();

	private Logging() {

	}

	public static Logging getSingleton() {
		return singletonInstance;
	}

	public void log(String msg) {
		System.out.println(System.currentTimeMillis() + ":" +msg);
	}
}