import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class AtmInterface {

//ATM USER DETAILS
public static String userId = "customer123";
public static int userPin = 1234;


public static class Transaction {
    private double amount;
    private Date timestamp;

    public Transaction(double amount) {
        this.amount = amount;
        this.timestamp = new Date();
    }

    public double getAmount() {
        return amount;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Transaction: Rs." + amount + "\nTimestamp: " + timestamp;
    }
}

public static class BankAccount {
    private double balance;
    private List<Transaction> transactionHistory;

    public BankAccount() {
        balance = 0.0;
        transactionHistory = new ArrayList<>();
    }

    public void deposit(double amount) {
        balance += amount;
        Transaction transaction = new Transaction(amount);
        transactionHistory.add(transaction);
        System.out.println("---Deposit successful---\nCurrent balance: Rs." + balance);
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            Transaction transaction = new Transaction(-amount);
            transactionHistory.add(transaction);
            System.out.println("---Withdrawal successful---\nCurrent balance: Rs." + balance);
        } else {
            System.out.println("---Insufficient funds---\nCurrent balance: Rs." + balance);
        }
    }

    public void transfer(double amount, BankAccount recipientAccount) {
        if (amount <= balance) {
            balance -= amount;
            Transaction transaction = new Transaction(-amount);
            transactionHistory.add(transaction);
            System.out.println("\nMoney is received by the recipient !!");
            recipientAccount.deposit(amount);
            System.out.println("---Transfer successful---\nYour Current balance: Rs." + balance);
        } else {
            System.out.println("---- Transfer Failed ----\n---Insufficient funds---\nYour Current balance: Rs." + balance);
        }
    }

    public void showTransactionHistory() {
        System.out.println("---Transaction History---");
        for (Transaction transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }

    public double getBalance() {
        return balance;
    }
}

   
    public static boolean verify(String id, int pin){
        if(userId.equals(id)){
            if(userPin == pin){
                System.out.println("---Valid User !! Successfully logged in---");
                return true;
            }
            else{
                System.out.println("---Incorrect Pin!! Try again---");
                return false;
            }
        }
        else{
            System.out.println("---Invalid User Id !! Try again---");
            return false;
        } 
    }
   
    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount();
        Scanner scanner = new Scanner(System.in);
        int choice;
        boolean user;
        System.out.println("---- Welcome to your bank ATM ----");

        do{
        System.out.print("Enter User Id : ");
        String id = scanner.next();
        System.out.print("Enter User Pin : "); 
        int pin = scanner.nextInt();
        user = verify(id, pin);
        } while(!user);
        
        System.out.println();
        do {
            System.out.println("\n--------ATM Interface--------\n");
            System.out.println("Choose one option : ");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Transfer");
            System.out.println("4. Transaction History");
            System.out.println("5. Quit\n");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter the amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    bankAccount.deposit(depositAmount);
                    break;
                case 2:
                    System.out.print("Enter the amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    bankAccount.withdraw(withdrawAmount);
                    break;
                case 3:
                    System.out.print("Enter the amount to transfer: ");
                    double transferAmount = scanner.nextDouble();
                    System.out.print("Enter the recipient's account balance: ");
                    double recipientBalance = scanner.nextDouble();
                    BankAccount recipientAccount = new BankAccount();
                    System.out.println("Recipient's balance");
                    recipientAccount.deposit(recipientBalance);
                    System.out.println();
                    bankAccount.transfer(transferAmount, recipientAccount);
                    break;
                case 4:
                    bankAccount.showTransactionHistory();
                    break;
                case 5:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }
}