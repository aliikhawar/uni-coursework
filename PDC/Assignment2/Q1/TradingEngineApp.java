package PDC.Assignment2.Q1;

public class TradingEngineApp {
    public static void main(String[] args) {
        long time = System.currentTimeMillis();

        TradeEvent order =
                new FIXOrderEvent("FIX-01", time, 1000, "ORD-101");

        TradeEvent quote =
                new ReutersQuoteEvent("Reuters-01", time, 1000, 998, 1002);

        order.dispatch();
        System.out.println();
        quote.dispatch();
    }
}