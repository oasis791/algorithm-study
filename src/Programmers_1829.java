import java.util.Queue;
import java.util.LinkedList;

public class Programmers_1829 {
	static class Solution {
		static int[] move_row = new int[] {-1, 0, 1, 0};
		static int[] move_col = new int[] {0, 1, 0, -1};
		public static int[] solution(int m, int n, int[][] picture) {
			int numberOfArea = 0;
			int maxSizeOfOneArea = 0;

			boolean[][] visited = new boolean[m][n];
			Queue<int[]> queue = new LinkedList<>();

			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					if(picture[i][j] > 0 && !visited[i][j]) {
						queue.offer(new int[] {i, j});
						visited[i][j] = true;
						numberOfArea++;
						int temp = 1;
						int color = picture[i][j];

						while(!queue.isEmpty()) {
							int[] cur = queue.poll();

							for (int k = 0; k < 4; k++) {
								int nextRow = cur[0] + move_row[k];
								int nextCol = cur[1] + move_col[k];

								if(nextRow >= 0 && nextRow < m && nextCol >= 0 && nextCol < n) {
									if(!visited[nextRow][nextCol] && picture[nextRow][nextCol] == color) {
										queue.offer(new int[]{nextRow, nextCol});
										visited[nextRow][nextCol] = true;
										temp++;
									}
								}
							}
						}

						maxSizeOfOneArea = Math.max(maxSizeOfOneArea, temp);
					}
				}
			}

			int[] answer = new int[2];
			answer[0] = numberOfArea;
			answer[1] = maxSizeOfOneArea;
			return answer;
		}
	}
}
