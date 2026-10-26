package PDC.Assignment2.Q5;

import java.util.Random;

public class SensorGridMonitorApp {
    public static void main(String[] args) throws InterruptedException {
        double[] data = new double[50000];
        Random random = new Random(1);

        for (int i = 0; i < data.length; i++) {
            data[i] = random.nextDouble() * 100;
        }

        data[123] = -10;
        data[45678] = 150;

        Thread[] workers = new Thread[4];
        double[] minimums = new double[4];
        double[] maximums = new double[4];

        long startTime = System.nanoTime();

        for (int i = 0; i < workers.length; i++) {
            final int index = i;
            final int start = i * data.length / workers.length;
            final int end = (i + 1) * data.length / workers.length;

            workers[i] = new Thread(() -> {
                SensorSearchEngine minSearch =
                        new ParallelMinSearch(data, start, end);

                SensorSearchEngine maxSearch =
                        new ParallelMaxSearch(data, start, end);

                minimums[index] = minSearch.processGrid();
                maximums[index] = maxSearch.processGrid();
            }, "Sensor-Worker-" + (i + 1));
        }

        for (Thread worker : workers) {
            worker.start();
        }

        for (Thread worker : workers) {
            worker.join();
        }

        double globalMin = minimums[0];
        double globalMax = maximums[0];

        for (int i = 1; i < workers.length; i++) {
            globalMin = Math.min(globalMin, minimums[i]);
            globalMax = Math.max(globalMax, maximums[i]);
        }

        double timeMs =
                (System.nanoTime() - startTime) / 1_000_000.0;

        for (int i = 0; i < workers.length; i++) {
            System.out.printf(
                    "%s | Min: %.2f | Max: %.2f%n",
                    workers[i].getName(), minimums[i], maximums[i]
            );
        }

        System.out.println("Total sensor values: " + data.length);
        System.out.println("Global minimum: " + globalMin);
        System.out.println("Global maximum: " + globalMax);
        System.out.printf("Execution time: %.3f ms%n", timeMs);
    }
}