//1504번 특정한 최단 경로
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baek_1504 {
    private static final int MAX = 999999;
    private static int N;
    private static int E;
    private static int[][] dist;
    private static int[] must;
    private static ArrayList<Property_1504>[] graph;
    private static PriorityQueue<Property_1504> pq = new PriorityQueue<>(new Comparator<Property_1504>() {
        @Override
        public int compare(Property_1504 o1, Property_1504 o2) {
            if(o1.weight < o2.weight)
                return -1;
            else if(o1.weight > o2.weight)
                return 1;
            else
                return 0;
        }
    });
    static class Property_1504{
        public int arrive;
        public int weight;

        public Property_1504(int v, int w) {
            this.arrive = v;
            this.weight = w;
        }
    }
    private static void dijkstra(int x,int index) {
        dist[index][x] = 0;
        pq.offer(new Property_1504(x, 0));
        while (!pq.isEmpty()) {
            Property_1504 temp = pq.poll();
            for (Property_1504 i : graph[temp.arrive]) {
                if (dist[index][i.arrive] > dist[index][temp.arrive] + i.weight) {
                    dist[index][i.arrive] = dist[index][temp.arrive] + i.weight;
                    pq.offer(new Property_1504(i.arrive, dist[index][i.arrive]));
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        dist = new int[3][N+1];
        for (int i = 0; i < 3; i++) {
            Arrays.fill(dist[i],MAX);
        }
        graph = new ArrayList[N+1];
        for (int i = 1; i < N + 1; i++)
            graph[i] = new ArrayList<>();
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(bf.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a].add(new Property_1504(b, c));
            graph[b].add(new Property_1504(a, c));
        }
        st = new StringTokenizer(bf.readLine(), " ");
        must = new int[2];
        must[0] = Integer.parseInt(st.nextToken());
        must[1] = Integer.parseInt(st.nextToken());
        if (E == 0) {
            System.out.println(-1);
            System.exit(0);
        }
        dijkstra(1, 0);
        dijkstra(must[0], 1);
        dijkstra(must[1], 2);
        int result1 = dist[0][must[0]] + dist[1][must[1]] + dist[2][N];
        int result2 = dist[0][must[1]] + dist[2][must[0]] + dist[1][N];
        int result = Math.min(result1, result2);
        if(result>=MAX)
            System.out.println(-1);
        else
            System.out.println(result);
    }
}
