import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Baek_21611 {
    static int n, m;
    static int[][] map;
    static Map<Integer, int[]> findMapIndex = new HashMap<>();
    static int[][] mapIndex;
    static int[] shark = new int[]{0, 0};
    static int[] move_row = new int[]{0, -1, 1, 0, 0};
    static int[] move_col = new int[]{0, 0, 0, -1, 1}; // 상 하 좌 우
    static int oneCount = 0;
    static int twoCount = 0;
    static int threeCount = 0;
    static int marbleCount = 0;
    static int tempMarbleCount = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n + 1][n + 1];
        shark[0] = (n + 1) / 2;
        shark[1] = (n + 1) / 2;
        initMapIndex();

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 1; j <= n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] != 0) {
                    marbleCount = Math.max(marbleCount, mapIndex[i][j]);
                }
            }
        }

        // 0. m번 반복
        for (int t = 0; t < m; t++) {
            st = new StringTokenizer(bf.readLine());
            int magicDir = Integer.parseInt(st.nextToken());
            int magicDist = Integer.parseInt(st.nextToken());

            // 1. 마법 사용 (해당 방향, 거리만큼)
            // 2. 벽 부서짐 (해당 칸 0으로)
            usingMagic(magicDir, magicDist);
            // 3. 칸 모임 (인덱스 증가시키면서, 0이면 채우기), 마지막 칸 인덱스 갖고있으면 좋음
            removeEmpty();

            // {
            // 4. 폭발 (인덱스 증가시키면서, 4개 이상이면 다 0만들기)

            // 5. 칸모임
            // } 얘넨 계속 반복
            while (true) {
                if (explode()) {
                    removeEmpty();
                } else {
                    break;
                }
            }

            // 6. 구슬 변화
            changeMarble();
        }

        System.out.println(oneCount + (2 * twoCount) + (3 * threeCount));
    }

    static void initMapIndex() {
        mapIndex = new int[n + 1][n + 1];
        // 1,1 부터
        int curRow = 1;
        int curCol = 1;
        int index = n * n - 1;
        // 상어를 만날 때 까지
        while (curRow != shark[0] && curCol != shark[1]) {
            // 오른쪽 끝
            while (curCol <= n && mapIndex[curRow][curCol] == 0) {
                mapIndex[curRow][curCol] = index;
                findMapIndex.put(index--, new int[]{curRow, curCol++});
            }
            curCol--;
            curRow++;
            // 아래 끝
            while (curRow <= n && mapIndex[curRow][curCol] == 0) {
                mapIndex[curRow][curCol] = index;
                findMapIndex.put(index--, new int[]{curRow++, curCol});
            }
            curCol--;
            curRow--;
            // 왼쪽 끝
            while (curCol >= 1 && mapIndex[curRow][curCol] == 0) {
                mapIndex[curRow][curCol] = index;
                findMapIndex.put(index--, new int[]{curRow, curCol--});
            }
            curCol++;
            curRow--;
            // 위 끝
            while (curRow >= 1 && mapIndex[curRow][curCol] == 0) {
                mapIndex[curRow][curCol] = index;
                findMapIndex.put(index--, new int[]{curRow--, curCol});
            }
            curRow++;
            curCol++;
        }
    }

    static void usingMagic(int dir, int dist) {
        int curRow = shark[0];
        int curCol = shark[1];
        tempMarbleCount = 0;

        for (int i = 1; i <= dist; i++) {
            curRow += move_row[dir];
            curCol += move_col[dir];

            if (map[curRow][curCol] != 0) {
                map[curRow][curCol] = 0;
                tempMarbleCount++;
            }
        }
    }

    static void removeEmpty() {
        // 투 포인터 사용해서 땡기기
        int zeroIndex = 0;

        for (int i = 1; i <= marbleCount; i++) {
            int[] cur = findMapIndex.get(i);

            if (map[cur[0]][cur[1]] == 0) {
                if (zeroIndex == 0) {
                    zeroIndex = mapIndex[cur[0]][cur[1]];
                }
            } else {
                if (zeroIndex != 0) {
                    int[] changePoint = findMapIndex.get(zeroIndex);
                    map[changePoint[0]][changePoint[1]] = map[cur[0]][cur[1]];
                    map[cur[0]][cur[1]] = 0;
                    int[] nextPoint = findMapIndex.get(zeroIndex + 1);
                    if (map[nextPoint[0]][nextPoint[1]] == 0) {
                        zeroIndex++;
                    } else {
                        zeroIndex = 0;
                    }
                }
            }
        }


        marbleCount -= tempMarbleCount;
    }

    static boolean explode() {
        int tempOneCount = 0;
        int tempTwoCount = 0;
        int tempThreeCount = 0;
        tempMarbleCount = 0;
        int startIndex = 0;
        int marbleNum = 0;
        int explodeCount = 0;

        for (int i = 1; i <= marbleCount + 1; i++) {
            int[] cur = findMapIndex.get(i);

            if (marbleNum != map[cur[0]][cur[1]]) {
                // 연속된게 끊기면
                if (i - startIndex >= 4) {
                    // 터지게하기
                    for (int j = startIndex; j < i; j++) {
                        int[] explodePoint = findMapIndex.get(j);
                        map[explodePoint[0]][explodePoint[1]] = 0;
                        explodeCount++;
                    }

                    if (marbleNum == 1) {
                        tempOneCount += explodeCount;
                    } else if (marbleNum == 2) {
                        tempTwoCount += explodeCount;
                    } else {
                        tempThreeCount += explodeCount;
                    }
                    tempMarbleCount += explodeCount;
                    explodeCount = 0;
                }
                startIndex = i;
                marbleNum = map[cur[0]][cur[1]];
            }
        }

        oneCount += tempOneCount;
        twoCount += tempTwoCount;
        threeCount += tempThreeCount;

        return tempOneCount != 0 || tempTwoCount != 0 || tempThreeCount != 0;
    }

    static void changeMarble() {
        int[][] newMap = new int[n + 1][n + 1];
        int startIndex = 1;
        int count = 0;
        int marbleNum = 0;
        int tempSize = marbleCount;

        for (int i = 1; i <= marbleCount + 1; i++) {
            int[] cur = findMapIndex.get(i);

            if (count == 0) {
                count = 1;
                marbleNum = map[cur[0]][cur[1]];
                continue;
            }

            if (map[cur[0]][cur[1]] != marbleNum) {
                if (startIndex >= n * n) {
                    map = newMap;
                    marbleCount = n * n - 1;
                    return;
                }
                int[] aPoint = findMapIndex.get(startIndex);
                newMap[aPoint[0]][aPoint[1]] = count;
                tempSize = startIndex;

                if (startIndex + 1 >= n * n) {
                    map = newMap;
                    marbleCount = n * n - 1;
                    return;
                }

                int[] bPoint = findMapIndex.get(startIndex + 1);
                newMap[bPoint[0]][bPoint[1]] = marbleNum;
                tempSize = startIndex + 1;

                startIndex += 2;
                count = 1;
                marbleNum = map[cur[0]][cur[1]];
            } else {
                count++;
            }
        }

        map = newMap;
        marbleCount = tempSize;
        // 6.2. 하나의 그룹이 두 개의 구슬 A,B로 변함
        // 6.2.1. 구슬 A => 그룹에 있는 구슬 개수
        // 6.2.2. 구슬 B => 그룹을 이루고 있는 구슬의 번호
        // 6.3. 그룹의 제일 작은 인덱스부터 A,B 순서로 칸에 들어감 (밀려남)
        // 6.4. 인덱스 넘어가면 버림
    }
}
