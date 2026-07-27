import java.util.*;
import java.util.function.Predicate;
import java.util.regex.Pattern;

//==================== Contact ====================

class Contact {

    private String name;
    private String phone;
    private String email;
    private String tag;

    public Contact(String name, String phone, String email, String tag) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.tag = tag;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getTag() {
        return tag;
    }

    @Override
    public String toString() {
        return "\nName  : " + name +
                "\nPhone : " + phone +
                "\nEmail : " + email +
                "\nTag   : " + tag;
    }
}

//==================== Specification Pattern ====================

interface SearchCriteria {

    Predicate<Contact> filter(String keyword);

}

//==================== Name Search ====================

class NameSearch implements SearchCriteria {

    public Predicate<Contact> filter(String keyword) {

        return contact ->
                contact.getName()
                        .toLowerCase()
                        .contains(keyword.toLowerCase());

    }

}

//==================== Phone Search ====================

class PhoneSearch implements SearchCriteria {

    public Predicate<Contact> filter(String keyword) {

        Pattern pattern = Pattern.compile(keyword);

        return contact ->
                pattern.matcher(contact.getPhone()).find();

    }

}

//==================== Email Search ====================

class EmailSearch implements SearchCriteria {

    public Predicate<Contact> filter(String keyword) {

        return contact ->
                contact.getEmail()
                        .equalsIgnoreCase(keyword);

    }

}

//==================== Tag Search ====================

class TagSearch implements SearchCriteria {

    public Predicate<Contact> filter(String keyword) {

        return contact ->
                contact.getTag()
                        .equalsIgnoreCase(keyword);

    }

}

//==================== Chain of Responsibility ====================

abstract class SearchHandler {

    protected SearchHandler next;

    public void setNext(SearchHandler next) {
        this.next = next;
    }

    public abstract void handle(List<Contact> contacts,
                                String type,
                                String keyword);

}

//==================== Name Handler ====================

class NameHandler extends SearchHandler {

    public void handle(List<Contact> contacts,
                       String type,
                       String keyword) {

        if (type.equalsIgnoreCase("name")) {

            SearchCriteria search = new NameSearch();

            contacts.stream()
                    .filter(search.filter(keyword))
                    .forEach(System.out::println);

        }

        else if (next != null) {

            next.handle(contacts, type, keyword);

        }

    }

}

//==================== Phone Handler ====================

class PhoneHandler extends SearchHandler {

    public void handle(List<Contact> contacts,
                       String type,
                       String keyword) {

        if (type.equalsIgnoreCase("phone")) {

            SearchCriteria search = new PhoneSearch();

            contacts.stream()
                    .filter(search.filter(keyword))
                    .forEach(System.out::println);

        }

        else if (next != null) {

            next.handle(contacts, type, keyword);

        }

    }

}

//==================== Email Handler ====================

class EmailHandler extends SearchHandler {

    public void handle(List<Contact> contacts,
                       String type,
                       String keyword) {

        if (type.equalsIgnoreCase("email")) {

            SearchCriteria search = new EmailSearch();

            contacts.stream()
                    .filter(search.filter(keyword))
                    .forEach(System.out::println);

        }

        else if (next != null) {

            next.handle(contacts, type, keyword);

        }

    }

}

//==================== Tag Handler ====================

class TagHandler extends SearchHandler {

    public void handle(List<Contact> contacts,
                       String type,
                       String keyword) {

        if (type.equalsIgnoreCase("tag")) {

            SearchCriteria search = new TagSearch();

            contacts.stream()
                    .filter(search.filter(keyword))
                    .forEach(System.out::println);

        }

        else {

            System.out.println("Invalid Search Type");

        }

    }

}

//==================== Main ====================

public class SearchContacts {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        List<Contact> contacts = new ArrayList<>();

        contacts.add(new Contact(
                "Navya",
                "9876543210",
                "navya@gmail.com",
                "Friend"));

        contacts.add(new Contact(
                "Rahul",
                "9123456789",
                "rahul@gmail.com",
                "Office"));

        contacts.add(new Contact(
                "Anjali",
                "9988776655",
                "anjali@gmail.com",
                "Family"));

        // Chain

        SearchHandler name = new NameHandler();
        SearchHandler phone = new PhoneHandler();
        SearchHandler email = new EmailHandler();
        SearchHandler tag = new TagHandler();

        name.setNext(phone);
        phone.setNext(email);
        email.setNext(tag);

        System.out.println("===== SEARCH CONTACT =====");

        System.out.println("Search By");

        System.out.println("1. name");
        System.out.println("2. phone");
        System.out.println("3. email");
        System.out.println("4. tag");

        System.out.print("Enter Search Type : ");

        String type = sc.nextLine();

        System.out.print("Enter Keyword : ");

        String keyword = sc.nextLine();

        System.out.println("\nSearch Result");

        name.handle(contacts, type, keyword);

    }

}