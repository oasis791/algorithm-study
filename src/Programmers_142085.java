import java.util.PriorityQueue;

public class Programmers_142085 {
    static class Solution {
        public int solution(int n, int k, int[] enemy) {

            if(enemy.length <= k) {
                return enemy.length;
            }

            PriorityQueue<Integer> pq = new PriorityQueue<>();
            for (int i = 0; i < enemy.length; i++) {

                pq.offer(enemy[i]);

                if(pq.size() > k) {
                    int poll = pq.poll();

                    if(n < poll) {
                        return i;
                    }
                    n -= poll;
                }
            }

            return enemy.length;
        }
    }
}
