import java.io.IOException;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class main {
	NetworkManager networkManager;
	public static void main(String args[]) throws IOException {
		new main().run();		
	}
	
	private void run() throws IOException {
		networkManager = new NetworkManager();
		
		while(true) {
			networkManager.handleInput();
		}
		//System.out.println(networkManager.ParseInput("getDouble;vision;ki;-1;"));
	}
	
	
}
