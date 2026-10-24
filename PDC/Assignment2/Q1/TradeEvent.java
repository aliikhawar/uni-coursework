package PDC.Assignment2.Q1;

public class TradeEvent {
    protected String senderId;
    protected long timestamp;
    protected double baseprice;

    public TradeEvent(String senderId, long timestamp, double baseprice) {
        this.senderId = senderId;
        this.timestamp = timestamp;
        this.baseprice = baseprice;

    }
}
