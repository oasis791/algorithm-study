import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_2238 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int answerPrice = 0;
        String soldPerson = "";

        int u = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        int[] priceCount = new int[u + 1];
        String[] sub = new String[u + 1];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            String name = st.nextToken();
            int price = Integer.parseInt(st.nextToken());

            priceCount[price]++;
            if (sub[price] == null) {
                sub[price] = name;
            }
        }

        int minCount = Integer.MAX_VALUE;
        for (int i = 1; i <= u; i++) {
            if (priceCount[i] != 0 && priceCount[i] < minCount) {
                minCount = priceCount[i];
                answerPrice = i;
                soldPerson = sub[i];
            }
        }

        System.out.println(soldPerson + " " + answerPrice);
    }
}
