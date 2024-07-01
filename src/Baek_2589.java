import java.io.*;
import java.util.*;

public class Baek_2589 {
	static int answer = 0;
	static int[] move_row = new int[]{-1, 0, 1, 0};
	static int[] move_col = new int[]{0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		boolean[][] land = new boolean[n][m];

		for (int i = 0; i < n; i++) {
			String s = bf.readLine();
			for (int j = 0; j < m; j++) {
				if(s.charAt(j) == 'L') {
					land[i][j] = true;
				} else {
					land[i][j] = false;
				}
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(land[i][j]) {
					bfs(i, j, n, m, land);
				}
			}
		}

		System.out.println(answer);
	}

	static void bfs(int row, int col, int n, int m, boolean[][] land) {
		boolean[][] visited = new boolean[n][m];
		int[][] dist = new int[n][m];
		visited[row][col] = true;
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[]{row, col});


		int max = 0;

		while(!queue.isEmpty()) {
			int[] cur = queue.poll();

			for (int i = 0; i < 4; i++) {
				int nextRow = cur[0] + move_row[i];
				int nextCol = cur[1] + move_col[i];

				if(nextRow >= 0 && nextRow < n && nextCol >= 0 && nextCol < m) {
					if(!visited[nextRow][nextCol] && land[nextRow][nextCol]) {
						dist[nextRow][nextCol] = dist[cur[0]][cur[1]] + 1;
						max = Math.max(dist[nextRow][nextCol], max);

						queue.offer(new int[]{nextRow, nextCol});
						visited[nextRow][nextCol] = true;
					}
				}
			}
		}

		answer = Math.max(answer, max);
	}
}
