package PDC.Assignment2.Q1;

public class ReutersQuoteEvent  extends TradeEvent {

     private double bidPrice;
    private double askPrice;

    public ReutersQuoteEvent(String senderId, long timestamp,
                             double basePrice,
                             double bidPrice, double askPrice) {
        super(senderId, timestamp, basePrice);
        this.bidPrice = bidPrice;
        this.askPrice = askPrice;
    }

     @Override
    public void dispatch() {
        System.out.println("Reuters market quote");
        super.dispatch();
        System.out.println("Bid price: " + bidPrice);
        System.out.println("Ask price: " + askPrice);
        System.out.printf("Bid-ask spread: %.2f%n", askPrice - bidPrice);
    }


    
}
