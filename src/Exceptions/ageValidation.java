package Exceptions;

public class ageValidation extends Exception{
	
	public ageValidation() {
		super("This person is less than age 18 and can't vote - please try again!");
	}
	
}
