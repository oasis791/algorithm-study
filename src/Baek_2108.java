import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class Baek_2108 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int[] arr = new int[n];
        int sum = 0;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        Map<Integer, Integer> map = new TreeMap<>();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(bf.readLine());
            sum += arr[i];
            min = Math.min(min, arr[i]);
            max = Math.max(max, arr[i]);
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }
        Arrays.sort(arr);
        int freq = 0;
        int freqNum = 0;
        boolean isFirst = false;
        for (Integer i : map.keySet()) {
            if (freqNum < map.get(i)) {
                freq = i;
                freqNum = map.get(i);
                isFirst = true;
            } else if (freqNum == map.get(i) && isFirst) {
                freq = i;
                isFirst = false;
            }
        }
        sb.append((int) Math.round((double) sum / n)).append("\n").append(arr[n / 2]).append("\n").append(freq).append("\n").append(max - min).append("\n");
        System.out.print(sb);
    }
}
