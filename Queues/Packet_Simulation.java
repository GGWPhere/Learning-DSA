import java.util.*;

public class Packet_Simulation {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int S = sc.nextInt();   // buffer size
            int n = sc.nextInt();   // number of packets

            //long data type for big packets
            Deque<Long> finishTimes = new ArrayDeque<>();

            for (int i = 0; i < n; i++) {
                long arrival  = sc.nextLong();
                long process  = sc.nextLong();

                //Remove finished packets
                while (!finishTimes.isEmpty() && finishTimes.peekFirst() <= arrival) {
                    finishTimes.pollFirst();
                }

                //Check if buffer is full
                if (finishTimes.size() == S) {
                    System.out.println(-1);
                    continue;
                }

                //Accept packet and compute start time
                long start;
                if (finishTimes.isEmpty()) {
                    start = arrival;
                } else {
                    start = Math.max(arrival, finishTimes.peekLast());
                }

                long finish = start + process;
                finishTimes.addLast(finish);

                System.out.println(start);
            }
        }
    }
}
