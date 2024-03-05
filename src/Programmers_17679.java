import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;

public class Programmers_17679 {
    static class Solution {
        static String[][] map;
        static int answer = 0;
        static ArrayList<int[]> sub;

        public int solution(int m, int n, String[] board) {
            map = new String[m][n];
            for (int i = 0; i < m; i++) {
                String[] input = board[i].split("");
                for (int j = 0; j < n; j++) {
                    map[i][j] = input[j];
                }
            }

            while (true) {
                // 지울 대상 찾기
                findBreakBlocks();
                // 지우기
                if (sub.size() == 0)
                    break;
                breakBlocks();
                // 떨주기
                fallDownBlocks();
            }

            return answer;
        }

        static void findBreakBlocks() {
            // 해당 블록 -> \ | 만 확인하면댐
            sub = new ArrayList<>();

            for (int i = 0; i < map.length - 1; i++) {
                for (int j = 0; j < map[i].length - 1; j++) {
                    String s = map[i][j];

                    if(!s.equals("X")) {
                        if(s.equals(map[i][j + 1]) && s.equals(map[i + 1][j + 1]) && s.equals(map[i + 1][j])) {
                            sub.add(new int[]{i, j});
                            sub.add(new int[]{i, j + 1});
                            sub.add(new int[]{i + 1, j + 1});
                            sub.add(new int[]{i + 1, j});
                        }
                    }
                }
            }

        }

        static void breakBlocks() {
            for (int i = 0; i < sub.size(); i++) {
                int[] point = sub.get(i);
                if(!map[point[0]][point[1]].equals("X")) {
                    map[point[0]][point[1]] = "X";
                    answer++;
                }
            }
        }

        static void fallDownBlocks() {
            for (int j = 0; j < map[0].length; j++) {
                for (int i = map.length - 1; i >= 0; i--) {
                    if(map[i][j].equals("X")) {
                        for (int k = i - 1; k >= 0; k--) {
                            if(!map[k][j].equals("X")) {
                                map[i][j] = map[k][j];
                                map[k][j] = "X";
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
}
