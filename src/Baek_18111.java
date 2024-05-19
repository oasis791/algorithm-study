import java.io.*;
import java.util.*;

public class Baek_18111 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        int[][] map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                min = Math.min(min, map[i][j]);
                max = Math.max(max, map[i][j]);
            }
        }

        int answer = Integer.MAX_VALUE;
        int height = 0;

        for (int t = max; t >= min; t--) {
            int remain = b;
            int time = 0;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j] == t) continue;

                    if (map[i][j] > t) {
                        remain += map[i][j] - t;
                        time += (map[i][j] - t) * 2;
                    } else {
                        remain -= t - map[i][j];
                        time += t - map[i][j];
                    }
                }
            }

            if (remain < 0) continue;

            if (answer > time) {
                answer = time;
                height = t;
            }
        }

        System.out.println(answer + " " + height);
    }
}
