
public class ChamaMember {
    private String id;
    private String name;
    private double totalContributed;


    public ChamaMember(String id, String name) throws IllegalArgumentException {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Member ID cannot be null or empty");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Member name cannot be null or empty");
        }

        this.id = id.trim();
        this.name = name.trim();
        this.totalContributed = 0.0;
    }

    //Get member's unique ID
    public String getId() {
        return id;
    }


    //get member's full name
    public String getName() {
        return name;
    }

    //Get total amount contributed by this member
    public double getTotalContributed() {
        return totalContributed;
    }


    public void addContribution(double amount) throws IllegalArgumentException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Contribution amount must be positive");
        }
        this.totalContributed += amount;
    }


    public String getMemberDetails() {
        return String.format("ID: %s | Name: %s | Total Contributed: KSh %.2f",
                id, name, totalContributed);
    }


    @Override
    public String toString() {
        return getMemberDetails();
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ChamaMember chamaMember = (ChamaMember) obj;
        return id.equals(chamaMember.id);
    }


    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
