import java.util.Base64;
import java.util.Scanner;

//================== User Class ==================
class User {

    private String name;
    private String email;
    private String password;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void display() {
        System.out.println("\n----- User Details -----");
        System.out.println("Name      : " + name);
        System.out.println("Email     : " + email);
        System.out.println("Password  : " + password);
    }
}

//================== Free User ==================
class FreeUser extends User {

    public FreeUser(String name, String email, String password) {
        super(name, email, password);
    }
}

//================== Premium User ==================
class PremiumUser extends User {

    public PremiumUser(String name, String email, String password) {
        super(name, email, password);
    }
}

//================== Builder Pattern ==================
class UserBuilder {

    private String name;
    private String email;
    private String password;

    public UserBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public UserBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public User build() {
        return new User(name, email, password);
    }
}

//================== Factory Pattern ==================
class UserFactory {

    public static User createUser(String type, String name, String email, String password) {

        UserBuilder builder = new UserBuilder()
                .setName(name)
                .setEmail(email)
                .setPassword(password);

        User temp = builder.build();

        if (type.equalsIgnoreCase("Premium")) {
            return new PremiumUser(temp.getName(), temp.getEmail(), temp.getPassword());
        } else {
            return new FreeUser(temp.getName(), temp.getEmail(), temp.getPassword());
        }
    }
}

//================== Validation ==================
class Validator {

    public static boolean validateEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }
}

//================== Password Utility ==================
class PasswordUtil {

    public static String hashPassword(String password) {

        return Base64.getEncoder().encodeToString(password.getBytes());

    }
}

//================== Main Class ==================
public class UserRegistration {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {

            System.out.println("===== User Registration =====");

            System.out.print("Enter Name : ");
            String name = sc.nextLine();

            System.out.print("Enter Email : ");
            String email = sc.nextLine();

            if (!Validator.validateEmail(email)) {
                throw new Exception("Invalid Email Address!");
            }

            System.out.print("Enter Password : ");
            String password = sc.nextLine();

            // Password Hashing
            password = PasswordUtil.hashPassword(password);

            System.out.print("Enter User Type (Free/Premium) : ");
            String type = sc.nextLine();

            User user = UserFactory.createUser(type, name, email, password);

            System.out.println("\nRegistration Successful!");

            user.display();

        } catch (Exception e) {

            System.out.println("Error : " + e.getMessage());

        }

        sc.close();
    }
}