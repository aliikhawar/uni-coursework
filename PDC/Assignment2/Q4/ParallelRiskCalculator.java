package PDC.Assignment2.Q4;

import java.util.concurrent.RecursiveTask;

public class ParallelRiskCalculator extends RecursiveTask<Double> {
    private static final int THRESHOLD = 1000;

    private double[] values;
    private int start;
    private int end;

    public ParallelRiskCalculator(double[] values, int start, int end) {
        this.values = values;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Double compute() {
        if (end - start <= THRESHOLD) {
            double sum = 0;

            for (int i = start; i < end; i++) {
                sum += values[i];
            }

            return sum;
        }

        int middle = (start + end) / 2;

        ParallelRiskCalculator left =
                new ParallelRiskCalculator(values, start, middle);

        ParallelRiskCalculator right =
                new ParallelRiskCalculator(values, middle, end);

        left.fork();
        right.fork();

        return left.join() + right.join();
    }
}