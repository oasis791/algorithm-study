import java.io.*;
import java.util.*;

public class Baek_1543 {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String doc = bf.readLine();
		String target = bf.readLine();

		int answer = 0;

		for (int start = 0; start <= doc.length() - target.length(); start++) {
			int count = 0;
			int targetIndex = 0;
			for (int i = start; i < doc.length(); i++) {

				char c = doc.charAt(i);

				if(target.charAt(targetIndex) == c) {
					targetIndex++;
				} else {
					targetIndex = 0;
				}

				if(targetIndex == target.length()) {
					count++;
					targetIndex = 0;
				}
			}

			answer = Math.max(count, answer);
		}

		System.out.println(answer);
	}
}
