package PDC.Assignment2.Q4;

import java.util.concurrent.RecursiveAction;

public class PortfolioThresholdTask extends RecursiveAction {
    private static final int THRESHOLD = 1000;

    private double[] values;
    private int start;
    private int end;
    private double cap;

    public PortfolioThresholdTask(double[] values, int start,
                                  int end, double cap) {
        this.values = values;
        this.start = start;
        this.end = end;
        this.cap = cap;
    }

    @Override
    protected void compute() {
        if (end - start <= THRESHOLD) {
            for (int i = start; i < end; i++) {
                if (values[i] > cap) {
                    values[i] *= 0.95;
                }
            }

            return;
        }

        int middle = (start + end) / 2;

        PortfolioThresholdTask left =
                new PortfolioThresholdTask(values, start, middle, cap);

        PortfolioThresholdTask right =
                new PortfolioThresholdTask(values, middle, end, cap);

        invokeAll(left, right);
    }
}