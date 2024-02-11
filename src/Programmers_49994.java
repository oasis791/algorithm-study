import java.util.HashMap;

public class Programmers_49994 {
    static class Solution {
        public int solution(String dirs) {
            HashMap<Character, int[]> next = new HashMap<>();
            next.put('U', new int[]{-1, 0});
            next.put('D', new int[]{1, 0});
            next.put('R', new int[]{0, 1});
            next.put('L', new int[]{0, -1});

            HashMap<Character, Integer> nextIndex = new HashMap<>();
            nextIndex.put('U', 0);
            nextIndex.put('R', 1);
            nextIndex.put('D', 2);
            nextIndex.put('L', 3);

            boolean[][][] visited = new boolean[11][11][4];
            int[] cur = new int[]{5, 5};
            int answer = 0;

            for (int i = 0; i < dirs.length(); i++) {
                int[] nextMove = next.get(dirs.charAt(i));

                int nextRow = cur[0] + nextMove[0];
                int nextCol = cur[1] + nextMove[1];

                if(nextRow < 0 || nextRow > 10 || nextCol < 0 || nextCol > 10) continue;

                if(!visited[nextRow][nextCol][nextIndex.get(dirs.charAt(i))]) {
                    visited[nextRow][nextCol][nextIndex.get(dirs.charAt(i))] = true;
                    visited[cur[0]][cur[1]][(nextIndex.get(dirs.charAt(i)) + 2) % 4] = true;
                    answer++;
                }

                cur[0] = nextRow;
                cur[1] = nextCol;
            }

            return answer;
        }
    }
}
