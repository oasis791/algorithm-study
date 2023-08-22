import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_4963 {
    static int[][] map;
    static int[] move_row = new int[]{-1, -1, -1, 0, 1, 1, 1, 0};
    static int[] move_col = new int[]{-1, 0, 1, 1, 1, 0, -1, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int w = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            if(w == 0 && h == 0)
                break;

            map = new int[h][w];

            for (int i = 0; i < h; i++) {
                st = new StringTokenizer(bf.readLine());
                for (int j = 0; j < w; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int count = 0;

            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (map[i][j] == 1) {
                        count++;
                        Queue<int[]> queue = new LinkedList<>();
                        queue.offer(new int[]{i, j});
                        map[i][j] = 0;
                        while (!queue.isEmpty()) {
                            int[] cur = queue.poll();

                            for (int k = 0; k < 8; k++) {
                                int nextRow = cur[0] + move_row[k];
                                int nextCol = cur[1] + move_col[k];

                                if (nextRow >= 0 && nextRow < h && nextCol >= 0 && nextCol < w) {
                                    if (map[nextRow][nextCol] == 1) {
                                        map[nextRow][nextCol] = 0;
                                        queue.offer(new int[]{nextRow, nextCol});
                                    }
                                }
                            }
                        }
                    }
                }
            }
            sb.append(count).append("\n");
        }

        System.out.print(sb);
    }
}
