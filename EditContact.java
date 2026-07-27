import java.util.Scanner;

//====================== Contact ======================

class Contact {

    private String name;
    private String phone;
    private String email;

    // Constructor
    public Contact(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    // Copy Constructor (Deep Copy)
    public Contact(Contact other) {
        this.name = other.name;
        this.phone = other.phone;
        this.email = other.email;
    }

    // Getters

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    // Setters with Validation

    public void setName(String name) throws Exception {

        if(name.isEmpty())
            throw new Exception("Name cannot be empty");

        this.name = name;
    }

    public void setPhone(String phone) throws Exception {

        if(!phone.matches("\\d{10}"))
            throw new Exception("Phone must contain exactly 10 digits");

        this.phone = phone;
    }

    public void setEmail(String email) throws Exception {

        if(!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"))
            throw new Exception("Invalid Email");

        this.email = email;
    }

    @Override
    public String toString() {

        return "\n----- Contact Details -----" +
                "\nName   : " + name +
                "\nPhone  : " + phone +
                "\nEmail  : " + email;
    }

}

//====================== Memento ======================

class ContactMemento {

    private Contact contact;

    public ContactMemento(Contact contact) {

        // Defensive Copy
        this.contact = new Contact(contact);

    }

    public Contact getSavedState() {

        return new Contact(contact);

    }

}

//====================== CareTaker ======================

class CareTaker {

    private ContactMemento memento;

    public void save(Contact contact) {

        memento = new ContactMemento(contact);

    }

    public Contact undo() {

        return memento.getSavedState();

    }

}

//====================== Command Interface ======================

interface Command {

    Contact execute() throws Exception;

}

//====================== Edit Command ======================

class EditContactCommand implements Command {

    private Contact contact;
    private String name;
    private String phone;
    private String email;

    public EditContactCommand(Contact contact,
                              String name,
                              String phone,
                              String email) {

        this.contact = contact;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    @Override
    public Contact execute() throws Exception {

        contact.setName(name);
        contact.setPhone(phone);
        contact.setEmail(email);

        return contact;

    }

}

//====================== Main ======================

public class EditContact {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Contact contact = new Contact(
                "Navya",
                "9876543210",
                "navya@gmail.com");

        CareTaker careTaker = new CareTaker();

        while(true){

            System.out.println("\n===== CONTACT MENU =====");

            System.out.println("1. View Contact");

            System.out.println("2. Edit Contact");

            System.out.println("3. Undo");

            System.out.println("4. Exit");

            System.out.print("Enter Choice : ");

            int choice = sc.nextInt();

            sc.nextLine();

            try{

                switch(choice){

                    case 1:

                        System.out.println(contact);

                        break;

                    case 2:

                        // Save previous state
                        careTaker.save(contact);

                        System.out.print("Enter New Name : ");
                        String name = sc.nextLine();

                        System.out.print("Enter New Phone : ");
                        String phone = sc.nextLine();

                        System.out.print("Enter New Email : ");
                        String email = sc.nextLine();

                        Command command =
                                new EditContactCommand(contact,
                                        name,
                                        phone,
                                        email);

                        contact = command.execute();

                        System.out.println("\nContact Updated Successfully.");

                        break;

                    case 3:

                        contact = careTaker.undo();

                        System.out.println("\nUndo Successful.");

                        break;

                    case 4:

                        System.out.println("Thank You!");

                        System.exit(0);

                    default:

                        System.out.println("Invalid Choice");

                }

            }

            catch(Exception e){

                System.out.println("Error : " + e.getMessage());

            }

        }

    }

}