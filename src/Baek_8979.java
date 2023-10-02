import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek_8979 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        PriorityQueue<int[]> rank = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] != o2[1]) {
                    return -1 * (o1[1] - o2[1]);
                } else if (o1[2] != o2[2]) {
                    return -1 * (o1[2] - o2[2]);
                } else {
                    return -1 * (o1[3] - o2[3]);
                }
            }
        });

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            int num = Integer.parseInt(st.nextToken());
            int gold = Integer.parseInt(st.nextToken());
            int silver = Integer.parseInt(st.nextToken());
            int bronze = Integer.parseInt(st.nextToken());

            rank.offer(new int[]{num, gold, silver, bronze});
        }

        int count = 0;
        int answer = 1;
        int beforeGold = 0;
        int beforeSilver = 0;
        int beforeBronze = 0;

        while (!rank.isEmpty()) {
            int[] cur = rank.poll();
            count++;

            if (beforeGold > cur[1]) {
                answer = count;
            } else if (beforeSilver > cur[2]) {
                answer = count;
            } else if (beforeBronze > cur[3]) {
                answer = count;
            }

            if (cur[0] == k) {
                System.out.println(answer);
                return;
            }

            beforeGold = cur[1];
            beforeSilver = cur[2];
            beforeBronze = cur[3];
        }
    }
}
