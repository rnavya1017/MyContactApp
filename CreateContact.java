import java.time.LocalDateTime;
import java.util.*;

//==================== PhoneNumber ====================

class PhoneNumber {

    private String number;

    public PhoneNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }
}

//==================== Email ====================

class Email {

    private String email;

    public Email(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}

//==================== Contact ====================

abstract class Contact {

    protected UUID id;
    protected String name;
    protected List<PhoneNumber> phoneNumbers;
    protected List<Email> emails;
    protected String address;
    protected LocalDateTime createdDate;

    public Contact(String name,
                   List<PhoneNumber> phoneNumbers,
                   List<Email> emails,
                   String address) {

        this.id = UUID.randomUUID();
        this.name = name;
        this.phoneNumbers = phoneNumbers;
        this.emails = emails;
        this.address = address;
        this.createdDate = LocalDateTime.now();
    }

    public abstract void display();
}

//==================== Person ====================

class Person extends Contact {

    public Person(String name,
                  List<PhoneNumber> phoneNumbers,
                  List<Email> emails,
                  String address) {

        super(name, phoneNumbers, emails, address);
    }

    @Override
    public void display() {

        System.out.println("\n------ Person Contact ------");

        System.out.println("ID : " + id);
        System.out.println("Name : " + name);

        System.out.println("Phone Numbers");

        for (PhoneNumber p : phoneNumbers)
            System.out.println(p.getNumber());

        System.out.println("Emails");

        for (Email e : emails)
            System.out.println(e.getEmail());

        System.out.println("Address : " + address);
        System.out.println("Created : " + createdDate);

    }

}

//==================== Organization ====================

class Organization extends Contact {

    public Organization(String name,
                        List<PhoneNumber> phoneNumbers,
                        List<Email> emails,
                        String address) {

        super(name, phoneNumbers, emails, address);
    }

    @Override
    public void display() {

        System.out.println("\n------ Organization Contact ------");

        System.out.println("ID : " + id);
        System.out.println("Organization : " + name);

        System.out.println("Phone Numbers");

        for (PhoneNumber p : phoneNumbers)
            System.out.println(p.getNumber());

        System.out.println("Emails");

        for (Email e : emails)
            System.out.println(e.getEmail());

        System.out.println("Address : " + address);
        System.out.println("Created : " + createdDate);

    }

}

//==================== Builder Pattern ====================

class ContactBuilder {

    private String name;
    private List<PhoneNumber> phones = new ArrayList<>();
    private List<Email> emails = new ArrayList<>();
    private String address;

    public ContactBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ContactBuilder addPhone(String phone) {
        phones.add(new PhoneNumber(phone));
        return this;
    }

    public ContactBuilder addEmail(String email) {
        emails.add(new Email(email));
        return this;
    }

    public ContactBuilder setAddress(String address) {
        this.address = address;
        return this;
    }

    public Contact buildPerson() {
        return new Person(name, phones, emails, address);
    }

    public Contact buildOrganization() {
        return new Organization(name, phones, emails, address);
    }

}

//==================== Factory Pattern ====================

class ContactFactory {

    public static Contact createContact(String type,
                                        ContactBuilder builder) {

        if (type.equalsIgnoreCase("Person")) {

            return builder.buildPerson();

        }

        return builder.buildOrganization();

    }

}

//==================== Main ====================

public class CreateContact {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {

            System.out.println("===== CREATE CONTACT =====");

            System.out.print("Enter Contact Type (Person/Organization) : ");
            String type = sc.nextLine();

            ContactBuilder builder = new ContactBuilder();

            System.out.print("Enter Name : ");
            builder.setName(sc.nextLine());

            System.out.print("How many phone numbers? ");
            int phoneCount = sc.nextInt();
            sc.nextLine();

            for (int i = 1; i <= phoneCount; i++) {

                System.out.print("Enter Phone " + i + " : ");
                builder.addPhone(sc.nextLine());

            }

            System.out.print("How many email addresses? ");
            int emailCount = sc.nextInt();
            sc.nextLine();

            for (int i = 1; i <= emailCount; i++) {

                System.out.print("Enter Email " + i + " : ");
                builder.addEmail(sc.nextLine());

            }

            System.out.print("Enter Address (Optional): ");
            builder.setAddress(sc.nextLine());

            Contact contact =
                    ContactFactory.createContact(type, builder);

            System.out.println("\nContact Created Successfully.");

            contact.display();

        }

        catch (Exception e) {

            System.out.println("Error : " + e.getMessage());

        }

        sc.close();

    }

}