//11050번 이항 계수1
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_11050 {
    private static int N;
    private static int K;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine()," ");
        N=Integer.parseInt(st.nextToken());
        K=Integer.parseInt(st.nextToken());
        int result1=1;
        int result2=1;
        int check=Math.min(K,N-K);
        for(int i=0;i<check;i++){
            result1=result1*N;
            N--;
        }
        int temp=check;
        for(int i=0;i<temp;i++){
            result2=result2*check;
            check--;
        }
        System.out.println(result1/result2);
    }
}
