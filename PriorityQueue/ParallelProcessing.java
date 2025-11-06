import java.util.*;

public class ParallelProcessing {
    static class Thread implements Comparable<Thread> {
        long finishTime;
        int index;

        Thread(long finishTime, int index) {
            this.finishTime = finishTime;
            this.index = index;
        }

        @Override
        public int compareTo(Thread other) {
            if (this.finishTime != other.finishTime)
                return Long.compare(this.finishTime, other.finishTime);
            return Integer.compare(this.index, other.index);
        }
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int n = sc.nextInt(); 
            int m = sc.nextInt(); 

            long[] jobs = new long[m];
            for (int i = 0; i < m; i++)
                jobs[i] = sc.nextLong();

            PriorityQueue<Thread> pq = new PriorityQueue<>();
            for (int i = 0; i < n; i++)
                pq.add(new Thread(0, i)); 

            for (int i = 0; i < m; i++) {
                Thread t = pq.poll(); 

                System.out.println(t.index + " " + t.finishTime);

                t.finishTime += jobs[i]; 
                pq.add(t); 
            }
        }
    }
}
