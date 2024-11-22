public class account {
    private double balance;
    private int accountNumber;
    

    public int getAccountNumber(){
        return accountNumber;
        
    }
    public  account(int accountNumber) {
        this.accountNumber = accountNumber ;
    }
    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
        } else {
            System.out.println("Insufficient balance!");
        }
    }

}
