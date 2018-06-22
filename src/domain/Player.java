package domain;

import java.util.ArrayList;

public class Player {
	
	//Instance variables
	protected String name;
	public Level level;
	public Health health;
	public static final int HEALTHPLAYER = 100;
	private int damage;
	protected ArrayList<Item> equipment = new ArrayList<Item>();
	//Constructor Declaration of Player
	public Player(String name) {
		setName(name);
		level = new Level(1);
		health = new Health(HEALTHPLAYER);	
	}
	public void equipWeapon(Weapon weapon) {
		//testing only with weapons for the moment
		this.setDamage(weapon.getDamage());
	}
	//Method getter player.name 
	public String getName() {
		return name;
	}
	
	//Method Setter player.name 
	private void setName(String _name) {
		if (_name.trim().length() > 0){
			this.name = _name;
		}
	}
	private void setDamage(int _damage) {
		this.damage = _damage;
	}
	public int getDamage() {
		return this.damage;
	}
}
