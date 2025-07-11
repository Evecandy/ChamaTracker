
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Chama {
    private List<ChamaMember> members;
    private List<Expense> expenses;
    private Map<String, ChamaMember> memberMap; // for quick member lookup by ID
    private String chamaName;


    //Constructor to create a new Chama
    //throws IllegalArgumentException if chamaName is null or empty
    public Chama(String chamaName) throws IllegalArgumentException {
        if (chamaName == null || chamaName.trim().isEmpty()) {
            throw new IllegalArgumentException("Chama name cannot be null or empty");
        }

        this.chamaName = chamaName.trim();
        this.members = new ArrayList<>();
        this.expenses = new ArrayList<>();
        this.memberMap = new HashMap<>();
    }


    //Get Chama name
    public String getChamaName() {
        return chamaName;
    }


    //Add a new member to the Chama
    //throws IllegalArgumentException if member is null or ID already exists
    public void addMember(ChamaMember member) throws IllegalArgumentException {
        if (member == null) {
            throw new IllegalArgumentException("Member cannot be null");
        }

        if (memberMap.containsKey(member.getId())) {
            throw new IllegalArgumentException("Member with ID '" + member.getId() + "' already exists");
        }

        try {
            members.add(member);
            memberMap.put(member.getId(), member);
            System.out.println("✓ Member added successfully: " + member.getName());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error adding member: " + e.getMessage());
        }
    }


    //records a member's contribution
    //throws IllegalArgumentException if member doesn't exist or amount is invalid
    public void recordContribution(String memberId, double amount) throws IllegalArgumentException {
        if (memberId == null || memberId.trim().isEmpty()) {
            throw new IllegalArgumentException("Member ID cannot be null or empty");
        }

        ChamaMember member = memberMap.get(memberId.trim());
        if (member == null) {
            throw new IllegalArgumentException("Member with ID '" + memberId + "' not found");
        }

        try {
            member.addContribution(amount);

            System.out.println("✓ Contribution recorded: " + member.getName() + " contributed KSh " + amount);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error recording contribution: " + e.getMessage());
        }
    }


    public void addExpense(Expense expense) throws IllegalArgumentException {
        if (expense == null) {
            throw new IllegalArgumentException("Expense cannot be null");
        }

        try {
            expenses.add(expense);
            System.out.println("✓ Expense added: " + expense.getDescription() + " - KSh " + expense.getAmount());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error adding expense: " + e.getMessage());
        }
    }


    //Calculate total contributions from all members and return the total
    public double calculateTotalContributions() {
        double total = 0.0;
        try {
            for (ChamaMember member : members) {
                total += member.getTotalContributed();
            }
        } catch (Exception e) {
            System.err.println("Error calculating total contributions: " + e.getMessage());
        }
        return total;
    }


    public double calculateTotalExpenses() {
        double total = 0.0;
        try {
            for (Expense expense : expenses) {
                total += expense.getAmount();
            }
        } catch (Exception e) {
            System.err.println("Error calculating total expenses: " + e.getMessage());
        }
        return total;
    }


    public double calculateCurrentBalance() {
        try {
            return calculateTotalContributions() - calculateTotalExpenses();
        } catch (Exception e) {
            System.err.println("Error calculating current balance: " + e.getMessage());
            return 0.0;
        }
    }


    public List<ChamaMember> getMembers() {
        return new ArrayList<>(members); // Return copy to prevent external modification
    }


    public List<Expense> getExpenses() {
        return new ArrayList<>(expenses); // Return copy to prevent external modification
    }


    public ChamaMember getMemberById(String memberId) {
        if (memberId == null || memberId.trim().isEmpty()) {
            return null;
        }
        return memberMap.get(memberId.trim());
    }

    //get number of members
    public int getMemberCount() {
        return members.size();
    }

    //get number of expenses
    public int getExpenseCount() {
        return expenses.size();
    }

    //check if chama has members
    public boolean hasMembers() {
        return !members.isEmpty();
    }

    //check if chama has expenses
    public boolean hasExpenses() {
        return !expenses.isEmpty();
    }


    public String getFinancialSummary() {
        StringBuilder summary = new StringBuilder();
        summary.append("\n" + "=".repeat(50) + "\n");
        summary.append("CHAMA FINANCIAL SUMMARY\n");
        summary.append("           ").append(chamaName.toUpperCase()).append("\n");
        summary.append("=".repeat(50) + "\n");
        summary.append(String.format("Total Members: %d\n", getMemberCount()));
        summary.append(String.format("Total Expenses Recorded: %d\n", getExpenseCount()));
        summary.append("-".repeat(50) + "\n");
        summary.append(String.format("Total Contributions: KSh %.2f\n", calculateTotalContributions()));
        summary.append(String.format("Total Expenses: KSh %.2f\n", calculateTotalExpenses()));
        summary.append("-".repeat(50) + "\n");
        summary.append(String.format("Current Balance: KSh %.2f\n", calculateCurrentBalance()));
        summary.append("=".repeat(50) + "\n");

        return summary.toString();
    }
}
