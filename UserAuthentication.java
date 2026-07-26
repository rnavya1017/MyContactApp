import java.security.MessageDigest;
import java.util.Optional;
import java.util.Scanner;

//==================== User ====================

class User {

    private String email;
    private String password;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}

//================ Password Hashing =================

class PasswordUtil {

    public static String hashPassword(String password) {

        try {

            MessageDigest md = MessageDigest.getInstance("SHA-256");

            byte[] hash = md.digest(password.getBytes());

            StringBuilder sb = new StringBuilder();

            for (byte b : hash) {

                sb.append(String.format("%02x", b));

            }

            return sb.toString();

        } catch (Exception e) {

            return null;

        }

    }

}

//================ Authentication Interface =================

interface Authentication {

    Optional<User> login(String email, String password);

}

//================ Basic Authentication =================

class BasicAuth implements Authentication {

    private User storedUser;

    public BasicAuth(User user) {
        this.storedUser = user;
    }

    @Override
    public Optional<User> login(String email, String password) {

        String hashedPassword = PasswordUtil.hashPassword(password);

        if (storedUser.getEmail().equals(email)
                && storedUser.getPassword().equals(hashedPassword)) {

            return Optional.of(storedUser);

        }

        return Optional.empty();

    }

}

//================ OAuth Authentication =================

class OAuthAuth implements Authentication {

    @Override
    public Optional<User> login(String email, String password) {

        System.out.println("OAuth Login Successful");

        return Optional.of(new User(email, "OAuthUser"));

    }

}

//================ Singleton Session Manager =================

class SessionManager {

    private static SessionManager instance;

    private User loggedInUser;

    private SessionManager() {

    }

    public static SessionManager getInstance() {

        if (instance == null) {

            instance = new SessionManager();

        }

        return instance;

    }

    public void login(User user) {

        loggedInUser = user;

    }

    public void logout() {

        loggedInUser = null;

    }

    public User getLoggedInUser() {

        return loggedInUser;

    }

}

//================ Main =================

public class UserAuthentication {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Registered User
        String email = "navya@gmail.com";
        String password = PasswordUtil.hashPassword("12345");

        User registeredUser = new User(email, password);

        System.out.println("Choose Authentication");

        System.out.println("1. Basic Authentication");

        System.out.println("2. OAuth Authentication");

        int choice = sc.nextInt();

        sc.nextLine();

        Authentication auth;

        if (choice == 1) {

            auth = new BasicAuth(registeredUser);

        } else {

            auth = new OAuthAuth();

        }

        System.out.print("Enter Email : ");
        String inputEmail = sc.nextLine();

        System.out.print("Enter Password : ");
        String inputPassword = sc.nextLine();

        Optional<User> result = auth.login(inputEmail, inputPassword);

        if (result.isPresent()) {

            SessionManager session = SessionManager.getInstance();

            session.login(result.get());

            System.out.println("\nLogin Successful");

            System.out.println("Logged In User : "
                    + session.getLoggedInUser().getEmail());

        } else {

            System.out.println("\nInvalid Email or Password");

        }

        sc.close();

    }

}