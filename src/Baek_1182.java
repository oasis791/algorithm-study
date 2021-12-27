//1182번 부분수열의 합
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek_1182 {
    private static int N;
    private static int S;
    private static int[] arr;
    private static int count = 0;

    public static void dfs(int index, int sum, boolean[] visited) {
        visited[index] = true;
        boolean[] newVisited = Arrays.copyOf(visited, visited.length);
        sum += arr[index];
        if (sum == S)
            count++;
        for (int i = index + 1; i < N; i++) {
            if(!visited[i])
                dfs(i, sum, newVisited);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        arr = new int[N];
        boolean[] visited = new boolean[N];

        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                visited[j] = false;
            dfs(i, 0,visited);
        }
        System.out.println(count);
    }
}
