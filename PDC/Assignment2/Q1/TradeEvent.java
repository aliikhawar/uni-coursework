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


    public void dispatch() {
        System.out.println("Sender: " + senderId);
        System.out.println("Timestamp: " + timestamp);
        System.out.println("Base Price: " + baseprice);
    }
}
