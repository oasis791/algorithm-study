import java.util.StringTokenizer;
import java.util.PriorityQueue;
import java.util.Comparator;

public class Programmers_42628 {
    static class Solution {
        public int[] solution(String[] operations) {

            PriorityQueue<Integer> maxQ = new PriorityQueue<>(Comparator.reverseOrder());
            PriorityQueue<Integer> minQ = new PriorityQueue<>();

            for (int i = 0; i < operations.length; i++) {
                StringTokenizer st = new StringTokenizer(operations[i]);
                String op = st.nextToken();
                int num = Integer.parseInt(st.nextToken());
                if(op.equals("I")) {
                    minQ.offer(num);
                    maxQ.offer(num);
                } else {
                    if(num == 1 && !maxQ.isEmpty()) {
                        minQ.remove(maxQ.poll());
                    } else {
                        if(!minQ.isEmpty()) {
                            maxQ.remove(minQ.poll());
                        }
                    }
                }
            }

            int[] answer = new int[2];
            if(maxQ.isEmpty() || minQ.isEmpty()) {
                answer[0] = 0;
                answer[1] = 0;
            } else {
                answer[0] = maxQ.poll();
                answer[1] = minQ.poll();
            }
            return answer;
        }
    }
}
