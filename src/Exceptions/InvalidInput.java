package Exceptions;

public class InvalidInput extends Exception{
	
	public InvalidInput() {
		super("This option is not valid! - please try again!");
	}
}
