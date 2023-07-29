import java.util.*;
import java.io.*;

public class Baek_17413 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String s = bf.readLine();

        boolean isTag = false;
        int targetIndex = 0;

        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if(isTag) {
                if(c == '>') {
                    isTag = false;
                    targetIndex = i + 1;
                }
                sb.append(c);

            } else {
                if(c == '<') {
                    isTag = true;
                    sb.append(c);
                } else if(c == ' ') {
                    sb.append(c);
                    targetIndex = i + 1;
                } else {
                    sb.insert(targetIndex, c);
                }
            }
        }

        System.out.println(sb);
    }
}
