import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_11053 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        int[] array = new int[N];
        int[] dp = new int[N];
        int max = 0;

        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }

        if(N == 1) {
            System.out.println(1);
            return;
        }

        dp[0] = 1;
        for (int i = 1; i < N; i++) {
            dp[i] = LIS(i, dp, array);
            max = Math.max(max, dp[i]);
        }
        System.out.println(max);
    }
    static int LIS(int index, int[] dp, int[] array) {
        int max = 0;
        for (int i = 0; i < index; i++) {
            if (array[i] < array[index]) {
                max = Math.max(dp[i], max);
            }
        }
        return max + 1;
    }
}
