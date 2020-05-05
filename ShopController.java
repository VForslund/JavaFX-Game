package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ShopController {

	private static String menuSection;

	@FXML
	private Label shopGoldLabel;
	@FXML
	private Button StatsButton;
	@FXML
	private Button MovesButton;
	@FXML
	private Button ItemsButton;
	@FXML
	private Button ReturnButton;
	@FXML
	private Button Item1Button;
	@FXML
	private Button Item3Button;
	@FXML
	private Button Item2Button;
	@FXML
	private Button Item4Button;

	@FXML
	public void initialize() {

		shopGoldLabel.textProperty().bind(MapController.Money.asString());

	}

	@FXML
	void Item1ButtonPressed(ActionEvent event) {

		switch (getMenuSection()) {
		case "Stats":
			if (MapController.getMoney() >= 100) {
				Player.setAtk(Player.getAtk() + 1);
				MapController.setMoney(MapController.getMoney() - 100);
			}
			break;
		case "Moves":
			break;
		case "Items":
			if (MapController.getMoney() >= 25) {
				MapController.setHeal(MapController.getHeal() + 1);
				MapController.setMoney(MapController.getMoney() - 25);
			}
			break;
		}
	}

	@FXML
	void Item2ButtonPressed(ActionEvent event) {

		switch (getMenuSection()) {
		case "Stats":

			if (MapController.getMoney() >= 100) {
				int HPUP = (Player.getMaxHP() + 1);
				Player.setMaxHP(HPUP);
				BattleController.playerMaxHP.set(HPUP);
				BattleController.setPlayerHPBar(Player.getHP(), Player.getMaxHP());
				MapController.setMoney(MapController.getMoney() - 100);
			}
			break;
		case "Moves":
			if (MapController.getMoney() >= 20) {
				Player.setWaterStrike(true);
				MapController.setMoney(MapController.getMoney() - 20);
				if (Player.isWaterStrike()) {
					Item2Button.setDisable(true);
				}
			}
			break;
		case "Items":
			if (MapController.getMoney() >= 25) {
				MapController.setHPPotion(MapController.getHPPotion() + 1);
				MapController.setMoney(MapController.getMoney() - 25);
			}
			break;
		}
	}

	@FXML
	void Item3ButtonPressed(ActionEvent event) {

		switch (getMenuSection()) {
		case "Stats":
			if (MapController.getMoney() >= 100) {
				Player.setDef(Player.getDef() + 1);
				MapController.setMoney(MapController.getMoney() - 100);
			}
			break;
		case "Moves":
			if (MapController.getMoney() >= 20) {
				Player.setFireStrike(true);
				MapController.setMoney(MapController.getMoney() - 20);
				if (Player.isFireStrike()) {
					Item3Button.setDisable(true);
				}
			}
			break;
		case "Items":
			if (MapController.getMoney() >= 25) {
				MapController.setATKPotion(MapController.getATKPotion() + 1);
				MapController.setMoney(MapController.getMoney() - 25);
			}
			break;
		}
	}

	@FXML
	void Item4ButtonPressed(ActionEvent event) {

		switch (getMenuSection()) {
		case "Stats":
			if (MapController.getMoney() >= 100) {
				Player.setSpeed(Player.getSpeed() + 1);
				MapController.setMoney(MapController.getMoney() - 100);
			}
			break;
		case "Moves":
			if (MapController.getMoney() >= 200) {
				
				Player.setGrassStrike(true);
				MapController.setMoney(MapController.getMoney() - 200);
				if (Player.isGrassStrike()) {
					Item4Button.setDisable(true);
				}
			}
			break;
		case "Items":
			if (MapController.getMoney() >= 25) {
				MapController.setSpeedPotion(MapController.getSpeedPotion() + 1);
				MapController.setMoney(MapController.getMoney() - 25);
			}
			break;
		}
	}

	@FXML
	void ItemsButtonPressed(ActionEvent event) {
		setMenuSection("Items");
		showitems();
		Item1Button.setText("HP potion");
		Item2Button.setText("Max HP potion");
		Item3Button.setText("ATK potion");
		Item4Button.setText("Speed potion");
	}

	@FXML
	void MovesButtonPressed(ActionEvent event) {
		setMenuSection("Moves");
		showitems();
		Item1Button.setDisable(true);
		if (Player.isFireStrike()) {
			Item3Button.setDisable(true);
		}
		if (Player.isWaterStrike()) {
			Item2Button.setDisable(true);
		}
		if (Player.isGrassStrike()) {
			Item4Button.setDisable(true);
		}
		Item1Button.setText("Normal Strike");
		Item2Button.setText("Water Strike");
		Item3Button.setText("Fire Strike");
		Item4Button.setText("Grass Strike");
	}

	@FXML
	void StatsButtonPressed(ActionEvent event) {
		setMenuSection("Stats");
		showitems();
		Item1Button.setText("Atk");
		Item2Button.setText("Hp");
		Item3Button.setText("Def");
		Item4Button.setText("Speed");
	}

	@FXML
	void ReturnButtonPressed(ActionEvent event) {
		Item1Button.setVisible(false);
		Item2Button.setVisible(false);
		Item3Button.setVisible(false);
		Item4Button.setVisible(false);
		Player.setShop(false);
	}

	public void showitems() {
		Item1Button.setVisible(true);
		Item2Button.setVisible(true);
		Item3Button.setVisible(true);
		Item4Button.setVisible(true);
		
		Item1Button.setDisable(false);
		Item2Button.setDisable(false);
		Item3Button.setDisable(false);
		Item4Button.setDisable(false);
	}

	public static String getMenuSection() {
		return menuSection;
	}

	public static void setMenuSection(String menuSection) {
		ShopController.menuSection = menuSection;
	}

}
