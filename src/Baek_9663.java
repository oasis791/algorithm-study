//9663번 N-Queen
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_9663 {
    static class MoveCheck{
        public boolean leftDown;
        public boolean down;
        public boolean rightDown;
        public MoveCheck(boolean leftDown, boolean down, boolean rightDown){
            this.leftDown = leftDown;
            this.down = down;
            this.rightDown = rightDown;
        }
    }
    private static int N;
    private static int result = 0;
    private static MoveCheck[][] matrix;
    private static void dfs(int x,int y) {
        if (x + 1 == N)
            result++;
        else {
            for (int i = 0; i < N; i++) {
                matrix[x + 1][i].leftDown = false;
                matrix[x + 1][i].down = false;
                matrix[x + 1][i].rightDown = false;
            }
            for (int i = 0; i < N; i++) {
                if (matrix[x][i].leftDown) {
                    if (i - 1 >= 0)
                        matrix[x + 1][i - 1].leftDown = true;
                }
                if (matrix[x][i].down) {
                    matrix[x + 1][i].down = true;
                }
                if (matrix[x][i].rightDown) {
                    if (i + 1 < N)
                        matrix[x+1][i+1].rightDown=true;
                }
            }

            if (y - 1 >= 0)
                matrix[x + 1][y - 1].leftDown = true;
            if (y + 1 < N)
                matrix[x + 1][y + 1].rightDown = true;
            matrix[x + 1][y].down = true;

            for (int i = 0; i < N; i++) {
                if (!(matrix[x + 1][i].leftDown || matrix[x + 1][i].down || matrix[x + 1][i].rightDown))
                    dfs(x + 1, i);
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        matrix = new MoveCheck[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                matrix[i][j] = new MoveCheck(false, false, false);
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                matrix[0][j].leftDown = false;
                matrix[0][j].down = false;
                matrix[0][j].rightDown = false;
            }
            dfs(0, i);
        }
        System.out.println(result);
    }
}