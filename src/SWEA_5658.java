import java.io.*;
import java.util.*;

public class SWEA_5658 {
    static String[] hexArray = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(bf.readLine());
        int n,k;

        for (int c = 1; c <= t; c++ ) {
            sb.append("#").append(c).append(" ");
            ArrayList<String> list = new ArrayList<>();
            StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            String[] input = bf.readLine().split("");
            rotate(list, input, n);
            Collections.sort(list, Comparator.reverseOrder());
            sb.append(toDec(list.get(k - 1), n / 4));
            System.out.println(sb.toString());
            sb.delete(0, sb.length());
        }
    }

    static void rotate (ArrayList<String> list, String[] input, int n) {
        int start = 0;
        int count = 0;
        int target = n / 4;
        StringBuilder temp = new StringBuilder();
        for(int r = 0; r < target; r++) {
            for(int i = 0; i < n; i++) {
                int next = start + i;
                if(next >= n) {
                    next -= n;
                }

                temp.append(input[next]);
                count++;

                if(count == target) {
                    if(!list.contains(temp.toString())) {
                        list.add(temp.toString());
                    }
                    count = 0;
                    temp.delete(0, target);
                }
            }
            start--;
            if(start < 0)
                start += n;
        }
    }

    static int toDec(String hex, int n) {
        String[] temp = hex.split("");
        int value = 0;
        int mul = 1;
        for(int i = n - 1; i >= 0; i--) {
            int j = 0;
            for(; j < 16; j++) {
                if(temp[i].equals(hexArray[j])) {
                    break;
                }
            }
            value += j * mul;
            mul *= 16;
        }
        return value;
    }
}

