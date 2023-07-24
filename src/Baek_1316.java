import java.io.*;
import java.util.*;

public class Baek_1316 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(bf.readLine());
        int answer = 0;

        loop:
        for(int t = 0; t < n; t++) {
            Set<Character> set = new HashSet<>();
            String word = bf.readLine();

            char before = '@';
            for(int i = 0; i < word.length(); i++) {
                char now = word.charAt(i);
                if(now != before) {
                    if(set.contains(now)) {
                        continue loop;
                    } else {
                        set.add(now);
                        before = now;
                    }
                }
            }
            answer++;
        }

        System.out.println(answer);
    }
}
