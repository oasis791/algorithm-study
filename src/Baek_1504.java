import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek_1504 {
    static int N, E;
    static int[][] matrix;
    static int[] dist;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        matrix = new int[N + 1][N + 1];


        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (matrix[a][b] != 0) {
                matrix[a][b] = Math.min(matrix[a][b], c);
                matrix[b][a] = Math.min(matrix[b][a], c);
            } else {
                matrix[a][b] = c;
                matrix[b][a] = c;
            }
        }
        st = new StringTokenizer(br.readLine());
        int V1 = Integer.parseInt(st.nextToken());
        int V2 = Integer.parseInt(st.nextToken());
        dist = new int[N + 1];

        // 1. 1 -> V1 -> V2 -> N
        int answer1 = 0;
        answer1 += dijkstra(1, V1);
        answer1 += dijkstra(V1, V2);
        answer1 += dijkstra(V2, N);
        // 2. 1 -> V2 -> V1 -> N
        int answer2 = 0;
        answer2 += dijkstra(1, V2);
        answer2 += dijkstra(V2, V1);
        answer2 += dijkstra(V1, N);

        bw.write(String.valueOf((answer1 >= 200000000 && answer2 >= 200000000) ? -1 : Math.min(answer1, answer2)));
        bw.flush();
        bw.close();
        br.close();
    }

    static int dijkstra(int start, int end) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        Arrays.fill(dist, 200000000);
        dist[start] = 0;

        pq.offer(new int[]{start, dist[start]});
        while (!pq.isEmpty()) {
            int[] data = pq.poll();
            int node = data[0];
            int weight = data[1];

            for (int i = 1; i <= N; i++) {
                if (i != node && matrix[node][i] != 0 && weight + matrix[node][i] < dist[i]) {
                    dist[i] = weight + matrix[node][i];
                    pq.offer(new int[]{i, dist[i]});
                }
            }
        }
        return dist[end];
    }
}
