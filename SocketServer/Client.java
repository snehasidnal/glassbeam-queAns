import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;


public class Client {
    private static Map<String, Match> matches = new HashMap<>();

     private static class Match {
        String location;
        List teams;
        String battingTeam;
        int chasing;
        int runs;
        int overs;
        boolean started;
        boolean ended;

        Match(List teams, String location) {
            this.teams = teams;
            this.location = location;
        }

        public List getTeam() {
            return this.teams;
        }

         public String getLocation() {
             return this.location;
         }
         public int getRuns() {
             return this.runs;
         }
         public int getOvers() {
             return this.overs;
         }

         public String getBattingTeam() {
             return this.battingTeam;
         }
         public void setRuns(int runs) {
              this.runs = runs;
         }

         public void setOvers(int overs) {
             this.overs = overs;
         }
         public  void setBattingTeam(String battingTeam){
            this.battingTeam = battingTeam;
         }

         public void setChasing(int chasing){
            this.chasing = chasing;
         }
    }

    public static void listenOnChannel1() {
        try {
            DatagramSocket socket = new DatagramSocket(54321);
            while (true) {
                byte[] buffer = new byte[9999];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                // Parse JSON message and create or end a match as necessary
                String message = new String(packet.getData(), 0, packet.getLength());
//                System.out.println(message);

                JSONObject json = new JSONObject(message);
                JSONArray teams = json.getJSONArray("teams");
                String location = json.getString("location");
                String state = json.getString("state");
                if (state.equals("started")) {
                    // Check if match already exists
                    Match match = matches.get(teams.get(0));
                    if (match == null) {
                        match = new Match(teams.toList(), location);
                        matches.put((String) teams.get(0), match);
                    }

                } else if (state.equals("ended")) {

                    }
                }
        } catch (IOException e) {
            // Log error and exit thread
            System.out.println(e);
            return;
        }
    }

    public static void listenOnChannel2() {
        try {
            DatagramSocket socket1 = new DatagramSocket(54322);
            while (true) {
                byte[] buffer = new byte[9999];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket1.receive(packet);

                // Parse JSON message and create or end a match as necessary
                String message = new String(packet.getData(), 0, packet.getLength());

                JSONObject json = new JSONObject(message);
                JSONArray teams1 = json.getJSONArray("teams");
                String batting = json.getString("batting");
                int runs = json.getInt("runs");
                int overs =json.getInt("overs");
                int chasing =json.getInt("chasing");
                if(matches!= null){
                    Match match = matches.get(teams1.get(0));
                    if(match!= null ) {
                        match.setRuns(runs);
                        match.setChasing(chasing);
                        match.setOvers(overs);
                        match.setBattingTeam(batting);
                    }
                    }
                }
            }
        catch (IOException e) {
            // Log error and exit thread
            System.out.println(e);
        }
    }
    public static void main(String[] args) throws IOException {
        new Thread(Client::listenOnChannel1).start();
        new Thread(Client::listenOnChannel2).start();

//         Start command line interface for getting latest scores
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter the name of one of the teams playing the match: ");
            String teamName = scanner.nextLine();
            Match match = matches.get(teamName);
            if (match == null) {
                System.out.println("No match with " + teamName + " is currently in progress");
            } else {
                System.out.println("Match between " + match.getTeam().get(0) + " & " +match.getTeam().get(1) +
                        " at " + match.getLocation() + ". " + match.getBattingTeam() + " is batting and has scored " +
                        match.getRuns() + " runs in " + match.getOvers() + " overs.");
            }
        }

    }
}
