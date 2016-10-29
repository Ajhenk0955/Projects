import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Garden {
	Lock lock;
	Condition dig, plant, fill;
	int dug, planted, filled;

	// constructor
	public Garden() {
		// initializing
		lock = new ReentrantLock();
		dig = lock.newCondition();
		plant = lock.newCondition();
		fill = lock.newCondition();
		dug = planted = filled = 0;
	}

	// waits for 5 or less holes filled
	public void waitToDig() throws InterruptedException {
		lock.lock();
		// wait to dig
		// wait for unfilled holes to be less than 5
		if (dug - filled >= 5) {
			System.out.println("Jordan is waiting to dig a hole");
			dig.await();
		}
		lock.unlock();

	}

	// digs a hole
	public void dig() {
		lock.lock();
		++dug;
		// if holes without plants, signal to plant
		if (dug > planted)
			plant.signal();
		System.out.println("Jordan dug a hole.	     "+dug);
		lock.unlock();

	}

	public void waitToPlant() throws InterruptedException {
		lock.lock();
		// waiting for empty hole
		if (dug == planted) {
			System.out.println("Charles is waiting to plant a hole");
			plant.await();
		}
		lock.unlock();

	}

	public void plant() {
		lock.lock();
		++planted;
		// if planted hole needs to be filled, signal to fill
		if (planted > filled)
			fill.signal();
		System.out.println("Charles planted a hole.	          "+ planted);
		lock.unlock();

	}

	public void waitToFill() throws InterruptedException {
		lock.lock();
		// waits if all holes are empty or filled
		if (planted == filled) {
			System.out.println("Tracy is waiting to fill a hole");
			fill.await();
		}
		lock.unlock();
	}

	public void fill() {
		lock.lock();
		// hole is filled
		++filled;
		// if unfilled holes are less than 5, signal to dig
		if (dug - filled < 5)
			dig.signal();
		System.out.println("Tracy filled a hole.	                    " + filled);
		lock.unlock();

	}

	public static void main(String args[]) throws InterruptedException {
		ExecutorService threadExecutor = Executors.newCachedThreadPool();
		Garden garden = new Garden();
		threadExecutor.execute(new Jordan(garden));
		threadExecutor.execute(new Charles(garden));
		threadExecutor.execute(new Tracy(garden));
		threadExecutor.shutdown();
		threadExecutor.awaitTermination(30, TimeUnit.SECONDS);
	}
}
