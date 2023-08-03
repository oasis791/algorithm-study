import java.io.*;
import java.util.*;

public class Baek_10986 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        long answer = 0;
        long[] sum = new long[n + 1];
        long[] count = new long[m];

        st = new StringTokenizer(bf.readLine());

        for(int i = 1; i <= n; i++) {
            sum[i] = (sum[i - 1] + Integer.parseInt(st.nextToken())) % m;
            if(sum[i] == 0)
                answer++;

            count[(int)sum[i]]++;
        }

        for(int i = 0; i < m; i++) {
            if(count[i] > 1) {
                answer += (count[i] * (count[i] - 1) / 2);
            }
        }

        System.out.println(answer);
    }
}
