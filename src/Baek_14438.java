import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_14438 {
    static int[] arr;
    static int[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(bf.readLine());
        arr = new int[n + 1];
        tree = new int[n * 4];

        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        init(1, 1, n);

        int m = Integer.parseInt(bf.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            switch (a) {
                case 1:
                    update(1, 1, n, b, c);
                    break;
                case 2:
                    sb.append(query(1, 1, n, b, c)).append("\n");
                    break;
                default:
                    break;
            }
        }

        System.out.print(sb);
    }

    static void init(int node, int start, int end) {
        if (start == end) {
            tree[node] = arr[start];
        } else {
            init(node * 2, start, (start + end) / 2);
            init(node * 2 + 1, (start + end) / 2 + 1, end);
            tree[node] = Math.min(tree[node * 2], tree[node * 2 + 1]);
        }
    }

    static void update(int node, int start, int end, int index, int val) {
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
        tree[node] = Math.min(tree[node * 2], tree[node * 2 + 1]);
    }

    static int query(int node, int start, int end, int left, int right) {
        if (left > end || right < start) {
            return Integer.MAX_VALUE;
        }

        if (left <= start && end <= right) {
            return tree[node];
        }

        int lmin = query(node * 2, start, (start + end) / 2, left, right);
        int rmin = query(node * 2 + 1, (start + end) / 2 + 1, end, left, right);
        return Math.min(lmin, rmin);
    }
}
