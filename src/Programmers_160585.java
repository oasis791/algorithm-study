public class Programmers_160585 {
    static class Solution {
        public int solution(String[] board) {
            char[][] map = new char[3][3];
            int count_o = 0;
            int count_x = 0;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    map[i][j] = board[i].charAt(j);

                    if(map[i][j] == 'X') {
                        count_x++;
                    } else if(map[i][j] == 'O') {
                        count_o++;
                    }
                }
            }

            if(count_x == 0 && count_o == 0) return 1;

            // 1. X가 O보다 많은 경우
            if(count_x > count_o) return 0;

            if(count_o - count_x >= 2) return 0;

            // 2. O가 끝냈는데 X도 놓은경우
            if(isFinished('O', map)) {
                if(count_x >= count_o) return 0;
            }
            // 3. X가 끝냈는데 O도 놓은경우
            if(isFinished('X', map)) {
                if(count_o > count_x) return 0;
            }
            return 1;
        }

        static boolean isFinished(char c, char[][] map) {

            for (int i = 0; i < 3; i++) {

                for (int j = 0; j < 3; j++) {
                    if(map[i][j] == c) {

                        boolean checked = true;
                        // 가로
                        for (int k = 0; k < 3; k++) {
                            if(map[i][k] != c) {
                                checked = false;
                                break;
                            }
                        }

                        if(checked) return true;
                        checked = true;
                        // 세로
                        for (int k = 0; k < 3; k++) {
                            if(map[k][i] != c) {
                                checked = false;
                                break;
                            }
                        }

                        if(checked) return true;

                        // 대각
                        if(map[1][1] == c) {
                            if(map[0][0] == c && map[2][2] == c) return true;
                            if(map[0][2] == c && map[2][0] == c) return true;
                        }
                    }
                }
            }

            return false;
        }
    }
}
