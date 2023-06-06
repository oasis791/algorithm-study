import java.io.*;
import java.util.*;

public class Baek_21921 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int[] hits = new int[n];

        st = new StringTokenizer(bf.readLine(), " ");

        for(int i = 0; i < n; i++) {
            hits[i] = Integer.parseInt(st.nextToken());
        }

        int answer = 0;
        int sum = 0;
        int day = 0;
        int count = 0;
        int start = 0;
        int end = 0;

        for(int i = 0; i < n; i++) {
            end = i;
            sum += hits[end];
            count++;

            if(count == x) {
                if(sum > answer) {
                    answer = sum;
                    day = 1;
                } else if (sum == answer) {
                    day++;
                }
                count--;
                sum -= hits[start++];
            }
        }

        if(answer > 0) {
            System.out.println(answer);
            System.out.println(day);
        } else {
            System.out.println("SAD");
        }
    }
}
