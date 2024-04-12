import java.util.*;
import java.io.*;

public class Baek_21608 {
    static int n;
    static int[][] map;
    static int[][] favorite;
    static int[] teacherOrder;
    static int[] move_row = new int[] { -1, 0, 1, 0 };
    static int[] move_col = new int[] { 0, 1, 0, -1 };

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        // 1. 교실은 NxN 격자
        // 2. 학교에 다니는 학생수 N^2명
        // 3. 학생은 1번부터 N^2까지 번호 매겨져 있음
        // 4. 가장 윗칸 1,1 가장 아래칸 n,n
        n = Integer.parseInt(bf.readLine());
        map = new int[n + 1][n + 1];
        teacherOrder = new int[n * n];
        favorite = new int[n * n + 1][4];

        // 5. 선생님은 학생의 순서를 정했고, 각 학생이 좋아하는 학생 4명도 조사함
        for (int i = 0; i < n * n; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int stuNum = Integer.parseInt(st.nextToken());
            teacherOrder[i] = stuNum;

            favorite[stuNum][0] = Integer.parseInt(st.nextToken());
            favorite[stuNum][1] = Integer.parseInt(st.nextToken());
            favorite[stuNum][2] = Integer.parseInt(st.nextToken());
            favorite[stuNum][3] = Integer.parseInt(st.nextToken());
        }

        // 6. 자리 정하기
        for (int i = 0; i < n * n; i++) {
            int stuNum = teacherOrder[i];
            setSeat(stuNum);
        }

        // 7. 만족도 구하기
        System.out.println(calculateSatisfaction());

    }

    static void setSeat(int stuNum) {

        // 6.1. 한칸에는 학생 한명의 자리만 있을 수 있다.
        // 6.2. |r1-r2| + |c1 - c2| = 1을 만족하는 두 칸 (r1,c1)과 (r2,c2)를 인접하다고 한다.

        int[] seatPoint = new int[] { 0, 0 };
        int favoriteCount = -1;
        int emptyCount = -1;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {

                if (map[i][j] == 0) {

                    int tempFavoriteCount = 0;
                    int tempEmptyCount = 0;

                    for (int k = 0; k < 4; k++) {
                        int nextRow = i + move_row[k];
                        int nextCol = j + move_col[k];

                        if (nextRow >= 1 && nextRow <= n && nextCol >= 1 && nextCol <= n) {
                            if (map[nextRow][nextCol] == 0) {
                                tempEmptyCount++;
                            } else {
                                for (int l = 0; l < 4; l++) {
                                    if (map[nextRow][nextCol] == favorite[stuNum][l]) {
                                        tempFavoriteCount++;
                                    }
                                }
                            }
                        }
                    }

                    // 6.3. 비어있는 칸 중에서, 좋아하는 학생이 인접한 칸에 가장 많은 칸으로 자리를 정한다.
                    if (tempFavoriteCount > favoriteCount) {
                        favoriteCount = tempFavoriteCount;
                        emptyCount = tempEmptyCount;
                        seatPoint[0] = i;
                        seatPoint[1] = j;
                    } else if (tempFavoriteCount == favoriteCount) {
                        // 6.4. 6.3.을 만족하는 칸이 여러개라면, 인접한 칸중에서 비어있는 칸이 가장 많은 칸으로 자리를 정한다.
                        if (tempEmptyCount > emptyCount) {
                            emptyCount = tempEmptyCount;
                            seatPoint[0] = i;
                            seatPoint[1] = j;
                        }
                    }
                    // 6.5. 6.4.를 만족하는 칸도 여러개인 경우, 행의 번호가 작은 칸으로, 그러한 칸도 여러개면 열의 번호가 가장 작은칸으로 자리를
                    // 정한다.
                }
            }
        }

        map[seatPoint[0]][seatPoint[1]] = stuNum;
    }

    static int calculateSatisfaction() {
        int count = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {

                int tempCount = 0;

                for (int k = 0; k < 4; k++) {
                    int nextRow = i + move_row[k];
                    int nextCol = j + move_col[k];

                    if (nextRow >= 1 && nextRow <= n && nextCol >= 1 && nextCol <= n) {
                        for (int l = 0; l < 4; l++) {
                            if (map[nextRow][nextCol] == favorite[map[i][j]][l]) {
                                tempCount++;
                            }

                        }
                    }
                }

                if (tempCount == 1) {
                    count += 1;
                } else if (tempCount == 2) {
                    count += 10;
                } else if (tempCount == 3) {
                    count += 100;
                } else if (tempCount == 4) {
                    count += 1000;
                }
            }
        }
        return count;
    }
}
