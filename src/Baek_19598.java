import java.io.*;
import java.util.*;

public class Baek_19598 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        List<int[]> meetings = new ArrayList<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int N = Integer.parseInt(bf.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            meetings.add(new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
        }

        Collections.sort(meetings, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        pq.offer(meetings.get(0)[1]);
        int answer = 1;

        for (int i = 1; i < N; i++) {
            while (!pq.isEmpty() && pq.peek() <= meetings.get(i)[0]) {
                pq.poll();
            }
            pq.offer(meetings.get(i)[1]);
            answer = Math.max(answer, pq.size());
        }

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        bf.close();
    }
}
