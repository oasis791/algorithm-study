import java.io.*;
import java.util.*;

public class Baek_15683 {
    static int[][] map;
    static int answer = Integer.MAX_VALUE;
    static int n, m;
    static ArrayList<int[]> cctv = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            for(int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if(map[i][j] >= 1 && map[i][j] <= 5) {
                    cctv.add(new int[]{i , j, map[i][j]});
                }
            }
        }

        dfs(0, map);
        System.out.println(answer);
    }

    static void dfs(int index, int[][] matrix) {
        if(index == cctv.size()) {
            int count = 0;
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < m; j++) {
                    if(matrix[i][j] == 0)
                        count++;
                }
            }
            answer = Math.min(answer, count);
            return;
        }

        int cctvNum = cctv.get(index)[2];

        switch(cctvNum) {
            case 1: 
                int[] move_row = new int[]{-1, 0, 1, 0};
                int[] move_col = new int[]{0, 1, 0, -1};
                for(int i = 0; i < 4; i++) {
                    int[][] temp = new int[n][m];
                    for(int j = 0; j < n; j++) {
                        temp[j] = Arrays.copyOf(matrix[j], m);
                    }

                    int nextRow = cctv.get(index)[0] + move_row[i];
                    int nextCol = cctv.get(index)[1] + move_col[i];
                    while(nextRow >= 0 && nextCol >= 0 && nextRow < n && nextCol < m && (temp[nextRow][nextCol] != 6)) {
                        if(temp[nextRow][nextCol] == 0) {
                            temp[nextRow][nextCol] = -1;
                        }
                        nextRow += move_row[i];
                        nextCol += move_col[i];
                    }
                    dfs(index + 1, temp);
                }
                break;
            case 2:
                int[] move_row_1 = new int[] {0, 1};
                int[] move_row_2 = new int[] {0 , -1};
                int[] move_col_1 = new int[] {1, 0};
                int[] move_col_2 = new int[] {-1, 0};
                for(int i = 0; i < 2; i++) {
                    int[][] temp = new int[n][m];
                    for(int j = 0; j < n; j++) {
                        temp[j] = Arrays.copyOf(matrix[j], m);
                    }

                    int nextRow = cctv.get(index)[0] + move_row_1[i];
                    int nextCol = cctv.get(index)[1] + move_col_1[i];
                    while(nextRow >= 0 && nextCol >= 0 && nextRow < n && nextCol < m && (temp[nextRow][nextCol] != 6)) {
                        if(temp[nextRow][nextCol] == 0) {
                            temp[nextRow][nextCol] = -1;
                        }
                        nextRow += move_row_1[i];
                        nextCol += move_col_1[i];
                    }

                    nextRow = cctv.get(index)[0] + move_row_2[i];
                    nextCol = cctv.get(index)[1] + move_col_2[i];
                    while(nextRow >= 0 && nextCol >= 0 && nextRow < n && nextCol < m && (temp[nextRow][nextCol] != 6)) {
                        if(temp[nextRow][nextCol] == 0) {
                            temp[nextRow][nextCol] = -1;
                        }
                        nextRow += move_row_2[i];
                        nextCol += move_col_2[i];
                    }

                    dfs(index + 1, temp);
                }
                break;
            case 3:
                move_row_1 = new int[] {-1, 0, 1, 0};
                move_row_2 = new int[] {0, 1, 0, -1};
                move_col_1 = new int[] {0, 1, 0, -1};
                move_col_2 = new int[] {1, 0, -1, 0};

                for(int i = 0; i < 4; i++) {
                    int[][] temp = new int[n][m];
                    for(int j = 0; j < n; j++) {
                        temp[j] = Arrays.copyOf(matrix[j], m);
                    }

                    int nextRow = cctv.get(index)[0] + move_row_1[i];
                    int nextCol = cctv.get(index)[1] + move_col_1[i];
                    while(nextRow >= 0 && nextCol >= 0 && nextRow < n && nextCol < m && (temp[nextRow][nextCol] != 6)) {
                        if(temp[nextRow][nextCol] == 0) {
                            temp[nextRow][nextCol] = -1;
                        }
                        nextRow += move_row_1[i];
                        nextCol += move_col_1[i];
                    }

                    nextRow = cctv.get(index)[0] + move_row_2[i];
                    nextCol = cctv.get(index)[1] + move_col_2[i];
                    while(nextRow >= 0 && nextCol >= 0 && nextRow < n && nextCol < m && (temp[nextRow][nextCol] != 6)) {
                        if(temp[nextRow][nextCol] == 0) {
                            temp[nextRow][nextCol] = -1;
                        }
                        nextRow += move_row_2[i];
                        nextCol += move_col_2[i];
                    }

                    dfs(index + 1, temp);

                }
                break;
            case 4:
                move_row_1 = new int[] {-1, 0, 1, 0};
                move_row_2 = new int[] {0, 1, 0, -1};
                int[] move_row_3 = new int[]{0, -1, 0, 1};
                move_col_1 = new int[] {0, 1, 0, -1};
                move_col_2 = new int[] {1, 0, -1, 0};
                int[] move_col_3 = new int[]{-1, 0, 1, 0};

                for(int i = 0; i < 4; i++) {
                    int[][] temp = new int[n][m];
                    for(int j = 0; j < n; j++) {
                        temp[j] = Arrays.copyOf(matrix[j], m);
                    }

                    int nextRow = cctv.get(index)[0] + move_row_1[i];
                    int nextCol = cctv.get(index)[1] + move_col_1[i];
                    while(nextRow >= 0 && nextCol >= 0 && nextRow < n && nextCol < m && (temp[nextRow][nextCol] != 6)) {
                        if(temp[nextRow][nextCol] == 0) {
                            temp[nextRow][nextCol] = -1;
                        }
                        nextRow += move_row_1[i];
                        nextCol += move_col_1[i];
                    }

                    nextRow = cctv.get(index)[0] + move_row_2[i];
                    nextCol = cctv.get(index)[1] + move_col_2[i];
                    while(nextRow >= 0 && nextCol >= 0 && nextRow < n && nextCol < m && (temp[nextRow][nextCol] != 6)) {
                        if(temp[nextRow][nextCol] == 0) {
                            temp[nextRow][nextCol] = -1;
                        }
                        nextRow += move_row_2[i];
                        nextCol += move_col_2[i];
                    }

                    nextRow = cctv.get(index)[0] + move_row_3[i];
                    nextCol = cctv.get(index)[1] + move_col_3[i];
                    while(nextRow >= 0 && nextCol >= 0 && nextRow < n && nextCol < m && (temp[nextRow][nextCol] != 6)) {
                        if(temp[nextRow][nextCol] == 0) {
                            temp[nextRow][nextCol] = -1;
                        }
                        nextRow += move_row_3[i];
                        nextCol += move_col_3[i];
                    }
                    dfs(index + 1, temp);
                }
                break;
            case 5: 
                move_row = new int[]{-1, 0, 1, 0};
                move_col = new int[]{0, 1, 0, -1};
                int[][] temp = new int[n][m];
                for(int j = 0; j < n; j++) {
                    temp[j] = Arrays.copyOf(matrix[j], m);
                }
                for(int i = 0; i < 4; i++ ) {
                    int nextRow = cctv.get(index)[0] + move_row[i];
                    int nextCol = cctv.get(index)[1] + move_col[i];

                    while(nextRow >= 0 && nextCol >= 0 && nextRow < n && nextCol < m && (temp[nextRow][nextCol] != 6)) {
                        if(temp[nextRow][nextCol] == 0) {
                            temp[nextRow][nextCol] = -1;
                        }
                        nextRow += move_row[i];
                        nextCol += move_col[i];
                    }
                }

                dfs(index + 1, temp);
                break;
            default:
                break;
        }
    }
}
