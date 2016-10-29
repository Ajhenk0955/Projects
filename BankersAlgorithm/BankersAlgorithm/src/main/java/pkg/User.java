import java.util.Random;

public class User implements Runnable {

	Banker bank;
	int[] recources;
	int ID;
	int[] need;
	int[] request;
	boolean success = false;

	Random rand = new Random();

	public User(int i, int[] need, int[] recources, Banker bank) {
		ID = i;
		this.need = need.clone();
		this.bank = bank;
		this.recources = recources.clone();
		request = need.clone();
	}

	@Override
	public void run() {
		while (!success) {
			// random time between 0 & 1
			try {
				Thread.sleep(rand.nextInt(1000));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (Utility.isLessThanOrEqualTo(need, recources)) {
				System.out.println("****** Thread " + ID + " completes. ******");
				try {
					bank.releaseResources(ID, need);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				success = true;
			} else {
				try {
					genRequest();
					bank.requestResources(ID, request);
					Utility.add(recources, request);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
	}

	// generate the request, prevents zeroed arrays
	private void genRequest() {
		int[] max = need.clone();
		Utility.subtract(max, recources);
		Utility.randomize(request, max);
		while (Utility.isZero(request))
			Utility.randomize(request, max);
	}

}
