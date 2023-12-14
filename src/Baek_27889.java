import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek_27889 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String input = bf.readLine();

        switch (input) {
            case "NLCS":
                System.out.println("North London Collegiate School");
                return;
            case "BHA":
                System.out.println("Branksome Hall Asia");
                return;
            case "KIS":
                System.out.println("Korea International School");
                return;
            case "SJA":
                System.out.println("St. Johnsbury Academy");
                return;
            default:
                break;
        }
    }
}
