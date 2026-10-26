package PDC.Assignment2.Q3;

public class TelemetryThread extends Thread {
    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(
                    getName() + " | Check " + i
                    + " | Simulated CPU: healthy"
                    + " | Simulated memory: healthy"
            );

            try {
                Thread.sleep(800);
            } catch (InterruptedException e) {
                interrupt();
                return;
            }
        }
    }
}