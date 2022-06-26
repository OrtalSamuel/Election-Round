package Exceptions;

public class DateValidation extends Exception {
	
	public DateValidation() {
		super("The date was not written properly - please try again!");
	}
}
