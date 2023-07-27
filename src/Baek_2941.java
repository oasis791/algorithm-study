import java.io.*;
import java.util.*;

public class Baek_2941 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        Set<String> set = new HashSet<>();
        init(set);

        String input = bf.readLine();
        int answer = 0;
        int size = input.length();

        loop:
        for(int i = 0; i < size; i++) {
            char c = input.charAt(i);

            if(c == 'd') {
                if(i + 3 <= size) {
                    if(set.contains(input.substring(i , i + 3))) {
                        i = i + 2;
                        answer++;
                        continue loop;
                    }
                }
            }

            if(c == 'c' || c == 'd' || c == 'l' || c == 'n' || c == 's' || c == 'z') {
                    if(i + 2 <= size) {
                        if(set.contains(input.substring(i, i+2))) {
                            i = i + 1;
                        } 
                    } 
            }

            answer++;
        }

        System.out.println(answer);
    }

    static void init(Set<String> set) {
        set.add("c=");
        set.add("c-");
        set.add("dz=");
        set.add("d-");
        set.add("lj");
        set.add("nj");
        set.add("s=");
        set.add("z=");
    }
}
