package Model;

import java.io.Serializable;

public abstract class Sick implements Serializable {

	private int days;

	public Sick(int days) {
		this.days = days;
	}

	
	public int getSickDays() {
		return days;
	}

	public void setSickDays(int days) {
		this.days = days;
	}	
}
