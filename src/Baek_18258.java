import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baek_18258 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(bf.readLine());
        Deque<Integer> dq = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            String op = st.nextToken();

            switch (op) {
                case "push":
                    dq.offer(Integer.parseInt(st.nextToken()));
                    break;
                case "pop":
                    if (dq.isEmpty()) {
                        sb.append("-1").append("\n");
                    } else {
                        sb.append(dq.pollFirst()).append("\n");
                    }
                    break;
                case "size":
                    sb.append(dq.size()).append("\n");
                    break;
                case "empty":
                    if (dq.isEmpty()) {
                        sb.append("1").append("\n");
                    } else {
                        sb.append("0").append("\n");
                    }
                    break;
                case "front":
                    if (dq.isEmpty()) {
                        sb.append("-1").append("\n");
                        continue;
                    } else {
                        sb.append(dq.peekFirst()).append("\n");
                    }
                    break;
                case "back":
                    if (dq.isEmpty()) {
                        sb.append("-1").append("\n");
                    } else {
                        sb.append(dq.peekLast()).append("\n");
                    }
                    break;
                default:
                    break;
            }
        }
        System.out.print(sb);
    }
}
