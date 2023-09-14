package entity;

public class SlipPrinter {
	Customer customer;
	
	public SlipPrinter(Customer customer) {
		this.customer = customer;
	}
	
	public String getSlipper() {
		RentalInfo rentalInfo = new RentalInfo();
		return rentalInfo.statement(customer);
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	

}
