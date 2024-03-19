import java.util.*;

public class Programmers_42861 {
    static class Solution {
        static int[] parent;

        public int solution(int n, int[][] costs) {
            int answer = 0;
            PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o1[2] - o2[2];
                }
            });

            parent = new int[n];

            for (int i = 0; i < costs.length; i++) {
                pq.offer(new int[]{costs[i][0], costs[i][1], costs[i][2]});
            }

            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }

            while(!pq.isEmpty()) {
                int[] poll = pq.poll();

                int p1 = poll[0];
                int p2 = poll[1];
                int dist = poll[2];

                if(find(p1) == find(p2)) 
                    continue;

                answer += dist;
                union(p1, p2);
            }

            return answer;
        }

        static int find (int p) {
            if(parent[p] == p) {
                return p;
            } else {
                return parent[p] = find(parent[p]);
            }
        }

        static void union(int p1, int p2) {
            p1 = find(p1);
            p2 = find(p2);

            if(p1 > p2) {
                parent[p2] = p1;
            } else {
                parent[p1] = p2;
            }
        }
    }
}
