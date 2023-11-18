import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_13900 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        long[] num = new long[n + 1];
        long[] cumSum = new long[n + 1];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 1; i <= n; i++) {
            num[i] = Long.parseLong(st.nextToken());
            cumSum[i] = num[i] + cumSum[i - 1];
        }

        long answer = 0L;
        for (int i = 2; i <= n; i++) {
            answer += num[i] * cumSum[i - 1];
        }
        System.out.println(answer);
    }
}
