import java.io.*;
import java.util.*;

public class Baek_10775 {
    static int[] parents;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int g = Integer.parseInt(bf.readLine());
        int p = Integer.parseInt(bf.readLine());

        parents = new int[g + 1];
        for(int i = 1; i <= g; i++) {
            parents[i] = i;
        }

        int count = 0;
        for(int i = 0; i < p; i++) {
            int t = Integer.parseInt(bf.readLine());
            int x = find(t);

            if(x > 0) {
                union(x, x - 1);
                count++;
            } else {
                break;
            }
        }

        System.out.println(count);
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

        if(x > y) {
            parents[x] = y;
        } else {
            parents[y] = x;
        }
    }
}
