import java.io.*;
import java.util.StringTokenizer;

public class Baek_15652 {
    static int N, M;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= N; i++) {
            dfs(i, 1, "");
        }
        bw.close();
        br.close();
    }

    static void dfs(int node, int depth,  String s) throws IOException {
        if (depth == M) {
            s += node + "\n";
            bw.write(s);
            bw.flush();
            return;
        }

        s += node + " ";
        for (int i = node; i <= N; i++) {
            dfs(i, depth + 1, s);
        }
    }
}
