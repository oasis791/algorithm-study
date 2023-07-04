import java.io.*;
import java.util.*;

public class Baek_2467 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int[] arr = new int[n];

        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        long sum = 0;
        int start = 0;
        int end = n - 1;
        int answer = Integer.MAX_VALUE;
        int answerStart = 0;
        int answerEnd = 0;

        while(start != end) {
            sum = arr[start] + arr[end];

            if(Math.abs(0 - answer) > Math.abs(0 - sum)) {
                answer = (int)Math.abs(0 - sum);
                answerStart = arr[start];
                answerEnd = arr[end];
            }
            
            if(sum >= 0) {
                end--;
            } else {
                start++;
            }
        }

        System.out.println(answerStart + " " + answerEnd);
    }
}
