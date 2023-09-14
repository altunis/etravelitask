package entity;
import java.util.HashMap;

import constants.*;

public class RentalInfo implements MovieTypes{
	 static HashMap<String, Movie> movies = new HashMap();
	 static {
		 movies.put("F001", new Movie("You've Got Mail",REGULAR));
		 movies.put("F002", new Movie("Matrix",REGULAR));
		 movies.put("F003", new Movie("Cars", CHILDRENS));
		 movies.put("F004", new Movie("Fast & Furious X", NEW));
	 }
	 
	    
  public RentalInfo() {
	  
  }

  public String statement(Customer customer) {
   
    double totalAmount = 0;
    int frequentEnterPoints = 0;
    String result = "Rental Record for " + customer.getName() + System.lineSeparator();
    for (MovieRental r : customer.getRentals()) {
      double thisAmount = 0;
      
      if (!movies.containsKey(r.getMovieId())) {
    	  System.out.println(String.format("The movie with code %s does not exist", r.getMovieId()));
    	  continue;
      }
      String movieCode = movies.get(r.getMovieId()).getCode();
      switch (movieCode) {
      	case REGULAR:
    	  thisAmount = 2;
          if (r.getDays() > MinimumDayToFreeRental.REGULAR_MOVIE) {
            thisAmount = ((r.getDays() - MinimumDayToFreeRental.REGULAR_MOVIE) * DailyPrice.REGULAR_MOVIE) + thisAmount;
          }
          break;
      	case NEW:
      		thisAmount = r.getDays() * DailyPrice.NEW_MOVIE;
      		break;
      	case CHILDRENS:
      		thisAmount = 1.5;
            if (r.getDays() > MinimumDayToFreeRental.CHILDRENS_MOVIE) {
              thisAmount = ((r.getDays() - MinimumDayToFreeRental.CHILDRENS_MOVIE) * DailyPrice.CHILDRENS_MOVIE) + thisAmount;
            }
            break;
        default:
        	System.out.println(String.format("Movie with code %s could not be found", movies.get(r.getMovieId()).getCode()));
      }
    	  
      //add frequent bonus points
      frequentEnterPoints++;
      // add bonus for a two day new release rental
      if (isMovieNew(movies.get(r.getMovieId()).getCode()) && r.getDays() > 2) frequentEnterPoints++;
      
      //print figures for this rental
      result += "\t" + movies.get(r.getMovieId()).getTitle() + "\t" + thisAmount + System.lineSeparator();
      totalAmount = totalAmount + thisAmount;
    }
    // add footer lines
    result += "Amount owed is " + totalAmount + System.lineSeparator();
    result += "You earned " + frequentEnterPoints + " frequent points" + System.lineSeparator();

    return result;
  }
  
  //Returns true If the movie is new
  public static boolean isMovieNew (String movieCode) {
	  return movieCode.equals(NEW);
  }
}
