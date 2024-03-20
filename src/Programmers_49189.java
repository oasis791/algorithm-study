import java.util.Arrays;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

public class Programmers_49189 {
    static class Solution {
        static int[] dist;
        static ArrayList<Integer>[] graph;
        public int solution(int n, int[][] edge) {
            dist = new int[n + 1];
            graph = new ArrayList[n + 1];

            for (int i = 1; i <= n; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int i = 0; i < edge.length; i++) {
                graph[edge[i][0]].add(edge[i][1]);
                graph[edge[i][1]].add(edge[i][0]);
            }

            Arrays.fill(dist, Integer.MAX_VALUE);

            dist[1] = 0;
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(1);

            while(!queue.isEmpty()) {
                int cur = queue.poll();

                for (int i = 0; i < graph[cur].size(); i++) {
                    int next = graph[cur].get(i);

                    if(dist[next] > dist[cur] + 1) {
                        queue.offer(next);
                        dist[next] = dist[cur] + 1;
                    }
                }
            }

            int max = 0;
            int count = 0;
            for (int i = 2; i <= n; i++) {
                if(max < dist[i]) {
                    max = dist[i];
                    count = 1;
                } else if (max == dist[i]) {
                    count++;
                }
            }

            return count;
        }
    }
}
