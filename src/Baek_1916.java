import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek_1916 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        }); // data[0] 에는 노드번호, data[1]에는 가중치

        int N = Integer.parseInt(bf.readLine());
        int M = Integer.parseInt(bf.readLine());
        int[][] busInfo = new int[N + 1][N + 1];
        for (int i = 0; i <= N; i++) {
            Arrays.fill(busInfo[i], Integer.MAX_VALUE);
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            busInfo[start][end] = Math.min(busInfo[start][end], weight);
        }
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int startNode = Integer.parseInt(st.nextToken());
        int targetNode = Integer.parseInt(st.nextToken());

        int[] distances = new int[N + 1];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[startNode] = 0;
        pq.offer(new int[]{startNode, 0});

        while (!pq.isEmpty()) {
            int[] data = pq.poll();
            int node = data[0];
            int weight = data[1];
            if (weight > distances[node])
                continue;
            for (int i = 1; i <= N; i++) { // 인접 행렬 탐색
                if(i == node)
                    continue;
                if (busInfo[node][i] != Integer.MAX_VALUE && (weight + busInfo[node][i]) < distances[i]) {
                    distances[i] = weight + busInfo[node][i];
                    pq.offer(new int[]{i, distances[i]});
                }
            }
        }
        System.out.println(distances[targetNode]);
    }
}