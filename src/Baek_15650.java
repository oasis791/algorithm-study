import java.io.*;
import java.util.StringTokenizer;

public class Baek_15650 {
    static int N;
    static int M;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[] num = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            num[i] = i;
        }

        for (int i = 1; i <= N; i++) {
            boolean[] visited = new boolean[N + 1];
            visited[i] = true;
            dfs(i, 1, visited, "");
        }
        br.close();
    }

    static void dfs(int node, int depth, boolean[] visited, String s) throws IOException {
        if (depth == M) {
            s = s + node + "\n";
            bw.write(s);
            bw.flush();
            return;
        }

        s += node + " ";
        for (int i = node; i <= N; i++) {
            if(!visited[i]) {
                visited[i] = true;
                dfs(i, depth + 1, visited, s);
                visited[i] = false;
            }
        }
    }
}
