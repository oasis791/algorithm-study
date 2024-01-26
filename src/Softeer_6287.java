import java.io.*;
import java.util.*;

public class Softeer_6287 {
    static int n;
    static int[] a, b, moveAtoB, moveBtoA;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());

        a = new int[n + 1];
        b = new int[n + 1];
        moveAtoB = new int[n + 1];
        moveBtoA = new int[n + 1];

        for(int i = 1; i <= n - 1; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            a[i] = Integer.parseInt(st.nextToken());
            b[i] = Integer.parseInt(st.nextToken());
            moveAtoB[i] = Integer.parseInt(st.nextToken());
            moveBtoA[i] = Integer.parseInt(st.nextToken());
        }

        StringTokenizer st = new StringTokenizer(bf.readLine());
        a[n] = Integer.parseInt(st.nextToken());
        b[n] = Integer.parseInt(st.nextToken());

        int[] dpA = new int[n + 1];
        int[] dpB = new int[n + 1];
        for(int i = 1; i <= n; i++) {
            dpA[i] = Math.min(a[i] + dpA[i - 1], a[i] + dpB[i - 1] + moveBtoA[i - 1]);
            dpB[i] = Math.min(b[i] + dpB[i - 1], b[i] + dpA[i - 1] + moveAtoB[i - 1]);
        }
        
        System.out.println(Math.min(dpA[n], dpB[n]));
    }
}

