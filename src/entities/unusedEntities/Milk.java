package entities.unusedEntities;

public class Milk {
	/**
	 * The class Milk is used for getting health and when getting damage
	 * <strong>THIS CLASS IS NOT USED</strong>
	 * @author karel
	 * @version 1.0
	 */
	protected int milk;
	public static final int MILKMIN = 0;
	public static final int MILKMAX = 300;
	private int milkStatus = 1;
	public Milk(int _healthPlayer) {
		this.setMilk(_healthPlayer);
	}
	/**
	 * the getter method of Health
	 * @return health
	 */
	public int getMilk() {
		return this.milk;
	}
	/**
	 * the setter method of health
	 * @param _milk
	 */
	private void setMilk(int _milk) {
		if (_milk <= MILKMAX) {
			this.milk = changeMilkStatus(_milk);///TODO 
		}
	}
	private int changeMilkStatus(int _milk) {
		//check for the stages of the amount of milk
		// change the appearnce of the milk bar for the amount of milk
		return _milk;
	}
}
