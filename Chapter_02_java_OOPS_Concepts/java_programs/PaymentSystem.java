abstract class Payment {
    protected double amount;
    
    public Payment(double amount) {
        this.amount = amount;
    }
    
    abstract void processPayment();
    
    void displayAmount() {
        System.out.println("Amount: $" + amount);
    }
}

class CreditCardPayment extends Payment {
    private String cardNumber;
    
    public CreditCardPayment(double amount, String cardNumber) {
        super(amount);
        this.cardNumber = cardNumber;
    }
    
    @Override
    void processPayment() {
        System.out.println("Processing credit card payment...");
        System.out.println("Card ending in: " + cardNumber.substring(cardNumber.length() - 4));
        displayAmount();
    }
}

class PayPalPayment extends Payment {
    private String email;
    
    public PayPalPayment(double amount, String email) {
        super(amount);
        this.email = email;
    }
    
    @Override
    void processPayment() {
        System.out.println("Processing PayPal payment...");
        System.out.println("Account: " + email);
        displayAmount();
    }
}

public class PaymentSystem {
    public static void main(String[] args) {
        Payment[] payments = {
            new CreditCardPayment(100.50, "1234567890123456"),
            new PayPalPayment(75.25, "user@example.com")
        };
        
        for (Payment payment : payments) {
            payment.processPayment();
            System.out.println();
        }
    }
}
