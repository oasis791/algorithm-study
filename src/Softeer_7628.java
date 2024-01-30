import java.io.*;
import java.util.*;

public class Softeer_7628 {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int[] heat = new int[n];

        StringTokenizer st = new StringTokenizer(bf.readLine());
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++) {
            heat[i] = Integer.parseInt(st.nextToken());
            max = Math.max(heat[i], max);
        }

        int answer = 0;
        for(int i = 2; i <= max; i++) {
            int count = 0;
            for(int j = 0; j < n; j++) {
                if(heat[j] % i == 0) {
                    count++;
                }
            }

            answer = Math.max(answer, count);
        }

        System.out.println(answer);
    }
}

