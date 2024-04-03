import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Codetree_artistry {
	static int n;
	static int answer = 0;
	static int[][] map;
	static int[] move_row = new int[] {-1, 0, 1, 0};
	static int[] move_col = new int[] {0, 1, 0, -1};
	static int[][] groupIndexMap;
	static ArrayList<ArrayList<int[]>> groupInfo;

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		// 0. n은 반드시 홀수(O)
		n = Integer.parseInt(bf.readLine());

		map = new int[n][n];

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int turn = 0; turn <= 3; turn++) {
			// 1. 그룹 만들기 (O)
			makeGroups();

			// 2. 조화로움 계산
			calculateArtistry();

			// 3. 돌리기
			rotateMap();
		}

		System.out.println(answer);
	}

	static void makeGroups() {

		groupInfo = new ArrayList<>();
		groupIndexMap = new int[n][n];
		int groupIndex = 0;
		boolean[][] visited = new boolean[n][n];
		Queue<int[]> queue = new LinkedList<>();

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (!visited[i][j]) {
					groupInfo.add(new ArrayList<>());
					groupInfo.get(groupIndex).add(new int[] {i, j});
					groupIndexMap[i][j] = groupIndex;
					queue.offer(new int[] {i, j});
					visited[i][j] = true;
					int num = map[i][j];

					while (!queue.isEmpty()) {
						int[] cur = queue.poll();

						for (int k = 0; k < 4; k++) {
							int nextRow = cur[0] + move_row[k];
							int nextCol = cur[1] + move_col[k];

							if (nextRow >= 0 && nextRow < n && nextCol >= 0 && nextCol < n) {
								if (map[nextRow][nextCol] == num && !visited[nextRow][nextCol]) {
									visited[nextRow][nextCol] = true;
									queue.offer(new int[] {nextRow, nextCol});
									groupInfo.get(groupIndex).add(new int[] {nextRow, nextCol});
									groupIndexMap[nextRow][nextCol] = groupIndex;
								}
							}
						}
					}

					groupIndex++;
				}
			}
		}
	}

	static void calculateArtistry() {
		int groupSize = groupInfo.size();

		// 각 그룹간의 조화로움 계산
		// 예를 들어, (G0, G1), (G0, G2), (G0, G3), (G1, G2), (G1, G3), (G2, G3)가 계산됨
		for (int i = 0; i < groupSize - 1; i++) {
			for (int j = i + 1; j < groupSize; j++) {
				int score = 0;
				ArrayList<int[]> groupA = groupInfo.get(i);
				ArrayList<int[]> groupB = groupInfo.get(j);

				// 그룹 A와 B의 조화로움
				// (그룹 A에 속한 칸 수) + (그룹 B에 속한 칸 수) X 그룹 A를 이루고 있는 숫자
				// X 그룹 B를 이루고 있는 숫자 X 그룹 A와 그룹 B가 서로 맞닿아 있는 변의 수

				// 그룹 A에 속한 칸 수
				int groupACounts = groupA.size();
				// 그룹 B에 속한 칸 수
				int groupBCounts = groupB.size();
				// 그룹 A를 이루고 있는 숫자
				int groupANum = map[groupA.get(0)[0]][groupA.get(0)[1]];
				// 그룹 B를 이루고 있는 숫자
				int groupBNum = map[groupB.get(0)[0]][groupB.get(0)[1]];
				// 그룹 A와 그룹 B가 맞닿아 있는 변의 수
				int sideCounts = 0;
				if (groupACounts < groupBCounts) {
					sideCounts = calculateSideCounts(groupA, j);
				} else {
					sideCounts = calculateSideCounts(groupB, i);
				}

				score = (groupACounts + groupBCounts) * groupANum * groupBNum * sideCounts;

				answer += score;
			}
		}
	}

	static int calculateSideCounts(ArrayList<int[]> group, int groupIndex) {
		int count = 0;

		for (int i = 0; i < group.size(); i++) {
			int[] curPoint = group.get(i);

			for (int j = 0; j < 4; j++) {
				int nextRow = curPoint[0] + move_row[j];
				int nextCol = curPoint[1] + move_col[j];

				if (nextRow >= 0 && nextRow < n && nextCol >= 0 && nextCol < n) {
					if (groupIndexMap[nextRow][nextCol] == groupIndex)
						count++;
				}
			}
		}
		return count;
	}

	static void rotateMap() {
		// 중앙 십자가 회전
		rotateCrossRotate();

		// 나머지 네 부분 회전
		rotateSquareRotate(new int[] {0, 0}, new int[] {n / 2 - 1, n / 2 - 1});
		rotateSquareRotate(new int[] {0, n / 2 + 1}, new int[] {n / 2 - 1, n - 1});
		rotateSquareRotate(new int[] {n / 2 + 1, 0}, new int[] {n - 1, n / 2 - 1});
		rotateSquareRotate(new int[] {n / 2 + 1, n / 2 + 1}, new int[] {n - 1, n - 1});
	}

	static void rotateCrossRotate() {
		int[] temp = new int[n / 2];
		// 위 -> 왼
		for (int i = 0; i < n / 2; i++) {
			temp[i] = map[n / 2][i];
			map[n / 2][i] = map[i][n / 2];
		}

		// 왼 -> 아
		for (int i = 0; i < n / 2; i++) {
			int origin = map[n - 1 - i][n / 2];
			map[n - 1 - i][n / 2] = temp[i];
			temp[i] = origin;
		}

		// 아 -> 오
		for (int i = 0; i < n / 2; i++) {
			int origin = map[n / 2][n - 1 - i];
			map[n / 2][n - 1 - i] = temp[i];
			temp[i] = origin;
		}

		// 오 -> 위
		for (int i = 0; i < n / 2; i++) {
			map[i][n / 2] = temp[i];
		}
	}

	static void rotateSquareRotate(int[] leftUp, int[] rightDown) {

		int[][] temp = new int[n / 2][n / 2];

		int originCol = leftUp[1];
		int originRow = rightDown[0];

		for (int i = 0; i <= n / 2 - 1; i++) {
			for (int j = 0; j <= n / 2 - 1; j++) {
				temp[i][j] = map[originRow--][originCol];
			}
			originCol++;
			originRow = rightDown[0];
		}

		for (int i = 0; i <= n / 2 - 1; i++) {
			for (int j = 0; j <= n / 2 - 1; j++) {
				map[leftUp[0] + i][leftUp[1] + j] = temp[i][j];
			}
		}
	}
}