import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//Expense class represents an expense made by the Chama group
//Tracks expense details including description, amount, and timestamp
public class Expense {
    private String description;
    private double amount;
    private LocalDateTime timestamp;

   //create a new expense
    public Expense(String description, double amount) throws IllegalArgumentException {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Expense description cannot be null or empty");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Expense amount must be positive");
        }

        this.description = description.trim();
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
    }

    //Get expense description
    public String getDescription() {
        return description;
    }


    public double getAmount() {
        return amount;
    }

    //When the expense was recorded
    public LocalDateTime getTimestamp() {
        return timestamp;
    }


    public String getExpenseDetails() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return String.format("Description: %s | Amount: KSh %.2f | Date: %s",
                description, amount, timestamp.format(formatter));
    }


    @Override
    public String toString() {
        return getExpenseDetails();
    }

    //true if expenses have same description, amount, and timestamp
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Expense expense = (Expense) obj;
        return Double.compare(expense.amount, amount) == 0 &&
                description.equals(expense.description) &&
                timestamp.equals(expense.timestamp);
    }


    @Override
    public int hashCode() {
        return description.hashCode() + Double.hashCode(amount) + timestamp.hashCode();
    }
}
