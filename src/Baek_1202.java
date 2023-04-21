import java.util.*;
import java.io.*;

public class Baek_1202 {
    public static void main (String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        long answer = 0;
        ArrayList<int[]> jr = new ArrayList<>();
        ArrayList<Integer> bag = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine(), " ");
            int weight = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());
            jr.add(new int[]{weight, price});
        }

        for(int i = 0; i < k; i++) {
            bag.add(Integer.parseInt(bf.readLine()));
        }

        Collections.sort(jr, new Comparator<int[]>() {
            @Override
            public int compare (int[] o1, int[] o2) {
                if(o1[0] == o2[0]) {
                    return o1[1] - o2[1];
                } 
                
                return o1[0] - o2[0];
            }
        });
        Collections.sort(bag);

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());

        for(int i = 0, j = 0; i < k; i++) {
            while(j < n && jr.get(j)[0] <= bag.get(i)) {
                pq.offer(jr.get(j++)[1]);
            }

            if(!pq.isEmpty()) {
                answer += pq.poll();
            }
        }
        System.out.println(answer);
    }
}
