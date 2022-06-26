package Model;


public class Candidate extends Citizen {

	protected int primariesNumber;
	protected String belongToParty;

	public Candidate(String name, String ID, int year, boolean isVoting, String belongToParty, int primariesNumber, int days) {
		super(name, ID, year, isVoting, days);
		this.belongToParty = belongToParty;
		this.primariesNumber = primariesNumber;
	}

	
	
	public int getPrimerizeNumber() {
		return primariesNumber;
	}

	public void getPrimerizeNumber(int placeInParty) {
		this.primariesNumber = placeInParty;
	}

	
	
	public String getBelongToParty() {
		return belongToParty;
	}

	public void setBelongToParty(String belongToParty) {
		this.belongToParty = belongToParty;
	}
	
	
	
	@Override
	public boolean equals(Object candidate) {
		return (super.equals(candidate));
	}



	@Override
	public String toString() {
		return super.toString() + ", primariesNumber: " + primariesNumber + ", belongToParty: " + belongToParty;
	}

}
