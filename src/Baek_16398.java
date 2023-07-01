import java.util.*;
import java.io.*;

public class Baek_16398 {
    static int[] parents;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });

        int n = Integer.parseInt(bf.readLine());
        parents = new int[n];

        for(int i = 1; i < n; i++) {
            parents[i] = i;
        }

        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
            for(int j = 0; j < i; j++) {
                int cost = Integer.parseInt(st.nextToken());
                if(i != j) {
                    pq.offer(new int[]{i, j, cost});
                }
            }
        }

        long answer = 0;

        while(!pq.isEmpty()) {
            int[] poll = pq.poll();
            int n1 = poll[0];
            int n2 = poll[1];
            int cost = poll[2];

            if(find(n1) == find(n2))
                continue;

            union(n1, n2);
            answer += cost;
        }

        System.out.println(answer);
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
