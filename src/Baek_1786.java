import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Baek_1786 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String text = br.readLine();
        String pattern = br.readLine();

        int[] table = new int[pattern.length()];
        int idx = 0;
        for (int i = 1; i < table.length; i++) {
            while (idx > 0 && pattern.charAt(i) != pattern.charAt(idx)) {
                idx = table[idx - 1];
            }

            if (pattern.charAt(i) == pattern.charAt(idx)) {
                idx += 1;
                table[i] = idx;
            }
        }

        int begin = 0, matched = 0;
        int answer = 0;
        Queue<Integer> queue = new LinkedList<>();

        while (begin <= text.length() - pattern.length()) {
            if (matched < pattern.length() && text.charAt(begin + matched) == pattern.charAt(matched)) {
                ++matched;
                if (matched == pattern.length()) {
                    answer++;
                    queue.add(begin + 1);
                }
            } else {
                if (matched == 0) {
                    ++begin;
                } else {
                    begin += matched - table[matched - 1];
                    matched = table[matched - 1];
                }
            }
        }

        bw.write(answer + "\n");
        while (!queue.isEmpty()){
            bw.write(queue.poll() + "\n");
        }

        bw.close();
    }
}