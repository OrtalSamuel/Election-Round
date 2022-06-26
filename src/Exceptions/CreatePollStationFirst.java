package Exceptions;

public class CreatePollStationFirst extends Exception {

	public CreatePollStationFirst() {
		super("The new voter didn't add to the list! - Create poll station for this type of voter first!");
	}
}
