import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek_1689 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());

        int[][] array = new int[n][2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            array[i][0] = Integer.parseInt(st.nextToken());
            array[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(array, (a, b) -> Integer.compare(a[0], b[0]));

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        minHeap.add(array[0][1]);
        int result = 1;

        for (int i = 1; i < n; i++) {
            while (!minHeap.isEmpty() && minHeap.peek() <= array[i][0]) {
                minHeap.poll();
            }
            minHeap.add(array[i][1]);
            result = Math.max(result, minHeap.size());
        }

        System.out.println(result);
    }
}