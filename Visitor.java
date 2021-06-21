import java.util.LinkedList;

public class Visitor extends Thread {
	private Cashier currentCashier;
	private LinkedList<Cashier> cashierOptions;

	public Visitor(LinkedList<Cashier> cashiers) {
		cashierOptions = cashiers;
		currentCashier = null;
	}

	public void run() {
		try {
			while (!isInterrupted()) {

				currentCashier = chooseCashier();
				currentCashier.increaseQueueLength();

				System.err
						.println(this.getName() + " decided to queue at this cashier:" + currentCashier.getName());

				currentCashier.startPayment();

				// Für unbestimmte Zeit schlafen/essen
				System.err
						.println(this.getName() + " currenctly eats and doesn't want to buy anything yet");

				enjoyFood();
			}
		} catch (InterruptedException e) {
			System.err.println(this.getName() + " was interrupted!");
		}
	}

	// Student benutzen diese Methode, um sich zu vergnuegen
	//pure copy from Prof.Huebner code:
	public void enjoyFood() throws InterruptedException {
		int sleepTime = (int) (1000 * Math.random());
		// Thread blockieren
		Thread.sleep(sleepTime);
	}

	public Cashier chooseCashier() {
		Cashier bestOption = null;

		for (Cashier cashier : cashierOptions) {
			if (bestOption == null) {
				bestOption = cashier;
			} else if (cashier.numStudentsWaiting() < bestOption.numStudentsWaiting()) {
				bestOption = cashier;
			}
		}
		return bestOption;
	}
}
