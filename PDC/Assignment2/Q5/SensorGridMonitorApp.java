package PDC.Assignment2.Q5;

import java.util.Random;

public class SensorGridMonitorApp {
    public static void main(String[] args) throws InterruptedException {
        double[] data = new double[50000];
        Random random = new Random(1);

        for (int i = 0; i < data.length; i++) {
            data[i] = random.nextDouble() * 100;
        }
