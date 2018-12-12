package entities.unusedEntities;

public class Love {
	/**
	 * The class Love is used for getting love and when getting damage
	 * <strong>THIS CLASS IS NOT USED</strong>
	 * @author karel
	 * @version 1.0
	 */
	protected int love;
	public static final int LOVEMIN = 0;
	public static final int LOVEMAX = 300;
	
	public Love(int _Love) {
		this.setLove(_Love);
	}
	/**
	 * the getter method of love
	 * @return health
	 */
	public int getLove() {
		return this.love;
	}
	/**
	 * the setter method of love
	 * @param _health
	 */
	private void setLove(int _love) {
		if (_love <= LOVEMAX) {
			this.love = _love;
		}
	}
	/**
	 * the setter of the damage
	 * @param _damage
	 */
	public void setDamage(int _damage) {
		if(_damage >= this.getLove()) {
			System.out.println("Love is 0!!");
		}
		else if(_damage <= 0){
			//damage is negative
		}
		else if(_damage < this.love) {
			this.setLove(this.getLove() - _damage);
		}
	}
}
