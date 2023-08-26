import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Baek_9934 {
    static ArrayList<Integer>[] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(bf.readLine());

        tree = new ArrayList[k];
        for (int i = 0; i < k; i++) {
            tree[i] = new ArrayList<>();
        }

        int[] arr = new int[(int) Math.pow(2, k) - 1];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0, arr.length, 0, arr);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < k; i++) {
            for (Integer node : tree[i]) {
                sb.append(node).append(" ");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }

    static void dfs(int start, int end, int depth, int[] arr) {
        if (start == end) {
            return;
        }

        int mid = (start + end) / 2;
        tree[depth].add(arr[mid]);

        dfs(start, mid, depth + 1, arr);
        dfs(mid + 1, end, depth + 1, arr);
    }
}

//4
//1 2 3 4 5 6 7 8 9 10 11 12 13 14 15

//
// 4    8      12
// 2 6       10       14
// 1 3 5 7  9 11    13  15