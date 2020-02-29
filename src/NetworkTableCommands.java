import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class NetworkTableCommands {
	NetworkTableInstance ntist;
	/**
	 * handles initialization of network tables
	 */
	public NetworkTableCommands() {
		ntist = NetworkTableInstance.getDefault();
		ntist.startClient("localhost");
		//ntist.startClientTeam(4664); // where TEAM=190, 294, etc, or use inst.startClient("hostname") or similar
		//ntist.startDSClient(); // recommended if running on DS computer; this gets the robot IP from the DS
	}
	
	/**
	 * gets a double located in specified table and entry
	 * @param _table - the table to look in
	 * @param _entry - the entry to look in
	 * @param _defaultVal - the fallback default value if no entry is found
	 */
	public double getDouble(String _table, String _entry, double _defaultVal) {
		NetworkTable table = ntist.getTable(_table);
		NetworkTableEntry entry = table.getEntry(_entry);
		return entry.getDouble(_defaultVal);
	}
	
	public boolean getBool(String _table, String _entry, boolean _defaultVal) {
		NetworkTable table = ntist.getTable(_table);
		NetworkTableEntry entry = table.getEntry(_entry);
		return entry.getBoolean(_defaultVal);
	}
	
	public String getString(String _table, String _entry, String _defaultVal) {
		NetworkTable table = ntist.getTable(_table);
		NetworkTableEntry entry = table.getEntry(_entry);
		return entry.getString(_defaultVal);
	}
	
	
	
}
