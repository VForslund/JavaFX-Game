package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;



public class LoadSave {

	public void test() {
		System.out.println( "works");
	}
	
	public static void main(String args[]) {
		
	}

	public static void saveGame(int x, int y, int hp, int maxHp, int Money, int atk, int speed, int def) {
		//save the players x & y coordinates in a .txt file
		String path = new File("").getAbsolutePath();
		System.out.println("" + path);
		File file = new File(path + "/savedFile.txt");
		try {
			String data = 
					x + "\n"+
					y + "\n"+
					hp + "\n"+
					maxHp + "\n"+
					Money + "\n"+ 
					atk + "\n"+ 
					speed + "\n"+ 
					def;
					
		FileOutputStream os = new FileOutputStream(file);
		os.write(data.getBytes());
		os.close();
	
		}catch(IOException event) {
		}
		}

	public static void loadGame() {
		// read the x & y cordinates from the .txt file and assigne them to the variables in player
		// with player.setX(int x) & player.setY(int y) methods.
		Player player = new Player();
		int [] values = new int[8];
		String path = new File("").getAbsolutePath();
		String file = "savedFile.txt";
		FileInputStream fis = null;
		BufferedReader reader = null;

		try {
			fis = new FileInputStream(file);
			if(fis != null) {

				reader = new BufferedReader(new InputStreamReader(fis));
				int i = 0;
				String line = reader.readLine();
				while(line != null){
					if(i < 5) {
						values[i] = Integer.parseInt(line);
					}
					line = reader.readLine();
					i++;
				}
				Player.setX(values[0]);
				Player.setY(values[1]);
				Player.setHP(values[2]);
				Player.setMaxHP(values[3]);
				BattleController.playerHP.set(values[2]);
				BattleController.playerMaxHP.set(values[3]);
				MapController.Money.set(values[4]);
				Player.setAtk(values[5]);
				Player.setSpeed(values[6]);
				Player.setDef(values[7]);
				BattleController.updateHPbar();
				
				
			}

		} catch (FileNotFoundException ex) {
		} catch(IOException e) {
		}
		
	}
}

	


