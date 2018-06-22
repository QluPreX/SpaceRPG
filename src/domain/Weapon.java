package domain;
import java.util.Random;
/**
 * the Weapon Class: an extension from the Item.
 * Here are all the weapon set
 * 
 */
public class Weapon extends Item {
	protected int damage;
	private Random random;
	private int[] range = {20,60};
	/**
	 * The constructor of the Weapon class
	 * Uses a name to define extends from the Item class.
	 * gets 1 damage amount
	 * @param name
	 */
	public Weapon(String name) {
		super(name);
		random = new Random();
		setDamage(createDamage(range));
	}
	/**
	 * The setter of the damage, needs an integer to set it
	 * @param _damage
	 */
	private void setDamage(int _damage) {
		this.damage = _damage;
	}
	/**
	 * Creates a damage number from the min and max (randomly)
	 * @param range
	 * @return an integer for damage
	 */
	private int createDamage(int[] range) {
		return random.nextInt(range[1]) + range[0];
	}
	/**
	 * the getter of Min of the damage of the weapon
	 * @return minimum
	 */
	public int getMin() { return this.range[0];}
	/**
	 * the getter of the max of the damage of the weapon
	 * @return maximum
	 */
	public int getMax() { return this.range[1];}
	/**
	 * the getter of the main damage of the weapon 
	 * @return damage
	 */
	public int getDamage() { return this.damage;}
}
