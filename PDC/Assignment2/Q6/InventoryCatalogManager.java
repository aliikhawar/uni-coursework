package PDC.Assignment2.Q6;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class InventoryCatalogManager {
    private static final int WORKERS = 10;
    private static final int OPERATIONS = 50000;
    private static final int PRODUCTS = 1000;

    public void benchmarkSynchronizedMap() throws InterruptedException {
        Map<String, Integer> catalog =
                Collections.synchronizedMap(new HashMap<>());

        runBenchmark("SynchronizedMap", catalog, false);
    }

    public void benchmarkConcurrentMap() throws InterruptedException {
        Map<String, Integer> catalog = new ConcurrentHashMap<>();

        runBenchmark("ConcurrentHashMap", catalog, true);
    }

    private void runBenchmark(String name,
                              Map<String, Integer> catalog,
                              boolean concurrent)
            throws InterruptedException {

        String[] productIds = new String[PRODUCTS];

        for (int i = 0; i < PRODUCTS; i++) {
            productIds[i] = "Product-" + i;
            catalog.put(productIds[i], 0);
        }

        ExecutorService pool = Executors.newFixedThreadPool(WORKERS);

        CountDownLatch ready = new CountDownLatch(WORKERS);
        CountDownLatch startGate = new CountDownLatch(1);

        for (int i = 0; i < WORKERS; i++) {
            final int workerId = i;

            pool.submit(() -> {
                ready.countDown();

                try {
                    startGate.await();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }

                for (int j = 0; j < OPERATIONS; j++) {
                    String id = productIds[(j + workerId) % PRODUCTS];

                    if (concurrent) {
                        catalog.compute(id, (key, stock) -> stock + 1);
                    } else {
                        synchronized (catalog) {
                            int stock = catalog.get(id);
                            catalog.put(id, stock + 1);
                        }
                    }
                }
            });
        }

        ready.await();

        long start = System.nanoTime();
        startGate.countDown();

        pool.shutdown();

        if (!pool.awaitTermination(5, TimeUnit.MINUTES)) {
            pool.shutdownNow();
            throw new IllegalStateException("Benchmark timed out");
        }

        double seconds = (System.nanoTime() - start) / 1_000_000_000.0;
        long totalOperations = (long) WORKERS * OPERATIONS;

        long finalStock = 0;

        for (int stock : catalog.values()) {
            finalStock += stock;
        }

        System.out.println("\n" + name);
        System.out.println("Workers: " + WORKERS);
        System.out.println("Transactions per worker: " + OPERATIONS);
        System.out.println("Total transactions: " + totalOperations);
        System.out.println("Final stock total: " + finalStock);
        System.out.println("Correct total: " + (finalStock == totalOperations));

        System.out.printf("Execution time: %.3f ms%n", seconds * 1000);
        System.out.printf(
                "Throughput: %.2f operations/second%n",
                totalOperations / seconds
        );
    }

    public static void main(String[] args) throws InterruptedException {
        InventoryCatalogManager manager = new InventoryCatalogManager();

        manager.benchmarkSynchronizedMap();
        manager.benchmarkConcurrentMap();
    }
}