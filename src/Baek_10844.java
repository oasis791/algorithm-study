//10844번 쉬운 계단 수
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_10844 {
    private static long[][] value;
    private static int N;
    private static long count=0;
    private static void checking(){
        for(int i=2;i<=N;i++){
            value[i][0]=value[i-1][1]%1000000000;
            if(i==2)
                value[i][1]=1+value[i-1][2];
            else
                value[i][1]=(value[i-1][0]+value[i-1][2])%1000000000;
            value[i][2]=(value[i-1][1]+value[i-1][3])%1000000000;
            value[i][3]=(value[i-1][2]+value[i-1][4])%1000000000;
            value[i][4]=(value[i-1][3]+value[i-1][5])%1000000000;
            value[i][5]=(value[i-1][4]+value[i-1][6])%1000000000;
            value[i][6]=(value[i-1][5]+value[i-1][7])%1000000000;
            value[i][7]=(value[i-1][6]+value[i-1][8])%1000000000;
            value[i][8]=(value[i-1][7]+value[i-1][9])%1000000000;
            value[i][9]=(value[i-1][8])%1000000000;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine()," ");
        N=Integer.parseInt(st.nextToken());
        value=new long[N+1][10];
        value[1][0]=0;
        for(int i=1;i<10;i++){
            value[1][i]=1;
        }
        if(N==1){
            for(int i=0;i<10;i++)
                count=count+value[1][i];
            System.out.println(count);
        }
        else{
            checking();
            for(int i=1;i<10;i++)
                count=count+value[N][i];
            System.out.println(count%1000000000);
        }
    }
}
