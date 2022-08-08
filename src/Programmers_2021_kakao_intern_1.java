import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Programmers_2021_kakao_intern_1 {
//    static class Solution {
//        public int solution(String s) {
//            String[][] map = {new String[]{"zero", "0"}, new String[]{"one", "1"}, new String[]{"two", "2"},
//                    new String[]{"three", "3"}, new String[]{"four", "4"}, new String[]{"five", "5"},
//                    new String[]{"six", "6"}, new String[]{"seven", "7"}, new String[]{"eight", "8"},
//                    new String[]{"nine", "9"}};
//            StringBuilder ans = new StringBuilder();
//            StringBuilder num = new StringBuilder();
//            for (int i = 0; i < s.length(); i++) {
//                if (num.length()!=0) {
//                    for (int j = 0; j < map.length; j++) {
//                        if (map[j][0].equals(num.toString())) {
//                            ans.append(map[j][1]);
//                            num.delete(0, num.length());
//                            break;
//                        }
//                    }
//                }
//                if (Character.isDigit(s.charAt(i))) {
//                    ans.append(s.charAt(i));
//                } else {
//                    num.append(s.charAt(i));
//                }
//            }
//
//            if (num.length()!=0) {
//                for (int j = 0; j < map.length; j++) {
//                    if (map[j][0].equals(num.toString())) {
//                        ans.append(map[j][1]);
//                        num.delete(0, num.length());
//                        break;
//                    }
//                }
//            }
//            return Integer.parseInt(ans.toString());
//        }
//    }

    static class Solution {
        public int solution(String s) {
            String[]  num= {"0","1","2","3","4","5","6","7","8","9"};
            String[] word= {"zero" , "one" , "two" , "three" , "four" , "five" , "six" , "seven" , "eight" , "nine"};
            for (int i = 0 ; i <10 ; i++){
                s = s.replace(word[i] , num[i]);
            }
            return Integer.parseInt(s);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String input = bf.readLine();
        Solution s = new Solution();
        System.out.println(s.solution(input));
    }
}
