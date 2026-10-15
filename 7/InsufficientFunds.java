/** Lab 7, Exercise 4: replace an insufficient-funds boolean with a checked exception. */
public class InsufficientFunds {
    static class InsufficientFundsException extends Exception {
        private static final long serialVersionUID = 1L;

        InsufficientFundsException(double balance, double requested) {
            super(String.format("Requested %.2f, but only %.2f is available", requested, balance));
        }
    }

    static class BankAccount {
        private final String accountNumber;
        private final String ownerName;
        private double balance;

        BankAccount(String accountNumber, String ownerName, double openingBalance) {
            if (accountNumber == null || accountNumber.isBlank() || ownerName == null || ownerName.isBlank()) {
                throw new IllegalArgumentException("Account number and owner name are required");
            }
            if (!Double.isFinite(openingBalance) || openingBalance < 0) {
                throw new IllegalArgumentException("Opening balance must be finite and nonnegative");
            }
            this.accountNumber = accountNumber;
            this.ownerName = ownerName;
            this.balance = openingBalance;
        }

        public double getBalance() { return balance; }

        public void deposit(double amount) {
            requirePositive(amount);
            if (!Double.isFinite(balance + amount)) throw new IllegalArgumentException("Balance too large");
            balance += amount;
        }

        public void withdraw(double amount) throws InsufficientFundsException {
            requirePositive(amount);
            if (amount > balance) throw new InsufficientFundsException(balance, amount);
            balance -= amount;
        }

        private static void requirePositive(double amount) {
            if (!Double.isFinite(amount) || amount <= 0) {
                throw new IllegalArgumentException("Amount must be finite and positive");
            }
        }

        @Override
        public String toString() {
            return String.format("%s [%s] balance: %.2f", accountNumber, ownerName, balance);
        }
    }

    public static void main(String[] args) {
        BankAccount account = new BankAccount("ACC-001", "Tendai", 50);
        account.deposit(25);
        try {
            account.withdraw(20);
            System.out.println(account);
            account.withdraw(70);
        } catch (InsufficientFundsException exception) {
            System.out.println("Withdrawal rejected: " + exception.getMessage());
        }
        System.out.println("Balance after rejected withdrawal: " + account.getBalance());
        System.out.println("A checked exception forces handling and carries details; a boolean is simpler for routine failure.");
    }
}
