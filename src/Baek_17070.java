import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_17070 {
    static int answer = 0;
    static int n;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        map = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for (int j = 1; j <= n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(1, 2, 0);
        System.out.println(answer);
    }

    static void dfs(int row, int col, int status) {
        if (row == n && col == n) {
            answer++;
            return;
        }

        switch (status) {
            case 0:
                if (col + 1 <= n && map[row][col + 1] == 0) {
                    dfs(row, col + 1, 0);
                }
                break;
            case 1:
                if (row + 1 <= n && map[row + 1][col] == 0) {
                    dfs(row + 1, col, 1);
                }
                break;
            case 2:
                if (col + 1 <= n && map[row][col + 1] == 0) {
                    dfs(row, col + 1, 0);
                }

                if (row + 1 <= n && map[row + 1][col] == 0) {
                    dfs(row + 1, col, 1);
                }
                break;
            default:
                break;
        }
        if (row + 1 <= n && col + 1 <= n && map[row][col + 1] == 0 && map[row + 1][col] == 0 && map[row + 1][col + 1] == 0) {
            dfs(row + 1, col + 1, 2);
        }
    }
}
