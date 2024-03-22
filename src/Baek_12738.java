import java.io.*;
import java.util.StringTokenizer;

public class Baek_12738 {
    public static void main (String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());

        StringTokenizer st = new StringTokenizer(bf.readLine());
        int[] lis = new int[n];
        lis[0] = Integer.parseInt(st.nextToken());
        int index = 1;

        for (int i = 1; i < n; i++) {
            int nextNum = Integer.parseInt(st.nextToken());

            if(lis[index - 1] < nextNum) {
                lis[index++] = nextNum;
            } else {
                int replaceIndex = lowerBound(lis, nextNum, index);
                lis[replaceIndex] = nextNum;
            }
        }

        System.out.println(index);
    }

    static int lowerBound(int[] lis, int nextNum, int index) {
        int left = 0;
        int right = index;

        while(left < right) {
            int mid = (left + right) / 2;

            if(lis[mid] < nextNum) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return right;
    }
}
