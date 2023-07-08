import java.util.HashMap;

public class Programmers_Clothes {
    static class Solution {
        public static int solution(String[][] clothes) {
            HashMap<String, Integer> map = new HashMap<>();
            for (int i = 0; i < clothes.length; i++) {
                String clot = clothes[i][0];
                String cate = clothes[i][1];
                if (map.containsKey(cate)) {
                    map.put(cate, map.get(cate) + 1);
                } else {
                    map.put(cate, 1);
                }
            }

            int answer = 1;
            if (map.size() == 1) {
                for (String s : map.keySet()) {
                    answer = map.get(s);
                }
            } else {
                for (String s : map.keySet()) {
                    answer *= map.get(s) + 1;
                }
                answer--;
            }
            return answer;
        }
    }
}
