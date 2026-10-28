package PDC.Assignment2.Q7;

public class CacheLocalityBenchmark {
    private static final int N = 2000;
    private static double[][] matrix = new double[N][N];
    private static volatile double result;

    public static double traverseRowMajor() {
        double sum = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sum += matrix[i][j];
            }
        }

        return sum;
    }

    public static double traverseColumnMajor() {
        double sum = 0;

        for (int j = 0; j < N; j++) {
            for (int i = 0; i < N; i++) {
                sum += matrix[i][j];
            }
        }

        return sum;
    }

    public static void main(String[] args) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                matrix[i][j] = 1.0;
            }
        }

        for (int i = 0; i < 5; i++) {
            result = traverseRowMajor();
            result = traverseColumnMajor();
        }

        long rowTotal = 0;
        long columnTotal = 0;

        double rowSum = 0;
        double columnSum = 0;

        for (int run = 1; run <= 10; run++) {
            long rowTime;
            long columnTime;
            long start;

            if (run % 2 == 1) {
                start = System.nanoTime();
                rowSum = traverseRowMajor();
                rowTime = System.nanoTime() - start;
                result = rowSum;

                start = System.nanoTime();
                columnSum = traverseColumnMajor();
                columnTime = System.nanoTime() - start;
                result = columnSum;
            } else {
                start = System.nanoTime();
                columnSum = traverseColumnMajor();
                columnTime = System.nanoTime() - start;
                result = columnSum;

                start = System.nanoTime();
                rowSum = traverseRowMajor();
                rowTime = System.nanoTime() - start;
                result = rowSum;
            }

            rowTotal += rowTime;
            columnTotal += columnTime;

            System.out.printf(
                    "Run %d | Row: %.3f ms | Column: %.3f ms%n",
                    run, rowTime / 1_000_000.0,
                    columnTime / 1_000_000.0
            );
        }

        System.out.println("Row sum: " + rowSum);
        System.out.println("Column sum: " + columnSum);
        System.out.printf(
                "Average row time: %.3f ms%n",
                rowTotal / 10.0 / 1_000_000.0
        );
        System.out.printf(
                "Average column time: %.3f ms%n",
                columnTotal / 10.0 / 1_000_000.0
        );
        System.out.printf(
                "Row-major speedup: %.3fx%n",
                (double) columnTotal / rowTotal
        );
    }
}