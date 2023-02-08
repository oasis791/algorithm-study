import java.io.*;
import java.util.*;

public class Baek_15666 {
    static int N, M;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static Set<String> set = new LinkedHashSet<>();
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
            dfs(i, 1, "");
        }

        for (String s : set) {
            bw.write(s);
        }

        bw.flush();
        br.close();
        bw.close();
    }

    static void dfs(int index, int depth, String s) throws IOException {
        if (depth == M) {
            s += arr[index] + "\n";
            set.add(s);
            return;
        }

        s += arr[index] + " ";
        for (int i = index; i < N; i++) {
            dfs(i, depth + 1, s);
        }
    }
}

