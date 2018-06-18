package bin;
import bin.Id;
import bin.Handy;

public class Weapon extends Item {
	
	//Instance variables
	int charge;
	int damage;
	float speed;
	String name;
	Id id;
	Handy Handy = new Handy();
	//Constructor declaration of Weapon
	public Weapon(String name, Id id, int charge, float speed, int damage) {
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
	
	//Method getter weapon.id
	public Id getId() {
		return this.id;
	}
	
	//Method setter weapon.charge
	private void setCharge(int _charge) {
		try {
			if(Handy.isPercent(_charge)) {
				this.charge = _charge;
			}
		}catch(Exception e) {
			throw new StringIndexOutOfBoundsException("charge of the weapon is incorrect");
		}
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
		try {
			if (_name.trim().length() > 0){
				this.name = _name;
			}
		}catch(Exception e) {
			throw new StringIndexOutOfBoundsException("name is empty");
		}
	}
}
