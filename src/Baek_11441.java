import java.io.*;
import java.util.*;

public class Baek_11441 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(bf.readLine());
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int[] prefix = new int[n + 1];

        for(int i = 1; i <= n; i++) {
            prefix[i] = prefix[i - 1] + Integer.parseInt(st.nextToken());
        }

        int m = Integer.parseInt(bf.readLine());

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            int j = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            sb.append(prefix[k] - prefix[j - 1]).append("\n");
        }
        
        System.out.print(sb);
    }
}
