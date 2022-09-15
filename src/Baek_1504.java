//1504번 특정한 최단 경로
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek_1504 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        int[][] graph = new int[N + 1][N + 1];
        int[] dist = new int[N + 1];

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(bf.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph[start][end] = weight;
        }

        st = new StringTokenizer(bf.readLine(), " ");
        int[] must = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};

        while (true) {

        }
    }
}
