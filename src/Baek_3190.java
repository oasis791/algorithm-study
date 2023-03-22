import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Baek_3190 {
    static Deque<int[]> sneak = new ArrayDeque<>();
    static int N, K, L;
    static int dir = 0; // 0: 동, 1: 남, 2: 서, 3: 북
    static int[] move_row = new int[]{0, 1, 0, -1};
    static int[] move_col = new int[]{1, 0, -1, 0};
    static int time = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        int[][] board = new int[N + 1][N + 1];
        sneak.offer(new int[]{1, 1});
        board[1][1] = 2; // 2는 뱀
        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            board[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1; // 1은 사과
        }
        L = Integer.parseInt(br.readLine());
        String[][] curve = new String[L][2];
        for (int i = 0; i < L; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            curve[i][0] = st.nextToken();
            curve[i][1] = st.nextToken();
        }

        int index = 0;
        while (true) {
            time++;
            int[] head = sneak.peekFirst();
            int nowRow = head[0] + move_row[dir];
            int nowCol = head[1] + move_col[dir];
            if (nowRow > N || nowRow <= 0 || nowCol > N || nowCol <= 0 || board[nowRow][nowCol] == 2) {
                break;
            }

            if (board[nowRow][nowCol] == 0) {
                int[] tail = sneak.pollLast();
                board[tail[0]][tail[1]] = 0;
            }
            board[nowRow][nowCol] = 2;
            sneak.offerFirst(new int[]{nowRow, nowCol});

            if(index < L) {
                if (Integer.parseInt(curve[index][0]) == time) {
                    if (curve[index][1].equals("L")) {
                        dir -= 1;
                    } else {
                        dir += 1;
                    }

                    if (dir == 4) {
                        dir = 0;
                    }
                    if (dir == -1) {
                        dir = 3;
                    }
                    index++;
                }
            }
        }
        bw.write(String.valueOf(time));
        bw.flush();
        bw.close();
        br.close();
    }
}
