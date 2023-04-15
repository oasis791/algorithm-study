import java.util.*;
import java.io.*;

public class Baek_2559 {
    public static void main (String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] temper = new int[n];
        st = new StringTokenizer(bf.readLine());
        for(int i = 0; i < n; i++) {
            temper[i] = Integer.parseInt(st.nextToken());
        }

        int sum = 0;
        int start = 0;
        int answer = Integer.MIN_VALUE;
        int cont = 0;

        for(int end = 0; end < n; end++) {
           sum += temper[end];
           cont++;

           while(cont > k) {
               sum -= temper[start++];
               cont--;
           }

           if(cont == k) {
               answer = Math.max(answer, sum);
           }
        }

        System.out.println(answer);
    }
}
