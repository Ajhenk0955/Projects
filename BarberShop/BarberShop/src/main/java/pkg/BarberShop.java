import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BarberShop {
	Lock lock;
	Condition barberReady;
	boolean barberSleeping;
	int count, capacity;

	// constructor
	public BarberShop() {
		// initializing
		lock = new ReentrantLock();
		barberReady = lock.newCondition();
		barberSleeping = true;
		// three chairs and 1 for barber
		capacity = 3 + 1;
		count = 0;

	}

	// return false if the barber shop is full when customer i tried to enter;
	// otherwise true
	boolean enter(int id) throws InterruptedException {
		lock.lock();

		// return false because chairs are filled
		if (count == capacity) {
			System.out.println("Customer " + id + " leaves without haircut.");
			lock.unlock();

			return false;
		}

		// number of chairs used is increased
		++count;

		if (barberSleeping) {
			barberSleeping = false;
			System.out.println("Customer " + id + " wakes the barber and gets a haircut.");
			lock.unlock();

			return true;
		}

		// wait for the barber
		System.out.println("Customer " + id + " sits down and waits.");
		barberReady.await();
		System.out.println("Customer " + id + " gets a haircut.");
		lock.unlock();

		return true;

	}

	void leave(int id) {

		lock.lock();
		// barber has finished and will start the next haircut
		barberReady.signal();

		// chairs is 1 less
		--count;
		System.out.println("Customer " + id + " leaves after a haircut.");
		if (count == 0) {
			barberSleeping = true;
			System.out.println("Barber goes to sleep");
		}
		lock.unlock();

	}

}
