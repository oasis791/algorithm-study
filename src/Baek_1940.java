import java.io.*;
import java.util.*;

public class Baek_1940 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int m = Integer.parseInt(bf.readLine());
        int[] ingr = new int[n];

        StringTokenizer st = new StringTokenizer(bf.readLine());
        for(int i = 0; i < n; i++) {
            ingr[i] = Integer.parseInt(st.nextToken());
        }

        int answer = 0;

        for(int start = 0; start < n; start++) {
            int sum = 0;
            int end = start + 1;

            while (end < n) {
                sum = ingr[start] + ingr[end++];
                if(sum == m) {
                    answer++;
                    break;
                }
            }
        }

        System.out.println(answer);
    }
}
