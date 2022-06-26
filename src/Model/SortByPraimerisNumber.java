package Model;

import java.util.Comparator;

public class SortByPraimerisNumber implements Comparator<Candidate> {

	
	public int compare(Candidate o1, Candidate o2) {
		// TODO Auto-generated method stub
		if(o1.primariesNumber < o2.primariesNumber)
			return -1;
		
		else if(o1.primariesNumber > o2.primariesNumber)
			return 1;

		return 0;
	}



}
