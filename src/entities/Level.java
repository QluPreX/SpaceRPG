package entities;

public class Level {
	/**
	 * The class Level is used for adding experience to the player
	 * <strong>THIS CLASS IS NOT USED</strong>
	 * @author karel
	 * @version 1.0
	 */
	protected int level;
	protected int exp;
	protected int expMax;
	public static final int LEVELMIN = 1;
	public static final int LEVELMAX = 30;
	public static final int EXPPERLEVEL = 100;
	
	public Level(int _level) {
		setExp(0);
		setLevel(LEVELMIN);
		setExpMax();
	}
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
		if (_exp < 0) {
			
		}
		else if(_exp <= this.getExpMax()) {
			this.exp += _exp;
		}
		//checking if exp and the current exp is not higher than the maximum exp
		else if(this.getExpMax() >= this.getExp()+_exp) {	
			int expTemp = expMax - (this.getExp()+_exp);
			addLevel(1);
			if (expTemp > 0){
				setExp(expTemp);
			}
		}
	}
	/**
	 * Adds level(s), sets the current exp to ZERO
	 * @param level
	 */
	private void addLevel(int _level) {
		if(_level == 1) {
			this.setExp(0);
			this.setLevel(this.getLevel() + _level);
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
		this.expMax = this.getLevel() * EXPPERLEVEL;
	}
	/**
	 * the getter of experience max
	 * @return expMax
	 */
	public int getExpMax() {
		return this.expMax;
	}
	/**
	 * the getter of the level
	 * @return level
	 */
	public int getLevel() {
		return this.level;
	}
	/**
	 * the setter of the level
	 * @param _level
	 */
	private void setLevel(int _level) {
		if ( (_level >= LEVELMIN) || (_level <= LEVELMAX) ) {
			this.level = _level;
		}
	}
}
