import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek_1389 {
    private static ArrayList<Integer>[] graph;
    private static boolean[] visited;
    private static int[] result;
    private static int[] temp;
    public static void main(String[] args) throws IOException {
        int N, M;
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        result = new int[N + 1];
        visited = new boolean[N + 1];
        temp = new int[N + 1];
        graph = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++)
            graph[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            graph[start].add(end);
            graph[end].add(start);
        }

        for (int i = 1; i <= N; i++) {
            Arrays.fill(temp, Integer.MAX_VALUE);
            temp[i] = 0;
            for(int j=0;j<graph[i].size();j++) {
                Arrays.fill(visited, false);
                visited[i] = true;
                dfs(graph[i].get(j), 1);
            }
            int sum = 0;
            for (int j = 1; j <= N; j++)
                sum += temp[j];
            result[i] = sum;
        }

        int min = Integer.MAX_VALUE;
        int res = 0;
        for (int i = 1; i <= N; i++) {
            if (result[i] < min) {
                min = result[i];
                res = i;
            }
        }
        System.out.println(res);
    }

    static void dfs(int start, int depth) {
        visited[start] = true;
        if(temp[start] > depth)
            temp[start] = depth;
        for (int i = 0; i < graph[start].size(); i++) {
            if (!visited[graph[start].get(i)]) {
                dfs(graph[start].get(i), depth + 1);
            }
        }
    }
}
