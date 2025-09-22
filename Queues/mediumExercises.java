/* Medium level exercises (level 2)
 * 1. First Non-Repeating Character in a stream
 *   -Given a stream of Characters (arriving one by one), print first character that has not repeated yet at each step
 *   -Use queue to track candidates
 * 2. Circular Tour (Gas Station Problem)
 *   -Given petrol[i] and distance[i] find the first station from where truck can start and complete circle.
 *   -Queue can simulate the circular traversal
 * 3. Sliding Window Maximum (Using deque)
 *   -Given integer array 'nums' and a window size 'k'
 *    slide window size 'k' from left to right and return maximum element in each windows.
 */

//1. IDEA
/* Keep queue of characters that are candidates for "non-repeating"
 * Maintain frequency map (int[26] or HashMap) to count occurences
 * Each character: Increment frequency, enqueue it, while queue front character has freq>1, dequeue it.
 * each character: the front of queue is the current first non-repeating character
 */

//2. IDEA
/* You are driving around a circular route with several gas stations
 * gas[i] -> amount of fuel you can fill in station 'i'
 * cost[i] -> fuel needed to travel from station 'i' to station (i + 1)
 * Find starting station index from which you can complete entire circle once without running fuel
 * If total gas < total cost, it's impossible
 * Otherwise, single pass can find start:
 *  Track tank(current fuel) and start(candidate station)
 *  Traverse stations:
 *      tank += gas[i] - cost[i]
 *      if tank becomes negative:
 *          It means we cannot start from any station up to 'i'
 *          Set 'start - i+1' reset 'tank = 0'
 */

 //3. IDEA
 /* Maintain a deque (double-ended queue) of indices where:
  * Front of deque -> index of current window's maximum 
  * Indices in deque correspond to values in decreasing order
  * Iterate 'i' from 0.. n-1:
  *     - Remove from back all indices whose value '< nums[i]'  (they cant be max if current value is larger)
  *     - Add current index 'i' to back
  *     - Remove from front if it's out of window ('i - k + 1 > deque.front')
  *     - if 'i>= k-1' record 'nums[deque.front]' as window max
  */
 import java.util.*;

public class mediumExercises {

    //1
    static void printFirstNonRepeating(String stream) {
        int[] freq = new int[26];       // assuming lowercase letters a-z
        Queue<Character> q = new LinkedList<>();

        for (char ch : stream.toCharArray()) {
            freq[ch - 'a']++;           // increment frequency
            q.add(ch);                  // enqueue current char

            // Remove all chars from front with freq > 1
            while (!q.isEmpty() && freq[q.peek() - 'a'] > 1) {
                q.remove();
            }

            if (q.isEmpty()) {
                System.out.print("-1 ");
            } else {
                System.out.print(q.peek() + " ");
            }
        }
    }

    //2
    public static int canCompleteCircuit(int[] gas, int[] cost){
        int totalGas= 0, totalCost = 0;
        int tank = 0, start = 0;
        for(int i = 0; i < gas.length; i++){
            totalGas += gas[i];
            totalCost += cost[i];
            tank += gas[i] - cost[i];
            if(tank < 0){       //can't reach next station
                start = i+1;    //try next as starting point
                tank = 0;       //reset tank
            }
        }
        return (totalGas<totalCost) ? -1 : start;
    }

    //3.
    public static int[] maxSlidingWindow(int[] nums, int k){
        if(nums.length == 0 || k<=0) return new int[0];
        Deque<Integer> dq = new LinkedList<>(); //stores indices
        int[] result = new int[nums.length - k+1];
        int ri = 0;
        for(int i =0; i<nums.length; i++){
            //Remove indices out of window from front
            while(!dq.isEmpty() && dq.peekFirst() < i-k+1){
                dq.pollFirst();
            }
            //Remove smaller values from back
            while(!dq.isEmpty() && nums[dq.peekLast()] < nums[i]){
                dq.pollLast();
            }
            //Add current index
            dq.offerLast(i);
            //add max to result once first window is ready
            if(i>=k-1){
                result[ri++] = nums[dq.peekFirst()];
            }
        }
        return result;
    }

        public static void main(String[] args) {
        int[] gas = {4, 6, 7, 4};
        int[] cost = {6,5,3,5};
        int startStation = canCompleteCircuit(gas, cost);
        System.out.println("Start station: " + startStation);

        int[] nums = {1,3,-1,-3,5,3,6,7};
        int k = 3;
        int[] maxes = maxSlidingWindow(nums, k);
        System.out.println(Arrays.toString(maxes));

        String stream = "aabc";   // try other inputs
        printFirstNonRepeating(stream);
    }
}
