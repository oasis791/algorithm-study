import java.io.*;
import java.util.*;

public class Baek_12873 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(bf.readLine());

        Queue<Integer> queue = new LinkedList<>();

        for(int i = 1; i <= N; i++) {
            queue.offer(i);
        }

        int t = 0;
        while(queue.size() > 1) {
            int size = queue.size();
            t++; 
            int index = (t % size * t % size * t % size) % size;
            if(index == 0) {
                index += size;
            }
            for(int i = 1; i < index ; i++) {
                Integer poll = queue.poll();
                queue.offer(poll);
            }
            queue.poll();
        }

        sb.append(queue.poll());
        System.out.println(sb);
    }
}
