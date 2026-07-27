import java.util.*;

//==================== Observer ====================

interface Observer {

    void update(String message);

}

//==================== Concrete Observer ====================

class UIObserver implements Observer {

    @Override
    public void update(String message) {

        System.out.println("UI Updated : " + message);

    }

}

//==================== Tag ====================

class Tag {

    private String name;

    // Bidirectional Relationship
    private Set<Contact> contacts = new HashSet<>();

    public Tag(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public void removeContact(Contact contact) {
        contacts.remove(contact);
    }

    @Override
    public String toString() {
        return name;
    }

}

//==================== Contact ====================

class Contact {

    private String name;

    // Bidirectional Relationship
    private Set<Tag> tags = new HashSet<>();

    public Contact(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void addTag(Tag tag) {
        tags.add(tag);
    }

    public void removeTag(Tag tag) {
        tags.remove(tag);
    }

    public void display() {

        System.out.println("\nContact : " + name);

        System.out.println("Tags : " + tags);

    }

}

//==================== Association Class ====================

class ContactTag {

    private Contact contact;
    private Tag tag;

    public ContactTag(Contact contact, Tag tag) {

        this.contact = contact;
        this.tag = tag;

        // Maintain Bidirectional Relationship

        contact.addTag(tag);

        tag.addContact(contact);

    }

}

//==================== Tag Manager ====================

class TagManager {

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

//==================== Main ====================

public class ApplyTags {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Contact navya = new Contact("Navya");
        Contact rahul = new Contact("Rahul");

        Tag family = new Tag("Family");
        Tag work = new Tag("Work");
        Tag friends = new Tag("Friends");

        TagManager manager = new TagManager();

        manager.addObserver(new UIObserver());

        while (true) {

            System.out.println("\n===== APPLY TAGS =====");

            System.out.println("1. Apply Tag to Navya");

            System.out.println("2. Remove Tag from Navya");

            System.out.println("3. View Contacts");

            System.out.println("4. Exit");

            System.out.print("Enter Choice : ");

            int choice = sc.nextInt();

            sc.nextLine();

            switch (choice) {

                case 1:

                    System.out.println("Available Tags");

                    System.out.println("1. Family");

                    System.out.println("2. Work");

                    System.out.println("3. Friends");

                    System.out.print("Choose Tag : ");

                    int tagChoice = sc.nextInt();

                    Tag selectedTag = null;

                    if (tagChoice == 1)
                        selectedTag = family;

                    else if (tagChoice == 2)
                        selectedTag = work;

                    else
                        selectedTag = friends;

                    new ContactTag(navya, selectedTag);

                    manager.notifyObservers("Tag Applied Successfully");

                    break;

                case 2:

                    System.out.println("Enter Tag Name : ");

                    String tagName = sc.next();

                    for (Tag tag : navya.getTags()) {

                        if (tag.getName().equalsIgnoreCase(tagName)) {

                            navya.removeTag(tag);

                            tag.removeContact(navya);

                            manager.notifyObservers("Tag Removed");

                            break;

                        }

                    }

                    break;

                case 3:

                    navya.display();

                    rahul.display();

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
