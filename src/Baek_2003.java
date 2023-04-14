import java.util.*;
import java.io.*;

public class Baek_2003 {
    public static void main (String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];

        st = new StringTokenizer(bf.readLine(), " ");
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int sum = 0;
        int start = 0;
        int end = 0;
        int answer = 0;
        for (int i = 0; i < n; i++) {
            end = i;
            sum += arr[end];

            while(sum > m) {
                sum -= arr[start++];
            }

            if(sum == m) {
                answer++;
            }
        }

        System.out.println(answer);
    }
}
