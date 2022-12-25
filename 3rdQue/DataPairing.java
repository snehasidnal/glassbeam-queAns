import java.util.LinkedList;
import java.util.Queue;

public class DataPairing {
    public static void main(String[] args) {
        Queue<Data> channel1 = new LinkedList<>();
        Queue<Data> channel2 = new LinkedList<>();
        channel1.add(new Data("R", 1, 1));
        channel1.add(new Data("R", 1, 2));
        channel1.add(new Data("R", 1, 3));
        channel1.add(new Data("B", 1, 4));
        channel1.add(new Data("B", 1, 8));
        channel1.add(new Data("G", 1, 5));
        channel2.add(new Data("B", 2, 6));
        channel2.add(new Data("B", 2, 8));
        channel2.add(new Data("R", 2, 9));
        channel2.add(new Data("G", 2, 10));
        channel2.add(new Data("B", 2, 7));
        channel2.add(new Data("R", 2, 20));

        while (!channel1.isEmpty() && !channel2.isEmpty()) {
            Data data1 = channel1.peek();
            Data data2 = channel2.peek();
            if (data1.type.equals(data2.type)) {
                System.out.println("(" + data1 + ", " + data2 + ")");
                channel1.poll();
                channel2.poll();
            } else if (channel1.size() > channel2.size()) {
                channel2.add(channel2.poll());
            } else {
                channel1.add(channel1.poll());
            }
        }
    }
}

class Data {
    String type;
    int channelNumber;
    int uniqueID;

    public Data(String type, int channelNumber, int uniqueID) {
        this.type = type;
        this.channelNumber = channelNumber;
        this.uniqueID = uniqueID;
    }

    @Override
    public String toString() {
        return type + channelNumber + "_" + uniqueID;
    }
}