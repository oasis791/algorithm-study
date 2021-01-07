//2447번 별 찍기 - 10
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek_2447 {
    private static String[][] matrix;
    private static void star(int x,int y,int N) {
        for (int i = x + N / 3; i < x + 2 * N / 3; i++) {
            for (int j = y + N / 3; j < y + 2 * N / 3; j++)
                matrix[i][j] = " ";
        }
        if (N > 3) {
            star(x, y, N / 3);
            star(x, y + N / 3, N / 3);
            star(x, y + 2 * N / 3, N / 3);
            star(x + N / 3, y, N / 3);
            star(x + N / 3, y + 2 * N / 3, N / 3);
            star(x + 2 * N / 3, y, N / 3);
            star(x + 2 * N / 3, y + N / 3, N / 3);
            star(x + 2 * N / 3, y + 2 * N / 3, N / 3);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine()," ");
        StringBuilder sb = new StringBuilder();
        int N;
        N=Integer.parseInt(st.nextToken());
        matrix=new String[N][N];
        for(String[] a:matrix)
            Arrays.fill(a,"*");
        star(0,0,N);

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++)
                sb.append(matrix[i][j]);
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
