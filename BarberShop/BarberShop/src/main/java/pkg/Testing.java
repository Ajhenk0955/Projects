
public class Testing {
	public static void main(String args[]){
		BarberShop barberShop = new BarberShop();
		
		for(int i = 0; i<10; i++){
			Customer customer = new Customer(i, barberShop);
			new Thread(customer).start();
		}
	}
}
