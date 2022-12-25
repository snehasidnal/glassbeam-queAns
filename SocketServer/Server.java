import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

import org.json.JSONObject;

public class Server {
    public static void main(String[] args) throws IOException {
        // Create a DatagramSocket to listen for incoming packets on port 12345
        DatagramSocket socket = new DatagramSocket(12345);

        // Create a Scanner to read input from the user



        while (true) {
            // Read a message from the user
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter a Teams 1: ");
            String[] team = new String[2];
            team[0] = scanner.nextLine();
            System.out.print("Enter a Teams 2: ");
            team[1] = scanner.nextLine();
            System.out.print("Enter a Location: ");
            String location = scanner.nextLine();
            System.out.print("Enter a State: ");
            String state = scanner.nextLine();

            // Create a JSON object from the message
            JSONObject json = new JSONObject();
            json.put("teams", team);
            json.put("location",location);
            json.put("state",state);

            // Convert the JSON object to a string
            String jsonString = json.toString();

            // Convert the string to a byte array
            byte[] data = jsonString.getBytes();

            // Create a DatagramPacket to send the data to the client
            InetAddress address = InetAddress.getByName("localhost");
            DatagramPacket packet = new DatagramPacket(data, data.length, address, 54321);

            // Send the packet
            socket.send(packet);
        }
    }
}
