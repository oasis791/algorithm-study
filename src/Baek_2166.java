import java.io.*;
import java.util.*;

public class Baek_2166 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bf.readLine());
        long[] x = new long[n + 1];
        long[] y = new long[n + 1];
        long sum1 = 0;
        long sum2 = 0;

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            x[i] = Integer.parseInt(st.nextToken());
            y[i] = Integer.parseInt(st.nextToken());
        }

        x[n] = x[0];
        y[n] = y[0];

        for (int i = 0; i < n; i++) {
            sum1 += x[i] * y[i + 1];
            sum2 += x[i + 1] * y[i];
        }

        System.out.printf("%.1f", Math.abs(sum1 - sum2) / 2.0);
    }
}
