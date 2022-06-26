package Exceptions;

public class ValidBirthYear extends Exception{
	
	public ValidBirthYear() {
		super("Your input is Not in the valid range - please try again");
	}

}
