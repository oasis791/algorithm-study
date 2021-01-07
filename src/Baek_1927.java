//1927번 최소힙
import java.util.PriorityQueue;
import java.util.Scanner;

public class Baek_1927 {
    private static int N;
    public static void main(String[] args){
        PriorityQueue<Integer> pq=new PriorityQueue<>();
        Scanner sc=new Scanner(System.in);
        N=sc.nextInt();
        int choice;
        for(int i=0;i<N;i++){
            choice=sc.nextInt();
            if(choice==0){
                if(pq.isEmpty())
                    System.out.println("0");
                else
                    System.out.println(pq.poll());
            }
            else
                pq.offer(choice);
        }
    }
}
