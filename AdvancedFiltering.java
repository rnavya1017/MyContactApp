import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

//==================== Contact ====================

class Contact {

    private String name;
    private String tag;
    private LocalDate dateAdded;
    private int contactCount;

    public Contact(String name, String tag,
                   LocalDate dateAdded, int contactCount) {

        this.name = name;
        this.tag = tag;
        this.dateAdded = dateAdded;
        this.contactCount = contactCount;
    }

    public String getName() {
        return name;
    }

    public String getTag() {
        return tag;
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public int getContactCount() {
        return contactCount;
    }

    @Override
    public String toString() {

        return "\nName : " + name +
                "\nTag : " + tag +
                "\nDate Added : " + dateAdded +
                "\nFrequently Contacted : " + contactCount + " times";
    }

}

//==================== Filter Interface ====================

interface ContactFilter {

    Predicate<Contact> apply();

}

//==================== Tag Filter ====================

class TagFilter implements ContactFilter {

    private String tag;

    public TagFilter(String tag) {
        this.tag = tag;
    }

    @Override
    public Predicate<Contact> apply() {

        return contact ->
                contact.getTag().equalsIgnoreCase(tag);

    }

}

//==================== Date Filter ====================

class DateFilter implements ContactFilter {

    private LocalDate date;

    public DateFilter(LocalDate date) {
        this.date = date;
    }

    @Override
    public Predicate<Contact> apply() {

        return contact ->
                contact.getDateAdded().isEqual(date);

    }

}

//==================== Frequently Contacted Filter ====================

class FrequentFilter implements ContactFilter {

    private int minimumCount;

    public FrequentFilter(int minimumCount) {
        this.minimumCount = minimumCount;
    }

    @Override
    public Predicate<Contact> apply() {

        return contact ->
                contact.getContactCount() >= minimumCount;

    }

}

//==================== Composite Pattern ====================

class CompositeFilter implements ContactFilter {

    private List<ContactFilter> filters = new ArrayList<>();

    public void addFilter(ContactFilter filter) {
        filters.add(filter);
    }

    @Override
    public Predicate<Contact> apply() {

        Predicate<Contact> result = contact -> true;

        for (ContactFilter filter : filters) {

            result = result.and(filter.apply());

        }

        return result;

    }

}

//==================== Strategy Pattern ====================

interface FilterStrategy {

    List<Contact> filter(List<Contact> contacts,
                         ContactFilter filter);

}

//==================== Concrete Strategy ====================

class StreamFilterStrategy implements FilterStrategy {

    @Override
    public List<Contact> filter(List<Contact> contacts,
                                ContactFilter filter) {

        return contacts.stream()

                .filter(filter.apply())

                // Comparator Sorting

                .sorted(Comparator
                        .comparing(Contact::getName)
                        .thenComparing(Contact::getContactCount)
                        .reversed())

                .collect(Collectors.toList());

    }

}

//==================== Main ====================

public class AdvancedFiltering {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        List<Contact> contacts = new ArrayList<>();

        contacts.add(new Contact(
                "Navya",
                "Friend",
                LocalDate.of(2024, 1, 10),
                25));

        contacts.add(new Contact(
                "Rahul",
                "Office",
                LocalDate.of(2024, 3, 15),
                10));

        contacts.add(new Contact(
                "Anjali",
                "Family",
                LocalDate.of(2024, 1, 10),
                30));

        contacts.add(new Contact(
                "Kiran",
                "Friend",
                LocalDate.of(2024, 5, 5),
                15));

        CompositeFilter composite = new CompositeFilter();

        System.out.println("===== ADVANCED FILTER =====");

        System.out.print("Enter Tag : ");
        String tag = sc.nextLine();

        composite.addFilter(new TagFilter(tag));

        System.out.print("Enter Date (yyyy-mm-dd) : ");
        LocalDate date = LocalDate.parse(sc.nextLine());

        composite.addFilter(new DateFilter(date));

        System.out.print("Minimum Contact Count : ");
        int count = sc.nextInt();

        composite.addFilter(new FrequentFilter(count));

        FilterStrategy strategy = new StreamFilterStrategy();

        List<Contact> result =
                strategy.filter(contacts, composite);

        System.out.println("\nFiltered Contacts");

        if (result.isEmpty()) {

            System.out.println("No Contact Found");

        } else {

            result.forEach(System.out::println);

        }

    }

}