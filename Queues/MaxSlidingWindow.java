import java.util.*;

public class MaxSlidingWindow {

    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || k <= 0) return new int[0];
        int n = nums.length;
        int[] output = new int[n - k + 1];
        int outIndex = 0;

        Deque<Integer> dq = new ArrayDeque<>(); // store indices

        for (int i = 0; i < n; i++) {
            //Remove indices out of current window
            while (!dq.isEmpty() && dq.peekFirst() <= i - k) {
                dq.pollFirst();
            }

            //Maintain decreasing order in deque
            while (!dq.isEmpty() && nums[dq.peekLast()] <= nums[i]) {
                dq.pollLast();
            }

            //Add current index
            dq.offerLast(i);

            //Add to output once we have a full window
            if (i >= k - 1) {
                output[outIndex++] = nums[dq.peekFirst()];
            }
        }
        return output;
    }
        public static void main(String[] args) {
        int[] arr = {2, 7, 3, 1, 5, 2, 6, 2};
        int m = 4;
        int[] result = maxSlidingWindow(arr, m);

        for (int val : result) {
            System.out.print(val + " ");
        }
    }
}
