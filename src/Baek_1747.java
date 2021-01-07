//1747번 소수&팰린드롬
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek_1747 {
    public static boolean decimal(int x) {
        if (x == 1)
            return false;
        for (int i = 2; i * i <= x; i++) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }
    public static boolean palindrome(int x){
        boolean result=true;
        String temp;
        if(x<10)
            return result;
        else{
            temp=Integer.toString(x);
            int tail=temp.length()-1;
            for(int i=0;i<=tail/2;i++){
                if(i==(tail-i))
                    break;
                if(temp.charAt(i)==temp.charAt(tail-i))
                    continue;
                else{
                    result=false;
                    break;
                }
            }
        }
        return result;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
        int N=Integer.parseInt(bf.readLine());
        while(true){
            if(decimal(N)==true) {
                if(palindrome(N)==true)
                    break;
            }
            N++;
        }
        System.out.println(N);
    }
}
