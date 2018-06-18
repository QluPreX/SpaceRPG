package domain;

public class Weapon extends Item {
	
	//Instance variables
	int charge;
	int damage;
	float speed;
	String name;
	//Constructor declaration of Weapon
	public Weapon(String name, int charge, float speed, int damage) {
		setName(name);
		setCharge(charge);
		setSpeed(speed);
		setDamage(damage);
	}
	
	//Method getter weapon.charge
	public int getCharge() {
		return this.charge;
	}
	
	//Method getter weapon.damage
	public int getDamage() {
		return this.damage;
	}
	
	//Method getter weapon.speed
	public float getSpeed() {
		return this.speed;
	}
	
	//Method getter weapon.name
	public String getName() {
		return this.name;
	}
	
	//Method setter weapon.charge
	private void setCharge(int _charge) {
		this.charge = _charge;
	}
	
	//Method setter weapon.damage
	public void setDamage(int _damage) {
		this.damage = _damage;
	}

	//Method setter weapon.speed
	public void setSpeed(float _speed) {
		this.speed = _speed;
	}
	
	//Method setter weapon.name
	public void setName(String _name) {
		if (_name.trim().length() > 0){
			this.name = _name;
		}
	}
}
