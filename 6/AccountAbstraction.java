/** Lab 6, Exercise 3: abstract accounts share deposit behaviour. */
public class AccountAbstraction {
    static abstract class Account {
        private double balance;

        Account(double openingBalance) {
            if (!Double.isFinite(openingBalance) || openingBalance < 0) {
                throw new IllegalArgumentException("Invalid opening balance");
            }
            balance = openingBalance;
        }

        public final void deposit(double amount) {
            if (!Double.isFinite(amount) || amount <= 0 || !Double.isFinite(balance + amount)) {
                throw new IllegalArgumentException("Deposit must be positive and leave a finite balance");
            }
            balance += amount;
        }

        public double getBalance() { return balance; }
        public abstract double monthlyFee();
    }

    static class SavingsAccount extends Account {
        SavingsAccount(double balance) { super(balance); }
        @Override public double monthlyFee() { return 5; }
    }

    static class CurrentAccount extends Account {
        CurrentAccount(double balance) { super(balance); }
        @Override public double monthlyFee() { return 12; }
    }

    public static void main(String[] args) {
        Account[] accounts = {new SavingsAccount(100), new CurrentAccount(100)};
        for (Account account : accounts) {
            account.deposit(50);
            System.out.printf("%s: balance=%.2f, monthly fee=%.2f%n",
                    account.getClass().getSimpleName(), account.getBalance(), account.monthlyFee());
        }
    }
}
