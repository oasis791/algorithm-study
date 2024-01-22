import java.io.*;
import java.util.*;

public class Softeer_Average_Score {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] prefix = new int[n + 1];

        st = new StringTokenizer(bf.readLine());
        for(int i = 1; i <= n; i++) {
            prefix[i] = prefix[i - 1] + Integer.parseInt(st.nextToken());
        }

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < k; i++) {
            st = new StringTokenizer(bf.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            double sum = prefix[end] - prefix[start - 1];
            double answer = (double)(sum / (end - start + 1));
            sb.append(String.format("%.2f", answer)).append("\n");
        }

        System.out.print(sb);
    }
}
