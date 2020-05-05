package application;
import java.util.Random;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;


public class BattleController {

	Random r = new Random(); 

	//player
	Image playerGreen = new Image(getClass().getResourceAsStream("PlayerGreen.png"));
	Image playerBlue = new Image(getClass().getResourceAsStream("PlayerBlue.png"));
	Image playerRed = new Image(getClass().getResourceAsStream("PlayerRed.png"));
	//zone1 monster
	Image monsterOneGreen = new Image(getClass().getResourceAsStream("ZoneOneGreen.png"));
	Image monsterOneBlue = new Image(getClass().getResourceAsStream("ZoneOneBlue.png"));
	Image monsterOneRed = new Image(getClass().getResourceAsStream("ZoneOneRed.png"));
	//zone 2 monster
	Image monsterTwoGreen = new Image(getClass().getResourceAsStream("ZoneTwoGreen.png"));
	Image monsterTwoBlue = new Image(getClass().getResourceAsStream("ZoneTwoBlue.png"));
	Image monsterTwoRed = new Image(getClass().getResourceAsStream("ZoneTwoRed.png"));
	
	//zone 3 monster
	Image monsterThreeGreen = new Image(getClass().getResourceAsStream("ZoneThreeGreen.png"));
	Image monsterThreeBlue = new Image(getClass().getResourceAsStream("ZoneThreeBlue.png"));
	Image monsterThreeRed = new Image(getClass().getResourceAsStream("ZoneThreeRed.png"));

	public static IntegerProperty playerHP = new SimpleIntegerProperty(25);
	public static IntegerProperty playerMaxHP = new SimpleIntegerProperty(25);
	private static SimpleDoubleProperty playerHPBar = new SimpleDoubleProperty(10);
	private static SimpleDoubleProperty enemyHPBar= new SimpleDoubleProperty(10);

	double hp;
	double maxHP;
	double playerbar;
	String enemyLVL;
	String enemyType;
	private boolean loadout = false;
	private boolean fightmove = false;

	@FXML
	private ImageView PlayerImageView;
	@FXML
	private TextArea combatTextTextArea;
	@FXML
	private Button fightButton;
	@FXML
	private Button bagButton;
	@FXML
	private Button loadoutButton;
	@FXML
	private Button runButton;
	@FXML
	private Label currentHPLable;
	@FXML
	private ProgressBar enemyHPProgressBar;
	@FXML
	private ProgressBar playerHPProgressBar;
	@FXML
	private Label maxHPLable;
	@FXML
	private Label enemyLVLLable;
	@FXML
	private ImageView ZoneOneImageView;
	@FXML
	private ImageView ZoneTwoImageView;
    @FXML
    private ImageView ZoneThreeImageView;



	@FXML
	public void initialize() {

		maxHPLable.textProperty().bind(playerMaxHP.asString());
		currentHPLable.textProperty().bind(playerHP.asString());
		playerHPProgressBar.progressProperty().bind(playerHPBar);
		enemyHPProgressBar.progressProperty().bind(enemyHPBar);

		enemyLVLLable.setText("?");
		combatTextTextArea.setText("You are about to face an encounter");
	}

	@FXML
	void bagButtonPressed(ActionEvent event) {

		if (isLoadout() && !isFightmove()) {
			PlayerImageView.setImage(playerGreen);
			Graphics.setColor(Color.GREEN);
			Player.setType("Grass");
			Enemy.enemyattack();
			resetbuttons();
			setLoadout(false);
		} else if(isFightmove() && !isLoadout()) {
			Player.setTypeStrike("Grass");
			combatConfirmed();
			resetbuttons();
			setFightmove(false);
		}
	}

	//Fight button 
	@FXML
	void fightButtonPressed(ActionEvent event) {

		// check if we are in loadout option or not
		if (isLoadout()) {
			PlayerImageView.setImage(playerRed);
			Graphics.setColor(Color.RED);
			Player.setType("Fire");
			Enemy.enemyattack();
			resetbuttons();
			setLoadout(false);
		} else {
			if (isFightmove()) {
				Player.setTypeStrike("Normal");
				combatConfirmed();
				resetbuttons();
				setFightmove(false);
			}

			else { 
				setFightmove(true);
				if (!Player.isFireStrike()) {
					runButton.setDisable(true);
				}
				if (!Player.isGrassStrike()) {
					bagButton.setDisable(true);
				}
				if (!Player.isWaterStrike()) {
					loadoutButton.setDisable(true);
				}				
				fightButton.setText("Normal Strike");
				loadoutButton.setText("Water Strike");
				bagButton.setText("Grass Strike");
				runButton.setText("Fire Strike");
			}
		}
	}

	@FXML
	void loadoutButtonPressed(ActionEvent event) {
		combatTextTextArea.setText("Select Loadout..." 
				+ "\n\nFire beats Grass"
				+ "\nWater beats Fire"
				+ "\nGrass beats Water");

		if (isLoadout()) {
			PlayerImageView.setImage(playerBlue);
			Graphics.setColor(Color.BLUE);
			Player.setType("Water");
			resetbuttons();
			Enemy.enemyattack();
			setLoadout(false);
		}else if (!isLoadout() && !isFightmove()){
			setLoadout(true);
			fightButton.setText("Fire");
			loadoutButton.setText("Water");
			bagButton.setText("Grass");
			runButton.setText("Return");
		} else {
			Player.setTypeStrike("Water");
			combatConfirmed();
			resetbuttons();
			setFightmove(false);
		}
	}

	@FXML
	void runButtonPressed(ActionEvent event) {

		if (isLoadout() && !isFightmove()) {
			resetbuttons();
			setLoadout(false);
		} else if (isFightmove() && !isLoadout()){
			Player.setTypeStrike("Fire");
			combatConfirmed();
			resetbuttons();
			setFightmove(false);
		} else {

			int randomNumber = r.nextInt(2);
			if (randomNumber == 1) {
				combatTextTextArea.setText("You were unable to escape" );
				Enemy.enemyattack();
				if(Player.getHP() <= 0) {
					gameOver();
				}
			} else {
				enemyHPBar.set(1);
				enemyLVLLable.setText("?");	
				battleover();
			}
		}
	}

	public void combatConfirmed() {
		System.out.println(Player.getTypeStrike());

		// check if new enemy
		if(Player.isNewEnemy()) {
			Enemy.setupEnemy();
			monsterImageLoader();
			enemyLVLLable.setText(Integer.toString(Player.getEnemyLVL()));
			Player.setNewEnemy(false);
		}

		if (Enemy.getSpeed() <= Player.getSpeed()) {
			Player.playerAttack();
			if(Enemy.getHp() > 0) {
			Enemy.enemyattack();
			}
		}
		if (Enemy.getSpeed() > Player.getSpeed()) {
			Enemy.enemyattack();
			if(Player.getHP() > 0) {
			Player.playerAttack();
			}
		}
		System.out.println(Player.getHP());

		if(Player.getHP() <= 0) {
			gameOver();
		}else if (Enemy.getHp() <= 0 && Player.getHP() > 0) {
			Victory();
		}
	}

	public void resetbuttons() {
		fightButton.setText("Fight");
		bagButton.setText("Bag");
		loadoutButton.setText("Loadout");
		runButton.setText("Run");
		
		fightButton.setDisable(false);
		bagButton.setDisable(false);
		loadoutButton.setDisable(false);
		runButton.setDisable(false);
		
	}
	public void updateHPVisuals() {
		currentHPLable.setText(Integer.toString(Player.getHP()));
		playerHPProgressBar.setProgress(Player.getBar());
		enemyHPProgressBar.setProgress(Enemy.getBar());


	}

	public void battleover() {
		ZoneOneImageView.setVisible(false);
		ZoneTwoImageView.setVisible(false);
		ZoneThreeImageView.setVisible(false);
		enemyLVLLable.setText("?");
		combatTextTextArea.setText("You are about to face an encounter");
		enemyHPBar.set(1);
		Player.setBattle(false);
	}
	public void gameOver() {
		combatTextTextArea.setText("You have been defeted...."
				+ "You droped all your money");	

		Player.setHP(Player.getMaxHP());
		playerHP.set(Player.getHP());
		playerHPBar.set(1);
		MapController.setMoney(0);	
		Player.setX(1300);
		Player.setY(340);
		battleover();

	}
	public void Victory() {
		int loot =( (MapController.getMoney() * 2 ) +(Player.getEnemyLVL()));
		combatTextTextArea.setText("");
		MapController.setMoney(loot);
		battleover();

	}

	public void monsterImageLoader() {
		if (Player.getZoneNumber() == 1) {

			enemyType = Enemy.getType();
			switch (enemyType) {
			case "Fire":
				ZoneOneImageView.setImage(monsterOneRed);
				break;
			case "Water":
				ZoneOneImageView.setImage(monsterOneBlue);
				break;
			case "Grass":
				ZoneOneImageView.setImage(monsterOneGreen);
				break;
			}

			ZoneOneImageView.setVisible(true);
			ZoneTwoImageView.setVisible(false);
			ZoneThreeImageView.setVisible(false);

		} else if (Player.getZoneNumber() == 2) {

			enemyType = Enemy.getType();
			switch (enemyType) {
			case "Fire":
				ZoneTwoImageView.setImage(monsterTwoRed);
				break;
			case "Water":
				ZoneTwoImageView.setImage(monsterTwoBlue);
				break;
			case "Grass":
				ZoneTwoImageView.setImage(monsterTwoGreen);
				break;
			}

			ZoneOneImageView.setVisible(false);
			ZoneTwoImageView.setVisible(true);
			ZoneThreeImageView.setVisible(false);

		}
		else if (Player.getZoneNumber() == 3) {

			enemyType = Enemy.getType();
			switch (enemyType) {
			case "Fire":
				ZoneThreeImageView.setImage(monsterThreeRed);
				break;
			case "Water":
				ZoneThreeImageView.setImage(monsterThreeBlue);
				break;
			case "Grass":
				ZoneThreeImageView.setImage(monsterThreeGreen);
				break;
			}

			ZoneOneImageView.setVisible(false);
			ZoneTwoImageView.setVisible(false);
			ZoneThreeImageView.setVisible(true);
			combatTextTextArea.setText("You dare to oppose me mortal");

		}
	}

	public static void updateHPbar() {
		setPlayerHPBar(Player.getHP(), Player.getMaxHP());
	}

	public boolean isLoadout() {
		return loadout;
	}

	public void setLoadout(boolean loadout) {
		this.loadout = loadout;
	}

	public static IntegerProperty getPlayerHP() {
		return playerHP;
	}

	public static void setPlayerHP(int playerHP) {
		BattleController.playerHP.set(playerHP);
	}

	public static IntegerProperty getPlayerMaxHP() {
		return playerMaxHP;
	}
	public static void setPlayerMaxHP(int playerMaxHP) {
		BattleController.playerMaxHP.set(playerMaxHP);
	}

	public static SimpleDoubleProperty getPlayerHPBar() {
		return playerHPBar;
	}

	public static void setPlayerHPBar(int currenthp, int maxhp) {
		double progress = ((currenthp * 1.0)/(maxhp* 1.0));
		BattleController.playerHPBar.set(progress); 
	}
	public static void setEnemyHPBar(int currenthp, int maxhp) {
		double progress = ((currenthp * 1.0)/(maxhp* 1.0));
		BattleController.enemyHPBar.set(progress); 
	}

	public boolean isFightmove() {
		return fightmove;
	}

	public void setFightmove(boolean fightmove) {
		this.fightmove = fightmove;
	}
}
