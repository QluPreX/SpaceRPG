package domain;
/**
 * The visor/helmet is an Gear type of Item
 * can be used to equip by the player.
 * The visor can boost specific types of stats for the player:
 * 	Increase line of sight ?
 * 	Increase accuracy for weapons
 * 	....
 * @author karel
 * @version 1.0
 */
public class Visor extends Gear{
	
	/**
	 * The contructor of the visor class
	 * use a name to define one
	 */
	public Visor(String name) {
		super(name);
	}
	
}
