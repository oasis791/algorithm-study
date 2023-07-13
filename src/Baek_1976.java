import java.util.*;
import java.io.*;

public class Baek_1976 {
    static int[] parents;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(bf.readLine());
        int m = Integer.parseInt(bf.readLine());
        parents = new int[n + 1];

        for(int i = 1; i <= n; i++) {
            parents[i] = i;
        }

        for(int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());

            for(int j = 1; j <= n; j++) {
                int temp = Integer.parseInt(st.nextToken());

                if(temp == 1) {
                    union(i , j);
                }
            }
        }

        StringTokenizer st = new StringTokenizer(bf.readLine());
        int same = 0;
        boolean check = false;
        for(int i = 0; i < m; i++) {
            int city = Integer.parseInt(st.nextToken());

            if(same == 0) {
                same = parents[city];
            } else {
                if(same != parents[city]) {
                    check = true;
                    break;
                }
            }
        }

        if(check) {
            System.out.println("NO");
        } else {
            System.out.println("YES");
        }
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
