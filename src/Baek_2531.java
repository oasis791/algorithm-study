import java.io.*;
import java.util.*;

public class Baek_2531 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int n = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] sushies = new int[n];

        for(int i = 0; i < n; i++) {
            sushies[i] = Integer.parseInt(bf.readLine());
        }

        int start = 0;
        int end = 0;
        int count = 0;
        int answer = 0;
        Map<Integer, Integer> map = new HashMap<>();

        while (start < n) {
            while(count < k) {
                if(end >= n) {
                    end -= n;
                }
                count++;
                if(map.containsKey(sushies[end])) {
                    map.put(sushies[end], map.get(sushies[end]) + 1);
                } else {
                    map.put(sushies[end], 1);
                }
                end++;
            }

            if(!map.containsKey(c)) {
                answer = Math.max(answer, map.size() + 1);
            } else {
                answer = Math.max(answer, map.size());
            }

            if(map.get(sushies[start]) == 1) {
                map.remove(sushies[start]);
            } else {
                map.put(sushies[start], map.get(sushies[start]) - 1);
            }

            start++;
            count--;
        }

        System.out.println(answer);
    }
}

// 7 9 7 30 2 7 9 25
