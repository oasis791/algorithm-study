//10845번 큐
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_10845 {
    private static int N;
    private static Queue<Integer> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        int item;
        int back=-1;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine(), " ");
            String input = st.nextToken();
            switch (input) {
                case "push":
                    item=Integer.parseInt(st.nextToken());
                    queue.offer(item);
                    back=item;
                    break;
                case "pop":
                    if(queue.isEmpty())
                        System.out.println("-1");
                    else
                        System.out.println(queue.poll());
                    break;
                case "size":
                    System.out.println(queue.size());
                    break;
                case "empty":
                    if(queue.isEmpty())
                        System.out.println("1");
                    else
                        System.out.println("0");
                    break;
                case "front":
                    if(queue.isEmpty())
                        System.out.println("-1");
                    else
                        System.out.println(queue.peek());
                    break;
                case "back":
                    if(queue.isEmpty())
                        System.out.println("-1");
                    else
                        System.out.println(back);
                    break;
                default:
                    break;
            }
        }
    }
}
