import java.util.*;
import java.io.*;

public class Baek_2932 {
    static int N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[][] data = new int[K][5];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            data[i][0] = X;
            data[i][1] = (X - 1) / N;
            data[i][2] = (X - 1) % N;
            data[i][3] = R - 1;
            data[i][4] = C - 1;
        }

        int turn;
        for (int d = 0; d < K; d++) {
            turn = 0;
            turn = turnTheTable(data, d, N);
            System.out.println(turn);
        }
    }

    public static int moveTarget(int n, int t, int N) {
        int moved = 0;
        if (n < t) {
            moved += t - n;
        } else if (n > t) {
            moved += N - n;
            moved += t;
        }
        return moved;
    }

    public static int turnTheTable(int[][] data, int d, int N) {
        int turn = 0;
        int[] current = data[d];
        int x = current[0];
        int rn = current[1];
        int cn = current[2];
        int rt = current[3];
        int ct = current[4];

        int mr = moveTarget(rn, rt, N);
        int mc = moveTarget(cn, ct, N);
        turn = turn + mc + mr;

        for (int d2 = d + 1; d2 < data.length; d2++) {
            int[] next = data[d2];
            if (next[0] == x) {
                next[1] = rt;
                next[2] = ct;
            } else {
                if (next[1] == rn) {
                    next[2] += mc;
                    if (next[2] >= N) {
                        next[2] %= N;
                    }
                }
                if (next[2] == ct) {
                    next[1] += mr;
                    if (next[1] >= N) {
                        next[1] %= N;
                    }
                }
            }
        }

        return turn;
    }
}
