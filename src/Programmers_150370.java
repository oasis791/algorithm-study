import java.util.*;

public class Programmers_150370 {
    static class Solution {
        public static int[] solution(String today, String[] terms, String[] privacies) {
            int convertedToday = convertDate(today);
            List<Integer> answer = new ArrayList<>();

            Map<String, Integer> map = new HashMap<>();
            for (int i = 0; i < terms.length; i++) {
                StringTokenizer st = new StringTokenizer(terms[i]);
                map.put(st.nextToken(), Integer.parseInt(st.nextToken()) * 28);
            }

            for (int i = 0; i < privacies.length; i++) {
                StringTokenizer st = new StringTokenizer(privacies[i]);
                int tempDate = convertDate(st.nextToken());
                tempDate += map.get(st.nextToken());

                if (tempDate <= convertedToday) {
                    answer.add(i + 1);
                }
            }

            int[] result = new int[answer.size()];

            for (int i = 0; i < answer.size(); i++) {
                result[i] = answer.get(i);
            }

            return result;
        }

        static int convertDate(String date) {
            StringTokenizer st = new StringTokenizer(date, ".");
            int year = Integer.parseInt(st.nextToken());
            int month = Integer.parseInt(st.nextToken());
            int day = Integer.parseInt(st.nextToken());

            return (year * 12 * 28) + (month * 28) + day;
        }
    }
}
