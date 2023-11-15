public class Programmers_150368 {

    static class Solution {
        static int emoticonPlusUser = 0;
        static int maxPrice = 0;
        static int[] percentage = new int[]{10, 20, 30, 40};

        public static int[] solution(int[][] users, int[] emoticons) {
            // 이모티콘은 10%, 20%, 30%, 40%
            // => 모든경우 조합해서 풀면 끝날듯

            selectPercent(users, emoticons, 0, new int[emoticons.length]);
            return new int[]{emoticonPlusUser, maxPrice};
        }

        static void selectPercent(int[][] users, int[] emoticons, int index, int[] selectedPercent) {
            if (index == emoticons.length) {
                calculate(selectedPercent, emoticons, users);
                return;
            }

            for (int i = 0; i < 4; i++) {
                selectedPercent[index] = percentage[i];
                selectPercent(users, emoticons, index + 1, selectedPercent);
            }
        }

        static void calculate(int[] selectedPercent, int[] emoticons, int[][] users) {
            int tempPlusUser = 0;
            int tempPrice = 0;

            for (int i = 0; i < users.length; i++) {
                int userPercent = users[i][0];
                int userPrice = users[i][1];
                int priceSum = 0;

                for (int j = 0; j < emoticons.length; j++) {
                    if (selectedPercent[j] >= userPercent) {
                        priceSum += emoticons[j] * (100 - selectedPercent[j]) / 100;
                    }
                }

                if (priceSum >= userPrice) {
                    tempPlusUser++;
                } else {
                    tempPrice += priceSum;
                }
            }

            if (tempPlusUser > emoticonPlusUser) {
                emoticonPlusUser = tempPlusUser;
                maxPrice = tempPrice;
            } else if (tempPlusUser == emoticonPlusUser) {
                if (tempPrice > maxPrice) {
                    maxPrice = tempPrice;
                }
            }
        }
    }
}
