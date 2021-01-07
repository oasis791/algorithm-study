//9372번 상근이의 여행
import java.util.ArrayList;
import java.util.Scanner;

public class Baek_9372 {
    private static ArrayList<Integer> visited=new ArrayList<>();
    private static int country;
    private static int[] result;
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int testCase,plane;
        testCase=sc.nextInt();
        result=new int[testCase];
        for(int t=0;t<testCase;t++){
            country=sc.nextInt();
            plane=sc.nextInt();
            for(int i=0;i<plane;i++){
                int x,y;
                x=sc.nextInt();
                y=sc.nextInt();
            }
            result[t]=country-1;
        }
        for(int i=0;i<result.length;i++)
            System.out.println(result[i]);
    }
}
