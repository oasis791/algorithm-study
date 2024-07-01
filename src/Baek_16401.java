import java.io.*;
import java.util.*;

public class Baek_16401 {
	static int answer = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		int[] snacks  = new int[n];

		int min = 1;
		int max = Integer.MIN_VALUE;

		st = new StringTokenizer(bf.readLine());
		// 최대한 긴 과자를 똑같이 조카들에게
		for (int i = 0; i < snacks.length; i++) {
			snacks[i] = Integer.parseInt(st.nextToken());

			max = Math.max(max, snacks[i]);
		}


		while (min <= max) {
			int mid = (min + max) / 2;
			int count = check(mid, snacks);

			if(count >= m) {
				answer = Math.max(answer, mid);
				min = mid + 1;
			} else {
				max = mid - 1;
			}
		}

		System.out.println(answer);
	}

	static int check(int mid, int[] snacks) {
		int count = 0;

		for (int i = 0; i < snacks.length; i++) {
			count += snacks[i] / mid;
		}

		return count;
	}
}
