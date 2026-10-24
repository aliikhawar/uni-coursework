package PDC.Assignment2.Q1;

public class FIXOrderEvent extends TradeEvent {

    private String orderId;

    public FIXOrderEvent(String senderId, long timestamp, double baseprice, String orderId) {
        super(senderId. timedstamp, baseprice);
        this.orderId = orderId;
        
    }

    
}
