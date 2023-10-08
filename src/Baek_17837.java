import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Baek_17837 {
    static int n, k;
    static int[][] map;
    static int[] move_row = new int[]{0, 0, -1, 1};
    static int[] move_col = new int[]{1, -1, 0, 0};
    static ArrayList<int[]>[][] horseMap;
    static HashMap<Integer, int[]> horseInfo = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n + 1][n + 1];
        horseMap = new ArrayList[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 1; j <= n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                horseMap[i][j] = new ArrayList<>();
            }
        }

        for (int i = 1; i <= k; i++) {
            st = new StringTokenizer(bf.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            horseMap[row][col].add(new int[]{i, dir - 1});
            horseInfo.put(i, new int[]{row, col, dir - 1});
        }

        for (int turn = 1; turn <= 1000; turn++) {
            for (int i = 1; i <= k; i++) {
                int[] curHorse = horseInfo.get(i);
                int curHorseRow = curHorse[0];
                int curHorseCol = curHorse[1];
                int curHorseDir = curHorse[2];

                check(i, turn);

                int targetIndex = 0;
                for (int j = 0; j < horseMap[curHorseRow][curHorseCol].size(); j++) {
                    if (horseMap[curHorseRow][curHorseCol].get(j)[0] == i) {
                        targetIndex = j;
                        break;
                    }
                }

                int nextRow = curHorseRow + move_row[curHorseDir];
                int nextCol = curHorseCol + move_col[curHorseDir];

                if (nextRow >= 1 && nextRow <= n && nextCol >= 1 && nextCol <= n) {
                    switch (map[nextRow][nextCol]) {
                        case 0:
                            whiteMove(targetIndex, curHorseRow, curHorseCol, nextRow, nextCol, turn);
                            break;
                        case 1:
                            redMove(targetIndex, curHorseRow, curHorseCol, nextRow, nextCol, turn);
                            break;
                        case 2:
                            blueMove(targetIndex, curHorseRow, curHorseCol, curHorseDir, i, turn);
                            break;
                        default:
                            break;
                    }
                } else {
                    blueMove(targetIndex, curHorseRow, curHorseCol, curHorseDir, i, turn);
                }
            }
        }

        System.out.println(-1);
    }

    static void whiteMove(int targetIndex, int curRow, int curCol, int nextRow, int nextCol, int turn) {
        for (int i = targetIndex; i < horseMap[curRow][curCol].size(); i++) {
            int[] beforeHorse = horseMap[curRow][curCol].get(i);
            horseMap[nextRow][nextCol].add(beforeHorse);
            horseInfo.put(beforeHorse[0], new int[]{nextRow, nextCol, beforeHorse[1]});
            horseMap[curRow][curCol].remove(i--);
            check(beforeHorse[0], turn);
        }
    }

    static void redMove(int targetIndex, int curRow, int curCol, int nextRow, int nextCol, int turn) {
        for (int i = horseMap[curRow][curCol].size() - 1; i >= targetIndex; i--) {
            int[] beforeHorse = horseMap[curRow][curCol].get(i);
            horseMap[nextRow][nextCol].add(beforeHorse);
            horseInfo.put(beforeHorse[0], new int[]{nextRow, nextCol, beforeHorse[1]});
            horseMap[curRow][curCol].remove(i);
            check(beforeHorse[0], turn);
        }
    }

    static void blueMove(int targetIndex, int curRow, int curCol, int curDir, int horseNum, int turn) {
        int nextDir = changeDir(curDir);

        int nextRow = curRow + move_row[nextDir];
        int nextCol = curCol + move_col[nextDir];
        horseMap[curRow][curCol].get(targetIndex)[1] = nextDir;

        if (nextRow >= 1 && nextRow <= n && nextCol >= 1 && nextCol <= n) {
            switch (map[nextRow][nextCol]) {
                case 0:
                    whiteMove(targetIndex, curRow, curCol, nextRow, nextCol, turn);
                    break;
                case 1:
                    redMove(targetIndex, curRow, curCol, nextRow, nextCol, turn);
                    break;
                case 2:
                default:
                    horseInfo.put(horseNum, new int[]{curRow, curCol, nextDir});
                    check(horseNum, turn);
                    break;
            }
        } else {
            horseInfo.put(horseNum, new int[]{curRow, curCol, nextDir});
            check(horseNum, turn);
        }
    }

    static int changeDir(int curDir) {
        // 0 오, 1 왼, 2 위, 3 아
        switch (curDir) {
            case 0:
                return 1;
            case 1:
                return 0;
            case 2:
                return 3;
            case 3:
                return 2;
            default:
                return -1;
        }
    }

    static void check(int horseNum, int turn) {
        int[] horse = horseInfo.get(horseNum);
        if (horseMap[horse[0]][horse[1]].size() >= 4) {
            System.out.println(turn);
            System.exit(0);
        }
    }
}