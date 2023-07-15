import java.io.*;
import java.util.*;

public class Baek_4803 {
    static int[] parents;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = 1;
        while(true) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            if(n == 0 && m == 0) {
                break;
            } else {
                sb.append("Case ").append(t++).append(": ");
                parents = new int[n + 1];
                for(int i = 1; i <= n; i++) {
                    parents[i] = i;
                }

                for(int i = 0; i < m; i++) {
                    st = new StringTokenizer(bf.readLine());
                    int p1 = Integer.parseInt(st.nextToken());
                    int p2 = Integer.parseInt(st.nextToken());

                    if(find(p1) == find(p2)) {
                        parents[find(p1)] = 0;
                        continue;
                    }

                    union(p1, p2);
                }

                int count = 0;

                for(int i = 1; i <= n; i++) {
                    if(parents[i] == i) {
                        count++;
                    }
                }

                if(count == 1) {
                    sb.append("There is one tree.").append("\n");
                } else if (count == 0) {
                    sb.append("No trees.").append("\n");
                } else {
                    sb.append("A forest of ").append(count).append(" trees.").append("\n");
                }
            }

        }
        System.out.print(sb);
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
