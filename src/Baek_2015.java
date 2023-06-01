import java.io.*;
import java.util.*;

public class Baek_2015 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        long answer = 0;
        
        int[] arr = new int[n];
        int[] prefixSum = new int[n];

        st = new StringTokenizer(bf.readLine(), " ");
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Map<Integer, Long> map = new HashMap<>();
        for(int i = 0; i < n; i++) {
            if(i == 0) {
                prefixSum[i] = arr[i];
            } else {
                prefixSum[i] = prefixSum[i - 1] + arr[i];
            }

            if(prefixSum[i] == k)
                answer++;

            if(map.containsKey(prefixSum[i] - k)) {
                answer += map.get(prefixSum[i] - k);
            }

            if(!map.containsKey(prefixSum[i])) {
                map.put(prefixSum[i], 1L);
            } else {
                map.put(prefixSum[i], map.get(prefixSum[i]) + 1);
            }
        }
        
        System.out.println(answer);
    }
}
