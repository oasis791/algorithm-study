import java.io.*;
import java.util.*;

public class Baek_1026 {
    public static void main (String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        Integer[] a = new Integer[n];
        Integer[] b = new Integer[n];

        StringTokenizer st = new StringTokenizer(bf.readLine());
        for(int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(bf.readLine());
        for(int i = 0; i < n; i++) {
            b[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(a);
        Arrays.sort(b, Collections.reverseOrder());

        int answer = 0;
        for(int i = 0; i < n; i++) {
            answer += a[i] * b[i];
        }

        System.out.println(answer);
    }
}
