package PDC.Assignment2.Q5;

public class ParallelMaxSearch extends SensorSearchEngine {
    public ParallelMaxSearch(double[] data, int start, int end) {
        super(data, start, end);
    }

    @Override
    public double processGrid() {
        double maximum = Double.NEGATIVE_INFINITY;

        for (int i = startIdx; i < endIdx; i++) {
            if (sensorData[i] > maximum) {
                maximum = sensorData[i];
            }
        }

        return maximum;
    }
}