import java.util.*;

//==================== Predefined Tags ====================

enum DefaultTag {
    FAMILY,
    FRIEND,
    WORK
}

//==================== Tag Class ====================

class Tag {

    private String name;

    public Tag(String name) throws Exception {

        if (name == null || name.trim().isEmpty()) {
            throw new Exception("Tag name cannot be empty.");
        }

        this.name = name.toUpperCase();
    }

    public String getName() {
        return name;
    }

    // equals() and hashCode() for uniqueness

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (!(obj instanceof Tag))
            return false;

        Tag tag = (Tag) obj;

        return name.equals(tag.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name;
    }

}

//==================== Flyweight Factory ====================

class TagFactory {

    private static Map<String, Tag> tagPool = new HashMap<>();

    public static Tag getTag(String name) throws Exception {

        String key = name.toUpperCase();

        if (!tagPool.containsKey(key)) {

            tagPool.put(key, new Tag(key));

        }

        return tagPool.get(key);

    }

}

//==================== Contact ====================

class Contact {

    private String name;

    // Many-to-Many Relationship
    private Set<Tag> tags = new HashSet<>();

    public Contact(String name) {
        this.name = name;
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

//==================== Main ====================

public class ManageTags {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {

            Contact c1 = new Contact("Navya");
            Contact c2 = new Contact("Rahul");

            // EnumSet for predefined tags

            EnumSet<DefaultTag> defaultTags =
                    EnumSet.allOf(DefaultTag.class);

            System.out.println("Predefined Tags : " + defaultTags);

            while (true) {

                System.out.println("\n===== TAG MENU =====");

                System.out.println("1. Create Custom Tag");

                System.out.println("2. Assign Tag to Navya");

                System.out.println("3. Assign Tag to Rahul");

                System.out.println("4. View Contacts");

                System.out.println("5. Exit");

                System.out.print("Enter Choice : ");

                int choice = sc.nextInt();

                sc.nextLine();

                switch (choice) {

                    case 1:

                        System.out.print("Enter Tag Name : ");

                        String tagName = sc.nextLine();

                        TagFactory.getTag(tagName);

                        System.out.println("Tag Created Successfully.");

                        break;

                    case 2:

                        System.out.print("Enter Tag : ");

                        String t1 = sc.nextLine();

                        c1.addTag(TagFactory.getTag(t1));

                        System.out.println("Tag Assigned.");

                        break;

                    case 3:

                        System.out.print("Enter Tag : ");

                        String t2 = sc.nextLine();

                        c2.addTag(TagFactory.getTag(t2));

                        System.out.println("Tag Assigned.");

                        break;

                    case 4:

                        c1.display();

                        c2.display();

                        break;

                    case 5:

                        System.out.println("Thank You!");

                        System.exit(0);

                    default:

                        System.out.println("Invalid Choice");

                }

            }

        } catch (Exception e) {

            System.out.println(e.getMessage());

        }

    }

}