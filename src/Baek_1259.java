//1259번 팰린드롬수
import java.util.Scanner;

public class Baek_1259 {
    private static void palindrome(String a){
        int result=0;
        int tail=a.length()-1;
        for(int i=0;i<=tail/2;i++){
            if(i==(tail-i))
                break;
            if(a.charAt(i)==a.charAt(tail-i))
                continue;
            else{
                result=1;
                break;
            }
        }
        if(result==0)
            System.out.println("yes");
        else
            System.out.println("no");
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String input;
        while(true){
            input=sc.next();
            if(input.equals("0"))
                break;
            palindrome(input);
        }
    }
}
