import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_2357 {
    static int[] arr;
    static int[][] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        arr = new int[n + 1];
        tree = new int[n * 4][2];

        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(bf.readLine());
        }

        init(1, 1, n);

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int[] query = query(1, 1, n, a, b);
            sb.append(query[1]).append(" ").append(query[0]).append("\n");
        }

        System.out.print(sb);
    }

    private static int[] query(int node, int start, int end, int left, int right) {
        if (left > end || right < start) {
            return new int[]{0, Integer.MAX_VALUE};
        }

        if (left <= start && end <= right) {
            return new int[]{tree[node][0], tree[node][1]};
        }

        int[] l = query(node * 2, start, (start + end) / 2, left, right);
        int[] r = query(node * 2 + 1, (start + end) / 2 + 1, end, left, right);
        return new int[]{Math.max(l[0], r[0]), Math.min(l[1], r[1])};
    }

    private static void init(int node, int start, int end) {
        if (start == end) {
            tree[node][0] = arr[start];
            tree[node][1] = arr[start];
        } else {
            init(node * 2, start, (start + end) / 2);
            init(node * 2 + 1, (start + end) / 2 + 1, end);
            tree[node][0] = Math.max(tree[node * 2][0], tree[node * 2 + 1][0]);
            tree[node][1] = Math.min(tree[node * 2][1], tree[node * 2 + 1][1]);
        }
    }
}
