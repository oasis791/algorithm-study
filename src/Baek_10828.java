//10828 스택
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Baek_10828 {
    private static int N;
    private static Stack<Integer> stack = new Stack<>();

    public static void main(String[] args) throws IOException {
        BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(bf.readLine()," ");
        N = Integer.parseInt(st.nextToken());
        int item;
        for (int i = 0; i < N; i++) {
            st=new StringTokenizer(bf.readLine()," ");
            String input = st.nextToken();
            switch (input) {
                case "push":
                    item = Integer.parseInt(st.nextToken());
                    stack.add(item);
                    break;
                case "pop":
                    if(stack.isEmpty())
                        System.out.println("-1");
                    else
                        System.out.println(stack.pop());
                    break;
                case "size":
                    System.out.println(stack.size());
                    break;
                case "empty":
                    if(stack.size()==0)
                        System.out.println("1");
                    else
                        System.out.println("0");
                    break;
                case "top":
                    if(stack.isEmpty())
                        System.out.println("-1");
                    else {
                        System.out.println(stack.peek());
                    }
                    break;
                default:
                    break;
            }
        }
    }
}
