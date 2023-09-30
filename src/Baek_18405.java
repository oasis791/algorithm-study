import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baek_18405 {
    static int n, k, s, x, y;
    static int[][] map;
    static int[] move_row = new int[]{-1, 0, 1, 0};
    static int[] move_col = new int[]{0, 1, 0, -1};
    static PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
        @Override
        public int compare(int[] o1, int[] o2) {
            return o1[2] - o2[2];
        }
    });

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        map = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 1; j <= n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] > 0) {
                    pq.offer(new int[]{i, j, map[i][j]});
                }
            }
        }

        st = new StringTokenizer(bf.readLine());
        s = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());

        for (int i = 0; i < s; i++) {
            Queue<int[]> queue = new LinkedList<>();
            while (!pq.isEmpty()) {
                queue.offer(pq.poll());
            }

            while (!queue.isEmpty()) {
                int[] cur = queue.poll();

                for (int j = 0; j < 4; j++) {
                    int nextRow = cur[0] + move_row[j];
                    int nextCol = cur[1] + move_col[j];

                    if (nextRow >= 1 && nextRow <= n && nextCol >= 1 && nextCol <= n) {
                        if (map[nextRow][nextCol] == 0) {
                            map[nextRow][nextCol] = cur[2];
                            pq.offer(new int[]{nextRow, nextCol, cur[2]});
                        }
                    }
                }
            }
        }

        System.out.println(map[x][y]);
    }
}
