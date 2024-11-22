public class BankDatabase {
    private int[] accountNumbers;
    private String[] pins;
    private double[] balances;
    private TransactionHistory[] transactionHistories;

    public   BankDatabase() {
        // Initialize with example accounts
        accountNumbers = new int[]{12345678, 12345679};
        pins = new String[]{"1234", "1235"};
        balances = new double[]{1000.00, 500.00};
        transactionHistories = new TransactionHistory[accountNumbers.length];
        // Initialize TransactionHistory for each account
        for (int i = 0; i < accountNumbers.length; i++) {
            transactionHistories[i] = new TransactionHistory();
        }
    }

    public boolean credinitialsValidation(int accountNumber, String pin) {
        for (int i = 0; i < accountNumbers.length; i++) {
            if (accountNumbers[i] == accountNumber && pins[i].equals(pin)) {
                return true;
            }
        }
        return false;
    }

    public double getBalance(int accountNumber) {
        System.out.println("Accessing balance for account: " + accountNumber);
        for (int i = 0; i < accountNumbers.length; i++) {
            if (accountNumbers[i] == accountNumber) {
                return balances[i];
            }
        }
        System.out.println("Account not found.");
        return 0.0;
    }

    public void performTransaction(int accountNumber, double amount) {
        
        System.out.println("Transaction on account: " + accountNumber + " for amount: " + amount);
        for (int i = 0; i < accountNumbers.length; i++) {
            if (accountNumbers[i] == accountNumber) {
                balances[i] += amount;
                // Create transaction detail
                String transactionDetail = "Account: " + accountNumber + ", Amount: " + amount + ", New Balance: " + balances[i];
                // Notify the TransactionHistory
                transactionHistories[i].update(transactionDetail);
                return;
            }
        }
        System.out.println("Account not found.");
    }

    // Method to get the TransactionHistory for an account
    public TransactionHistory getTransactionHistory(int accountNumber) {
        for (int i = 0; i < accountNumbers.length; i++) {
            if (accountNumbers[i] == accountNumber) {
                return transactionHistories[i];
            }
        }
        System.out.println("Account not found.");
        return null;
    }
}
