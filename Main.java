package application;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

//implements Runnable calls the run() over and over again.
public class Main extends Application implements Runnable {

	//Objects

	Graphics graphics = new Graphics();
	Player player = new Player();

	//Variables
	private boolean running = false;
	private Thread thread;


	@Override
	public void start(Stage primaryStage) {

		//Sets the primaryStage from the graphics class
		primaryStage = graphics.getMainStage();

		System.out.println("Game is running: " + running);

		//Shows the window & stage
		primaryStage.show();

		startGame();

		System.out.println("Game is running: " + running);

		//So the game stops when closing down the window
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent event) {
				running = false;
				System.exit(1);
				//stopGame();
			}
		});
	}

	//This updates the game 
	public void run() {

		long lastTime = System.nanoTime();
		final double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();

		// boolean condition keeping the game running		
		while (running) {


			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1) {
				//methods that needs to be updated are put here

				graphics.frame();

				updates++;	
				delta--;
			}
			//FPS counter
			frames++;
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				//System.out.println(updates + "Ticks, Fps" + frames);
				updates = 0;
				frames = 0;
			}
		}
	}
	//This starts a thread
	public synchronized void startGame() {
		if(running)
			return;

		running = true;
		thread = new Thread(this);
		thread.start();
	}
	public static void main(String[] args) {
		launch(args);
	}
}
