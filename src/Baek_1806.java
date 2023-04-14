import java.util.*;
import java.io.*;

public class Baek_1806 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        
        int arr[] = new int[n];

        st = new StringTokenizer(bf.readLine(), " ");
        for(int i = 0 ; i < n ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int sum = 0;
        int answer = Integer.MAX_VALUE;
        for(int end = 0; end < n; end++) {
            sum += arr[end];
            while (sum >= s) {
                answer = Math.min(answer, end - start + 1);
                sum -= arr[start++];
            }
        }

        if(answer == Integer.MAX_VALUE) {
            answer = 0;
        }
        System.out.println(answer);
    }
}
