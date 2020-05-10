import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NetworkManager {
	ServerSocket serversocket;
	NetworkTableCommands netCommand;

	public NetworkManager() throws IOException {
		serversocket = new ServerSocket(1133, 10);
		netCommand = new NetworkTableCommands();
	}

	/**
	 * this will block until data is received to process.
	 * 
	 * @return
	 * @throws IOException
	 */
	public void handleInput() throws IOException {
		Socket client = serversocket.accept();

		Scanner scanner = new Scanner(client.getInputStream());
		PrintWriter pw = new PrintWriter(client.getOutputStream(), true);

		//System.out.println("Waiting for data ....");
		String input = scanner.nextLine();
		//System.out.println("data received sending message back");

		pw.println(ParseInput(input));

		client.close();
	}

	/**
	 * takes in a string and extracts the command and arguments. Then calls and
	 * returns the data in the form of a string
	 * 
	 * @param _input
	 * @return
	 */
	public String ParseInput(String _input) {
		String command = _input.substring(0, _input.indexOf(";"));
		List<String> args = getArgs(_input);
		/*System.out.println("Args");
		for(String arg : args){
			System.out.print(arg + ", ");
		}
		System.out.println();*/
		switch (command) {
		case ("getDouble"):
			return Double.toString(netCommand.getDouble(args.get(0), args.get(1), Double.parseDouble(args.get(2))));
		case ("getBool"):
			return String.valueOf(netCommand.getBool(args.get(0), args.get(1), Boolean.parseBoolean(args.get(2))));
		case ("getString"):
			return netCommand.getString(args.get(0), args.get(1), args.get(1));
		case ("setDouble"):
			return netCommand.setDouble(args.get(0), args.get(1), Double.parseDouble(args.get(2)));
		case ("setBool"):
			return netCommand.setBool(args.get(0), args.get(1), Boolean.parseBoolean(args.get(2)));
		case ("setString"):
			return netCommand.setString(args.get(0), args.get(1), args.get(2));
		default:
			return "Error unknown commnad";
		}
	}

	/**
	 * gets all terms enclosed by semicolons in input
	 * 
	 * @param _input
	 * @return a list of arguments
	 */
	private List<String> getArgs(String _input) {
		List<String> args = new ArrayList<String>();
		for (int i = 1; getOccurrenceIndex(i + 1, _input, ";") != -1; i++) {
			args.add(_input.substring(getOccurrenceIndex(i, _input, ";") + 1, getOccurrenceIndex(i + 1, _input, ";")));

		}

		return args;
	}

	/**
	 * gets the index of the nth occurrence of a character in a string. Returns -1
	 * if the character is not found
	 * 
	 * @param _occurrence - the occurrence amount
	 * @param _input      - the string
	 * @param _character  - the character to look for
	 * @return
	 */
	public int getOccurrenceIndex(int _occurrence, String _input, String _character) {
		if (_input.contains(_character)) {
			int index = 0;
			int tempIndex = 0;
			// index starts at 0 and moves up the string to each occurrence of character
			for (int i = 0; i < _occurrence; i++) {
				if (!_input.contains(_character)) {
					index = -1;
					break;
				}
				tempIndex = _input.indexOf(_character);
				_input = _input.substring(tempIndex + 1);
				if (i == 0) {
					index += tempIndex;
				} else {
					index += tempIndex + 1;
				}
			}

			return index;
		} else {
			return -1;
		}

	}

}
