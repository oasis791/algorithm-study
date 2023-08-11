import java.io.*;
import java.util.*;

public class Baek_15651 {
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        dfs(n, m, 1, "");

        System.out.println(sb);
    }

    static void dfs(int n, int m, int depth, String s) {
        if(depth > m) {
            sb.append(s).append("\n");
            return;
        }

        for(int i = 1; i <= n; i++) {
            dfs(n, m, depth + 1, s + i + " ");
        }
    }
}
