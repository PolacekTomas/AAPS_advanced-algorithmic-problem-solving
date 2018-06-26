import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Tomas Polacek on 24-Jan-18.
 * TDDD95 - Ex1: Ljutnja!
 */
public class Ljutnja {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {

            long M = scanner.nextLong();
            int N = scanner.nextInt();
            long candyDemand = 0;

            long[] wishes = new long[N];

            for (int i = 0; i < N; i++) {
                wishes[i] = scanner.nextLong();
                candyDemand += wishes[i];
            }

            if (M >= candyDemand) {
                System.out.println("0");
                break;
            }

            Arrays.sort(wishes);

            long candyRest = candyDemand - M;
            long anger = 0;

            for (int i = 0; i < N; i++) {
                int remainChildren = N - i;
                long dealCandies = Math.min(wishes[i], candyRest / remainChildren);
                anger += dealCandies * dealCandies;
                candyRest -= dealCandies;
            }

            System.out.println(anger);

        }

    }

}
