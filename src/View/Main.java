package View;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

import Exceptions.CandidateCanNotBeSoldier;
import Exceptions.CheckId;
import Exceptions.CreatePollStationFirst;
import Exceptions.DateValidation;
import Exceptions.InvalidInput;
import Exceptions.ThePartyDoesntExists;
import Exceptions.ValidBirthYear;
import Exceptions.WrongPrimerizeNumber;
import Exceptions.YesOrNot;
import Exceptions.ageValidation;
import Exceptions.numberOfPraimerizeIsAlreadyoccupied;
import Model.ElectionRound;
import Model.ElectionRound2;
import Model.ElectionsUI;
import Model.Party;
import Model.Soldier;


public class Main implements ElectionsUI {
	
	static Scanner in = new Scanner(System.in);
	
	static LocalDate date =  LocalDate.of(2020, 12, 2);
	static ElectionRound2 e = new ElectionRound2(1, date); 
	static boolean isValidInput = false;
	static int num, temp = 2;
	
	
	
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		Main m = new Main();
		
		System.out.println("For open previous file press 0 / For start new Elections press 1:");
		int value = in.nextInt();
		
		if(value == 0) {
			System.out.println("Enter file name: ");
			String fileName = in.next();
			try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(fileName))) {
				e = (ElectionRound2)input.readObject();
				input.close();
			}
		}
		
		e.setHardCode();
		
		
		do {
			
		if (value == 1) {
			System.out.println("Please choose a section: ");
			System.out.println("For additing polling station press 1: ");
			System.out.println("For additing citizen press 2: ");
			System.out.println("For additing party press 3: ");	
			System.out.println("For additing candidate press 4: ");
			System.out.println("For watching all the poll stations press 5: ");
			System.out.println("For watching all the citizens press 6: ");
			System.out.println("For watching all the parties press 7: ");
			System.out.println("For asking citizens if they want to vote press 8: ");
			System.out.println("For watching election results press 9: ");
			System.out.println("For Exit Press: 0");
			num = in.nextInt();	
			
			if(num ==  4) {
				temp = num;
				num = 2;
			}	
		}
	
		else 
			num = 0;
		
		
		switch(num) {
		
		case 1:
			
			m.createPollStation();

			break;
						
		case 2:

			m.createCitizenType();
			
			break;
				

		case 3: 
			
			m.createParty();

			break;


			
		case 5:

			m.printPollStatins();
	
			break;
	
		
		
		case 6:

			m.printCitizensList();
			
			break;
		
		
		case 7:

			m.printPartiesList();	
			
			break;
		
			
		case 8:
			
			m.setCitizensChoose();

			break;
			

			
		case 9:
			
			m.electionResults();
			
			break;
		
			
		case 0:
//			System.out.print("name your file and save: ");
//			fileName = in.next();
//			ObjectOutputStream outFile = new ObjectOutputStream(new FileOutputStream(fileName));
//			outFile.writeObject(e);
//			outFile.close();
			
			System.out.println("Bye bye!");
			break;
			
			
		   default:

               System.out.println("Invalid choice.");
		}
			
		System.out.println("\n");
		
	}while (num != 0);

	}

	
	
	
	
	
	public void createPollStation() {
		
		System.out.println("Add Polling Station: ");

		System.out.print("Enter Adress: \n");
		String address = in.next();

		isValidInput = false;
		while(!isValidInput){
			System.out.println("Choose Type of Polling station "
			+ "- healthy (h) / corona (c) / soldier (s) / corona soldier (t) ");	
			String choose = in.next();
			
			try {
				if(e.InputValition(choose) == true);
					isValidInput = true;
				
			}catch(InvalidInput e1) {
				System.out.println(e1.getMessage());		
				
			}catch(InputMismatchException e1) {
				System.out.println("ERROR");		
			}
			e.setPollStation(choose, address);	
		}
		isValidInput = false;
	}
	
	
	
	
	
	public void createCitizenType() {
		
		isValidInput = false;
		boolean isVoting = false;
		String name, party = null, isWeapons = null, isSuit = null, id = null;
		int days = 0, birthYear = 0, PrimariesNum = 0, x = 2;
	
		if(temp == 4)
			System.out.println("Add candidate: \n");
		
		else
			System.out.println("add citizen: \n");
		
		System.out.print("Enter name: ");
		name = in.next();
	

		while(!isValidInput){
			System.out.print("Enter id: ");
			id = in.next();	
			
			try {
				if(e.idValidtion(id) == true);
					isValidInput = true;
				
			}catch(CheckId e1) {
				System.out.println(e1.getMessage());
		
				
			}catch(InputMismatchException e1) {
				System.out.println("ERROR");
				
			}	
		}
			
		
		isValidInput = false;
		while(!isValidInput){
			try {
				System.out.print("Enter birth Year: ");
				birthYear = in.nextInt(); 
				
				if(e.checkVotingValitation(birthYear, temp) == true)
					isValidInput = true;
				
			}catch(ValidBirthYear e1) {
				System.out.println(e1.getMessage());
			
				
			}catch(ageValidation e1) {
				System.out.println(e1.getMessage());
		
			
			}catch (CandidateCanNotBeSoldier e1) {
				System.out.println(e1.getMessage());
				
				
			}catch(InputMismatchException e1) {
				System.out.println("ERROR");
			}
		}
	

		
		Object obj = null;
		if(e.isSoldier(birthYear) == true && num == 2) {
			
			isValidInput = false;
			while(!isValidInput){
				try {
					System.out.print("Is there any weapons? - (Y/N) ");
					isWeapons = in.next();
					
					if(e.yesOrNot(isWeapons) == true)
						isValidInput = true;
					
					}catch(InvalidInput e1) {
						System.out.println(e1.getMessage());
			
				
					}catch(InputMismatchException e1) {
						System.out.println("ERROR");
						}
			}
			obj = e.createSoldier(name, id, birthYear, isVoting, isWeapons);	
		}
	
		if(e.isSoldier(birthYear) == false && temp == 2) 
			obj = e.createCitizen(name, id, birthYear, isVoting);
		

			
		
		
		isValidInput = false;
		while(!isValidInput){
			try {
				System.out.print("Sick?  - if YES, Enter how many days he sick, if NOT Enter 0 days ");
				days = in.nextInt();
					
				if(e.daysValidtion(days) == true)
					isValidInput = true;
					
				}catch(InvalidInput e1) {
					System.out.println(e1.getMessage());
					
				
				}catch(InputMismatchException e1) {
					System.out.println("ERROR");
			
				}
			}
		
		if (temp != 4) {
			if(obj.getClass().getSimpleName().equals("Soldier") || obj.getClass().getSimpleName().equals("sickSoldier")) {
			
				isValidInput = false;
				while(!isValidInput){
					try {
						if(e.checkIfPollStatinExistsForTheObject() == true)
							isValidInput = true;
						
						} catch (CreatePollStationFirst e1) {
							System.out.println(e1.getMessage());
							e.remove();
							x = 2;
							break;
								
						} catch(InputMismatchException e1) {
							System.out.println("ERROR");	
						}
					}
				}
			x = 1;
		}
			
			
		if(days != 0 && x != 2) {	
			isValidInput = false;
			while(!isValidInput){
				try {
					System.out.print("Is there a protective suit? - (Y/N) ");
					isSuit = in.next();
						
					if(e.yesOrNot(isSuit) == true)
						isValidInput = true;
						
					}catch(InvalidInput e1) {
						System.out.println(e1.getMessage());
				
					
					}catch(InputMismatchException e1) {
						System.out.println("ERROR");
					}
			}
		}
		else
			isSuit = "y";
			
		if(num == 2)
			e.updateSicknessForSoldierAndCitizen(days, isSuit);
			
				
			
			
		if(temp == 4) {
			
			isValidInput = false;
			while(!isValidInput) {	
				try {
					System.out.print("Candidate Party: ");
					party = in.next();
					
					if(e.partyIsExists(party) == true)
						isValidInput = true;
				
				}catch(ThePartyDoesntExists e1) {
					System.out.println(e1.getMessage());
			
				
				}catch(InputMismatchException e1) {
					System.out.println("ERROR");
				}
			}
		
				
		isValidInput = false;
		while(!isValidInput) {	
			try {
				System.out.print("Primaries number: ");
				PrimariesNum = in.nextInt();
				
				if(e.checkPrimerizeNumber(PrimariesNum, party) == true)
					isValidInput = true;
			
			}catch(WrongPrimerizeNumber e1) {
				System.out.println(e1.getMessage());
				
			}catch(numberOfPraimerizeIsAlreadyoccupied e1) {
				System.out.println(e1.getMessage());	
		
			}catch(InputMismatchException e1) {
				System.out.println("ERROR");
			}
		}
		e.createCandidate(name, id, birthYear, isVoting, party, PrimariesNum, days, isSuit);	
		temp = 0;
		}
	}
	
	

	
	
	
	
	public void createParty() {
		
		LocalDate FoundationDate = null;
		int year = 0, month = 0, day = 0;
		String side = null;
		
		System.out.println("Add a party: \n");
		
		System.out.print("Enter party name: ");
		String partyName = in.next();
		
		
		
		isValidInput = false;
		while(!isValidInput) {	
			try {
				System.out.print("party side:     Left (l) / Center (c) / Right (r) \n");
				side = in.next();
				
				if(e.sideValitionInput(side) == true)
					isValidInput = true;
			
			}catch(InvalidInput e1) {
				System.out.println(e1.getMessage());
		
			
			}catch(InputMismatchException e1) {
				System.out.println("ERROR");
			}
		}
		side = e.setSide(side);
		
		
		System.out.println("Foundation date: \n");

		isValidInput = false;
		while(!isValidInput) {	
			try {
				System.out.print("Enter year: ");
				year = in.nextInt();
				
				System.out.print("Month: ");
				month = in.nextInt();

				System.out.print("Day: ");
				day = in.nextInt();
				
				if(e.dateValidtion(year, month, day) == true)
					isValidInput = true;
					FoundationDate =  LocalDate.of(year, month, day);
				
			}catch(DateValidation e1) {
				System.out.println(e1.getMessage());
		
			
			}catch(InputMismatchException e1) {
				System.out.println("ERROR");
			}
		}
		e.createParty(partyName, side, FoundationDate );
		
	}
	

	
	
	
	public void printPollStatins() {
		
		System.out.println("Poll stations List");
		System.out.println(e.toString(num));
	}
	
	
	
	
	
	public void printCitizensList() {
		
		System.out.println("Citizens list:");
		System.out.println(e.toString(num));	
	}
	
	
	
	
	
	public void printPartiesList() {
		
		System.out.println("Parties list:");
		System.out.println(e.toString(num));
	}
	
	
	
	

	
	public void setCitizensChoose() {
		
		String answer = null, chooseParty = null;
		isValidInput = false;
		
		for (int i = 0; i < e.allCitizen.size(); i++) {
			
			System.out.println("Name: " + e.allCitizen.get(i).getName() + " ,Id: "
			+ e.allCitizen.get(i).getID());
		
		
			while(!isValidInput){
				try {
					System.out.println("Would you like to vote?  -  (Y/N)");
					answer = in.next();
					
					if(e.yesOrNot(answer) == true)
						isValidInput = true;
					
					}catch(InvalidInput e1) {
						System.out.println(e1.getMessage());
			
				
					}catch(InputMismatchException e1) {
						System.out.println("ERROR");
						}
			}
			
			if(answer.matches("n")) {
				isValidInput = false;
				continue;
			}
			else if(answer.matches("y"))
				e.allCitizen.get(i).setIsVoting(true);
			
			
			
			System.out.println("Which party would you like to vote to?\n");
			System.out.println("Parties List: ");
			for (int j = 0; j < e.allParties.size(); j++) {
				System.out.println(e.allParties.get(j).getPartyName());	
			}
			System.out.println();

			isValidInput = false;
			while(!isValidInput) {	
				try {
					System.out.print("Party: ");
					chooseParty = in.next();
					
					if(e.partyIsExists(chooseParty) == true)
						isValidInput = true;
				
				}catch(ThePartyDoesntExists e1) {
					System.out.println(e1.getMessage());
			
				
				}catch(InputMismatchException e1) {
					System.out.println("ERROR");
				}
			}
			isValidInput = false;
		//---!-----	
		e.updateVoteToPollStation(chooseParty, e.allCitizen.get(i).getSickDays(), e.allCitizen.get(i).getClass().getSimpleName());
		}	
	}
	
	
	
	
	
	public void electionResults() {
		
		System.out.println("Elections Result: ");
		
		System.out.println("1).Parties votes in each poll station: ");
		e.partiesVotesByPollStations();
		
		System.out.println();
		System.out.println("2).General parties votes: ");
		e.sumPartiesVotes();
		
		System.out.println();
		System.out.println("3). The percent of voters in each poll station: ");
		e.percentOfVotersInEachPollStation();
	}
	
}
