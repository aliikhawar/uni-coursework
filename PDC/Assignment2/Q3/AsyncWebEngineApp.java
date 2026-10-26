package PDC.Assignment2.Q3;

public class AsyncWebEngineApp {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(
                "Default thread: " + Thread.currentThread().getName()
        );

        TelemetryThread telemetry = new TelemetryThread();
        telemetry.setName("Telemetry-Worker");

        Thread logs = new Thread(new LogProcessorTask());
        logs.setName("Log-Worker");

        telemetry.start();
        logs.start();

        System.out.println("Telemetry state: " + telemetry.getState());
        System.out.println("Log state: " + logs.getState());

        telemetry.join();
        logs.join();

        System.out.println("Both workers completed.");
        System.out.println("Telemetry final state: " + telemetry.getState());
        System.out.println("Log final state: " + logs.getState());
    }
}