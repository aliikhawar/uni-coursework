package PDC.Assignment2.Q1;

public class FIXOrderEvent extends TradeEvent {

    private String orderId;

    public FIXOrderEvent(String senderId, long timestamp, double baseprice, String orderId) {
        super(senderId. timedstamp, baseprice);
        this.orderId = orderId;

    }

    @Override 

    public void dispatch() {
        
        double fee = basePrice * 0.0015;
        double total = basePrice + fee;
        System.out.println("Stock purchase request);
        super.dispatch();
        System.out.println("Order ID: " + orderId);
        System.out.println("Fee: " + fee);
        System.out.println("Total: " + total);
        System.out.println("Order dispatched successfully.");
        

    
}
