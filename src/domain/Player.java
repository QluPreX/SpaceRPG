package domain;

public class Player {
	
	//Instance variables
	protected String name;
	protected Level level;
	protected int health;
	protected int healthMax;
	
	//Constructor Declaration of Player
	public Player(String name) {
		setName(name);
		setHealth(health);
	}
	
	//Method getter player.name 
	public String getName() {
		return name;
	}
	
	//Method getter player.health 
	public int getHealth() {
		return health;
	}
	
	//Method Setter player.name 
	private void setName(String _name) {
		try {
			if (_name.trim().length() > 0){
				this.name = _name;
			}
		}catch(Exception e) {
			throw new StringIndexOutOfBoundsException("name is empty");
		}
	}
	
	//Method setter player.health
	private void setHealth(int _health) {
		System.out.println("Game over NOT implemented yset");
		if (_health <= 0) {
			//gameOver
			
		}else if (_health > 100) {
			//100 needs to change to a dynamic variable from the spaceSuit
			this.health = _health;
		}
	}
}
