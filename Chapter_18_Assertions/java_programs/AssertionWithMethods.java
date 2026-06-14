/**
 * AssertionWithMethods.java
 * Demonstrates assertions used with methods, including
 * private method preconditions, post-conditions, and class invariants.
 *
 * Run with assertions enabled:
 *   java -ea AssertionWithMethods
 */
public class AssertionWithMethods {
    private double balance;

    public AssertionWithMethods(double initialBalance) {
        assert initialBalance >= 0 : "Initial balance cannot be negative: " + initialBalance;
        this.balance = initialBalance;
    }

    // Public method: uses exception for argument validation
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }

        double oldBalance = balance;
        balance += amount;

        // Post-condition: balance should have increased
        assert balance > oldBalance : "Balance did not increase after deposit";
        assert balance >= 0 : "Balance became negative after deposit";
    }

    // Public method: uses exception for argument validation
    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient funds");
        }

        double oldBalance = balance;
        balance -= amount;

        // Post-condition
        assert balance == oldBalance - amount : "Balance calculation error";
        assert balance >= 0 : "Balance became negative after withdrawal";
    }

    // Private method: assertion acceptable for internal logic
    private double calculateInterest(double rate) {
        assert rate > 0 && rate < 1 : "Rate must be between 0 and 1, but was: " + rate;
        return balance * rate;
    }

    // Class invariant check
    public void checkInvariant() {
        assert balance >= 0 : "Class invariant violated: balance is negative";
    }

    public double getBalance() {
        return balance;
    }

    public static void main(String[] args) {
        AssertionWithMethods account = new AssertionWithMethods(1000);

        account.deposit(500);
        System.out.println("Balance after deposit: " + account.getBalance());

        account.withdraw(200);
        System.out.println("Balance after withdrawal: " + account.getBalance());

        // Calculate interest (private method)
        double interest = account.calculateInterest(0.05);
        System.out.println("Interest: " + interest);

        // Check class invariant
        account.checkInvariant();
        System.out.println("Class invariant satisfied");
    }
}
