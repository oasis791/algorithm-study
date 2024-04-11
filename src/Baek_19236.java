import java.util.*;
import java.io.*;

public class Baek_19236 {
    static int[] sharkInfo = new int[3]; // 0: row, 1: col, 2: dir
    static int[][] fishMap = new int[4][4];
    static int[] move_row = new int[] { -1, -1, 0, 1, 1, 1, 0, -1 }; // 상, 좌상, 좌, 좌하, 하, 우하, 우, 우상
    static int[] move_col = new int[] { 0, -1, -1, -1, 0, 1, 1, 1 };
    static Map<Integer, int[]> fishInfo = new HashMap<>();
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        // 1. 4x4 공간
        // 2. 한칸에는 물고기 한마리 있음
        // 3. 각 물고기는 번호(1<= i <= 16) 와 방향(8가지 방향)을 갖고 있다.

        for (int i = 0; i < 4; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < 4; j++) {
                int fishNum = Integer.parseInt(st.nextToken());
                int dirNum = Integer.parseInt(st.nextToken()) - 1;

                fishMap[i][j] = fishNum;
                fishInfo.put(fishNum, new int[] { i, j, dirNum });
            }
        }

        // 4. 청소년 상어는 (0,0)에 있는 물고기를 먹고, (0,0)에 들어간다.
        // 5. 상어의 방향은 (0,0)에 있던 물고기의 방향과 같다.
        answer += fishMap[0][0];
        sharkInfo[0] = 0;
        sharkInfo[1] = 0;
        sharkInfo[2] = fishInfo.get(fishMap[0][0])[2];
        fishInfo.put(fishMap[0][0], new int[] { -1, -1, -1 });
        fishMap[0][0] = 0;

        // 6. 물고기 이동
        fishMove();

        int temp = answer;
        // 7. 상어 이동
        sharkMove(temp);

        System.out.println(answer);

    }

    static void fishMove() {
        // 6.1. 물고기는 번호가 작은 물고기부터 순서대로 이동

        for (int i = 1; i <= 16; i++) {
            int[] curInfo = fishInfo.get(i);
            int row = curInfo[0];
            int col = curInfo[1];
            int dir = curInfo[2];

            if (row == -1)
                continue;

            for (int j = 0; j < 8; j++) {
                int nextRow = row + move_row[(dir + j) % 8];
                int nextCol = col + move_col[(dir + j) % 8];

                if (nextRow >= 0 && nextRow < 4 && nextCol >= 0 && nextCol < 4) {
                    if (nextRow != sharkInfo[0] || nextCol != sharkInfo[1]) {
                        if (fishMap[nextRow][nextCol] > 0) {
                            int nextFish = fishMap[nextRow][nextCol];
                            int nextFishDir = fishInfo.get(nextFish)[2];
                            fishInfo.put(nextFish, new int[] { row, col, nextFishDir });
                            fishMap[row][col] = nextFish;
                        } else {
                            fishMap[row][col] = 0;
                        }
                        fishMap[nextRow][nextCol] = i;
                        fishInfo.put(i, new int[] { nextRow, nextCol, (dir + j) % 8 });
                        break;
                    }
                }
                // 6.1.1. 물고기는 한 칸을 이동할 수 있고(o), 이동할 수 있는 칸은 빈 칸, 다른 물고기가 있는 칸 (O)
                // 6.1.2. 이동할 수 없는 칸은 상어가 있는 칸, 경계를 넘는 칸
                // 6.2. 각 물고기는 방향이 이동할 수 있는 칸을 향할 때 까지 45도 반시계 회전한다.
                // 6.2.1. 만약, 이동할 수 있는 칸이 없으면 이동하지 않는다.
                // 6.2.2. 이동할 수 있으면 이동한다.
                // 6.2.3. 다른 물고기가 있는 칸으로 이동할 때는 서로의 위치를 바꾼다.
            }

        }
    }

    static void sharkMove(int count) {
        if (count > answer) {
            answer = count;
        }

        int[] originSharkInfo = Arrays.copyOf(sharkInfo, 3);
        int[][] originFishMap = new int[4][4];
        for (int i = 0; i < 4; i++) {
            originFishMap[i] = Arrays.copyOf(fishMap[i], 4);
        }
        Map<Integer, int[]> originFishInfo = new HashMap<>();
        for (int i : fishInfo.keySet()) {
            originFishInfo.put(i, fishInfo.get(i));
        }

        int nextRow = sharkInfo[0];
        int nextCol = sharkInfo[1];

        // 7.1. 상어는 방향에 있는 칸으로 이동할 수 있음
        // 7.2. 한번에 여러개의 칸을 이동할 수 있음

        while (nextRow + move_row[sharkInfo[2]] >= 0 && nextRow + move_row[sharkInfo[2]] < 4
                && nextCol + move_col[sharkInfo[2]] >= 0 && nextCol + move_col[sharkInfo[2]] < 4) {
            nextRow += move_row[sharkInfo[2]];
            nextCol += move_col[sharkInfo[2]];

            if (fishMap[nextRow][nextCol] > 0) {
                int temp = fishMap[nextRow][nextCol];
                sharkInfo[2] = fishInfo.get(fishMap[nextRow][nextCol])[2];
                fishInfo.put(fishMap[nextRow][nextCol], new int[] { -1, -1, -1 });
                fishMap[nextRow][nextCol] = 0;

                sharkInfo[0] = nextRow;
                sharkInfo[1] = nextCol;
                fishMove();
                sharkMove(count + temp);

                for (int i = 0; i < 4; i++) {
                    fishMap[i] = Arrays.copyOf(originFishMap[i], 4);
                }
                for (int i : originFishInfo.keySet()) {
                    fishInfo.put(i, originFishInfo.get(i));
                }
                sharkInfo = Arrays.copyOf(originSharkInfo, 3);
            }
                }
        // 7.3.상어가 물고기가 있는 칸으로 이동했다면, 그 칸에 물고기를 먹고, 그 물고기의 방향을 갖게 된다.
        // 7.4. 이동하는 중에 지나는 칸에 있는 물고기는 먹지 않는다.
        // 7.5. 물고기가 없는 칸으로는 이동할 수 없다.
        // 7.6. 상어가 이동할 수 있는 칸이 없으면, 공간에서 벗어나 집으로 간다.

    }
}
