//1978번 소수 찾기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_1978 {
    private static int N;
    private static int result=0;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine()," ");
        N=Integer.parseInt(st.nextToken());
        int[] input=new int[N];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(bf.readLine()," ");
            input[i]=Integer.parseInt(st.nextToken());
        }
        for(int i=0;i<N;i++){
            int check=0;
            for(int j=2;j<input[i];i++){
                if(input[i]%j==0)
                    check++;
            }
            if(check==0)
                result++;
        }
        System.out.println(result);
    }
}
