import java.io.*;
import java.util.*;

public class Baek_6236 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int[] money = new int[n];

		int left = 0;
		int right = 0;

		for (int i = 0; i < n; i++) {
			money[i] = Integer.parseInt(bf.readLine());
			right += money[i];
			left = Math.max(left, money[i]);
		}

		while(left <= right) {
			int mid = (left + right) / 2;

			int count = check(money, mid);

			if (count > m) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}

		System.out.println(left);
    }

	static int check(int[] money, int mid) {
		int count = 0;
		int remain = 0;

		for (int i = 0; i < money.length; i++) {
			if(remain < money[i]) {
				count++;
				remain = mid;
			} 		

			remain -= money[i];
		}

		return count;
	}
}
