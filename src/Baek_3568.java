//3568번 iSharp
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Baek_3568 {
    private static String input;
    private static String defaultDataType;
    private static ArrayList<String> newDataType = new ArrayList<>();
    private static Stack<String> stack = new Stack<>();
    private static int count = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        input = bf.readLine();
        StringTokenizer st = new StringTokenizer(input, " ");
        while (st.hasMoreTokens()) {
            if (count == 0) {
                defaultDataType = st.nextToken();
                count++;
            } else {
                StringTokenizer st2 = new StringTokenizer(st.nextToken(), ",");
                StringTokenizer st3 = new StringTokenizer(st2.nextToken(), ";");
                newDataType.add(st3.nextToken());
            }
        }
        for (int i = 0; i < newDataType.size(); i++) {
            String temp = newDataType.get(i);
            StringBuilder element = new StringBuilder();
            String[] temp2 = temp.split("");
            for (int j = 0; j < temp2.length; j++) {
                if (temp2[j].equals("&")) {
                    stack.push("&");
                } else if (temp2[j].equals("*")) {
                    stack.push("*");
                } else if (temp2[j].equals("[")) {
                    stack.push("[]");
                } else if (temp2[j].equals("]")) {
                    continue;
                } else {
                    element.append(temp2[j]);
                }
            }

            StringBuilder sb = new StringBuilder();
            sb.append(defaultDataType);
            while(!stack.isEmpty())
                sb.append(stack.pop());
            sb.append(" "+element+";");
            System.out.println(sb);
        }
    }
}
