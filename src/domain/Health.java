package domain;

public class Health {
	/**
	 * The class Health is used for getting health and when getting damage
	 * @author karel
	 * @version 1.0
	 */
	protected int health;
	public static final int HEALTMIN = 0;
	public static final int HEALTHMAX = 100;
	
	public Health(int _healthPlayer) {
		this.setHealth(_healthPlayer);
	}
	/**
	 * the setter of the damage
	 * @param _damage
	 */
	public void setDamage(int _damage) {
		if(_damage >= this.getHealth()) {
			System.out.println("GameOver");
		}
		else if(_damage <= 0){
			//damage is negative
		}
		else if(_damage < this.health) {
			this.setHealth(this.getHealth() - _damage);
		}
	}
	/**
	 * the getter method of Health
	 * @return health
	 */
	public int getHealth() {
		return this.health;
	}
	/**
	 * the setter method of health
	 * @param _health
	 */
	private void setHealth(int _health) {
		if (_health <= HEALTHMAX) {
			this.health = _health;
		}
	}
	
}
