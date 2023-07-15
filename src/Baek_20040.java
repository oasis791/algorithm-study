import java.io.*;
import java.util.*;

public class Baek_20040 {
    static int[] parents;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        parents = new int[n];

        for(int i = 1; i < n; i++) {
            parents[i] = i;
        }

        int answer = 0;
        boolean isFinished = false;
        for(int i = 1; i <= m; i++) {
            st = new StringTokenizer(bf.readLine());
            if(!isFinished) {
                int p1 = Integer.parseInt(st.nextToken());
                int p2 = Integer.parseInt(st.nextToken());

                if(find(p1) == find(p2)) {
                    answer = i;
                    isFinished = true;
                }

                union(p1, p2);
            }
        }

        System.out.println(answer);
    }

    static int find(int x) {
        if(parents[x] == x) {
            return x;
        } else {
            return parents[x] = find(parents[x]);
        }
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if(parents[x] > y) {
            parents[x] = y;
        } else {
            parents[y] = x;
        }
    }
}
