import java.util.*;
import java.io.*;

public class Baek_16926 {
	static int n, m, r;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());

		map = new int[n][m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(bf.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int turn = 0; turn < r; turn++) {
			rotate();
		}

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				sb.append(map[i][j]).append(" ");
			}
			sb.append("\n");
		}

		System.out.print(sb);
	}

	static void rotate() {
		boolean[][] visited = new boolean[n][m];
		int[] leftUp = new int[] { 0, 0 };
		int[] rightDown = new int[] { n - 1, m - 1 };

		while (leftUp[0] < rightDown[0] && !visited[leftUp[0]][leftUp[1]]) {
			int row = leftUp[0];
			int col = leftUp[1];
			int temp = map[row][col];

			row++;
			while (row <= rightDown[0]) {
				int temp2 = map[row][col];
				visited[row][col] = true;
				map[row++][col] = temp;
				temp = temp2;
			}
			row--;
			col++;
			while (col <= rightDown[1]) {
				int temp2 = map[row][col];
				visited[row][col] = true;
				map[row][col++] = temp;
				temp = temp2;
			}
			col--;
			row--;

			while (row >= leftUp[0]) {
				int temp2 = map[row][col];
				visited[row][col] = true;
				map[row--][col] = temp;
				temp = temp2;
			}
			row++;
			col--;

			while (col >= leftUp[1]) {
				int temp2 = map[row][col];
				visited[row][col] = true;
				map[row][col--] = temp;
				temp = temp2;
			}
			col++;

			leftUp[0]++;
			leftUp[1]++;
			rightDown[0]--;
			rightDown[1]--;
		}
	}
}
