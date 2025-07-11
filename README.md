# ChamaTracker

Problem Statement:
Build a simple console-based Chama Tracker that allows:
Chama Tracker System
Build a simple console-based system to track a group's (Chama) finances. The system should allow:

Adding members to the group
Recording each member's contributions
Recording expenses paid by the group
Viewing financial summaries including:
Total contributions from all members
Total expenses
Current balance (contributions minus expenses)

Key Requirements:


class Member {
private String id;          // Unique identifier for member
private String name;       // Member's full name
private double totalContributed;  // Total amount contributed

    // Constructor
    // Methods to:
    // - Get member details
    // - Add to contribution
    // - Get contribution amount
}

class Expense {
private String description; // What the expense was for
private double amount;     // How much was spent

    // Constructor
    // Methods to:
    // - Get expense details
}

class Chama {
private List<Member> members;   // All group members
private List<Expense> expenses; // All group expenses

    // Methods to:
    // - Add a new member
    // - Record a member's contribution
    // - Add a new expense
    // - Calculate total contributions
    // - Calculate total expenses
    // - Calculate current balance
}


Constraints:
Use only core Java (no libraries or frameworks)
Use List and Map
Output via console only

