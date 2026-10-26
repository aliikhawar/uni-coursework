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