//11000번 강의실 배정
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baek_11000 {
    private static int N;
    private static PriorityQueue<Integer> pq = new PriorityQueue<>();
    private static ArrayList<int[]> classes = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
            classes.add(new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
        }
        Collections.sort(classes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return o1[1] - o2[1];
                }
                return o1[0] - o2[0];
            }
        });
        pq.offer(0);
        for (int i = 0; i < classes.size(); i++) {
            int temp = pq.poll();
            if (classes.get(i)[0] >= temp) {
                pq.offer(classes.get(i)[1]);
            } else {
                pq.offer(temp);
                pq.offer(classes.get(i)[1]);
            }
        }
        System.out.println(pq.size());
    }
}
