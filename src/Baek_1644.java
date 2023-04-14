import java.util.*;
import java.io.*;

public class Baek_1644 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bf.readLine()); 
        boolean[] prime = new boolean[n + 1];

        for(int i = 2; i < n + 1; i++) {
            if(prime[i])
                continue;
            for(int j = i * 2; j < n + 1; j += i)
                prime[j] = true;
        }

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 2; i < n + 1; i++) {
            if(!prime[i])
                list.add(i);
        }

        int start = 0;
        int answer = 0;
        int sum = 0;
        for(int end = 0; end < list.size(); end++) {
           sum += list.get(end);

           while(sum > n) {
               sum -= list.get(start++);
           }

           if(sum == n) {
               answer++;
           }
        }

        System.out.println(answer);
    }
}
