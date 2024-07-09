import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Baek_2660 {
    static final int INF = 100000000;

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());

        int[][] map = new int[n + 1][n + 1];

        while (true) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (a == -1 && b == -1) break;

            map[a][b] = 1;
            map[b][a] = 1;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i != j && map[i][j] == 0) map[i][j] = INF;
            }
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                }
            }
        }

        int score = Integer.MAX_VALUE;
        ArrayList<Integer> list = new ArrayList<>();

        loop:
        for (int i = 1; i <= n; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = 1; j <= n; j++) {
                if (map[i][j] == INF) continue loop;

                max = Math.max(max, map[i][j]);
            }

            if (score > max) {
                score = max;
                list = new ArrayList<>();
                list.add(i);
            } else if (score == max) {
                list.add(i);
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(score).append(" ").append(list.size()).append("\n");
        Collections.sort(list);

        for (int i : list) {
            sb.append(i).append(" ");
        }

        System.out.println(sb);
    }
}
