import java.util.*;

//====================== Observer ======================

interface Observer {
    void update(String message);
}

//====================== Concrete Observer ======================

class NotificationService implements Observer {

    @Override
    public void update(String message) {
        System.out.println("Notification : " + message);
    }
}

//====================== Subject ======================

class ContactManager {

    private List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void notifyObservers(String message) {

        for (Observer observer : observers) {
            observer.update(message);
        }

    }
}

//====================== Contact ======================

class Contact {

    private int id;
    private String name;
    private String phone;
    private boolean deleted; // Soft Delete Flag

    public Contact(int id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.deleted = false;
    }

    public int getId() {
        return id;
    }

    public boolean isDeleted() {
        return deleted;
    }

    // Soft Delete
    public void softDelete() {
        deleted = true;
    }

    @Override
    public String toString() {

        return "\nID : " + id +
                "\nName : " + name +
                "\nPhone : " + phone +
                "\nStatus : " + (deleted ? "Deleted" : "Active");
    }

}

//====================== Main ======================

public class DeleteContact {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Logged-in user's contact list
        List<Contact> contacts = new ArrayList<>();

        contacts.add(new Contact(1, "Navya", "9876543210"));
        contacts.add(new Contact(2, "Rahul", "9123456789"));

        ContactManager manager = new ContactManager();

        manager.addObserver(new NotificationService());

        while (true) {

            System.out.println("\n===== DELETE CONTACT =====");

            System.out.println("1. View Contacts");
            System.out.println("2. Soft Delete");
            System.out.println("3. Hard Delete");
            System.out.println("4. Exit");

            System.out.print("Enter Choice : ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:

                    for (Contact c : contacts) {
                        System.out.println(c);
                    }

                    break;

                case 2:

                    System.out.print("Enter Contact ID : ");
                    int softId = sc.nextInt();

                    Contact softContact = null;

                    for (Contact c : contacts) {

                        if (c.getId() == softId) {
                            softContact = c;
                            break;
                        }

                    }

                    if (softContact == null) {
                        System.out.println("Contact Not Found.");
                        break;
                    }

                    System.out.print("Are you sure? (yes/no) : ");
                    sc.nextLine();
                    String confirmSoft = sc.nextLine();

                    if (confirmSoft.equalsIgnoreCase("yes")) {

                        softContact.softDelete();

                        manager.notifyObservers("Contact " + softId + " Soft Deleted");

                        System.out.println("Contact Soft Deleted Successfully.");

                    } else {

                        System.out.println("Deletion Cancelled.");

                    }

                    break;

                case 3:

                    System.out.print("Enter Contact ID : ");
                    int hardId = sc.nextInt();

                    Contact hardContact = null;

                    for (Contact c : contacts) {

                        if (c.getId() == hardId) {
                            hardContact = c;
                            break;
                        }

                    }

                    if (hardContact == null) {
                        System.out.println("Contact Not Found.");
                        break;
                    }

                    System.out.print("Are you sure? (yes/no) : ");
                    sc.nextLine();
                    String confirmHard = sc.nextLine();

                    if (confirmHard.equalsIgnoreCase("yes")) {

                        contacts.remove(hardContact);

                        manager.notifyObservers("Contact " + hardId + " Hard Deleted");

                        System.out.println("Contact Hard Deleted Successfully.");

                    } else {

                        System.out.println("Deletion Cancelled.");

                    }

                    break;

                case 4:

                    System.out.println("Thank You");
                    System.exit(0);

                default:

                    System.out.println("Invalid Choice");

            }

        }

    }

}