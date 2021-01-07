//6603번 로또
import java.util.Scanner;

public class Baek_6603 {
    public static int[] lotto;
    public static boolean[] visited;
    public static int testCase;
    public static void drawing(int start,int end){
        if(end==6) {
            for (int i = 0; i < testCase; i++)
                if(visited[i])
                    System.out.print(lotto[i]+" ");
            System.out.println();
        }
        for(int i=start;i<testCase;i++){
            visited[i]=true;
            drawing(i+1,end+1);
            visited[i]=false;
        }

    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        while(true){
            testCase=sc.nextInt();
            if(testCase==0)
                break;
            lotto=new int[testCase];
            visited=new boolean[testCase];
            for(int i=0;i<testCase;i++)
                lotto[i]=sc.nextInt();
            drawing(0,0);
            System.out.println();
        }
    }
}
