import java.util.*;
import java.io.*;

public class Baek_1926 {
	static int[] move_row = new int[] {-1, 0, 1, 0};
	static int[] move_col = new int[] {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int[][] map = new int[n][m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(bf.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int max = 0;
		int count = 0;

		boolean[][] visited = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(!visited[i][j] && map[i][j] == 1) {
					count++;
					int temp = 0;

					Queue<int[]> queue = new LinkedList<>();
					visited[i][j] = true;
					queue.offer(new int[]{i, j});

					while(!queue.isEmpty()) {
						temp++;
						int[] cur = queue.poll();

						for (int k = 0; k < 4; k++) {
							int nextRow = cur[0] + move_row[k];
							int nextCol = cur[1] + move_col[k];

							if(nextRow >= 0 && nextRow < n && nextCol >= 0 && nextCol < m) {
								if(!visited[nextRow][nextCol] && map[nextRow][nextCol] == 1) {
									visited[nextRow][nextCol] = true;
									queue.offer(new int[]{nextRow, nextCol});
								}
							}
						}
					}
					max = Math.max(max, temp);
				}
			}
		}

		System.out.println(count);
		System.out.println(max);
	}
}
