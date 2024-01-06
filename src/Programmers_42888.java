import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.HashMap;

public class Programmers_42888 {
    static class Solution {
        public String[] solution(String[] record) {
            HashMap<String, String> userInfo = new HashMap<>();

            for (int i = 0; i < record.length; i++) {
                StringTokenizer st = new StringTokenizer(record[i]);
                String op = st.nextToken();
                String uid = st.nextToken();

                if (op.equals("Enter") || op.equals("Change")) {
                    String nickname = st.nextToken();
                    userInfo.put(uid, nickname);
                }
            }

            ArrayList<String> answer = new ArrayList<>();

            for (int i = 0; i < record.length; i++) {
                StringTokenizer st = new StringTokenizer(record[i]);
                StringBuilder sb = new StringBuilder();
                String op = st.nextToken();
                String uid = st.nextToken();

                if (op.equals("Enter")) {
                    sb.append(userInfo.get(uid)).append("님이 ");
                    sb.append("들어왔습니다.");
                    answer.add(sb.toString());
                } else if (op.equals("Leave")) {
                    sb.append(userInfo.get(uid)).append("님이 ");
                    sb.append("나갔습니다.");
                    answer.add(sb.toString());
                }
            }


            String[] answerArr = new String[answer.size()];
            for (int i = 0; i < answer.size(); i++) {
                answerArr[i] = answer.get(i);
            }
            return answerArr;
        }
    }
}