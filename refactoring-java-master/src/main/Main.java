package main;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import entity.*;

public class Main {

  public static void main(String[] args) {
    String expected = "Rental Record for C. U. Stomer\n\tYou've Got Mail\t3.5\n\tMatrix\t2.0\nAmount owed is 5.5\nYou earned 2 frequent points\n";
    Customer testCustomer = new Customer("C. U. Stomer", Arrays.asList(new MovieRental("F001", 3), new MovieRental("F002", 1)));
    SlipPrinter slipPrinter = new SlipPrinter(testCustomer);
    if (!slipPrinter.getSlipper().equals(expected)) {
      throw new AssertionError("Expected: " + System.lineSeparator() + String.format(expected) + System.lineSeparator() + System.lineSeparator() + "Got: " + System.lineSeparator() + slipPrinter.getSlipper());
    } else {
    	System.out.println("The test case is successfull");
    }
    
    Scanner scannerObj = new Scanner(System.in);
    System.out.println("Enter Customer name");
    String customerName = scannerObj.nextLine();
    
    List<MovieRental> movies = new ArrayList<MovieRental>();
    String userWantsToContinueStr = "Y";
    while (userWantsToContinueStr.equals("Y")) {
    	System.out.println("Enter the movie code");
    	String movieName = scannerObj.nextLine();
    	System.out.println("Enter the number of days");
    	int numOfDayToRent = Integer.valueOf(scannerObj.nextLine());
    	movies.add(new MovieRental(movieName,numOfDayToRent));
    	
    	System.out.println("If you want to add more movies click \"Y\", otherwise click any other button");
    	userWantsToContinueStr = scannerObj.nextLine();
    }
    
    Customer customer = new Customer(customerName,movies);
    slipPrinter = new SlipPrinter(customer);
    System.out.println(slipPrinter.getSlipper());
    scannerObj.close();
    

  }
}
