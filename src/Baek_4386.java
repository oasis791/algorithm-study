import java.util.*;
import java.io.*;

public class Baek_4386 {
    static int[] parents;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        parents = new int[n];

        for(int i = 1; i < n; i++) {
            parents[i] = i;
        }

        PriorityQueue<double[]> pq = new PriorityQueue<>(new Comparator<>() {
            @Override
            public int compare(double[] o1, double[] o2) {
                return (int)(o1[2] - o2[2]);
            }
        });
        ArrayList<double[]> stars = new ArrayList<>(n);

        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());
            stars.add(new double[]{x, y});
        }

        for(int i = 0; i < n - 1; i++) {
            double[] from = stars.get(i);

            for(int j = i + 1; j < n; j++) {
                double[] to = stars.get(j);
                pq.offer(new double[]{i, j, distanceBetweenPoints(from, to)});
            }
        }

        double answer = 0;
        while(!pq.isEmpty()) {
            double[] poll = pq.poll();
            int p1Index = (int)poll[0];
            int p2Index = (int)poll[1];
            double dist = poll[2];

            if(find(p1Index) == find(p2Index))
                continue;

            union(p1Index, p2Index);
            answer += dist;

        }
        System.out.printf("%.2f", answer);
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

    static double distanceBetweenPoints(double[] p1, double[] p2) {
        return Math.sqrt(Math.pow((p1[0] - p2[0]), 2) + Math.pow((p1[1] - p2[1]), 2));
    }
}
