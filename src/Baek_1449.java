//1449번 수리공 항승
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek_1449 {
    private static int N;
    private static double L;
    private static int count=0;
    private static double length;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine()," ");
        N=Integer.parseInt(st.nextToken());
        L=Double.parseDouble(st.nextToken());
        int[] input=new int[N];
        st= new StringTokenizer(bf.readLine()," ");
        for(int i=0;i<N;i++)
            input[i]=Integer.parseInt(st.nextToken());
        Arrays.sort(input);

        boolean check=false;
        double start=0;
        for(int i=0;i+1<N;i++){
            if(!check) {
                start = (double) (input[i]) - 0.5;
                check=true;
            }
            length=((double)input[i+1]+0.5)-start;
            if(length>L){
                count++;
                check=false;
            }
        }
        System.out.println(count+1);
    }
}
