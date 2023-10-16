public class Baek_4673 {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        boolean[] check = new boolean[10001];
        for (int i = 1; i <= 10000; i++) {
            int n = func(i);

            if (n <= 10000) {
                check[n] = true;
            }
        }

        for (int i = 1; i <= 10000; i++) {
            if(!check[i])
                sb.append(i).append("\n");
        }

        System.out.print(sb);
    }

    static int func (int num) {
        int sum = num;

        while (num != 0) {
            sum += num % 10;
            num /= 10;
        }

        return sum;
    }
}
