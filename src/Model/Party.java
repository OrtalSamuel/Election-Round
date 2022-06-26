package Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;


public class Party implements Serializable{
	
	private String partyName;
	private enum Sides {right, left, center};
	private String side;
	private LocalDate foundationDate;
	private Vector<Candidate>CandidateList;
	
	
	public Party(String partyName, String side, LocalDate foundationDate) {
	
		this.partyName = partyName;
		this.side = side;
		this.foundationDate = foundationDate;
		CandidateList = new Vector<Candidate>();
	}

	

	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}


	
		
	public String getSide() {
		return side;
	}
	public void setSide(String side) {
		this.side = side;
	}

	
	
	

	public LocalDate getFoundationDate() {
		return foundationDate;
	}
	public void setFoundationDate(LocalDate foundationDate) {
		this.foundationDate = foundationDate;
	}

	
	
	public Vector<Candidate> getCandidateList() {
		return CandidateList;
	}
	
	public void setCandidateList(Candidate cand) {
		this.CandidateList.add(cand);
	}
	
	


	public void sortCandidateList() {

		 Collections.sort(CandidateList, new SortByPraimerisNumber());
	}
	
	


	@Override
	public String toString() {
			
		StringBuffer stringToPrint = new StringBuffer();
			
		stringToPrint.append("Party: party name: " + partyName + ", side: " + side + ", foundationDate: " + foundationDate);
		
		for (int i = 0; i < CandidateList.size(); i++) {
			stringToPrint.append("\n" + CandidateList.get(i));
		}

		return stringToPrint.toString();


	
	
	}
}
