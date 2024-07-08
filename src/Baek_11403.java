import java.io.*;
import java.util.*;

public class Baek_11403 {
	static final int INF = 100_000_000;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(bf.readLine());
		int[][] dist = new int[n][n];

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			for (int j = 0; j < n; j++) {
				dist[i][j] = Integer.parseInt(st.nextToken());

				if(dist[i][j] == 0) dist[i][j] = INF;
			}
		}

		// k: 경유지
		for (int k = 0; k < n; k++) {
			// 노드 i -> j로 가는 경우
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
				}
			}
		}

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(dist[i][j] == INF) {
					sb.append(0);
				} else {
					sb.append(1);
				}
				sb.append(" ");
			}
			sb.append("\n");
		}

		System.out.print(sb);
    }
}
