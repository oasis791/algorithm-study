public class Programmers_60057 {
	static class Solution {
		public static int solution(String s) {
			String str = "";
			StringBuilder tempAnswer;
			StringBuilder compare;
			StringBuilder progress;

			// i: 문자열 자르는 단위
			for (int size = 1; size <= s.length(); size++) {

				int count = 0;
				tempAnswer = new StringBuilder();
				compare = new StringBuilder();
				progress = new StringBuilder();

				for (int i = 0; i < s.length(); i++) {
					progress.append(s.charAt(i));

					if(progress.length() == size) {
						if(compare.length() == 0) {
							compare.append(progress.toString());
							progress.delete(0, progress.length());
							count = 1;
						} else {
							if(compare.toString().equals(progress.toString())) {
								count++;
								progress.delete(0, progress.length());
							} else {
								if(count > 1) {
									tempAnswer.append(count);
								}
								tempAnswer.append(compare.toString());
								compare.delete(0, compare.length());
								compare.append(progress.toString());
								progress.delete(0, progress.length());
								count = 1;
							}
						}
					}
				}

				if(compare.length() != 0) {
					if(count > 1) tempAnswer.append(count);
					tempAnswer.append(compare.toString());
				}

				if(progress.length() != 0) {
					tempAnswer.append(progress.toString());
				}

				if(str.equals("") || str.length() > tempAnswer.length()) {
					str = tempAnswer.toString();
				}
			}

			return str.length();
		}
	}
}
