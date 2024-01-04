import java.util.ArrayList;
import java.util.Collections;

public class Programmers_17677 {

    static class Solution {
        public static int solution(String str1, String str2) {
            str1 = str1.toUpperCase();
            str2 = str2.toUpperCase();

            ArrayList<String> list1 = makeList(str1);
            ArrayList<String> list2 = makeList(str2);

            if (list1.size() == 0 && list2.size() == 0) {
                return 65536;
            }

            Collections.sort(list1);
            Collections.sort(list2);

            return (int) (calc(list1, list2) * 65536);
        }

        static ArrayList<String> makeList(String str) {
            ArrayList<String> list = new ArrayList<>();

            for (int i = 0; i < str.length() - 1; i++) {
                char a = str.charAt(i);
                char b = str.charAt(i + 1);

                if (Character.isLetter(a) && Character.isLetter(b)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(a).append(b);
                    list.add(sb.toString());
                }
            }

            return list;
        }

        static double calc(ArrayList<String> list1, ArrayList<String> list2) {
            ArrayList<String> inter = new ArrayList<>();
            ArrayList<String> union = new ArrayList<>();

            for (String s : list1) {
                if (list2.contains(s)) {
                    list2.remove(s);
                    inter.add(s);
                }
                union.add(s);
            }

            for (String s : list2) {
                union.add(s);
            }

            return (double) ((double) inter.size() / (double) union.size());
        }
    }
}
