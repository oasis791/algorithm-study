import java.util.Queue;
import java.util.LinkedList;

public class Programmers_42583 {
    static class Solution {
        public static int solution(int bridge_length, int weight, int[] truck_weights) {

            int time = 0;
            Queue<int[]> queue = new LinkedList<>();
            int index = 0;
            int curWeight = 0;

            while (index < truck_weights.length || !queue.isEmpty()) {
                time++;

                while (!queue.isEmpty() && queue.peek()[1] == time) {
                    int[] poll = queue.poll();
                    curWeight -= poll[0];
                }


                if (queue.size() < bridge_length && (index < truck_weights.length)) {
                    if (curWeight + truck_weights[index] <= weight) {
                        curWeight += truck_weights[index];
                        queue.offer(new int[]{truck_weights[index], time + bridge_length});
                        index++;
                    }
                }
            }

            return time;
        }
    }
}