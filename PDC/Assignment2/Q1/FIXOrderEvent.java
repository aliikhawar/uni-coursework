package PDC.Assignment2.Q1;

public class FIXOrderEvent extends TradeEvent {
    private String orderId;

    public FIXOrderEvent(String senderId, long timestamp, double baseprice, String orderId) {
        super(senderId, timestamp, baseprice);
        this.orderId = orderId;
    }

    @Override
    public void dispatch() {
        double fee = baseprice * 0.0015;
        double total = baseprice + fee;
        
        System.out.println("Stock purchase request");
        super.dispatch();
        System.out.println("Order ID: " + orderId);
        System.out.println("Fee: " + fee);
        System.out.println("Total: " + total);
        System.out.println("Order dispatched successfully.");
    }
}
