import java.util.Arrays;

public class Programmers_Repeating_Binary_Convert {
    static class Solution {
        public static int[] solution(String s) {
            StringBuilder sb = new StringBuilder();
            int round = 0;
            int zeroCount = 0;
            while(s.length() > 1) {
                round++;

                for(int i = 0; i < s.length(); i++) {
                    if(s.charAt(i) == '0') {
                        zeroCount++;
                    } else {
                        sb.append(s.charAt(i));
                    }
                }

                s = Integer.toBinaryString(sb.toString().length());
                sb.delete(0, sb.length());
            }

            return new int[] {round, zeroCount};
        }
    }
}
