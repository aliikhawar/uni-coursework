package PDC.Assignment2.Q5;

public class ParallelMinSearch extends SensorSearchEngine {
    public ParallelMinSearch(double[] data, int start, int end) {
        super(data, start, end);
    }

    @Override
    public double processGrid() {
        double minimum = Double.POSITIVE_INFINITY;

        for (int i = startIdx; i < endIdx; i++) {
            if (sensorData[i] < minimum) {
                minimum = sensorData[i];
            }
        }

        return minimum;
    }
}