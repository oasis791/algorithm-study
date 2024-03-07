import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public class Programmers_64064 {
    static class Solution {
        static int answer = 0;
        static ArrayList<Integer>[] sub;
        static Set<String> set = new HashSet<>();
        public int solution(String[] user_id, String[] banned_id) {
            sub = new ArrayList[banned_id.length];

            for (int i = 0; i < banned_id.length; i++) {
                sub[i] = new ArrayList<>();
            }

            for (int i = 0; i < banned_id.length; i++) {
                String ban = banned_id[i];

                loop:
                for (int j = 0; j < user_id.length; j++) {
                    String user = user_id[j];
                    if(ban.length() != user.length()) continue loop;

                    for (int k = 0; k < user.length(); k++) {
                        if(ban.charAt(k) != user.charAt(k) &&
                                ban.charAt(k) != '*') continue loop;
                    }

                    sub[i].add(j);
                }

            }

            dfs(0, new boolean[user_id.length]);

            return answer;
        }

        static void dfs (int index, boolean[] visited) {
            if (index == sub.length) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < visited.length; i++) {
                    sb.append(visited[i] + " ");
                }

                if(!set.contains(sb.toString())) {
                    set.add(sb.toString());
                    answer++;
                }
                return;
            }

            for (int i = 0; i < sub[index].size(); i++) {
                int cur = sub[index].get(i);

                if(!visited[cur]) {
                    visited[cur] = true;
                    dfs (index + 1, visited);
                    visited[cur] = false;
                }
            }
        }
    }
}
