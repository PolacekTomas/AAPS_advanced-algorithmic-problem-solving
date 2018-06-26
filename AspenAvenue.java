import java.util.*;

/**
 * Created by Tomas Polacek on 19-Jan-18.
 * TDDD95 - Ex1: Aspen Avenue
 */
public class AspenAvenue {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {

            int N = scanner.nextInt();
            int treesPerSide = N / 2;
            int L = scanner.nextInt();
            int W = scanner.nextInt();
            double treesDist = ((double) L) / (treesPerSide - 1);

            List<Integer> trees = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                trees.add(scanner.nextInt());
            }
            trees.sort(Comparator.naturalOrder());

            double[][] movingDist = new double[treesPerSide+1][treesPerSide+1];
            for (int i = 1; i <= treesPerSide; i++) {
                double expPos = (i-1) * treesDist;
                movingDist[i][0] = movingDist[i-1][0] + calcPyth(expPos-trees.get(i-1), 0);
                movingDist[0][i] = movingDist[0][i-1] + calcPyth(expPos-trees.get(i-1), W);
            }
            for (int i = 1; i <= treesPerSide; i++) {
                for (int j = 1; j <= treesPerSide; j++) {
                    double expPosSameSide = (i-1) * treesDist;
                    double expPosDiffSide = (j-1) * treesDist;
                    movingDist[i][j] = Math.min(
                            movingDist[i-1][j] + calcPyth(expPosSameSide-trees.get(i+j-1), 0),
                            movingDist[i][j-1] + calcPyth(expPosDiffSide-trees.get(i+j-1), W)
                    );
                }
            }

            System.out.println(String.format("%.10f", movingDist[treesPerSide][treesPerSide]));

        }

    }

    private static double calcPyth(double x, double y) {
        return Math.sqrt(x * x + y * y);
    }

}
