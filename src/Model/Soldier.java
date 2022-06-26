package Model;

public class Soldier extends Citizen {

	protected boolean carryWeapon;

	public Soldier(String name, String ID, int year, boolean isVoting, boolean weapon, int days) {
		super(name, ID, year, isVoting, days);
		this.carryWeapon = weapon;

	}

	public boolean getCarryWeapon() {
		return this.carryWeapon;
	}

	@Override
	public String toString() {
		return super.toString() + ", carryWeapon: " + carryWeapon; 
	}

}
