import java.io.*;
import java.util.*;

public class Baek_13334 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        int n = Integer.parseInt(bf.readLine());
        int[][] map = new int[n][2];
        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
            map[i][0] = Integer.parseInt(st.nextToken());
            map[i][1] = Integer.parseInt(st.nextToken());

            if(map[i][0] > map[i][1]) {
                int temp = map[i][0];
                map[i][0] = map[i][1];
                map[i][1] = temp;
            }
        }
        int d = Integer.parseInt(bf.readLine());

        Arrays.sort(map, new Comparator<>(){
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[1] == o2[1]) {
                    return o1[0] - o2[0];
                } else {
                    return o1[1] - o2[1];
                }
            }
        });

        int answer = 0;

        for(int i = 0; i < n; i++) {
            int home = map[i][0];
            int office = map[i][1];
            int end = office;
            int start = end - d;

            if(home >= start) {
                pq.offer(home);
                while(pq.peek() < start) {
                    pq.poll();
                }
            }
            answer = Math.max(answer, pq.size());
        }

        System.out.println(answer);
    }
}
