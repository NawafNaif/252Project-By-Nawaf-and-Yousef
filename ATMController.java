public class ATMController {
    private ATMView view;
    private ProxyBankDatabase proxyBankDatabase;

    public ATMController(ATMView view, ProxyBankDatabase proxyBankDatabase) {
        this.view = view;
        this.proxyBankDatabase = proxyBankDatabase;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount. Deposit amount must be positive.");
            return;
        }
        System.out.println("Deposited $" + amount);
        proxyBankDatabase.performTransaction(amount);
        showBalance();
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount. Withdrawal amount must be positive.");
            return;
        }
        double currentBalance = proxyBankDatabase.getBalance();
        if (amount <= currentBalance) {
            proxyBankDatabase.performTransaction(-amount);
            System.out.println("Withdrawn $" + amount);
        } else {
            System.out.println("Insufficient balance!");
        }
        showBalance();
    }

    public void showBalance() {
        double balance = proxyBankDatabase.getBalance();
        view.displayBalance(balance);
    }
}
