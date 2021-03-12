//9370번 미확인 도착지
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baek_9370 {
    private static int T;
    private static ArrayList<Property_9370>[] graph;
    private static PriorityQueue<Property_9370> pq = new PriorityQueue<>(new Comparator<Property_9370>() {
        @Override
        public int compare(Property_9370 o1, Property_9370 o2) {
            if(o1.weight < o2.weight)
                return -1;
            else if(o1.weight > o2.weight)
                return 1;
            else
                return 0;
        }
    });
    private static int[][] dist;
    private static int[] maybe;
    private static ArrayList<Integer> result = new ArrayList<>();
    static class Property_9370 {
        public int arrive;
        public int weight;

        public Property_9370(int v, int w) {
            arrive = v;
            weight = w;
        }

    }
    private static void dijkstra(int index) {
        while (!pq.isEmpty()) {
            Property_9370 temp = pq.poll();
            for (Property_9370 i : graph[temp.arrive]) {
                if (dist[index][i.arrive] > dist[index][temp.arrive] + i.weight) {
                    dist[index][i.arrive] = dist[index][temp.arrive] + i.weight;
                    pq.offer(new Property_9370(i.arrive, dist[index][i.arrive]));
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(bf.readLine());
        for (int test = 0; test < T; test++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            //n : 교차로 수 , m : 도로 수 , t : 목적지 후보 수
            int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken()), t = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(bf.readLine());
            //s : 예술가들의 출발지 , g,h 교차로 사이에 있는 도로는 필수적으로 지나가야함
            int s = Integer.parseInt(st.nextToken()), g = Integer.parseInt(st.nextToken()), h = Integer.parseInt(st.nextToken());
            // dist 무한대로 초기화
            dist = new int[3][n + 1];
            for(int i=0;i<3;i++) {
                Arrays.fill(dist[i], Integer.MAX_VALUE);
            }
            // graph 초기화
            graph = new ArrayList[n + 1];
            for (int i = 0; i < n + 1; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(bf.readLine(), " ");
                int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken()), d = Integer.parseInt(st.nextToken());
                graph[a].add(new Property_9370(b, d));
                graph[b].add(new Property_9370(a, d));
            }
            maybe = new int[t];
            for (int i = 0; i < t; i++) {
                maybe[i] = Integer.parseInt(bf.readLine());
            }
            dist[0][s] = 0;
            pq.offer(new Property_9370(s, 0));
            dijkstra(0);
            dist[1][g] = 0;
            pq.offer(new Property_9370(g, 0));
            dijkstra(1);
            dist[2][h] = 0;
            pq.offer(new Property_9370(h, 0));
            dijkstra(2);
            int[][] tempResult = new int[t][2];
            for (int i = 0; i < t; i++) {
                tempResult[i][0] = dist[0][g] + dist[1][h] + dist[2][maybe[i]]; // s -> g -> h -> maybe[i]
                tempResult[i][1] = dist[0][h] + dist[2][g] + dist[1][maybe[i]]; // s -> h -> g -> maybe[i]
            }

            for (int i = 0; i < t; i++) {
                int temp;
                temp = Math.min(tempResult[i][0], tempResult[i][1]);
                if(temp==dist[0][maybe[i]])
                    result.add(maybe[i]);
            }
            Collections.sort(result);
            for (int i = 0; i < result.size(); i++) {
                System.out.print(result.get(i)+" ");
            }
            System.out.println();
            result.clear();
        }
    }
}
