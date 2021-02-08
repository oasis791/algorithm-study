//10711번 모래성
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_10711 {
    private static int H;
    private static int W;
    private static int[][] matrix;
    private static final int[] move_h = {-1, -1, -1, 0, 0, 1, 1, 1};
    private static final int[] move_w = {-1, 0, 1, -1, 1, -1, 0, 1};
    private static Queue<int[]> q = new LinkedList<>();
    private static int wave = 0;
    private static void bfs() {
        int standSize = q.size();
        int count = 0;
        for(;count<standSize;count++) {
            int[] temp = q.poll();
            for (int i = 0; i < 8; i++) {
                int nextH = temp[0] + move_h[i];
                int nextW = temp[1] + move_w[i];
                if ((nextH >= 0 && nextH < H && nextW >= 0 && nextW < W) && matrix[nextH][nextW] != 0) {
                    matrix[nextH][nextW] -= 1;
                    if (matrix[nextH][nextW] == 0){
                        q.offer(new int[]{nextH, nextW});
                    }
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        matrix = new int[H][W];
        for (int i = 0; i < H; i++) {
            String input = bf.readLine();
            String[] temp = input.split("");
            for (int j = 0; j < W; j++) {
                if (temp[j].equals(".")) {
                    matrix[i][j] = 0;
                    q.offer(new int[]{i, j});
                } else {
                    matrix[i][j] = Integer.parseInt(temp[j]);
                }
            }
        }
        while(!q.isEmpty()) {
            bfs();
            wave++;
        }
        System.out.println(wave - 1);
    }
}
