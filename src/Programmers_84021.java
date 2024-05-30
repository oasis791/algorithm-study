import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

public class Programmers_84021 {
	static class Solution {
		static ArrayList<ArrayList<int[]>> empty = new ArrayList<>();
		static ArrayList<ArrayList<int[]>> blocks = new ArrayList<>();
		static int[] move_row = new int[]{-1, 0, 1, 0};
		static int[] move_col = new int[]{0, 1, 0, -1};
		static int n;
		static int answer = 0;
		public static int solution(int[][] game_board, int[][] table) {
			n = game_board.length;
			// 1. 테이블 위 퍼즐 조각을 빈 공간에 적절히 올려 놓는다.

			// 2. 조각은 한번에 하나씩 채워 넣는다.
			// 3. 조각을 회전시킬 수 있지만, 뒤집을 수 없음
			// 4. 새로 채워넣은 퍼즐 조각과 인접한 칸이 비어있으면 안된다.

			// 5. 게임 판의 빈 칸 목록들을 다 뽑아낸다.
			boolean[][] visited = new boolean[n][n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if(game_board[i][j] == 0 && !visited[i][j]) {
						emptyBfs(i, j, game_board, visited);
					}
				}
			}

			// 6. 테이블 위에 블록을 다 뽑아낸다.
			visited = new boolean[n][n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if(table[i][j] == 1 && !visited[i][j]) {
						blockBfs(i, j, table, visited);
					}
				}
			}

			// shift 해서 제일 좌측 상단으로 몬다.
			for (int i = 0; i < empty.size(); i++) {
				shift(empty.get(i));
			} 
			for (int i = 0; i < blocks.size(); i++) {
				shift(blocks.get(i));
			}


			// 7. 모든 조합 확인(돌려가며)해서 최대 개수 찾으면 끝?
			dfs(0, new boolean[blocks.size()], 0, 0);

			return answer;
		}

		static void shift (ArrayList<int[]> list) {

			int minRow = n;
			int minCol = n;

			for (int[] point : list) {
				minRow = Math.min(point[0], minRow);
				minCol = Math.min(point[1], minCol);
			}

			for (int[] point : list) {
				point[0] -= minRow;
				point[1] -= minCol;
			}
		}

		static void dfs (int curEmpty, boolean[] usedBlock, int sum, int count) {
			if (count == blocks.size() || curEmpty >= empty.size()) {
				answer = Math.max(answer, sum);
				return;
			}

			boolean isChecked = false;

			answer = Math.max(answer, sum);
			for (int i = 0; i < blocks.size(); i++) {
				if(!usedBlock[i] && (empty.get(curEmpty).size() == blocks.get(i).size())) {
					if(check(curEmpty, i)) {
						usedBlock[i] = true;
						isChecked = true;
						dfs(curEmpty + 1, usedBlock, sum + empty.get(curEmpty).size(), count + 1);
					}
				}
			}

			if(!isChecked) {
				dfs(curEmpty + 1, usedBlock, sum, count);
			}

		}

		static boolean check(int emptyIndex, int blockIndex) {
			ArrayList<int[]> emptyPoints = empty.get(emptyIndex);
			ArrayList<int[]> blockPoints = blocks.get(blockIndex);
			boolean[][] checkMap = new boolean[n][n];

			for (int[] point : emptyPoints) {
				checkMap[point[0]][point[1]] = true;
			}

loop:
			for (int i = 0; i < 4; i++) {
				if(i != 0)
					rotate(blockPoints);

				for (int[] point : blockPoints) {
					if(!checkMap[point[0]][point[1]]) {
						continue loop;
					}
				}
				return true;
			}

			return false;
		}

		static void rotate (ArrayList<int[]> blockPoints) {

			for (int[] point : blockPoints) {
				int temp = point[1];
				point[1] = -1 * point[0];
				point[0] = temp;

			}

			shift(blockPoints);
		}

		static void emptyBfs(int i, int j, int[][] game_board, boolean[][] visited) {
			Queue<int[]> queue = new LinkedList<>();
			queue.offer(new int[]{i, j});
			visited[i][j] = true;
			empty.add(new ArrayList<>());

			ArrayList<int[]> curShape = empty.get(empty.size() - 1);
			curShape.add(new int[] {i, j});

			while(!queue.isEmpty()) {
				int[] cur = queue.poll();

				for (int k = 0; k < 4; k++) {
					int nextRow = cur[0] + move_row[k];
					int nextCol = cur[1] + move_col[k];

					if(nextRow >= 0 && nextRow < n && nextCol >= 0 && nextCol < n) {
						if(game_board[nextRow][nextCol] == 0 && !visited[nextRow][nextCol]) {
							visited[nextRow][nextCol] = true;
							queue.offer(new int[]{nextRow, nextCol});
							curShape.add(new int[]{nextRow, nextCol});
						}
					}
				}
			}
		}

		static void blockBfs(int i, int j, int[][] table, boolean[][] visited) {

			Queue<int[]> queue = new LinkedList<>();
			queue.offer(new int[]{i, j});
			visited[i][j] = true;
			blocks.add(new ArrayList<>());

			ArrayList<int[]> curShape = blocks.get(blocks.size() - 1);
			curShape.add(new int[] {i, j});

			while(!queue.isEmpty()) {
				int[] cur = queue.poll();

				for (int k = 0; k < 4; k++) {
					int nextRow = cur[0] + move_row[k];
					int nextCol = cur[1] + move_col[k];

					if(nextRow >= 0 && nextRow < n && nextCol >= 0 && nextCol < n) {
						if(table[nextRow][nextCol] == 1 && !visited[nextRow][nextCol]) {
							visited[nextRow][nextCol] = true;
							queue.offer(new int[]{nextRow, nextCol});
							curShape.add(new int[]{nextRow, nextCol});
						}
					}
				}
			}
		}
	}
}
