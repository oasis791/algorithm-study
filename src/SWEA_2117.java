package algo;

import java.util.*;
import java.io.*;

public class SWEA_2117 {
	static int T, N, M, answer, max;
	static int[][] map;
	static int[] move_row = new int[] {-1, 0, 1, 0};
	static int[] move_col = new int[] {0, 1, 0, -1};
	static int[][] visited;
	static Queue<int[]> queue;
	static int[] checkHome;
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(bf.readLine());
		
		for(int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			answer = 0;
			map = new int[N][N];
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(bf.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i = 0; i < N; i++) {
				for(int j = 0 ; j < N; j++) {
					queue = new LinkedList<>();
					visited = new int[N][N];
					queue.offer(new int[] {i,j});
					checkHome = new int[10000];
					visited[i][j] = 1;
					counting();
					findAnswer();
				}
			}
			sb.append("#").append(t).append(" ").append(answer).append("\n");
		}
		
		System.out.print(sb);
	}
	
	static int calculateFee (int k) {
		return k * k + (k-1) * (k-1);
	}
	
	static void counting() {
		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			if(map[now[0]][now[1]] == 1) {
				checkHome[visited[now[0]][now[1]]]++;
			}
			for(int i = 0; i < 4; i++) {
				int nextRow = now[0] + move_row[i];
				int nextCol = now[1] + move_col[i];
				if(nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < N) {
					if(visited[nextRow][nextCol] == 0) {
						visited[nextRow][nextCol] = visited[now[0]][now[1]] + 1;
						queue.offer(new int[] {nextRow, nextCol});
					}
				}
			}
		}
	}
	
	static void findAnswer() {
		for(int i = 1; i < 10000; i++) {
			checkHome[i] += checkHome[i-1];
		}

		for (int i = 1 ; i < 10000; i++) {
			if(checkHome[i] * M - calculateFee(i) >= 0) {
				answer = Math.max(answer, checkHome[i]);
			}
		}
	}
}
