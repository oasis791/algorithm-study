import java.io.*;
import java.util.*;

public class Baek_11728 {
    public static void main (String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] a = new int[n];
        int[] b = new int[m];

        st = new StringTokenizer(bf.readLine(), " ");
        for(int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(bf.readLine(), " ");
        for(int i = 0; i < m; i++) {
            b[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(a);
        Arrays.sort(b);

        int targetA = 0;
        int targetB = 0;

        boolean finishedA = false;
        boolean finishedB = false;

        while(!finishedA || !finishedB) {
            if(targetA >= n) {
               finishedA = true;
            } 

            if(targetB >= m) {
                finishedB = true;
            }

            if(!finishedA && !finishedB) {
                if(a[targetA] > b[targetB]) {
                    sb.append(b[targetB++]).append(" ");
                } else {
                    sb.append(a[targetA++]).append(" ");
                }
            } else if(finishedA && !finishedB) {
                sb.append(b[targetB++]).append(" ");

            } else if(!finishedA && finishedB) {
                sb.append(a[targetA++]).append(" ");
            }
        }

        System.out.println(sb);
    }
}
