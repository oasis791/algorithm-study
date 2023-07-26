import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;

public class Baek_1251 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        LinkedList<String> list = new LinkedList<>();
        for (int i = 2; i < str.length(); i++) {
            for (int j = 1; j < i; j++) {
                StringBuilder strSb1 = new StringBuilder(str.substring(0, j));
                StringBuilder strSb2 = new StringBuilder(str.substring(j, i));
                StringBuilder strSb3 = new StringBuilder(str.substring(i));
                String str1 = strSb1.reverse().toString();
                String str2 = strSb2.reverse().toString();
                String str3 = strSb3.reverse().toString();
                list.add(str1 + str2 + str3);

            }
        }
        Collections.sort(list);
        System.out.println(list.pop());
    }
}
