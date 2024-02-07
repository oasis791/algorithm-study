import java.util.HashMap;

public class Programmers_172927 {
    static class Solution {
        static int answer = Integer.MAX_VALUE;
        static HashMap<String, Integer> indexMap;
        static int[][] info = new int[][] {{1, 1, 1}, {5, 1, 1}, {25, 5, 1}};
        public int solution(int[] picks, String[] minerals) {
            indexMap = new HashMap<>();
            indexMap.put("diamond", 0);
            indexMap.put("iron", 1);
            indexMap.put("stone", 2);

            dfs(picks, minerals, 0, 0);
            return answer;
        }

        static void dfs(int[] picks, String[] minerals, int curIndex, int sum) {
            boolean check = false;
            for (int i = 0; i < 3; i++) {
                if(picks[i] > 0) {
                    check = true;
                    break;
                }
            }

            if(!check) {
                answer = Math.min(answer, sum);
                return;
            }

loop:
            for(int i = 0; i < picks.length; i++) {
                if(picks[i] > 0) {
                    int temp = sum;
                    for(int j = curIndex; j < curIndex + 5; j++) {
                        if(j >= minerals.length) {
                            answer = Math.min(temp, answer);
                            continue loop;
                        }

                        int mineralIndex = indexMap.get(minerals[j]);
                        temp += info[i][mineralIndex];
                    }
                    picks[i]--;
                    dfs(picks, minerals, curIndex + 5, temp);
                    picks[i]++;
                }
            }
        }
    }
}
