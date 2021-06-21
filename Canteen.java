
import java.util.LinkedList;

public class Canteen {
	public final LinkedList<Visitor> people = new LinkedList<>();
	//public final int NUMBER_OF_STUDENTS = 10;

	public final LinkedList<Cashier> cashiers = new LinkedList<>();
	public final int NUMBER_OF_CASHIERS = 3;

	//pure copy from Prof.Huebner code:
	public static void main(String[] args) {
		/* Starte Simulation */
		new Canteen().startSimulation();
	}

	public void startSimulation() {

		//fuege alle Kassen in eine Liste  ein:
		for (int i = 1; i <= NUMBER_OF_CASHIERS; i++) {
			cashiers.add(new Cashier("Cashier Number " + i));
		}

		String[] names = {"John",  "Professor Huebner", "Susan", "Aleksandra", "Joe","Professor Wendholt", "Matthias", "Romy", "Mohammed", "Professor Klauck"};

		System.err.println("--------------------  CANTEEN IS OPENED, WELCOME DEAR VISITORS! -------------------");


		// fuege alle Besucher in die Liste ein + erzeuge-Threads



		for (int i = 0; i < names.length; i++) {
			//for (int i = 1; i <= NUMBER_OF_STUDENTS; i++) {

			Visitor actualVisitor = new Visitor(cashiers);
			actualVisitor.setName(names[i]);
			//actualVisitor.setName("Visitor "+ i);

			people.add(actualVisitor);
			actualVisitor.start(); //starte ein Tread
		}

		// Laufzeit abwarten
		//pure copy from Prof.Huebner code:
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
		}

		// Studenten - Threads stoppen
		//pure copy from Prof.Huebner code:
		for (Visitor current : people) {
			current.interrupt();
		}

		System.err.println("-------------------- CANTEEN IS CLOSED - SEE YOU TOMORROW! -------------------");
	}

}
