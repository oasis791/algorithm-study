import java.util.Set;
import java.util.HashSet;

public class Programmers_43163 {
    class Solution {
        static int answer = Integer.MAX_VALUE;
        static Set<String> wordSet;
        static Set<String> visited;
        public int solution(String begin, String target, String[] words) {
            wordSet = new HashSet<>();
            visited = new HashSet<>();
            for(int i = 0; i < words.length; i++) {
                wordSet.add(words[i]);
            }

            visited.add(begin);
            dfs(begin, target, 0);

            return answer == Integer.MAX_VALUE ? 0 : answer;
        }

        static void dfs(String curWord, String target, int depth) {
            if(depth > answer)
                return;

            if(curWord.equals(target)) {
                answer = Math.min(answer, depth);
                return;
            }

            for(int i = 0; i < curWord.length(); i++) {
                StringBuilder sb = new StringBuilder();
                for(int j = 0; j < i; j++) {
                    sb.append(curWord.charAt(j));
                }

                for(char j = 'a'; j <= 'z'; j++) {
                    if(curWord.charAt(i) == j)
                        continue;

                    sb.append(j);
                    for(int k = i + 1; k < curWord.length(); k++) {
                        sb.append(curWord.charAt(k));
                    }

                    if(wordSet.contains(sb.toString()) && !visited.contains(sb.toString())) {
                        visited.add(sb.toString());
                        dfs(sb.toString(), target, depth + 1);
                        visited.remove(sb.toString());
                    }
                    for(int k = i; k < curWord.length(); k++) {
                        sb.deleteCharAt(sb.length() - 1);
                    }
                }
            }
        }
    }
}
