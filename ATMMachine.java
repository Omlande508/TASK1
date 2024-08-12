import java.util.ArrayList;
import java.util.Scanner;

// Class representing a bank account
class Account {
    private double balance;
    private String pin;
    private ArrayList<String> transactionHistory;

    // Constructor initializes the account with a starting balance and PIN
    public Account(double initialBalance, String initialPin) {
        if (initialBalance >= 0.0) {
            balance = initialBalance;
        }
        pin = initialPin;
        transactionHistory = new ArrayList<>();
        transactionHistory.add("Account created with balance: $" + initialBalance);
    }

    // Method to get the current balance
    public double getBalance() {
        return balance;
    }

    // Method to deposit money into the account
    public void deposit(double amount) {
        if (amount > 0.0) {
            balance += amount;
            transactionHistory.add("Deposited: $" + amount);
            System.out.println("Deposited: $" + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    // Method to withdraw money from the account
    public void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient funds.");
        } else if (amount <= 0.0) {
            System.out.println("Invalid withdrawal amount.");
        } else {
            balance -= amount;
            transactionHistory.add("Withdrew: $" + amount);
            System.out.println("Withdrew: $" + amount);
        }
    }

    // Method to change the account PIN
    public void changePin(String newPin) {
        pin = newPin;
        transactionHistory.add("PIN changed successfully.");
        System.out.println("PIN changed successfully.");
    }

    // Method to check if the entered PIN is correct
    public boolean validatePin(String enteredPin) {
        return pin.equals(enteredPin);
    }

    // Method to print the transaction history
    public void printTransactionHistory() {
        System.out.println("\nTransaction History:");
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }
}

// Main class to simulate the ATM machine
public class ATMMachine {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initialize an account with a balance of $1000 and a PIN of "1234"
        Account account = new Account(1000.0, "1234");

        // Loop for ATM operations
        while (true) {
            // PIN validation
            System.out.print("Enter your PIN: ");
            String enteredPin = scanner.nextLine();
            if (!account.validatePin(enteredPin)) {
                System.out.println("Incorrect PIN. Please try again.");
                continue;
            }

            // Main menu
            System.out.println("\nATM Main Menu:");
            System.out.println("1. Balance Inquiry");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Change PIN");
            System.out.println("5. Transaction History");
            System.out.println("6. Exit");
            System.out.print("Please choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1: // Balance Inquiry
                    System.out.println("Current Balance: $" + account.getBalance());
                    break;
                case 2: // Deposit Money
                    System.out.print("Enter the amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline character
                    account.deposit(depositAmount);
                    System.out.println("New Balance: $" + account.getBalance());
                    break;
                case 3: // Withdraw Money
                    System.out.print("Enter the amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline character
                    account.withdraw(withdrawAmount);
                    System.out.println("New Balance: $" + account.getBalance());
                    break;
                case 4: // Change PIN
                    System.out.print("Enter your new PIN: ");
                    String newPin = scanner.nextLine();
                    account.changePin(newPin);
                    break;
                case 5: // Transaction History
                    account.printTransactionHistory();
                    break;
                case 6: // Exit
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}
