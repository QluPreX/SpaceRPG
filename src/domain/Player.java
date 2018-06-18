package domain;

public class Player {
	
	//Instance variables
	protected String name;
	public Level level;
	public Health health;
	public static final int HEALTHPLAYER = 100;
	//Constructor Declaration of Player
	public Player(String name) {
		setName(name);
		level = new Level(1);
		health = new Health(HEALTHPLAYER);
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
}
