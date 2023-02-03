import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek_2170 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int answer = 0;
        int startPoint = Integer.MIN_VALUE;
        int endPoint = Integer.MIN_VALUE;

        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        }); // o1[0] : 시작점 o1[1] : 끝점
        int N = Integer.parseInt(bf.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            pq.offer(new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
        }

        while (!pq.isEmpty()) {
            int[] data = pq.poll();
            int nowStart = data[0];
            int nowEnd = data[1];
            if (nowStart > endPoint) {
                answer += endPoint - startPoint;
                startPoint = nowStart;
                endPoint = nowEnd;
            } else {
                endPoint = Math.max(endPoint, nowEnd);
            }
        }
        answer += endPoint - startPoint;
        System.out.println(answer);
    }
}
