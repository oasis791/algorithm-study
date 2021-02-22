//5427번 불
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Baek_5427 {
    private static int testCase;
    private static int H;
    private static int W;
    private static String[][] room;
    private static int[] move_x = {-1, 0, 1, 0};
    private static int[] move_y = {0, 1, 0, -1};
    private static boolean[][] visitedFire;
    private static boolean[][] visitedSangGeun;
    private static int[][] second;
    private static Deque<int[]> q;
    private static int out;
    private static void bfs() {
        out = -1;
        while (!q.isEmpty()) {
            int[] temp = q.poll();
            switch (room[temp[0]][temp[1]]) {
                case "*":
                    for (int i = 0; i < 4; i++) {
                        int nextX = temp[0] + move_x[i];
                        int nextY = temp[1] + move_y[i];
                        if (nextX < 0 || nextX >= H || nextY < 0 || nextY >= W) {
                            continue;
                        } else {
                            if (!room[nextX][nextY].equals("#") && !visitedFire[nextX][nextY]) {
                                visitedFire[nextX][nextY] = true;
                                q.offer(new int[]{nextX, nextY});
                                if(!visitedSangGeun[nextX][nextY]){
                                    room[nextX][nextY] = "*";
                                }
                            }
                        }
                    }
                    break;
                case "@":
                    for (int i = 0; i < 4; i++) {
                        int nextX = temp[0] + move_x[i];
                        int nextY = temp[1] + move_y[i];
                        if (nextX < 0 || nextX >= H || nextY < 0 || nextY >= W) {
                            out = second[temp[0]][temp[1]] + 1;
                            return;
                        } else {
                            if (!visitedSangGeun[nextX][nextY] && !room[nextX][nextY].equals("#") && !room[nextX][nextY].equals("*")) {
                                second[nextX][nextY] = second[temp[0]][temp[1]] + 1;
                                if(visitedFire[temp[0]][temp[1]]){
                                    room[temp[0]][temp[1]] = "*";
                                }
                                room[nextX][nextY] = "@";
                                visitedSangGeun[nextX][nextY] = true;
                                q.offer(new int[]{nextX, nextY});

                            }
                        }
                    }
                    break;
                default:
                    break;
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        testCase = Integer.parseInt(bf.readLine());
        for (int t = 0; t < testCase; t++) {
            StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            q = new LinkedList<>();
            room = new String[H][W];
            visitedFire = new boolean[H][W];
            visitedSangGeun = new boolean[H][W];
            second = new int[H][W];
            for (int i = 0; i < H; i++) {
                String[] temp = bf.readLine().split("");
                for (int j = 0; j < W; j++) {
                    room[i][j] = temp[j];
                    if (room[i][j].equals("*")) {
                        q.offerFirst(new int[]{i, j});
                        visitedFire[i][j] = true;
                    }
                    if (room[i][j].equals("@")) {
                        q.offerLast(new int[]{i, j});
                        visitedSangGeun[i][j] = true;
                    }
                }
            }
            bfs();
            if (out == -1) {
                System.out.println("IMPOSSIBLE");
            } else {
                System.out.println(out);
            }
        }

    }
}
