import java.util.PriorityQueue;

public class Programmers_42626 {

    static class Solution {
        public static int solution(int[] scoville, int K) {
            int answer = 0;
            PriorityQueue<Integer> pq = new PriorityQueue<>();

            for (int i = 0; i < scoville.length; i++) {
                pq.offer(scoville[i]);
            }

            while (pq.size() >= 2) {
                int first = pq.poll();

                if (first >= K) {
                    return answer;
                }

                answer++;
                int second = pq.poll();
                pq.offer(first + (second * 2));
            }

            if (pq.size() >= 1) {
                if (pq.poll() >= K) {
                    return answer;
                }
            }
            return -1;
        }
    }
}
