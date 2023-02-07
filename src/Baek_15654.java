import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek_15654 {
    static int N, M;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int arr[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        int index = 0;
        st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            arr[index++] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        for (int i = 0; i < N; i++) {
            boolean[] visited = new boolean[N];
            visited[i] = true;
            dfs(i, visited, 1, "");
        }

        br.close();
        bw.close();
    }

    static void dfs(int index, boolean[] visited, int depth, String s) throws IOException {
        if (depth == M) {
            s += arr[index] + "\n";
            bw.write(s);
            bw.flush();
            return;
        }

        s += arr[index] + " ";
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(i, visited, depth + 1, s);
                visited[i] = false;
            }
        }
    }
}
