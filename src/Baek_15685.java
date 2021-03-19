//15685번 드래곤 커브
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Baek_15685 {
    private static int N;
    private static boolean[][] matrix;
    private static ArrayList<int[]> point = new ArrayList<>();
    private static ArrayList<Integer> info = new ArrayList<>();
    private static int count = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        matrix = new boolean[101][101];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
            int x, y, d, g;
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            point.add(new int[]{x, y});
            matrix[y][x] = true;
            d = Integer.parseInt(st.nextToken());
            info.add(d);
            switch (d) {
                case 0:
                    if (x + 1 >= 101) {
                        break;
                    }
                    matrix[y][x + 1] = true;
                    point.add(new int[]{x + 1, y});
                    break;
                case 1:
                    if (y - 1 < 0) {
                        break;
                    }
                    matrix[y - 1][x] = true;
                    point.add(new int[]{x, y - 1});
                    break;
                case 2:
                    if (x - 1 < 0) {
                        break;
                    }
                    matrix[y][x - 1] = true;
                    point.add(new int[]{x - 1, y});
                    break;
                case 3:
                    if (y + 1 >= 101) {
                        break;
                    }
                    matrix[y + 1][x] = true;
                    point.add(new int[]{x, y + 1});
                    break;
                default:
                    break;
            }
            g = Integer.parseInt(st.nextToken());
            if (point.size() >= 2) {
                for (int j = 0; j < g; j++) { // 세대 증가
                    int size = point.size();
                    int infoIndex = info.size() - 1;
                    // 세대별로 증가 시켜주기 위해 point 모두 인덱싱
                    for (int k = size - 2; k >= 0; k--) { // size-1는 Last점 size-2부터 옮겨야 될 포인트
                        int[] last = point.get(point.size() - 1);
                        int moveInfo = info.get(infoIndex);
                        moveInfo += 1;
                        if (moveInfo == 4)
                            moveInfo = 0;
                        info.add(moveInfo);
                        switch (moveInfo) {
                            case 0:
                                if (last[0] + 1 >= 101) {
                                    break;
                                }
                                matrix[last[1]][last[0] + 1] = true;
                                point.add(new int[]{last[0] + 1, last[1]});
                                break;
                            case 1:
                                if (last[1] - 1 < 0) {
                                    break;
                                }
                                matrix[last[1] - 1][last[0]] = true;
                                point.add(new int[]{last[0], last[1] - 1});
                                break;
                            case 2:
                                if (last[0] - 1 < 0) {
                                    break;
                                }
                                matrix[last[1]][last[0] - 1] = true;
                                point.add(new int[]{last[0] - 1, last[1]});
                                break;
                            case 3:
                                if (last[1] + 1 >= 101) {
                                    break;
                                }
                                matrix[last[1] + 1][last[0]] = true;
                                point.add(new int[]{last[0], last[1] + 1});
                                break;
                            default:
                                break;
                        }
                        infoIndex--;
                    }
                }
                point.clear();
                info.clear();
            }
        }
        for (int i = 0; i < 101; i++) {
            for (int j = 0; j < 101; j++) {
                if (j + 1 >= 101 || i + 1 >= 101) {
                    continue;
                }
                if (matrix[i][j] && matrix[i][j + 1] && matrix[i + 1][j] && matrix[i + 1][j + 1]) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }
}
