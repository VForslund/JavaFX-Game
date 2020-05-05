package application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;


public class MapController {
	public static IntegerProperty Money = new SimpleIntegerProperty(2000);
	public static IntegerProperty Heal = new SimpleIntegerProperty(10);
	public static IntegerProperty ATKPotion = new SimpleIntegerProperty(2);
	public static IntegerProperty SpeedPotion = new SimpleIntegerProperty(1);
	public static IntegerProperty HPPotion = new SimpleIntegerProperty(3);

	@FXML
	private MenuItem closeMenuItem;

	@FXML
	private MenuItem saveMenuItem;

	@FXML
	private MenuItem saveAndCloseMenuItem;

	@FXML
	private MenuItem loadMenuItem;

	@FXML
	private MenuItem howToPlayMenuItem;

	@FXML
	private Label goldLable;

	@FXML
	private Label mapHPLabel;

	@FXML
	private Label mapMaxHPLabel;

	@FXML
	private Label healLable;

	@FXML
	private Label HPPotionLable;

	@FXML
	private Label ATKPotionLable;

	@FXML
	private Label SpeedPotionLable;


	@FXML
	public void initialize() {

		goldLable.textProperty().bind(Money.asString());
		SpeedPotionLable.textProperty().bind(SpeedPotion.asString());
		ATKPotionLable.textProperty().bind(ATKPotion.asString());
		HPPotionLable.textProperty().bind(HPPotion.asString());
		healLable.textProperty().bind(Heal.asString());


		mapMaxHPLabel.textProperty().bind(BattleController.playerMaxHP.asString());
		mapHPLabel.textProperty().bind(BattleController.playerHP.asString());

	}



	@FXML
	void closeGame(ActionEvent event) {
		System.exit(1);

	}

	@FXML
	void howToPlayGame(ActionEvent event) {
		//graphics.setRoot(howToPlay);


	}

	@FXML
	void loadGame(ActionEvent event) {
		LoadSave.loadGame();


	}

	@FXML
	void saveAndCloseGame(ActionEvent event) {
		LoadSave.saveGame(Player.getX(), Player.getY(), Player.getHP(), Player.getMaxHP(), MapController.getMoney(), Player.getAtk(), Player.getSpeed(), Player.getDef());	
		System.exit(1);
	}

	@FXML
	void saveGame(ActionEvent event) {
		LoadSave.saveGame(Player.getX(), Player.getY(), Player.getHP(), Player.getMaxHP(), MapController.getMoney(), Player.getAtk(), Player.getSpeed(), Player.getDef());	

	}



	public static int getMoney() {
		return Money.getValue().intValue();
	}
	public static void setMoney(int money) {
		Money.set(money);
	}

	public static int getHeal() {
		return Heal.getValue().intValue();
	}
	public static void setHeal(int heal) {
		Heal.set(heal);
	}

	public static int getATKPotion() {
		return ATKPotion.getValue().intValue();
	}
	public static void setATKPotion(int atkpotion) {
		ATKPotion.set(atkpotion);
	}

	public static int getSpeedPotion() {
		return SpeedPotion.getValue().intValue();
	}
	public static void setSpeedPotion(int speedpotion) {
		SpeedPotion.set(speedpotion);
	}

	public static int getHPPotion() {
		return HPPotion.getValue().intValue();
	}
	public static void setHPPotion(int hppotion) {
		HPPotion.set(hppotion);
	}





}
