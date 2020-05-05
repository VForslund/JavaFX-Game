package application;

import java.util.Random;
import javafx.scene.input.KeyCode;

public class Player {

	static Random rand = new Random(); 

	//player speed
	private static int VelX;
	private static int VelY;

	//player stats
	private static int HP = 25;
	private static int MaxHP = 25;
	private static int atk = 4;
	private static int def = 1;
	private static int speed = 6;
	private static String type  = "Water";
	private static String typeStrike  = "Normal";
	private static double bar;
	
	private static boolean fireStrike = false;
	private static boolean waterStrike = false;
	private static boolean grassStrike = false;

	//player coordinates
	private static int x = 1300;
	private static int y = 340;
	private static boolean battle = false;
	private static boolean newEnemy = false;
	private static boolean shop = false;

	//enemy
	static int randomNumber;
	private static int enemyLVL;
	static int zoneNumber;


	public static void update() {
		walls();
		encounter();
	}

	//moves the player
	public static void move(javafx.scene.input.KeyEvent e) {
		KeyCode key = e.getCode();

		if (key == KeyCode.A || key == KeyCode.LEFT) {
			setVelX(-5); 
		} else if (key == KeyCode.D ||key == KeyCode.RIGHT) {
			setVelX(5);
		} else if (key == KeyCode.W ||key == KeyCode.UP) { 
			setVelY(-5);
		} else if (key == KeyCode.S ||key == KeyCode.DOWN) {
			setVelY(5);
		}
	}


	//Stops the player
	public static void stop(javafx.scene.input.KeyEvent e) {
		KeyCode key = e.getCode();

		if (key == KeyCode.A || key == KeyCode.LEFT) {
			setVelX(0); 
		} else if (key == KeyCode.D ||key == KeyCode.RIGHT) {
			setVelX(0);
		} else if (key == KeyCode.W ||key == KeyCode.UP) { 
			setVelY(0);
		} else if (key == KeyCode.S ||key == KeyCode.DOWN) {
			setVelY(0);
		} else 	if (key == KeyCode.SPACE) {
			shopAnditems();
		} else if (key == KeyCode.DIGIT1) {
			if(MapController.getHeal() > 0) {
				MapController.setHeal(MapController.getHeal() - 1);
				int hpdiff = Player.getMaxHP() - Player.getHP();
				if (hpdiff >= 5) {
					BattleController.setPlayerHP(Player.getHP() + 5);
					Player.setHP(Player.getHP() +5);
				}else {
					BattleController.setPlayerHP(Player.getHP() + hpdiff);
					Player.setHP(Player.getHP() +hpdiff);
				}
				BattleController.updateHPbar();
			}

		} else if (key == KeyCode.DIGIT2) {
			if(MapController.getHPPotion() > 0) {
				MapController.setHPPotion(MapController.getHPPotion() - 1);	
			}
		} else if (key == KeyCode.DIGIT3) {
			if(MapController.getATKPotion()> 0) {
				MapController.setATKPotion(MapController.getATKPotion() - 1);
			}
		} else if (key == KeyCode.DIGIT4) {
			if(MapController.getSpeedPotion() > 0) {
				MapController.setSpeedPotion(MapController.getSpeedPotion() - 1);	
			}
		}
	}

	public static void shopAnditems() {

		if (Player.getX() >= 1250 && Player.getY() <= 350) {
			setShop(true);
		}else {
			System.out.println("too far away");
		}
	}

	//Create this method 
	//it needs to keep the player from hitting the trees, rocks, and buildings
	public static void walls() {

		if (x>1220 && y < 300) {
			if (VelX != 0 && y < 290) {
				x = 1220;
			}else if(VelY != 0 && x > 1230) {
				y = 300;
			}
		}
		//fence
		if( x > 1380) {
			x = 1380;
		}
		if( x < 0) {
			x = 0;
		}
		//tree 1
		if( x > 1235 && y > 360 ) { 
			if(VelX == 5) {
				x = x - 5;
			}
			else if(VelX == 0 && x == 1240) {
				x = 1235;
			}
			else if(VelY == 5) {
				y = y - 5;
			}
			else if(VelY == 0 && y == 365) {
				y = 360;
			}
		}
		//tree 2 
		if( x > 530 && x <= 1235 && y > 640 ) { 
			if(VelX == 5) {
				x = x - 5;
			}
			else if(VelX == 0 && x == 535) {
				x = 530;
			}
			else if(VelY == 5) {
				y = y - 5;
			}
			else if(VelY == 0 && y == 645) {
				y = 635;
			}
		}
		//tree 3
		if( x > 120 && x <= 530 && y > 700 ) { 
			if(VelX == 5) {
				x = x - 5;
			}
			else if(VelX == 0 && x == 125) {
				x = 120;
			}
			else if(VelY == 5) {
				y = y - 5;
			}
			else if(VelY == 0 && y == 705) {
				y = 695;
			}
		}
		//tree 4 
		if( x >= 130 && x <= 50 && y > 700 ) { 
			if(VelX == 5) {
				x = x - 5;
			}
			else if(VelX == 0 && x == 135) {
				x = 130;
			}
			else if(VelY == 5) {
				y = y - 5;
			}
			else if(VelY == 0 && y == 705) {
				y = 695;
			}
		}
		//tree 5
		if( x < 130 && y >= 570 ) { 
			if(VelX == -5) {
				x = x + 5;
			}
			else if(VelX == 0 && x == 125) {
				x = 130;
			}
			else if(VelY == 5) {
				y = y - 5;
			}
			else if(VelY == 0 && y == 575) {
				y = 570;
			}
		}
		//tree 6
		if( x < 130 && y < 225 ) { 
			if(VelX == -5) {
				x = x + 5;
			}
			else if(VelX == 0 && x == 125) {
				x = 130;
			}
			else if(VelY == -5) {
				y = y + 5;
			}
			else if(VelY == 0 && y == 220) {
				y = 225;
			}
		}
		//tree 7 
		if(x > 870 && x < 960 && y > 185 && y < 340 ) { 
			if(VelX == 5) {
				x = x - 5;
			}
			else if(VelX == -5) {
				x = x + 5;
			}
			else if(x == 875 ) {
				x = 870;
			}
			else if(x == 955 ) {
				x = 960;
			}
			else if(VelY == 5) {
				x = x - 5;
			}
			else if(x == 345 ) {
				x = 340;
			}
		}
		//tree 8 
		if( x <= 885  && y < 70 ) { 
			if(VelX == 0 && x == 125) {
				x = 130;
			}
			else if(VelY == -5) {
				y = y + 5;
			}
			else if(VelY == 0 && y == 65) {
				y = 70;
			}
		}


		//tree 9 
		if( x > 885 && x <= 1220  && y < 100 ) { 
			if(VelX == 5) {
				x = x - 5;
			}
			else if(VelX == 0 && x == 890) {
				x = 885;
			}
			else if(VelY == -5) {
				y = y + 5;
			}
			else if(VelY == 0 && y == 95) {
				y = 100;
			}
		}

		//tree 10
		if(x > 710 && x < 825 && y > 245 && y <  425 ) { 
			if(VelX == 5) {
				x = x - 5;
			}
			else if(VelX == -5) {
				x = x + 5;
			}
			else if(x == 715 ) {
				x = 710;
			}
			else if(x == 820 ) {
				x = 825;
			}
			else if(VelY == -5) {
				y = y + 5;
			}
			else if(y == 420 ) {
				y = 425;
			}
			else if(VelY == 5) {
				y = y - 5;
			}
			else if(y == 250 ) {
				y = 245;
			}
		}
		//tree 11
		if(x > 755 && x < 825 && y >= 425 && y <  500 ) { 
			if(VelX == 5) {
				x = x - 5;
			}
			else if(VelX == -5) {
				x = x + 5;
			}
			else if(x == 760 ) {
				x = 755;
			}
			else if(x == 820 ) {
				x = 825;
			}
			else if(VelY == -5) {
				y = y + 5;
			}
			else if(y == 495 ) {
				y = 500;
			}
		}

		//tree 12
		if(x > 290 && x < 490 && y < 420 ) { 
			if(VelX == 5) {
				x = x - 5;
			}
			else if(VelX == -5) {
				x = x + 5;
			}
			else if(x == 295 ) {
				x = 290;
			}
			else if(x == 485 ) {
				x = 490;
			}
			else if(VelY == -5) {
				y = y + 5;
			}
			else if(y == 415 ) {
				y = 420;
			}
		}
		//tree 13
		if(x > 290 && x < 380 && y < 505 ) { 
			if(VelX == 5) {
				x = x - 5;
			}
			else if(VelX == -5) {
				x = x + 5;
			}
			else if(x == 295 ) {
				x = 290;
			}
			else if(x == 375 ) {
				x = 380;
			}
			else if(VelY == -5) {
				y = y + 5;
			}
			else if(y == 500 ) {
				y = 505;
			}
		}
		//rock 1
		if(x > 885 && x < 960 && y < 185 ) { 
			if(VelX == 5) {
				x = x - 5;
			}
			else if(VelX == -5) {
				x = 885;
			}
			else if(x == 890 ) {
				x = 885;
			}
		}
		//rock 2
		if(x > 885 && x < 960 && y < 600 && y >= 340  ) { 
			if(VelX == 5) {
				x = x - 5;
			}
			else if(VelX == -5) {
				x = 885;
			}
			else if(x == 890 ) {
				x = 885;
			}else if(VelY == -5) {
				y = y + 5;
			}else if(y == 600) {
				y = 595;
			}
		}
		//rock 3
		if(x > 690 && x < 770 && y < 250 ) { 
			if(VelX == 5) {
				x = x - 5;
			}
			else if(VelX == -5) {
				x = 690;
			}
			else if(x == 695 ) {
				x = 690;
			}
		}
		//rock 4
		if(x > 535 && x < 610 && y < 530 && y > 140  ) { 
			if(VelX == 5) {
				x = x - 5;
			}
			else if(VelX == -5) {
				x = 535;
			}
			else if(x == 540 ) {
				x = 535;

			}else if(VelY == -5) {
				y = y + 5;
			}else if(VelY == 5) {
				y = y - 5;
			}else if(y == 525) {
				y = 530;
			}else if(y == 145) {
				y = 140;
			}
		}
	}

	public static void encounter() {
		randomNumber = rand.nextInt(500);

		if (x <= 1030 && x >= 950 && y >= 280 && y <= 660 && (VelX != 0 || VelY != 0)) {
			if (randomNumber > 0 && randomNumber <= 10 ) {
				System.out.println();
				//battleCoordinates();
				setEnemyLVL(randomNumber);

				System.out.println("Battle zone 1 Activated");
				System.out.println("Encounter lvl: " + randomNumber);
				setZoneNumber(1);
				setNewEnemy(true);
				setBattle(true);
			}
		} 

		if (x <= 825 && x >= 550 && y >= 525 && y <= 660 && (VelX != 0 || VelY != 0)) {
			if (randomNumber > 10 && randomNumber <= 20 ) {
				System.out.println();
				//battleCoordinates();
				setEnemyLVL(randomNumber);

				System.out.println("Battle zone 2 Activated");
				System.out.println("encounter lvl: " + randomNumber);
				setZoneNumber(2);
				setNewEnemy(true);
				setBattle(true);
			}
		}

		if (x <= 130 && x >= 0 && y >= 250 && y <= 550 && (VelX != 0 || VelY != 0)) {
			if (randomNumber > 20 && randomNumber <= 30 ) {
				System.out.println();
				//battleCoordinates();
				setEnemyLVL(randomNumber);

				System.out.println("Battle zone 3 Activated");
				System.out.println("encounter lvl: " + randomNumber);
				setZoneNumber(3);
				setNewEnemy(true);
				setBattle(true);
			}
		} 
	}

	public static void playerAttack() {

		int enemyCurrentHp = (int) (Enemy.getHp() - ((getAtk() * atktypeMultiplier()) - Enemy.getDef()));
		Enemy.setHp(enemyCurrentHp);
		BattleController.setEnemyHPBar(Enemy.getHp(), Enemy.getMaxhp());

	}
	
	public static double atktypeMultiplier() {

		if (Enemy.getType() == "Fire") {
			if (Player.getTypeStrike() == "Water") {
				return 2;
			}
			if (Player.getTypeStrike() == "Grass") {
				return 0.5;
			}
		}

		if (Enemy.getType() == "Grass") {
			if (Player.getTypeStrike() == "Fire") {
				return 2;
			}
			if (Player.getTypeStrike() == "Water") {
				return 0.5;
			}
		}

		if (Enemy.getType() == "Water") {
			if (Player.getTypeStrike() == "Grass") {
				return 2;
			}
			if (Player.getTypeStrike() == "Fire") {
				return 0.5;
			}
		}
		return 1;
	}

	public static int getVelX() {
		return VelX;
	}
	public static void setVelX(int velX) {
		VelX = velX;
	}
	public static int getVelY() {
		return VelY;
	}
	public static void setVelY(int velY) {
		VelY = velY;
	}
	public static int getX() {
		return x;
	}
	public static void setX(int x) {
		Player.x = x;
	}
	public static int getY() {
		return y;
	}
	public static void setY(int y) {
		Player.y = y;
	}
	public static boolean isBattle() {
		return battle;
	}
	public static void setBattle(boolean battle) {
		Player.battle = battle;
	}
	public static int getHP() {
		return HP;
	}
	public static double getBar() {	
		bar = (Player.getHP() * 1.0) / (Player.getMaxHP() * 1.0) ;
		return bar;
	}

	public static void setHP(int HP) {
		Player.HP = HP;
	}
	public static int getMaxHP() {
		return MaxHP;
	}
	public static void setMaxHP(int maxHP) {
		Player.MaxHP = maxHP;
	}
	public static int getEnemyLVL() {
		return enemyLVL;
	}	
	public static void setEnemyLVL(int enemyLVL) {
		Player.enemyLVL = enemyLVL;
	}

	public static boolean isNewEnemy() {
		return newEnemy;
	}

	public static void setNewEnemy(boolean newEnemy) {
		Player.newEnemy = newEnemy;
	}

	public static int getAtk() {
		return atk;
	}

	public static void setAtk(int atk) {
		Player.atk = atk;
	}

	public static int getDef() {
		return def;
	}

	public static void setDef(int def) {
		Player.def = def;
	}

	public static int getSpeed() {
		return speed;
	}

	public static void setSpeed(int speed) {
		Player.speed = speed;
	}

	public static String getType() {
		return type;
	}

	public static void setType(String type) {
		Player.type = type;
	}
	public static void setZoneNumber(int zoneNumber) {
		Player.zoneNumber = zoneNumber;
	}
	public static int getZoneNumber() {
		return zoneNumber;
	}

	public static boolean isShop() {
		return shop;
	}

	public static void setShop(boolean shop) {
		Player.shop = shop;
	}

	public static String getTypeStrike() {
		return typeStrike;
	}

	public static void setTypeStrike(String typeStrike) {
		Player.typeStrike = typeStrike;
	}

	public static boolean isFireStrike() {
		return fireStrike;
	}

	public static void setFireStrike(boolean fireStrike) {
		Player.fireStrike = fireStrike;
	}

	public static boolean isWaterStrike() {
		return waterStrike;
	}

	public static void setWaterStrike(boolean waterStrike) {
		Player.waterStrike = waterStrike;
	}

	public static boolean isGrassStrike() {
		return grassStrike;
	}

	public static void setGrassStrike(boolean grassStrike) {
		Player.grassStrike = grassStrike;
	}
}
