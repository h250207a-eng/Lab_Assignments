/** Lab 5, Exercise 5: compare pattern matching with polymorphic dispatch. */
public class PaymentProcessing {
    interface Payment {
        void process();
    }

    static class CardPayment implements Payment {
        private final String lastFourDigits;

        CardPayment(String lastFourDigits) { this.lastFourDigits = lastFourDigits; }
        public String getLastFourDigits() { return lastFourDigits; }

        @Override
        public void process() {
            System.out.println("Card ending " + lastFourDigits + " processed");
        }
    }

    static class MobileMoneyPayment implements Payment {
        private final String provider;

        MobileMoneyPayment(String provider) { this.provider = provider; }
        public String getProvider() { return provider; }

        @Override
        public void process() { System.out.println(provider + " payment processed"); }
    }

    static class PaymentProcessor {
        void pay(Payment payment) {
            // EXPLAIN: the pattern variable combines a type check with a safe cast.
            if (payment instanceof CardPayment card) {
                System.out.println("Card ending " + card.getLastFourDigits() + " processed");
            } else if (payment instanceof MobileMoneyPayment mobile) {
                System.out.println(mobile.getProvider() + " payment processed");
            } else {
                throw new IllegalArgumentException("Unsupported payment type");
            }
        }

        void payPolymorphically(Payment payment) {
            payment.process();
        }
    }

    public static void main(String[] args) {
        PaymentProcessor processor = new PaymentProcessor();
        Payment[] payments = {new CardPayment("1234"), new MobileMoneyPayment("EcoCash")};
        System.out.println("Pattern matching:");
        for (Payment payment : payments) processor.pay(payment);
        System.out.println("Polymorphism:");
        for (Payment payment : payments) processor.payPolymorphically(payment);
        System.out.println("A new payment type needs a new branch in pay; process dispatch needs no branch change.");
    }
}
