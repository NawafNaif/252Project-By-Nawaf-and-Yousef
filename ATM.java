import java.util.Scanner;

public class ATM {
    private static ATM instance;

    private ATM() {
    }

    public static ATM getInstance() {
        if (instance == null) {
            instance = new ATM();
        }
        return instance;
    }

    public static void start() {
        // Initialize components
        Scanner scanner = new Scanner(System.in);
        ATMView view = new ATMView();
        ProxyBankDatabase proxyBankDatabase = new ProxyBankDatabase();

        // ATM is already up and running
        System.out.println("Welcome to the ATM!");

        // User enters account number and PIN
        System.out.print("Please enter your account number: ");
        int accountNumber = scanner.nextInt();

        System.out.print("Please enter your PIN: ");
        String pin = scanner.next();

        // Authenticate user
        proxyBankDatabase.authenticate(accountNumber, pin);

        if (!proxyBankDatabase.isAuthenticated()) {
            System.out.println("Exiting ATM. Please try again later.");
            scanner.close();
            return;
        }

        ATMController controller = new ATMController(view, proxyBankDatabase);

        // User action loop
        boolean exit = false;
        while (!exit) {
            System.out.println("\nPlease choose an option:");
            System.out.println("1. Show Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Show Transaction History");
            System.out.println("5. Exit");

            System.out.print("Your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    controller.showBalance();
                    break;
                case 2:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    controller.deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    controller.withdraw(withdrawAmount);
                    break;
                case 4:
                    // Show transaction history
                    TransactionHistory transactionHistory = proxyBankDatabase.getTransactionHistory();
                    if (transactionHistory != null) {
                        transactionHistory.showHistory();
                    } else {
                        System.out.println("No transaction history available.");
                    }
                    break;
                case 5:
                    exit = true;
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        ATM atm = ATM.getInstance();
        atm.start();
    }
}
