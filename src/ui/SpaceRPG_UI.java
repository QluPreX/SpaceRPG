package ui;
import domain.*;
public class SpaceRPG_UI {
	public static void main(String[] args) {
		Player player = new Player("player");
		Weapon backupWeapon = new Weapon("Backup weapon");
		System.out.println("player damage:"+player.getDamage());
		System.out.println("weapon damage:" +backupWeapon.getDamage());
		player.equipWeapon(backupWeapon);
		System.out.println("equipping weapon "+backupWeapon.getName());
		System.out.println("player damage:"+player.getDamage());
	}
}