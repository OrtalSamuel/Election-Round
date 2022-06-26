package Model;
import java.io.Serializable;
import java.util.Vector;


public class PollStation <T> implements Serializable {
	
	public enum stationType{corona, healthy, sickSoldier, soldier};
	private stationType type;
	private int serialNumber;
	private static int count;
	private String address;
	private	Vector<Citizen>allowedCitizen;

	
	Vector<String> votes = new Vector<String>();
	
	public PollStation(String address, String type) {
		
		this.address = address;
		this.type = stationType.valueOf(type);
		serialNumber = count++;
		allowedCitizen = new Vector<Citizen>();	
		
	}



	public  void votesStorage(String partyVote) {

		votes.add(partyVote);	
	}

	
	public void counterTheVotesByParties(Vector<Party>allParties) {
		
		int counter = 0;
		for (int i = 0; i < allParties.size(); i++) {
			for (int j = 0; j < votes.size(); j++) {
				if(allParties.get(i).getPartyName().equals(votes.get(j))) 
					counter++;	
			}
		
			System.out.println(allParties.get(i).getPartyName() + ":" + counter);
			counter = 0;
		}
	}
	

	public int sumAllVotes(String partyName) {
		
		int counter = 0;
		for (int i = 0; i < votes.size(); i++) {
			if(votes.get(i).equals(partyName))
				counter++;
		}
		return counter;
	}
	
	
	public int votersPercent() {	
		

		int counter = 0;
		for (int i = 0; i < allowedCitizen.size(); i++) {
			if(allowedCitizen.get(i).isVoting())
				counter++;
		}
			return ((counter * 100) / allowedCitizen.size());	
	}
		

	
	
	public stationType getType() {
		return type;
	}
	public void setType(stationType type) {
		this.type = type;
	}



	public int getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}


	
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	
	

	public Vector<T> getAllowedCitizen() {
		return (Vector<T>) allowedCitizen;
	}
	
	public void setAllowedCitizen(Citizen allowedCitizen) {
		this.allowedCitizen.add(allowedCitizen);
	}



	@Override
	public String toString() {
			
		StringBuffer stringToPrint = new StringBuffer();
			
		stringToPrint.append("type: " + type + ", serial number: " + serialNumber + ", address: " + address);
		
		for (int i = 0; i < allowedCitizen.size(); i++) {
			stringToPrint.append("\n" + allowedCitizen.get(i));
		}

		return stringToPrint.toString();
			

	
	
	

	

	
	
	
	
		}
	
}
