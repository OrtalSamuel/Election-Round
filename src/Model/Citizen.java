package Model;

public class Citizen extends Sick {
	
	private String name;
	private String Id;
	private int birthYear;
	private boolean isVoting;
	
	
	
	public Citizen(String name, String Id, int year, boolean isVoting, int days) {
		super(days);
		this.name = name;
		this.Id = Id;
		this.birthYear = year;
		this.isVoting = false;	
	}

	
	public String getName() {
		return this.name;
	}
	
	
	

	public boolean isVoting() {
		return isVoting;
	}
	public void setIsVoting(boolean answer) {
		this.isVoting = answer;	
	}
	
	

	public String getID() {
		return this.Id;
	}

	public int getYear() {
		return this.birthYear;
	}
	
	
	
	
	
	


	@Override
	public boolean equals(Object obj) {
		Citizen other = (Citizen) obj;
		if (this.Id != other.Id) {
			return false;
		}
		return true;
	}


	@Override
	public String toString() {
		return "name: " + name + ", ID: " + Id + ", birthYear: " + birthYear;
	}
	
	


	
	


	
	


}






	
	



