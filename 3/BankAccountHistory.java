import java.util.ArrayList;
import java.util.List;

/** Lab 3, Exercise 3: extend BankAccount with a protected history snapshot. */
public class BankAccountHistory {
    static class BankAccount {
        private final String accountNumber;
        private String ownerName;
        private double balance;
        private final List<String> history = new ArrayList<>();

        BankAccount(String accountNumber, String ownerName, double openingBalance) {
            if (accountNumber == null || accountNumber.isBlank()) {
                throw new IllegalArgumentException("Account number is required");
            }
            if (!Double.isFinite(openingBalance) || openingBalance < 0) {
                throw new IllegalArgumentException("Opening balance must be finite and nonnegative");
            }
            this.accountNumber = accountNumber;
            setOwnerName(ownerName);
            this.balance = openingBalance;
            history.add(String.format("Opening balance: %.2f", openingBalance));
        }

        public String getAccountNumber() { return accountNumber; }
        public String getOwnerName() { return ownerName; }
        public double getBalance() { return balance; }

        public void setOwnerName(String ownerName) {
            if (ownerName == null || ownerName.isBlank()) {
                throw new IllegalArgumentException("Owner name is required");
            }
            this.ownerName = ownerName.trim();
        }

        public void deposit(double amount) {
            requirePositive(amount);
            if (!Double.isFinite(balance + amount)) {
                throw new IllegalArgumentException("Resulting balance is too large");
            }
            balance += amount;
            history.add(String.format("Deposit: %.2f", amount));
        }

        public boolean withdraw(double amount) {
            requirePositive(amount);
            if (amount > balance) return false;
            balance -= amount;
            history.add(String.format("Withdrawal: %.2f", amount));
            return true;
        }

        private static void requirePositive(double amount) {
            if (!Double.isFinite(amount) || amount <= 0) {
                throw new IllegalArgumentException("Amount must be finite and positive");
            }
        }

        public List<String> getTransactionHistory() {
            // EXPLAIN: a snapshot cannot be modified or changed by later transactions.
            return List.copyOf(history);
        }

        @Override
        public String toString() {
            return String.format("%s [%s] balance: %.2f", accountNumber, ownerName, balance);
        }
    }

    public static void main(String[] args) {
        BankAccount account = new BankAccount("ACC-001", "Tendai", 500);
        account.deposit(250);
        System.out.println("Withdraw 100: " + account.withdraw(100));
        System.out.println("Withdraw 5000: " + account.withdraw(5000));
        List<String> snapshot = account.getTransactionHistory();
        try {
            snapshot.clear();
        } catch (UnsupportedOperationException exception) {
            System.out.println("History modification blocked.");
        }
        account.deposit(50);
        System.out.println("Snapshot entries: " + snapshot.size());
        System.out.println("Current history: " + account.getTransactionHistory());
        System.out.println(account);
    }
}
