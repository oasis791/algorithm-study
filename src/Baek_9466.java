import java.util.*;
import java.io.*;

public class Baek_9466 {
    static int count = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int T = Integer.parseInt(st.nextToken());

        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(bf.readLine());
            int[] students = new int[n + 1];
            boolean[] visited = new boolean[n + 1];
            boolean[] finished = new boolean[n + 1];
            count = 0;

            st = new StringTokenizer(bf.readLine());
            for (int i = 1; i < n + 1; i++) {
                students[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 1; i <= n; i++) {
                dfs(students, visited, finished, i);
            }

            System.out.println(n - count);
        }
    }

    static void dfs(int[] students, boolean[] visited, boolean[] finished, int index) {
        if (visited[index]) {
            return;
        }

        visited[index] = true;
        int next = students[index];

        if (!visited[next]) {
            dfs(students, visited, finished, next);
        } else {
            if (!finished[next]) {
                count++;
                for (int i = next; i != index; i = students[i]) {
                    count++;
                }
            }
        }

        finished[index] = true;
    }
}