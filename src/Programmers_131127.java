import java.util.*;

public class Programmers_131127 {
    class Solution {
        public int solution(String[] want, int[] number, String[] discount) {
            int answer = 0;
            Map<String, Integer> map = new HashMap<>();

            int amount = 0;

            for (int i = 0; i < number.length; i++) {
                amount += number[i];
            }

            int start = 0;
            int end = 0;

            while (end <= discount.length) {
                if (end - start >= amount) {
                    boolean check = true;
                    for (int i = 0; i < want.length; i++) {
                        if (map.containsKey(want[i])) {
                            if (map.get(want[i]) < number[i]) {
                                check = false;
                                break;
                            }
                        } else {
                            check = false;
                            break;
                        }
                    }
                    if (check)
                        answer++;

                    map.put(discount[start], map.get(discount[start]) - 1);
                    start++;
                }

                if (end >= discount.length)
                    break;
                if (map.containsKey(discount[end])) {
                    map.put(discount[end], map.get(discount[end]) + 1);
                } else {
                    map.put(discount[end], 1);
                }

                end++;
            }

            return answer;
        }
    }
}
