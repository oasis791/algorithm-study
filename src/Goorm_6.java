import java.io.*;
import java.util.*;

public class Goorm_6 {
    static int answer = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        String[] arr = bf.readLine().split("");

        Set<String> set = new HashSet<>();

        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n - 2; j++) {
                int index = i + j;
                if (index >= n)
                    break;
                sb.append(arr[index]);
                set.add(sb.toString());
            }
        }

        List<String> list = new ArrayList<>(set);
        Collections.sort(list);

        int size = n - 2;
        dfs(list, arr, 0, 1, 0, size);

        System.out.println(answer);
    }

    static void dfs(List<String> list, String[] arr, int index, int depth, int sum, int size) {
        if (depth == 3) {
            StringBuilder sb = new StringBuilder();
            for (; index < arr.length; index++) {
                sb.append(arr[index]);
            }

            sum += list.indexOf(sb.toString()) + 1;

            answer = Math.max(answer, sum);
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            if(index + i >= arr.length)
                break;
            sb.append(arr[index + i]);
            int tempSum = sum + list.indexOf(sb.toString()) + 1;
            dfs(list, arr, index + i + 1, depth + 1, tempSum, size);
        }

    }
}