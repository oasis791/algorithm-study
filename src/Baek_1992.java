//1992번 쿼드트리
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek_1992 {
    private static int[][] matrix;
    private static void quadTree(int x,int y,int N) {
        boolean check = false;
            for (int i = x; i < x + N; i++) {
                if (check)
                    break;
                for (int j = y; j < y + N; j++) {
                    if (matrix[i][j] != matrix[x][y]) {
                        check = true;
                        break;
                    }
                }
            }
        if (!check) {
            System.out.print(matrix[x][y]);
        } else {
            System.out.print("(");
            quadTree(x, y, N / 2);
            quadTree(x, y + (N / 2), N / 2);
            quadTree(x + (N / 2), y, N / 2);
            quadTree(x + (N / 2), y + (N / 2), N / 2);
            System.out.print(")");
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        matrix = new int[N][N];
        for (int i = 0; i < N; i++) {
            String input = bf.readLine();
            String[] temp = input.split("");
            for (int j = 0; j < N; j++) {
                matrix[i][j] = Integer.parseInt(temp[j]);
            }
        }
        quadTree(0, 0, N);
    }
}
