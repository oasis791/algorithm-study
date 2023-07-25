import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_11505 {
    static final int DIV_NUM = 1_000_000_007;
    static long[] arr;
    static long[] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        arr = new long[n + 1];
        tree = new long[n * 4];

        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(bf.readLine());
        }

        init(1, 1, n);

        for (int i = 0; i < m + k; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            switch (a) {
                case 1:
                    update(1, 1, n, b, c);
                    break;
                case 2:
                    sb.append(query(1, 1, n, b, (int) c)).append("\n");
                    break;
                default:
                    break;
            }
        }

        System.out.print(sb);
    }

    private static long query(int node, int start, int end, int left, int right) {
        if (left > end || right < start) {
            return 1;
        }

        if (left <= start && end <= right) {
            return tree[node];
        }

        long lpro = query(node * 2, start, (start + end) / 2, left, right);
        long rpro = query(node * 2 + 1, (start + end) / 2 + 1, end, left, right);
        return ((lpro % DIV_NUM) * (rpro % DIV_NUM)) % DIV_NUM;
    }

    private static void update(int node, int start, int end, int index, int val) {
        if (index < start || index > end) {
            return;
        }

        if (start == end) {
            arr[index] = val;
            tree[node] = val;
            return;
        }
        update(node * 2, start, (start + end) / 2, index, val);
        update(node * 2 + 1, (start + end) / 2 + 1, end, index, val);
        tree[node] = ((tree[node * 2] % DIV_NUM) * (tree[node * 2 + 1] % DIV_NUM)) % DIV_NUM;
    }

    private static void init(int node, int start, int end) {
        if (start == end) {
            tree[node] = arr[start];
        } else {
            init(node * 2, start, (start + end) / 2);
            init(node * 2 + 1, (start + end) / 2 + 1, end);
            tree[node] = ((tree[node * 2] % DIV_NUM) * (tree[node * 2 + 1] % DIV_NUM)) % DIV_NUM;
        }
    }
}
