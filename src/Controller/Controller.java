package Controller;

import Model.ElectionRound;
import View.Main;

public class Controller {
	
	private Main main;
	private ElectionRound election;
	
	public Controller(Main main, ElectionRound election) {
		
		this.main = main;
		this.election = election;
		
	}
	
}
