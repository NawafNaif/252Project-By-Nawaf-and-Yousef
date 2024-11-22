public class ProxyBankDatabase {
    private BankDatabase bankDatabase;
    private boolean authenticated;
    private int authenticatedAccountNumber;

    public ProxyBankDatabase() {
        this.bankDatabase = new BankDatabase();
        this.authenticated = false;
    }

    public void authenticate(int accountNumber, String pin) {
        if (bankDatabase.credinitialsValidation(accountNumber, pin)) {
            this.authenticated = true;
            this.authenticatedAccountNumber = accountNumber;
            System.out.println("Authentication successful for: " + accountNumber);
        } else {
            System.out.println("Authentication failed.");
        }
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public double getBalance() {
        if (authenticated) {
            return bankDatabase.getBalance(authenticatedAccountNumber);
        } else {
            throw new SecurityException("Unauthorized.");
        }
    }

    public void performTransaction(double amount) {
        if (authenticated) {
            bankDatabase.performTransaction(authenticatedAccountNumber, amount);
        } else {
            throw new SecurityException("Unauthorized.");
        }
    }

    // Method to get TransactionHistory of the authenticated account
    public TransactionHistory getTransactionHistory() {
        if (authenticated) {
            return bankDatabase.getTransactionHistory(authenticatedAccountNumber);
        } else {
            throw new SecurityException("Unauthorized.");
        }
    }
}
