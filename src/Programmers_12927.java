import java.util.PriorityQueue;
import java.util.Comparator;

public class Programmers_12927 {
    static class Solution {
        public long solution(int n, int[] works) {
            long answer = 0;
            PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());

            for (int i = 0; i < works.length; i++) {
                pq.offer(works[i]);
            }

            for (int i = 0; i < n; i++) {
                if(pq.isEmpty()) 
                    return 0;

                int temp = pq.poll();
                if(temp - 1 > 0) {
                    pq.offer(temp - 1);
                }
            }

            while(!pq.isEmpty()) {
                int temp = pq.poll();
                answer += temp * temp;
            }

            return answer;
        }
    }
}
