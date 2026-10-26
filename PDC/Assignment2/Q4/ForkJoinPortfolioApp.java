package PDC.Assignment2.Q4;

import java.util.concurrent.ForkJoinPool;

public class ForkJoinPortfolioApp {
    public static void main(String[] args) {
        double[] original = new double[10000];

        for (int i = 0; i < original.length; i++) {
            original[i] = i + 1;
        }

        double[] serial = original.clone();
        double[] parallel = original.clone();
        double cap = 7000;

        long start = System.nanoTime();

        double serialBefore = 0;

        for (double value : serial) {
            serialBefore += value;
        }

        for (int i = 0; i < serial.length; i++) {
            if (serial[i] > cap) {
                serial[i] *= 0.95;
            }
        }

        double serialAfter = 0;

        for (double value : serial) {
            serialAfter += value;
        }

        double serialMs = (System.nanoTime() - start) / 1_000_000.0;

        ForkJoinPool pool = new ForkJoinPool();

        start = System.nanoTime();

        double parallelBefore = pool.invoke(
                new ParallelRiskCalculator(parallel, 0, parallel.length)
        );

        pool.invoke(
                new PortfolioThresholdTask(
                        parallel, 0, parallel.length, cap
                )
        );

        double parallelAfter = pool.invoke(
                new ParallelRiskCalculator(parallel, 0, parallel.length)
        );

        double parallelMs = (System.nanoTime() - start) / 1_000_000.0;

        pool.shutdown();

        System.out.printf("Serial risk before: %.2f%n", serialBefore);
        System.out.printf("Parallel risk before: %.2f%n", parallelBefore);
        System.out.printf("Serial risk after: %.2f%n", serialAfter);
        System.out.printf("Parallel risk after: %.2f%n", parallelAfter);

        boolean match =
                Math.abs(serialBefore - parallelBefore) < 0.01
                && Math.abs(serialAfter - parallelAfter) < 0.01;

        System.out.println("Results match: " + match);
        System.out.printf("Serial processing time: %.3f ms%n", serialMs);
        System.out.printf("Parallel processing time: %.3f ms%n", parallelMs);
        System.out.printf("Speedup: %.3fx%n", serialMs / parallelMs);
    }
}