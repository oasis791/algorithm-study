import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;

public class Programmers_72411 {
    public class Solution {
        static HashMap<String, Integer> map = new HashMap<>();
        public String[] solution(String[] orders, int[] course) {

            ArrayList<String> answer = new ArrayList<>();

            for (int i = 0; i < orders.length; i++) {
                String[] orderArr = orders[i].split("");
                Arrays.sort(orderArr);
                dfs(orderArr, 0, 0, new StringBuilder());
            }

            ArrayList<String>[] bestMenu = new ArrayList[11];
            for (int i = 1; i <= 10; i++) {
                bestMenu[i] = new ArrayList<>();
            }
            int[] bestCount = new int[11];

            for (String key : map.keySet()) {
                int temp = key.length();
                int val = map.get(key);

                if(val >= 2 && bestCount[temp] < val) {
                    bestMenu[temp] = new ArrayList<>();
                    bestMenu[temp].add(key);
                    bestCount[temp] = val;
                } else if (val >= 2 && bestCount[temp] == val) {
                    bestMenu[temp].add(key);
                }
            }

            for (int i = 0; i < course.length; i++) {
                for (int j = 0; j < bestMenu[course[i]].size(); j++) {
                    answer.add(bestMenu[course[i]].get(j));
                }
            }

            String[] arr = new String[answer.size()];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = answer.get(i);
            }

            Arrays.sort(arr);
            return arr;
        }

        static void dfs (String[] orderArr, int depth, int index, StringBuilder sb) {

            if(depth >= 2) {
                map.put(sb.toString(), map.getOrDefault(sb.toString(), 0) + 1);
            }

            for (int i = index; i < orderArr.length; i++) {
                sb.append(orderArr[i]);
                dfs(orderArr, depth + 1, i + 1, sb);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }
}
