import java.util.*;
import java.io.*;

public class Baek_16968 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] input = bf.readLine().split("");
        String prev = "";
        int answer = 1;

        if(input[0].equals("d")) {
            answer *= 10;
            prev = "d";
        } else {
            answer *= 26;
            prev = "c";
        }

        for(int i = 1; i < input.length; i++) {
           int mulNum = 1;
           if(input[i].equals("d")) {
               mulNum = 10;
           } else {
               mulNum = 26;
           }

           if(input[i].equals(prev))
               mulNum--;

           answer *= mulNum;
           prev = input[i];
        }

        System.out.println(answer);
    }
}
