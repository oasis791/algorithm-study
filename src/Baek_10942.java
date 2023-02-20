import java.io.*;
import java.util.StringTokenizer;

public class Baek_10942 {
    static int[] num;
    static boolean[][] dp;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        num = new int[N + 1];
        dp = new boolean[N + 1][N + 1];

        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 1; i <= N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        checking();
        StringBuilder sb = new StringBuilder();
        int M = Integer.parseInt(bf.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            if (dp[S][E]) {
                sb.append(1).append("\n");
            } else {
                sb.append(0).append("\n");
            }
        }

        System.out.println(sb);
    }
    static void checking() {
        for(int i = 1; i <= N; i++)
            dp[i][i] = true;

        for(int i = 1; i <= N - 1; i++)
            if(num[i] == num[i + 1])
                dp[i][i + 1]= true;

        for(int i = 2; i < N; i++){
            for(int j = 1; j <= N - i; j++){
                if(num[j] == num[j + i] && dp[j + 1][j + i - 1])
                    dp[j][j + i] = true;
            }
        }
    }
}
