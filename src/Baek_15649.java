//15649번 N과 M(1)
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek_15649 {
    private static int N;
    private static int M;
    private static boolean[] visited;
    private static int[] result;
    private static int[] num;
    private static StringBuilder sb = new StringBuilder();
    private static void dfs(int index,int count){
        result[count - 1] = num[index];
        visited[index] = true;
        boolean[] temp = Arrays.copyOf(visited, visited.length);
        if (count == M) {
            for (int i = 0; i < M-1; i++) {
                System.out.print(result[i]+" ");
            }
            System.out.println(result[M-1]);
        }
        else {
            for (int i = 0; i < N; i++) {
                visited = Arrays.copyOf(temp, temp.length);
                if (!visited[i]) {
                    sb.append(" " + num[i]);
                    dfs(i, count + 1);
                    visited[i] = true;
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        num = new int[N];
        result = new int[M];
        int initNum = 1;
        for (int i = 0; i < N; i++)
            num[i] = initNum++;
        for (int i = 0; i < N; i++) {
            visited = new boolean[N];
            sb.append(num[i]);
            dfs(i,1);
        }
    }
}
