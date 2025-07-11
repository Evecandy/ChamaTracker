import java.util.InputMismatchException;
import java.util.Scanner;


//is the main class for the Chama Tracker
public class ChamaTracker {
    private static Scanner scanner = new Scanner(System.in);
    private static Chama chama;

    //main method is the entry point
    public static void main(String[] args) {
        System.out.println("=".repeat(60));
        System.out.println("WELCOME TO CHAMA TRACKER CONSOLE");
        System.out.println("=".repeat(60));

        try {
            initializeChama();
            displayMenu();
        } catch (Exception e) {
            System.err.println("x Fatal error: " + e.getMessage());
            System.exit(1);
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }

    //initialize the chama with a name
    private static void initializeChama() {
        while (chama == null) {
            try {
                System.out.print("Enter your Chama name: ");
                String chamaName = scanner.nextLine();
                chama = new Chama(chamaName);
                System.out.println("✓ Chama '" + chamaName + "' initialized successfully!\n");
            } catch (IllegalArgumentException e) {
                System.err.println("❌ Error: " + e.getMessage());
                System.out.println("Please try again.\n");
            } catch (Exception e) {
                System.err.println("❌ Unexpected error: " + e.getMessage());
                System.out.println("Please try again.\n");
            }
        }
    }

    //displaying main menu and handling user interaction
    private static void displayMenu() {
        boolean running = true;

        while (running) {
            try {
                printMainMenu();
                int choice = getIntegerInput("Enter your choice: ");

                switch (choice) {
                    case 1:
                        addMember();
                        break;
                    case 2:
                        recordContribution();
                        break;
                    case 3:
                        addExpense();
                        break;
                    case 4:
                        viewMembers();
                        break;
                    case 5:
                        viewExpenses();
                        break;
                    case 6:
                        viewFinancialSummary();
                        break;
                    case 7:
                        running = false;
                        System.out.println("\n✓ Thank you for using Chama Tracker!");
                        System.out.println("✓ Goodbye!");
                        break;
                    default:
                        System.err.println("❌ Invalid choice. Please select 1-7.");
                }

                if (running) {
                    System.out.println("\nPress Enter to continue...");
                    scanner.nextLine();
                }

            } catch (Exception e) {
                System.err.println("❌ Error: " + e.getMessage());
                System.out.println("Please try again.\n");
            }
        }
    }

   //Print menu options
    private static void printMainMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("           CHAMA TRACKER MENU");
        System.out.println("           " + chama.getChamaName().toUpperCase());
        System.out.println("=".repeat(50));
        System.out.println("1. Add Member");
        System.out.println("2. Record Member Contribution");
        System.out.println("3. Add Expense");
        System.out.println("4. View All Members");
        System.out.println("5. View All Expenses");
        System.out.println("6. View Financial Summary");
        System.out.println("7. Exit");
        System.out.println("=".repeat(50));
    }

    //add a new member to the chama,
    private static void addMember() {
        try {
            System.out.println("\n--- ADD NEW MEMBER ---");

            System.out.print("Enter member ID: ");
            String id = scanner.nextLine();

            System.out.print("Enter member name: ");
            String name = scanner.nextLine();

            ChamaMember chamaMember = new ChamaMember(id, name);
            chama.addMember(chamaMember);

        } catch (IllegalArgumentException e) {
            System.err.println("❌ Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("❌ Unexpected error adding member: " + e.getMessage());
        }
    }


    private static void recordContribution() {
        try {
            if (!chama.hasMembers()) {
                System.err.println("❌ No members found. Please add members first.");
                return;
            }

            System.out.println("\n--- RECORD CONTRIBUTION ---");

            System.out.print("Enter member ID: ");
            String memberId = scanner.nextLine();

            double amount = getDoubleInput("Enter contribution amount (KSh): ");

            chama.recordContribution(memberId, amount);

        } catch (IllegalArgumentException e) {
            System.err.println("❌ Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("❌ Unexpected error recording contribution: " + e.getMessage());
        }
    }


    private static void addExpense() {
        try {
            System.out.println("\n--- ADD EXPENSE ---");

            System.out.print("Enter expense description: ");
            String description = scanner.nextLine();

            double amount = getDoubleInput("Enter expense amount (KSh): ");

            Expense expense = new Expense(description, amount);
            chama.addExpense(expense);

        } catch (IllegalArgumentException e) {
            System.err.println("❌ Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("❌ Unexpected error adding expense: " + e.getMessage());
        }
    }

    //view members and their contributions
    private static void viewMembers() {
        try {
            System.out.println("\n--- ALL MEMBERS ---");

            if (!chama.hasMembers()) {
                System.out.println("No members found.");
                return;
            }

            System.out.println("Total Members: " + chama.getMemberCount());
            System.out.println("-".repeat(60));

            for (ChamaMember chamaMember : chama.getMembers()) {
                System.out.println(chamaMember.getMemberDetails());
            }

        } catch (Exception e) {
            System.err.println("❌ Error viewing members: " + e.getMessage());
        }
    }


    private static void viewExpenses() {
        try {
            System.out.println("\n--- ALL EXPENSES ---");

            if (!chama.hasExpenses()) {
                System.out.println("No expenses recorded.");
                return;
            }

            System.out.println("Total Expenses: " + chama.getExpenseCount());
            System.out.println("-".repeat(70));

            for (Expense expense : chama.getExpenses()) {
                System.out.println(expense.getExpenseDetails());
            }

        } catch (Exception e) {
            System.err.println("❌ Error viewing expenses: " + e.getMessage());
        }
    }


    private static void viewFinancialSummary() {
        try {
            System.out.println(chama.getFinancialSummary());
        } catch (Exception e) {
            System.err.println("❌ Error generating financial summary: " + e.getMessage());
        }
    }

    //
    private static int getIntegerInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                return value;
            } catch (InputMismatchException e) {
                System.err.println("❌ Please enter a valid number.");
                scanner.nextLine(); // Clear invalid input
            } catch (Exception e) {
                System.err.println("❌ Error reading input: " + e.getMessage());
                scanner.nextLine(); // Clear input
            }
        }
    }

    //
    private static double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                double value = scanner.nextDouble();
                scanner.nextLine(); // Consume newline

                if (value <= 0) {
                    System.err.println("❌ Amount must be positive. Please try again.");
                    continue;
                }

                return value;
            } catch (InputMismatchException e) {
                System.err.println("❌ Please enter a valid number.");
                scanner.nextLine(); // Clear invalid input
            } catch (Exception e) {
                System.err.println("❌ Error reading input: " + e.getMessage());
                scanner.nextLine(); // Clear input
            }
        }
    }
}
