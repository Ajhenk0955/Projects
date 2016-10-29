import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Banker implements Runnable {

	private List<Integer> threads = new ArrayList<Integer>();
	private List<int[]> requests = new ArrayList<int[]>();

	private int[] available; // vector of length m
	private int[][] need;
	private int[][] allocation; // n x m matrix

	private Semaphore requestL, mutex, notify;

	// innitialization
	public Banker(int[][] need, int[] available, int[][] allocation) {

		this.need = need;
		this.available = available;
		this.allocation = allocation;

		// semaphores
		requestL = new Semaphore(0);
		mutex = new Semaphore(1);
		notify = new Semaphore(0);

		System.out.println("Banker is running...");
	}

	public void run() {
		while (true) {

			// wait while no request or no safe request
			try {
				requestL.acquire();
				mutex.acquire();

			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// wake and evaluate when receive new request or user thread
			// completes
			// checks if requests is empty, if not continues
			if (!threads.isEmpty()) {
				for (int i = 0; i < threads.size(); i++) {
					int[] request = requests.get(i);
					int userID = threads.get(i);

					// need to check for safety
					if (safety(request, allocation[userID], need[userID])) {

						// update matrix
						Utility.subtract(available, request);
						Utility.add(allocation[userID], request);

						threads.remove(i);
						requests.remove(i);

						// notify waiting threads
						notify.release();
						break;

					}
				}
			}
			mutex.release();
		}
	}

	// adds request and current allocated amount
	// if free resources is greater than the needed amount to finish
	// then grant request
	private boolean safety(int[] request2, int[] allocation2, int[] need2) {
		if (Utility.isLessThanOrEqualTo(request2, available)) {
			int[] tempNeed = need2.clone();
			Utility.subtract(tempNeed, allocation2);
			return Utility.isLessThanOrEqualTo(tempNeed, available);

		}
		return false;
	}

	/*
	 * request resources userNumber request
	 */
	public boolean requestResources(Integer customerNumber, int[] request) throws InterruptedException {

		mutex.acquire();

		int[] temp = allocation[customerNumber].clone();

		// puts new request in
		threads.add(customerNumber);
		requests.add(request);

		System.out.print("Thread " + customerNumber + " asks for ");
		Utility.printArray(request);

		// releases semaphore and notifies banker
		requestL.release();
		mutex.release();

		// checks and waits for a change in allocation
		while (true) {
			notify.acquire();
			mutex.acquire();
			if (!threads.contains(customerNumber)) {
				mutex.release();
				break;
			}
			notify.release();
			mutex.release();
		}
		System.out.print("Thread " + customerNumber + " got ");
		Utility.printArray(request);

		return true;

	}

	/*
	 * Release Resources userNumber release
	 */
	public void releaseResources(int customerNumber, int[] release) throws InterruptedException {
		// process returns resources

		mutex.acquire();

		// adds resources to available and clears out the alloaction
		Utility.add(available, release);
		Utility.zeroOut(allocation[customerNumber]);

		// notify banker
		requestL.release();
		mutex.release();
	}

	public static void main(String args[]) throws InterruptedException {
		int numberOfResources = 5;
		int numberOfUsers = 5;
		// Add a user userNumber maximumDemand

		int[][] need = new int[numberOfUsers][numberOfResources];
		int[][] allocation = new int[numberOfUsers][numberOfResources];
		int[] maximum = new int[numberOfResources];
		int[] available = new int[numberOfResources];
		for (int i = 0; i < numberOfResources; i++) {
			maximum[i] = 10;
			available[i] = 10;
		}

		for (int i = 0; i < numberOfUsers; i++) {
			Utility.randomize(need[i], maximum);
		}

		// start bank
		Banker bank = new Banker(need, available, allocation);
		new Thread(bank).start();
		// start users
		for (int i = 0; i < numberOfUsers; i++) {
			User user = new User(i, need[i], allocation[i], bank);
			System.out.print("Thread " + i + " begins to run and needs ");
			Utility.printArray(need[i]);
			new Thread(user).start();
		}
		System.out.println();

	}
}
