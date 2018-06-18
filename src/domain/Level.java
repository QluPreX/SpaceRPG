package domain;

public class Level {
	/**
	 * The class Level is used for adding experience to the player
	 * @author karel
	 * @version 1.0
	 */
	protected int level;
	protected int exp;
	protected int expMax;
	public static final int LEVELMIN = 1;
	public static final int LEVELMAX = 30;
	public static final int EXPPERLEVEL = 100;
	/**
	 * the getter of the experience
	 * @return experience
	 */
	public int getExp() {
		return this.exp;
	}
	/**
	 * The setter of the exp from the player
	 * If the exp comes above the expMax a level is added
	 * When the Exp get will level up the player 2 or more times, the function will be recursive
	 *@param experience
	 */
	public void setExp(int _exp) {
		//check if exp is not negative
		if (_exp <= 0) {
			
		}
		//checking if exp and the current exp is not higher than the maximum exp
		else if(expMax >= this.getExp()+_exp) {	
			int expTemp = expMax - (this.getExp()+_exp);
			addLevel(1);
			if (expTemp > 0){
				setExp(expTemp);
			}
		}
		else {
			this.exp += _exp;
		}
	}
	/**
	 * When a next level is set, setExpToZero is used
	 * sets exp to int = 0
	 */
	private void setExpToZero() {
		this.exp = 0;
	}
	/**
	 * Adds level(s), sets the current exp to ZERO
	 * @param level
	 */
	private void addLevel(int _level) {
		if(_level == 1) {
			setExpToZero();
			this.level += _level;
			setExpMax();
		}
		else {
			//when level is more then 1
		}
	}
	/**
	 * creates a bigger experience per level
	 */
	private void setExpMax() {
		this.expMax = this.level * EXPPERLEVEL;
	}
	/**
	 * Creates a base level and exp
	 */
	public void createLevel() {
		this.level = LEVELMIN;
		this.exp = 0;
	}
}
