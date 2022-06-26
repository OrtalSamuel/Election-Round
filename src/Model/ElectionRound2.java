package Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Vector;

import Exceptions.CandidateCanNotBeSoldier;
import Exceptions.CheckId;
import Exceptions.CreatePollStationFirst;
import Exceptions.DateValidation;
import Exceptions.InvalidInput;
import Exceptions.ThePartyDoesntExists;
import Exceptions.ValidBirthYear;
import Exceptions.WrongPrimerizeNumber;
import Exceptions.numberOfPraimerizeIsAlreadyoccupied;

public class ElectionRound2 implements Serializable{

	LocalDate currentYear = LocalDate.now();
	
	int count = 0;
	private int round;
	private LocalDate date;
	public  Vector<PollStation>allPollStation;
	private  Vector<PollStation<Citizen>>coronaStation;
	private  Vector<PollStation<Citizen>>healthyStation;
	private  Vector<PollStation<Soldier>>soldierStation;
	private  Vector<PollStation<Soldier>>sickSoldierStation;
	
	public  Vector<Party>allParties;
	public  Set<Citizen>allCitizen;
	private Set<Candidate>allCandidate;
	
	
	public ElectionRound2(int round, LocalDate date) {
		
		this.date = date;
		this.round = round;
		
		allCitizen = new Set<>();
		allParties = new Vector<>();
		allCandidate = new Set<>();
		
		coronaStation = new Vector<>();
		healthyStation = new Vector<>();
		soldierStation = new Vector<>();
		sickSoldierStation = new Vector<>();
		allPollStation = new Vector<>(); 
	}
	
	
	public void setHardCode() {
		LocalDate d = null;
		
		//citizen
		allCitizen.add(new Citizen("eden", "192645398", 1998, false, 3));
		allCitizen.add(new Citizen("ido", "313530396", 1995, false, 0));
		allCitizen.add(new Citizen("omer","524364758", 2005, false, 3));
		allCitizen.add(new Citizen("yosi", "524362342", 2001, false, 5));
	
		//Candidate
		allCandidate.add(new Candidate("dani", "918354762", 1962, false, "licud",  3, 0));
		allCandidate.add(new Candidate("yoav", "177265444", 1977, false, "licud",  5, 0));
//		allCandidate.add(new Candidate("amos", "837364523", 1972, false, "havoda", 1, 4));
//		allCandidate.add(new Candidate("noa", "837932432",  1975, false, "havoda", 7, 0));
//		allCandidate.add(new Candidate("michal","935263744", 1962, false, "meretz", 2, 0));
//		allCandidate.add(new Candidate("ram", "493593644", 1962, false, "meretz", 4, 2));


		//Parties-!-
		allParties.add(new Party("licud", "right", d.of(1973, 9, 13))); 
		allParties.add(new Party("meretz", "left", d.of(1992, 3, 9)));
		allParties.add(new Party("havoda", "center", d.of(2019, 2, 21)));
		

		//PollStation -!-
		healthyStation.add(new PollStation<Citizen>("carmel", "healthy"));
		coronaStation.add(new PollStation<Citizen>("oren", "corona"));
		
		addCandidateHardCode();
		divideCitizensToPollingStations();
		pollStationUnion();
	}
	

	public void addCandidateHardCode() {
		for (int i = 0; i < allCandidate.size(); i++) {
			allCitizen.add(allCandidate.get(i));
		}
		
		for (int i = 0; i < allParties.size(); i++) {
			for (int j = 0; j < allCandidate.size(); j++) {
				if(allParties.get(i).getPartyName().equals(allCandidate.get(j).belongToParty))
					allParties.get(i).getCandidateList().add(allCandidate.get(j));			
			}			
		}
		
		for (int i = 0; i < allParties.size(); i++) {
			sortCandidateByPrimerizeNumber(allParties.get(i));
		}

	}
	
	public void sortCandidateByPrimerizeNumber(Party p) {
		p.sortCandidateList();
	}
	
	
	
	public void newCandidateToParties(Candidate c) {
		
		for (int i = 0; i < allParties.size(); i++) {
			if(allParties.get(i).getPartyName().equals(c.belongToParty))
				allParties.get(i).getCandidateList().add(c);					
		}
	}
	
	
	
	
	public void setPollStation(String choose, String address) {
		
		if(choose.matches("c")) {	
			choose ="corona";
			addPollStation(choose, address);
		}
		if(choose.matches("h")) {
			choose = "healthy";
			addPollStation(choose, address);
		}	
		if(choose.matches("s")) {
			choose = "soldier";
			addPollStation(choose, address);
		}	
		if(choose.matches("t")) {
			choose = "sickSoldier";
			addPollStation(choose, address);
		}
	}
	
	

	
	public void divideCitizensToPollingStations() { 
		
		for (int i = count; i < allCitizen.size(); i++) {	
			
			if(allCitizen.get(i).getSickDays() == 0 && allCitizen.get(i).getClass().getSimpleName().equals("Citizen") || allCitizen.get(i).getSickDays() == 0 && allCitizen.get(i).getClass().getSimpleName().equals("Candidate")) {
				healthyStation.get(0).setAllowedCitizen(allCitizen.get(i));
				count++;
			}
			if(allCitizen.get(i).getSickDays() != 0 && allCitizen.get(i).getClass().getSimpleName().equals("Citizen") || allCitizen.get(i).getSickDays() != 0 && allCitizen.get(i).getClass().getSimpleName().equals("Candidate")) {
				coronaStation.get(0).setAllowedCitizen(allCitizen.get(i));
				count++;
			}
			if(allCitizen.get(i).getSickDays() == 0 && allCitizen.get(i).getClass().getSimpleName().equals("Soldier")) {
				soldierStation.get(0).setAllowedCitizen(allCitizen.get(i));
				count++;
			}
			if(allCitizen.get(i).getSickDays() != 0 &&  allCitizen.get(i).getClass().getSimpleName().equals("Soldier")) {
				sickSoldierStation.get(0).setAllowedCitizen(allCitizen.get(i));	
				count++;
			}	
		}		
	}
	
	
	public void pollStationUnion() {
		
		allPollStation.addAll(coronaStation);
		allPollStation.addAll(healthyStation);
		allPollStation.addAll(soldierStation);
		allPollStation.addAll(sickSoldierStation);
	}
	
	
	public void addPollStation(String choose, String address){
		
		if(choose.matches("corona")) {
			coronaStation.add(new PollStation<Citizen>(address, "corona"));
			allPollStation.addAll(coronaStation);
		}
		else if(choose.matches("healthy")) {
			healthyStation.add(new PollStation<Citizen>(address, "healthy"));
			allPollStation.addAll(healthyStation);
		}
		else if(choose.matches("soldier")) {
			soldierStation.add(new PollStation<Soldier>(address, "soldier"));
			allPollStation.addAll(soldierStation);
		}
		else if(choose.matches("sickSoldier")) {
			sickSoldierStation.add(new PollStation<Soldier>(address, "sickSoldier"));
			allPollStation.addAll(sickSoldierStation);	
		}
	}
	

	
	
	public boolean isSoldier(int birthYear) {
		
		if(currentYear.getYear() - birthYear >= 18 && currentYear.getYear() - birthYear <= 21)
			return true;
		
		return false;			
	}
	
	
	
	
	public Soldier createSoldier(String name, String id, int birthYear, boolean isVoting, String isWeapons) {
		boolean weapons = false;

		if(isWeapons.equals("y"))
			weapons = true;
			Soldier s = new Soldier(name, id, birthYear, isVoting, weapons, 0);
			allCitizen.add(s);
			return s;	
	}
	
	
	
	public Citizen createCitizen(String name, String id, int birthYear, boolean isVoting) {
		Citizen c = new Citizen(name, id, birthYear, isVoting, 0);
		allCitizen.add(c);
		return c;	
	}
	
	
	
	public void createCandidate(String name, String id, int birthYear, boolean isVoting, String belongToParty, int primariesNumber, int days, String isSuit) {
		Citizen cand = new Candidate(name, id, birthYear, isVoting, belongToParty,  primariesNumber, 0);
		allCitizen.add((Candidate)cand);
		allCandidate.add((Candidate) cand);
		updateSicknessForSoldierAndCitizen(days, isSuit);
		newCandidateToParties((Candidate) cand);
	}
	
	
	public void updateSicknessForSoldierAndCitizen(int days, String isSuit) {

		if(isSuit.matches("n"))
			remove();
	
		else { 
			allCitizen.get(allCitizen.size()-1).setSickDays(days);
			divideCitizensToPollingStations();
		}
	}
	
	
	public void remove() {
		allCitizen.remove(allCitizen.size()-1);	
	}
	
	
	
	public void createParty(String partyName, String side, LocalDate FoundationDate) {
		
		Party p = new Party(partyName, side ,FoundationDate);
		allParties.add(p);
		sortCandidateByPrimerizeNumber(p);
	}
	
	
	
	public String setSide(String side) {
		
		if(side.matches("l")) {
			side = "left";
		}
		if(side.matches("c")) {
			side = "center";
		}
		if(side.equals("r")) {
			side = "right";
		}
		return side;
	}
	
	
	
	public void updateVoteToPollStation(String chooseParty, int days, String className) {
		
		if(days != 0 && className.equals("Citizen") || days != 0 && className.equals("Candidate"))
			coronaStation.get(0).votesStorage(chooseParty);
		
		else if(days == 0 && className.equals("Citizen") || days == 0 && className.equals("Candidate"))
			healthyStation.get(0).votesStorage(chooseParty);
		
		else if(days != 0 && className.equals("Soldier"))
			sickSoldierStation.get(0).votesStorage(chooseParty);
		
		else if(days == 0 && className.equals("Soldier"))
			soldierStation.get(0).votesStorage(chooseParty);
	}
	
	
	
	public void partiesVotesByPollStations() {
		
		for (int i = 0; i < allPollStation.size(); i++) {
			System.out.println();
			System.out.println(allPollStation.get(i).getType() + " staion: ");
			allPollStation.get(i).counterTheVotesByParties(allParties);	
		}	
	}
	
	
	
	public void sumPartiesVotes() {
		
		int generalCounter = 0;
		for (int i = 0; i < allParties.size(); i++) {
			for (int j = 0; j < allPollStation.size(); j++) {
				generalCounter += allPollStation.get(j).sumAllVotes(allParties.get(i).getPartyName());
			}
			System.out.println(allParties.get(i).getPartyName() + ": " + generalCounter);
			generalCounter = 0;
		}
	}
	
	
	
	public void percentOfVotersInEachPollStation() {
		
		for (int i = 0; i < allPollStation.size(); i++) {
			System.out.print(allPollStation.get(i).getType() + " station: ");
			System.out.print(allPollStation.get(i).votersPercent() + "%\n");
			
		}
		
	}
	
	
	
	public String toString(int num) {
		
		StringBuffer stringToPrint = new StringBuffer();
		
		if(num == 5) {
			if(allPollStation != null)	
				for (int i = 0; i < allPollStation.size(); i++) {	
				
					if(allPollStation.get(i).getType().name().equals("corona"))
						stringToPrint.append(coronaStation.toString());
					
					if(allPollStation.get(i).getType().name().equals("healthy"))
						stringToPrint.append(healthyStation.toString());
					
					if(allPollStation.get(i).getType().name().equals("sickSoldier"))
						stringToPrint.append(sickSoldierStation.toString());	
					
					
					if(allPollStation.get(i).getType().name().equals("soldier"))
						stringToPrint.append(soldierStation.toString());	
					
					stringToPrint.append("\n\n");	
				}
			
			return stringToPrint.toString();
		}
	
		
		if(num == 6) {
			if(allCitizen != null)	
				for (int i = 0; i < allCitizen.size(); i++) 
					stringToPrint.append(allCitizen.get(i).toString() + "\n");
			
			return stringToPrint.toString();
			}
	
		if(num == 7) {
			if(allParties != null)	
				for (int i = 0; i < allParties.size(); i++) 
					stringToPrint.append(allParties.get(i).toString() + "\n\n");
				
			}
			return stringToPrint.toString();
	}
	
	
	
	
//Exceptions-----------------------------------------------------------------------------
	public boolean InputValition(String choose) throws InvalidInput{
		
		if(!choose.matches("c|s|h|t")) 
			throw new InvalidInput();	
	
		else
			return true;	
}
	
	
	
	
	public boolean idValidtion(String id) throws CheckId { 
		
		if(id.length() != 9) 
			throw new CheckId();

		return true;
	}
	
	
	
	public boolean checkVotingValitation(int birthYear, int num) throws Exceptions.ageValidation, ValidBirthYear, CandidateCanNotBeSoldier {
		
		if(currentYear.getYear() - birthYear < 18)
				throw new Exceptions.ageValidation();
		
		else if(currentYear.getYear() - birthYear > 120)
				throw new Exceptions.ValidBirthYear();
		
		else if(isSoldier(birthYear) && num == 4)
				throw new Exceptions.CandidateCanNotBeSoldier();
			
		return true;
	}
	
	
	
	public boolean yesOrNot(String temp) throws InvalidInput {
		
		if(!temp.matches("y|n"))
			throw new Exceptions.InvalidInput();
		
		return true;
	}
	
	
	
	public boolean daysValidtion(int days) throws InvalidInput {
		if(days < 0)
			throw new Exceptions.InvalidInput();
		
		return true;
	}
	
	
	
	public boolean partyIsExists(String partyName) throws ThePartyDoesntExists {
		
		for (int i = 0; i < allParties.size(); i++) {	
			if(allParties.get(i).getPartyName().equals(partyName)) 
				return true;
			
			else	
				continue;		
		}
		throw new Exceptions.ThePartyDoesntExists();
	}
	
	
	
	public boolean checkPrimerizeNumber(int num, String partyName) throws WrongPrimerizeNumber, numberOfPraimerizeIsAlreadyoccupied {
		if(num <= 30 && num >= 1) {
			for (int i = 0; i < allParties.size(); i++) {
				if(allParties.get(i).getPartyName().equals(partyName)) {
					for (int j = 0; j < allParties.get(i).getCandidateList().size(); j++) {
						if(((Candidate) allParties.get(j).getCandidateList().get(j)).getPrimerizeNumber() == num)
							throw new Exceptions.numberOfPraimerizeIsAlreadyoccupied();
					}
				}	
			}
		}
		else 
			throw new Exceptions.WrongPrimerizeNumber();
		
		return true;
	}
	
	
	
	public boolean sideValitionInput(String side) throws InvalidInput {
		
		if(!side.matches("l|r|c")) 
			throw new InvalidInput();		
		
		return true;
}
	
	
	
	public boolean dateValidtion(int year, int month, int day) throws DateValidation {
		if(year >= 1948 && year <= currentYear.getYear())
			if(month <= 12 && month > 0)
				if(day > 0 && day <= 31)
					return true;
		
		throw new Exceptions.DateValidation();
	}
	
	
	public boolean checkIfPollStatinExistsForTheObject() throws CreatePollStationFirst {
		
		int value = 0;
		for (int i = 0; i < allPollStation.size(); i++) {
			
			if(allPollStation.get(i).getType().name().equals("soldier")) { 
				value = 1;
				break;
			}
			else if(allPollStation.get(i).getType().name().equals("sickSoldier")) { 
				value = 1;
				break;
			}	
		}
		
		if(value == 1)
			return true;
		else
			throw new Exceptions.CreatePollStationFirst();
		
	}
}
