package application;

import java.util.Random;

public class Enemy {

	static Random rn = new Random(); 



	private static int hp;
	private static int maxhp;
	private static int atk;
	private static int def;
	private static int speed;
	private static String type;
	static int enemytypenumber;


	private static double bar;



	public static void setupEnemy() {
		setHp(Player.getEnemyLVL() * 2);
		setMaxhp(Player.getEnemyLVL() * 2); 
		setAtk((Player.getEnemyLVL() / 2) + 1);
		setDef(Player.getEnemyLVL() / 5);
		setSpeed(Player.getEnemyLVL());


		enemytypenumber = rn.nextInt(3) + 1;
		switch (enemytypenumber) {
		case 1:
			setType("Fire");
			break;
		case 2:
			setType("Water");
			break;
		case 3:
			setType("Grass");
			break;

		}

		System.out.println("atk :" + getAtk() + "   def: " + getDef() + "     Speed: "  + getSpeed() + "   Type: " + getType());

	}

	public static void enemyattack() {		
		int enemydamage = 0;
		if (Enemy.getAtk() > Player.getDef()) {
			enemydamage = Enemy.getAtk() - Player.getDef();
		}
		int currenthp = (int) ((Player.getHP() - (enemydamage) * typeMultiplier()));
		Player.setHP(currenthp);
		BattleController.setPlayerHP(currenthp);
		BattleController.setPlayerHPBar(Player.getHP(), Player.getMaxHP());
	}

	public static double typeMultiplier() {

		if (Enemy.getType() == "Fire") {
			if (Player.getType() == "Water") {
				return 0.5;
			}
			if (Player.getType() == "Grass") {
				return 2;
			}
		}

		if (Enemy.getType() == "Grass") {
			if (Player.getType() == "Fire") {
				return 0.5;
			}
			if (Player.getType() == "Water") {
				return 2;
			}
		}

		if (Enemy.getType() == "Water") {
			if (Player.getType() == "Grass") {
				return 0.5;
			}
			if (Player.getType() == "Fire") {
				return 2;
			}
		}
		return 1;
	}


	public static int getHp() {
		return hp;
	}
	public static void setHp(int hp) {
		Enemy.hp = hp;
	}
	public static int getMaxhp() {
		return maxhp;
	}
	public static void setMaxhp(int maxhp) {
		Enemy.maxhp = maxhp;
	}
	public static int getAtk() {
		return atk;
	}
	public static void setAtk(int atk) {
		Enemy.atk = atk;
	}
	public static int getDef() {
		return def;
	}
	public static void setDef(int def) {
		Enemy.def = def;
	}
	public static int getSpeed() {
		return speed;
	}
	public static void setSpeed(int speed) {
		Enemy.speed = speed;
	}
	public static String getType() {
		return type;
	}
	public static void setType(String type) {
		Enemy.type = type;
	}

	public static double getBar() {	
		bar = (Enemy.getHp() * 1.0) / (Enemy.getMaxhp() * 1.0) ;
		return bar;
	}


}
