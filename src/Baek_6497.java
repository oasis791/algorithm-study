import java.io.*;
import java.util.*;

public class Baek_6497 {
    static int[] parents;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            if(m == 0 && n == 0) {
                break;
            }

            int elec = 0;

            PriorityQueue<int[]> pq = new PriorityQueue<>(n, new Comparator<>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o1[2] - o2[2];
                }
            });

            parents = new int[m];

            for(int i = 0; i < m; i++) {
                parents[i] = i;
            }

            for(int i = 0; i < n; i++) {
                st = new StringTokenizer(bf.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int z = Integer.parseInt(st.nextToken()); 
                pq.offer(new int[]{x, y, z});
                elec += z;
            }

            int sum = 0;

            while(!pq.isEmpty()) {
                int[] poll = pq.poll();
                int x = poll[0];
                int y = poll[1];
                int weight = poll[2];

                if(find(x) == find(y))
                    continue;            

                union(x, y);
                sum += weight;
            }

            System.out.println(elec - sum);
        }
    }

    static int find(int x) {
        if(parents[x] == x) {
            return x;
        } else {
            return find(parents[x]);
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
