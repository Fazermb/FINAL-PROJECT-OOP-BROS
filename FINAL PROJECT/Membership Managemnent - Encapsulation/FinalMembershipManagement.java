import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.Calendar;
import java.util.Random; // Importing Random class

class Member {
    private String name;
    private String membershipId;
    private boolean isActive;
    private int attendanceCount;
    private Date expirationDate; // New attribute for expiration date
    private int membershipDuration; // Duration in months

    // Constructor
    public Member(String name, String membershipId, int membershipDuration) {
        this.name = name;
        this.membershipId = membershipId;
        this.isActive = true;
        this.attendanceCount = 0;
        this.membershipDuration = membershipDuration;
        this.expirationDate = calculateExpirationDate(); // Set expiration date on creation
    }

    // Method to calculate the expiration date based on membership duration
    private Date calculateExpirationDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, membershipDuration); // Membership lasts for specified months
        return calendar.getTime();
    }

    // Getters and setters for encapsulation
    public String getName() {
        return name;
    }

    public String getMembershipId() {
        return membershipId;
    }

    public boolean isActive() {
        return isActive;
    }

    public int getAttendanceCount() {
        return attendanceCount;
    }

    public Date getExpirationDate() {
        return expirationDate; // Getter for expiration date
    }

    // Methods to renew or cancel membership
    public void renewMembership(int additionalMonths) {
        if (!isActive) {
            isActive = true;
            this.membershipDuration += additionalMonths; // Add additional months to duration
            this.expirationDate = calculateExpirationDate(); // Reset expiration date on renewal
            System.out.println("Membership renewed for: " + name);
        } else {
            System.out.println("Membership is already active for: " + name);
        }
    }

    public void cancelMembership() {
        if (isActive) {
            isActive = false;
            System.out.println("Membership canceled for: " + name);
        } else {
            System.out.println("Membership is already inactive for: " + name);
        }
    }

    // Track attendance
    public void markAttendance() {
        if (isActive) {
            attendanceCount++;
            System.out.println(name + " has attended the gym. Total attendance: " + attendanceCount);
        } else {
            System.out.println(name + " cannot attend. Membership is inactive.");
        }
    }
}

class MembershipManagement {
    private List<Member> members;
    private Random random; // Random object for generating membership ID

    // Constructor
    public MembershipManagement() {
        members = new ArrayList<>();
        random = new Random(); // Initialize the Random object
    }

    // Add a new member
    public void addMember(String name, int membershipDuration) {
        String membershipId = generateMembershipId(); // Generate a random 4-digit membership ID
        members.add(new Member(name, membershipId, membershipDuration));
        System.out.println("\nNew member added: " + name + " with ID: " + membershipId + "\n");
    }

    // Generate a random 4-digit membership ID
    private String generateMembershipId() {
        int id = 1000 + random.nextInt(9000); // Generate a random number between 1000 and 9999
        return String.valueOf(id); // Convert to String
    }

    // Remove a member
    public void removeMember(String membershipId) {
        members.removeIf(member -> member.getMembershipId().equals(membershipId));
        System.out.println("\nMember with ID " + membershipId + " removed.\n");
    }

    // Display all members
    public void displayMembers() {
        System.out.println("\nMember List:");
        for (Member member : members) {
            System.out.println("--------------------------------------------------");
            System.out.println("Name: " + member.getName());
            System.out.println("ID: " + member.getMembershipId());
            System.out.println("Active: " + member .isActive());
            System.out.println("Attendance: " + member.getAttendanceCount());
            System.out.println("Expiration Date: " + member.getExpirationDate()); // Display expiration date
            System.out.println("--------------------------------------------------");
        }
        System.out.println(); // Add an extra line for spacing
    }

    // Method to get a member by index
    public Member getMember(int index) {
        if (index >= 0 && index < members.size()) {
            return members.get(index);
        }
        return null; // Return null if index is out of bounds
    }
}

public class FinalMembershipManagement {
    public static void main(String[] args) {
        MembershipManagement management = new MembershipManagement();
        Scanner scanner = new Scanner(System.in);
        String choice;

        do {
            System.out.println("1. Add Member");
            System.out.println("2. Display Members");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    // Adding a new member
                    System.out.print("\n");
                    System.out.print("Enter member name: ");
                    String name = scanner.nextLine();
                    System.out.print("Choose membership plan (1 month, 3 months, 6 months, 12 months): ");
                    int duration = scanner.nextInt();
                    scanner.nextLine(); // Consume newline left-over
                    management.addMember(name, duration); // Call addMember without membershipId
                    break;

                case "2":
                    // Displaying all members
                    management.displayMembers();
                    break;

                case "3":
                    System.out.println("\nExiting the system. Thank you!\n");
                    break;

                default:
                    System.out.println("\nInvalid choice. Please try again.\n");
                    break;
            }
        } while (!choice.equals("3"));

        scanner.close();
    }
}