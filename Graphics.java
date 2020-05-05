package application;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Graphics {

	private Pane root;
	private Pane root2;
	private Pane root3;
	private Scene mainScene;
	private Scene combatScene;
	private Scene shopScene;
	private Stage mainStage;

	//Objects
	Circle circle = new Circle();

	Image image = new Image(getClass().getResourceAsStream("overlay.png"));

	private static Paint color = Color.BLUE;

	//Constructor of Graphics.
	public Graphics() {
		//The window
		try {
			root = (Pane)FXMLLoader.load(getClass().getResource("Map.fxml"));
		} catch (IOException e) {
			System.err.println("Loading root failed");
			e.printStackTrace();
		}		

		try {
			root2 = (Pane)FXMLLoader.load(getClass().getResource("Battle.fxml"));
		} catch (IOException e) {
			System.err.println("Loading root2 failed");
			e.printStackTrace();
		}
		
		try {
			root3 = (Pane)FXMLLoader.load(getClass().getResource("Shop.fxml"));
		} catch (IOException e) {
			System.err.println("Loading root3 failed");
			e.printStackTrace();
		}

		mainScene = new Scene(root,1400,800);
		mainStage = new Stage();

		combatScene = new Scene(root2,1400,800);
		shopScene = new Scene(root3,1400,800);
		
		
		mainStage.setScene(mainScene);

		ImageView overlay = new ImageView(image);

		//The player
		circle.setCenterX(Player.getX());
		circle.setCenterY(Player.getY());
		circle.setRadius(20);
		circle.setFill(getColor());

		root.getChildren().add(circle);
		root.getChildren().add(overlay);


	}


	//Updates all the graphics called in Main
	public void frame() {


		//this will solves a thread issue, to do FX application stuff in a non FX application thread. 
		Platform.runLater(() -> {

			if (Player.isBattle()) {

				Player.setVelX(0);
				Player.setVelY(0);
				mainStage.setScene(combatScene);

			} else if (Player.isShop()) {
				mainStage.setScene(shopScene);

			} else {

				circle.setFill(getColor());
				mainStage.setScene(mainScene);
			}

		});

		getMainScene().setOnKeyPressed(e -> {
			Player.move(e);
		});

		getMainScene().setOnKeyReleased(e -> {
			Player.stop(e);	
		});

		Player.update();
		position();
	}


	// moves the player. 
	public void position() {

		Player.setX(Player.getX() + Player.getVelX());
		Player.setY(Player.getY() + Player.getVelY());

		circle.setCenterX(Player.getX());
		circle.setCenterY(Player.getY());
	}

	//Getters and setters
	public Stage getMainStage() {
		return mainStage;
	}

	public Pane getRoot() {
		return root;
	}


	public Scene getMainScene() {
		return mainScene;
	}

	public Circle getCircle() {
		return circle;
	}

	public void setCircle(Circle circle) {
		this.circle = circle;
	}


	public void setRoot(Pane root) {
		this.root = root;
	}


	public static Paint getColor() {
		return color;
	}


	public static void setColor(Paint color) {
		Graphics.color = color;
	}
}
