import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Baek_1874 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(bf.readLine());
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(bf.readLine());
        }

        Stack<Integer> stack = new Stack<>();
        int index = 0;
        for (int i = 1; i <= n; i++) {
            while (stack.size() > 0 && stack.peek() == arr[index]) {
                index++;
                stack.pop();
                sb.append("-").append("\n");
            }

            stack.push(i);
            sb.append("+").append("\n");
        }

        while (stack.size() > 0 && stack.peek() == arr[index]) {
            index++;
            stack.pop();
            sb.append("-").append("\n");
        }

        if (stack.size() > 0) {
            System.out.println("NO");
        } else {
            System.out.println(sb);
        }

    }
}

