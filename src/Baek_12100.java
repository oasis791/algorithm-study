//12100번 2048 (Easy)
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_12100 {
    private static int N;
    private static int[][] matrix;
    private static int max = 0;
    private static Queue<Integer> Q = new LinkedList<>();
    private static boolean check[][];
    private static void move(int choice) {
        switch (choice) {
            case 1: // 위
                check = new boolean[N][N];
                for (int j = 0; j < N; j++) {
                    for (int i = 0; i < N; i++) {
                        if (matrix[i][j] != 0) {
                            Q.offer(matrix[i][j]);
                            matrix[i][j] = 0;
                        }
                    }
                    int temp;
                    int index = 0;
                    if (!Q.isEmpty()) {
                        temp = Q.poll();
                        matrix[index][j] = temp;
                        index++;
                    }
                    while (!Q.isEmpty()) {
                        temp = Q.poll();
                        if (temp == matrix[index - 1][j] && !check[index - 1][j]) {
                            matrix[index - 1][j] *= 2;
                            check[index - 1][j] = true;
                        } else {
                            matrix[index][j] = temp;
                            index++;
                        }
                    }
                }
                break;
            case 2: // 아래
                check = new boolean[N][N];
                for (int j = 0; j < N; j++) {
                    for (int i = N - 1; i >= 0; i--) {
                        if (matrix[i][j] != 0) {
                            Q.offer(matrix[i][j]);
                            matrix[i][j] = 0;
                        }
                    }
                    int temp;
                    int index = N - 1;
                    if (!Q.isEmpty()) {
                        temp = Q.poll();
                        matrix[index][j] = temp;
                        index--;
                    }
                    while (!Q.isEmpty()) {
                        temp = Q.poll();
                        if (temp == matrix[index + 1][j] && !check[index + 1][j]) {
                            matrix[index + 1][j] *= 2;
                            check[index + 1][j] = true;
                        } else {
                            matrix[index][j] = temp;
                            index--;
                        }
                    }
                }
                break;
            case 3: // 오른쪽
                check = new boolean[N][N];
                for (int i = 0; i < N; i++) {
                    for (int j = N - 1; j >= 0; j--) {
                        if (matrix[i][j] != 0) {
                            Q.offer(matrix[i][j]);
                            matrix[i][j] = 0;
                        }
                    }
                    int temp;
                    int index = N - 1;
                    if (!Q.isEmpty()) {
                        temp = Q.poll();
                        matrix[i][index] = temp;
                        index--;
                    }
                    while (!Q.isEmpty()) {
                        temp = Q.poll();
                        if (temp == matrix[i][index + 1] && !check[i][index + 1]) {
                            matrix[i][index + 1] *= 2;
                            check[i][index + 1] = true;
                        } else {
                            matrix[i][index] = temp;
                            index--;
                        }
                    }
                }
                break;
            case 4: // 왼쪽
                check = new boolean[N][N];
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        if (matrix[i][j] != 0) {
                            Q.offer(matrix[i][j]);
                            matrix[i][j] = 0;
                        }
                    }
                    int temp;
                    int index = 0;
                    if (!Q.isEmpty()) {
                        temp = Q.poll();
                        matrix[i][index] = temp;
                        index++;
                    }
                    while (!Q.isEmpty()) {
                        temp = Q.poll();
                        if (temp == matrix[i][index - 1] && !check[i][index - 1]) {
                            matrix[i][index - 1] *= 2;
                            check[i][index - 1] = true;
                        } else {
                            matrix[i][index] = temp;
                            index++;
                        }
                    }
                }
                break;
            default:
                break;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        matrix = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
            for (int j = 0; j < N; j++)
                matrix[i][j] = Integer.parseInt(st.nextToken());
        }
        int[][] temp = new int[N][N];
        for (int j = 0; j < N; j++)
            temp[j] = Arrays.copyOf(matrix[j], matrix[j].length);

        int max = 0;
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 4; j++) {
                for (int k = 1; k <= 4; k++) {
                    for (int l = 1; l <= 4; l++) {
                        for (int m = 1; m <= 4; m++) {
                            move(i);
                            move(j);
                            move(k);
                            move(l);
                            move(m);
                            for (int n = 0; n < N; n++) {
                                for (int o = 0; o < N; o++) {
                                    max = Math.max(matrix[n][o], max);
                                }
                                matrix[n] = Arrays.copyOf(temp[n], temp[n].length);
                            }
                        }
                    }
                }
            }
        }
        System.out.println(max);
    }
}
