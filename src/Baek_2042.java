import java.io.*;
import java.util.*;

public class Baek_2042 {
    static long[] arr;
    static long[] tree;
    static int n, m, k;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(bf.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new long[n + 1];
        tree = new long[n * 4];

        for(int i = 1; i <= n; i++) {
            arr[i] = Long.parseLong(bf.readLine());
        }

        // tree 생성
        init(1, 1, n);

        // 1: tree 변경, 2: 구간 합 구하기
        for(int i = 0; i < m + k; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            switch(a) {
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
    
    static void init(int node, int start, int end) {
        if(start == end) {
            tree[node] = arr[start];
        } else {
            init(node * 2, start, (start + end) / 2);
            init(node * 2 + 1, (start + end) / 2 + 1, end);
            tree[node] = tree[node * 2] + tree[node * 2 + 1];
        }
    }

    static void update(int node, int start, int end, int index, long val) {
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
        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }

    static long query(int node, int start, int end, int left, int right) {
        if (left > end || right < start) {
            return 0;
        }

        if (left <= start && end <= right) {
            return tree[node];
        }

        long lsum = query(node * 2, start, (start + end) / 2, left, right);
        long rsum = query(node * 2 + 1, (start + end) / 2 + 1, end, left, right);
        return lsum + rsum;
    }
}
