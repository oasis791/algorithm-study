import java.io.*;
import java.util.*;

public class Softeer_6291 {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<>(){
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[1] == o2[1]) {
                    return o2[0] - o1[1];
                } else {
                    return o1[1] - o2[1];
                }
            }
        });

        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int s = Integer.parseInt(st.nextToken());
            int f = Integer.parseInt(st.nextToken());
            pq.offer(new int[]{s, f});
        }

        int endTime = 0;
        int count = 0;
        while(!pq.isEmpty()) {
            int[] poll = pq.poll();

            if(poll[0] >= endTime) {
                count++;
                endTime = poll[1];
            }
        }

        System.out.println(count);
    }
}


