import java.io.*;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Baek_10816_V2 {
    public static void main (String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int[] cards = new int[n];

        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < n; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(cards);

        int m = Integer.parseInt(bf.readLine());
        st = new StringTokenizer(bf.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            int key = Integer.parseInt(st.nextToken());

            sb.append(upperBound(key, cards) - lowerBound(key, cards)).append(" ");
        }

        System.out.println(sb);
    }

    static int upperBound (int key, int[] cards) {
        int lo = 0;
        int hi = cards.length;

        while (lo < hi) {
            int mid = (lo + hi) / 2;

            if (key < cards[mid]) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        return lo;
    }

    static int lowerBound (int key, int[] cards) {
        int lo = 0;
        int hi = cards.length;

        while (lo < hi) {
            int mid = (lo + hi) / 2;

            if (key <= cards[mid]) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        return lo;
    }
}
