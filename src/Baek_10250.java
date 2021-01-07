//10250번 ACM 호텔
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_10250 {
    private static int T;
    private static int H;
    private static int W;
    private static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine()," ");
        T=Integer.parseInt(st.nextToken());
        while(T>0){
            st=new StringTokenizer(bf.readLine()," ");
            H=Integer.parseInt(st.nextToken());
            W=Integer.parseInt(st.nextToken());
            N=Integer.parseInt(st.nextToken());
            int x=(N/H)+1;
            int y=N%H;
            if(N%H==0){
                x=N/H;
                y=H;
            }
            System.out.println(y*100+x);
            T--;
        }
    }
}
