
import java.util.concurrent.locks.ReentrantLock;

public class Cashier {
	int queue;
	private final String cashierName;

	//security = mutex
	private final ReentrantLock security = new ReentrantLock();

	public Cashier(String name) {
		this.cashierName = name;
		this.queue = 0;
	}

	// Customer ruft die Methode ENTER auf

	public void startPayment() throws InterruptedException {

		security.lock();
		queue--;
		// Einkaufen
		System.err.println("                                             "
				+ Thread.currentThread().getName() + " buys food right now at this cashier:" + cashierName);
		try {
			processPayment();

			// Kasse verlassen
			System.err.println("                                                                                   "
					+ Thread.currentThread().getName() + " leaves the cashier " + cashierName + " and goes to eat");
		} finally {//die Befehle nach finally werden immer grantiert ausgefuehrt, good practice
			security.unlock();
		}
	}

	// Customer benutzen diese Methode, um einzukaufen
	//pure copy from Prof.Huebner code:
	public void processPayment() throws InterruptedException {
		int sleepTime = (int) (1000 * Math.random());

		// Ausführenden Thread blockieren
		Thread.sleep(sleepTime);
	}

	public String getName() {
		return cashierName;
	}

	public int numStudentsWaiting() {
		return queue;
	}

	public void increaseQueueLength() {
		this.queue++;
	}
}
