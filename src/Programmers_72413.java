import java.util.*;

public class Programmers_72413 {
    static class Solution {
        static int answer = Integer.MAX_VALUE;
        static ArrayList<int[]>[] map;

        public int solution(int n, int s, int a, int b, int[][] fares) {

            map = new ArrayList[n + 1];

            for (int i = 1; i <= n; i++) {
                map[i] = new ArrayList<>();
            }

            for (int i = 0; i < fares.length; i++) {
                int v1 = fares[i][0];
                int v2 = fares[i][1];
                int weight = fares[i][2];

                map[v1].add(new int[]{v2, weight});
                map[v2].add(new int[]{v1, weight});
            }

            // S에서 Dijkstra
            int[] sPath = new int[n + 1];
            dijkstra(s, n, sPath);
            // A에서 Dijkstra
            int[] aPath = new int[n + 1];
            dijkstra(a, n, aPath);
            // B에서 Dijkstra
            int[] bPath = new int[n + 1];
            dijkstra(b, n, bPath);

            // S에서 각 노드 -> A + 노드 -> B 의 최소값 찾기

            for (int i = 1; i <= n; i++) {
                if(sPath[i] != Integer.MAX_VALUE && aPath[i] != Integer.MAX_VALUE && bPath[i] != Integer.MAX_VALUE) {
                    answer = Math.min(answer, sPath[i] + aPath[i] + bPath[i]);
                }
            }
            return answer;
        }

        static void dijkstra (int start, int n, int[] path) {

            Arrays.fill(path, Integer.MAX_VALUE);
            path[start] = 0;

            PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<>() {
                @Override
                public int compare (int[] o1, int[] o2) {
                    return o1[1] - o2[1];
                }
            });

            pq.offer(new int[] {start, 0});

            while(!pq.isEmpty()) {
                int[] cur = pq.poll();

                for (int i = 0; i < map[cur[0]].size(); i++) {
                    int[] next = map[cur[0]].get(i);

                    if(path[next[0]] > path[cur[0]] + next[1]) {
                        path[next[0]] = path[cur[0]] + next[1];
                        pq.offer(new int[]{next[0], path[next[0]]});
                    }
                }
            }
        }
    }
}
