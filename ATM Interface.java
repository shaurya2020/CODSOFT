import java.util.Scanner;

// Class to represent the bank account
class BankAccount {
    private double balance;

    // Constructor
    public BankAccount(double initialBalance) {
        if (initialBalance <= 0) {
            System.out.println("Initial balance must be greater than zero.");
            this.balance = 0;
        } else {
            this.balance = initialBalance;
        }
    }

    // Deposit money
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("✅ Successfully deposited: $" + amount);
        } else {
            System.out.println("❌ Deposit amount must be greater than zero.");
        }
    }

    // Withdraw money
    public void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("❌ Insufficient balance. Transaction failed.");
        } else if (amount <= 0) {
            System.out.println("❌ Withdrawal amount must be greater than zero.");
        } else {
            balance -= amount;
            System.out.println("✅ Successfully withdrawn: $" + amount);
        }
    }

    // Check balance
    public void checkBalance() {
        System.out.println("💰 Current balance: $" + balance);
    }
}

// Class to represent the ATM
class ATM {
    private BankAccount account;
    private Scanner scanner;

    // Constructor
    public ATM(BankAccount account) {
        this.account = account;
        this.scanner = new Scanner(System.in);
    }

    public ATM(Scanner scanner) {
        this.scanner = scanner;
    }

    // Display menu and handle user input
    public void displayMenu() {
        while (true) {
            System.out.println("\n===== ATM MENU =====");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            
            int choice = validateInput();

            switch (choice) {
                case 1 -> account.checkBalance();
                case 2 -> handleDeposit();
                case 3 -> handleWithdrawal();
                case 4 -> {
                    System.out.println("Thank you for using the ATM. Goodbye! 👋");
                    scanner.close();
                    return;
                }
                default -> System.out.println("❌ Invalid option. Please try again.");
            }
        }
    }

    // Handle deposit input and validation
    private void handleDeposit() {
        System.out.print("Enter the amount to deposit: $");
        double amount = validateDoubleInput();
        account.deposit(amount);
    }

    // Handle withdrawal input and validation
    private void handleWithdrawal() {
        System.out.print("Enter the amount to withdraw: $");
        double amount = validateDoubleInput();
        account.withdraw(amount);
    }

    // Validate integer input
    private int validateInput() {
        while (!scanner.hasNextInt()) {
            System.out.print("❌ Invalid input. Please enter a number: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    // Validate double input
    private double validateDoubleInput() {
        while (!scanner.hasNextDouble()) {
            System.out.print("❌ Invalid input. Please enter a valid amount: ");
            scanner.next();
        }
        return scanner.nextDouble();
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }
}

// Main class
public class Main {
    public static void main(String[] args) {
        System.out.println("🌟 Welcome to the ATM 🌟");
        
        // Initialize a bank account with $1000 starting balance
        BankAccount account = new BankAccount(1000);
        ATM atm = new ATM(account);

        // Start the ATM interface
        atm.displayMenu();
    }
}
