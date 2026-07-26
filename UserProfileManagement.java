 import java.security.MessageDigest;
import java.util.Scanner;

//=================== User Class ===================

class User {

    private String name;
    private String email;
    private String password;
    private String preference;

    public User(String name, String email, String password, String preference) {
        this.name = name;
        this.email = email;
        this.password = hashPassword(password);
        this.preference = preference;
    }

    // JavaBeans Getters

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPreference() {
        return preference;
    }

    // Validation inside setters

    public void setName(String name) throws Exception {

        if(name.isEmpty()) {
            throw new Exception("Name cannot be empty");
        }

        this.name = name;
    }

    public void setEmail(String email) throws Exception {

        if(!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new Exception("Invalid Email");
        }

        this.email = email;
    }

    public void setPassword(String password) throws Exception {

        if(password.length()<5) {
            throw new Exception("Password must contain at least 5 characters");
        }

        this.password = hashPassword(password);
    }

    public void setPreference(String preference) {
        this.preference = preference;
    }

    // Password Hashing

    private String hashPassword(String password) {

        try {

            MessageDigest md = MessageDigest.getInstance("SHA-256");

            byte[] hash = md.digest(password.getBytes());

            StringBuilder sb = new StringBuilder();

            for(byte b : hash) {

                sb.append(String.format("%02x", b));

            }

            return sb.toString();

        }

        catch(Exception e) {

            return "";

        }

    }

    public void displayProfile() {

        System.out.println("\n------ User Profile ------");

        System.out.println("Name       : " + name);

        System.out.println("Email      : " + email);

        System.out.println("Preference : " + preference);

    }

}

//=================== Command Interface ===================

interface Command {

    void execute() throws Exception;

}

//=================== Update Profile Command ===================

class UpdateProfileCommand implements Command {

    private User user;
    private String name;
    private String email;

    public UpdateProfileCommand(User user,String name,String email){

        this.user=user;
        this.name=name;
        this.email=email;

    }

    public void execute() throws Exception {

        user.setName(name);

        user.setEmail(email);

        System.out.println("\nProfile Updated Successfully.");

    }

}

//=================== Change Password Command ===================

class ChangePasswordCommand implements Command{

    private User user;
    private String password;

    public ChangePasswordCommand(User user,String password){

        this.user=user;
        this.password=password;

    }

    public void execute() throws Exception{

        user.setPassword(password);

        System.out.println("\nPassword Changed Successfully.");

    }

}

//=================== Preference Command ===================

class UpdatePreferenceCommand implements Command{

    private User user;
    private String preference;

    public UpdatePreferenceCommand(User user,String preference){

        this.user=user;
        this.preference=preference;

    }

    public void execute(){

        user.setPreference(preference);

        System.out.println("\nPreference Updated Successfully.");

    }

}

//=================== Main ===================

public class UserProfileManagement {

    public static void main(String[] args) {

        Scanner sc=new Scanner(System.in);

        // Logged-in User

        User user=new User(
                "Navya",
                "navya@gmail.com",
                "12345",
                "Dark Theme");

        while(true){

            System.out.println("\n===== USER PROFILE =====");

            System.out.println("1.View Profile");

            System.out.println("2.Update Profile");

            System.out.println("3.Change Password");

            System.out.println("4.Update Preference");

            System.out.println("5.Exit");

            System.out.print("Enter Choice : ");

            int choice=sc.nextInt();

            sc.nextLine();

            try{

                switch(choice){

                    case 1:

                        user.displayProfile();

                        break;

                    case 2:

                        System.out.print("Enter New Name : ");

                        String name=sc.nextLine();

                        System.out.print("Enter New Email : ");

                        String email=sc.nextLine();

                        Command updateProfile=
                                new UpdateProfileCommand(user,name,email);

                        updateProfile.execute();

                        break;

                    case 3:

                        System.out.print("Enter New Password : ");

                        String password=sc.nextLine();

                        Command changePassword=
                                new ChangePasswordCommand(user,password);

                        changePassword.execute();

                        break;

                    case 4:

                        System.out.print("Enter Preference : ");

                        String preference=sc.nextLine();

                        Command updatePreference=
                                new UpdatePreferenceCommand(user,preference);

                        updatePreference.execute();

                        break;

                    case 5:

                        System.out.println("Thank You");

                        System.exit(0);

                    default:

                        System.out.println("Invalid Choice");

                }

            }

            catch(Exception e){

                System.out.println("Error : "+e.getMessage());

            }

        }

    }

}