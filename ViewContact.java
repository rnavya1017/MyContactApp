import java.util.Optional;

//====================== Contact ======================

class Contact {

    private String name;
    private String phone;
    private String email;
    private String address;

    public Contact(String name, String phone, String email, String address) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    // Getter Methods

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public Optional<String> getEmail() {
        return Optional.ofNullable(email);
    }

    public Optional<String> getAddress() {
        return Optional.ofNullable(address);
    }

    // toString() Override

    @Override
    public String toString() {

        return String.format(
                "Name    : %s\nPhone   : %s\nEmail   : %s\nAddress : %s",
                name,
                phone,
                getEmail().orElse("Not Available"),
                getAddress().orElse("Not Available")
        );

    }

}

//====================== Immutable View Object ======================

final class ContactView {

    private final String details;

    public ContactView(String details) {
        this.details = details;
    }

    public String getDetails() {
        return details;
    }
}

//====================== Decorator Pattern ======================

interface ContactDisplay {

    ContactView display();

}

//====================== Basic Display ======================

class BasicContactDisplay implements ContactDisplay {

    private Contact contact;

    public BasicContactDisplay(Contact contact) {
        this.contact = contact;
    }

    @Override
    public ContactView display() {

        return new ContactView(contact.toString());

    }

}

//====================== Decorator ======================

abstract class ContactDecorator implements ContactDisplay {

    protected ContactDisplay display;

    public ContactDecorator(ContactDisplay display) {
        this.display = display;
    }

}

//====================== Fancy Display ======================

class FancyDisplay extends ContactDecorator {

    public FancyDisplay(ContactDisplay display) {
        super(display);
    }

    @Override
    public ContactView display() {

        ContactView view = display.display();

        String output =
                "=============================\n" +
                "     CONTACT DETAILS\n" +
                "=============================\n"
                        + view.getDetails() +
                "\n=============================";

        return new ContactView(output);

    }

}

//====================== Main ======================

public class ViewContact {

    public static void main(String[] args) {

        // Logged-in User views contact

        Contact contact = new Contact(
                "Navya",
                "9876543210",
                "navya@gmail.com",
                "Bangalore"
        );

        ContactDisplay display =
                new FancyDisplay(
                        new BasicContactDisplay(contact));

        ContactView view = display.display();

        System.out.println(view.getDetails());

    }

}