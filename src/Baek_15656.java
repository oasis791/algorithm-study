import java.io.*;
import java.util.*;

public class Baek_15656 {
    static int n, m;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];

        st = new StringTokenizer(bf.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        dfs(arr, 1, "");
        System.out.println(sb);
    }

    static void dfs(int[] arr, int depth, String s) {
        if(depth > m) {
            sb.append(s).append("\n");
            return;
        }

        for(int i = 0; i < n; i++) {
            dfs(arr, depth + 1, s + arr[i] + " ");
        }
    }
}
