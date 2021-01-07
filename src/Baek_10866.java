//10866번 덱
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Baek_10866 {
    private static int N;
    private static Deque<Integer> dq=new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine()," ");
        N=Integer.parseInt(st.nextToken());
        int item;
        for(int i=0;i<N;i++){
            st=new StringTokenizer(bf.readLine()," ");
            String input=st.nextToken();
            switch(input){
                case "push_front":
                    item=Integer.parseInt(st.nextToken());
                    dq.offerFirst(item);
                    break;
                case "push_back":
                    item=Integer.parseInt(st.nextToken());
                    dq.offerLast(item);
                    break;
                case "pop_front":
                    if(dq.isEmpty())
                        System.out.println("-1");
                    else
                        System.out.println(dq.pollFirst());
                    break;
                case "pop_back":
                    if(dq.isEmpty())
                        System.out.println("-1");
                    else
                        System.out.println(dq.pollLast());
                    break;
                case "size":
                    System.out.println(dq.size());
                    break;
                case "empty":
                    if(dq.isEmpty())
                        System.out.println("1");
                    else
                        System.out.println("0");
                    break;
                case "front":
                    if(dq.isEmpty())
                        System.out.println("-1");
                    else
                        System.out.println(dq.peekFirst());
                    break;
                case "back":
                    if(dq.isEmpty())
                        System.out.println("-1");
                    else
                        System.out.println(dq.peekLast());
                    break;
                default:
                    break;
            }
        }
    }
}
