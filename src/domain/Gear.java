package domain;
import java.util.Random;
/**
 * the gear class: these are items than can be equipped,
 * but cannot be used by the player as an ability.
 * these are passive items that boosts your stats
 * @author karel
 * @version 1.0
 */
public abstract class Gear extends Item {
	private Random random;
	protected int durability;
	private int durabilityMax;
	private int[] range = {20,40};
	/**
	 * the constructor of the Gear class
	 * has a durability and max of it
	 * @param name
	 */
	public Gear(String name) {
		super(name);
		random = new Random();
		int durTemp = createDurabilityMax(range);
		setDurability(durTemp);
		setDurabilityMax(durTemp);
	}
	/**
	 * the setter of the durability for the gear
	 * @param _durability
	 */
	private void setDurability(int _durability) {
		this.durability = _durability;
	}
	/**
	 * this method creates a max durability by using a min and max range
	 * @param range
	 * @return the created durability
	 */
	private int createDurabilityMax(int[] range) {
		return random.nextInt(range[1]) + range[0];
	}
	/**
	 * the setter of the max durability for the gear
	 * @param _durabilityMax
	 */
	private void setDurabilityMax(int _durabilityMax) {
		this.durabilityMax = _durabilityMax;
	}
	/**
	 * this method removes durability from your gear
	 * @param damage
	 */
	public void hitDamage(int damage) {
		setDurability(getDurability()-damage);
	}
	/**
	 * this method repairs this gear to its maximum
	 */
	public void repairFull() {
		setDurability(getDurabilityMax());
	}
	/**
	 * this is the getter of the durability for the gear class
	 * @return durability
	 */
	public int getDurability() { return this.durability;}
	/**
	 * this is the getter of the max durability for the gear class
	 * @return durabilitymax
	 */
	public int getDurabilityMax() { return this.durabilityMax;}
}
