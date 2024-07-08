import java.io.*;
import java.util.*;

public class Baek_1956 {
	static final int INF = 100000000;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());

		int v = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());

		int[][] dist = new int[v + 1][v + 1];

		for (int i = 0; i < e; i++) {
			st = new StringTokenizer(bf.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			dist[a][b] = c;
		}

		for (int i = 1; i <= v; i++) {
			for (int j = 1; j <= v; j++) {
				if(dist[i][j] == 0) dist[i][j] = INF;
			}
		}

		for (int k = 1; k <= v; k++) {
			for (int i = 1; i <= v; i++) {
				for (int j = 1; j <= v; j++) {
					dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
				}
			}
		}

		int min = INF;
		for (int i = 1; i <= v; i++) {
			min = Math.min(min, dist[i][i]);
		}

		if(min >= INF) {
			System.out.println(-1);
		} else {
			System.out.println(min);
		}
    }
}
