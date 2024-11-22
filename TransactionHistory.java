import java.util.ArrayList;
import java.util.List;

public class TransactionHistory implements Observer {
    private List<String> transactions = new ArrayList<>();

    @Override
    public void update(String transactionDetail) {
        transactions.add(transactionDetail);
        System.out.println("Transaction Recorded: " + transactionDetail);
    }

    public void showHistory() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions recorded.");
            return;
        }
        System.out.println("Transaction History:");
        for (String transaction : transactions) {
            System.out.println(transaction);
        }
    }
}