import java.util.*;
import java.util.regex.*;

class CustomerVerificationService {
    private HashMap<String, String> customerDatabase = new HashMap<>();

    public boolean registerCustomer(String customerId, String password) {
        if (customerDatabase.containsKey(customerId)) {
            System.out.println("Customer ID already exists. Please log in.");
            return false;
        } else {
            if (isValidPassword(password)) {
                customerDatabase.put(customerId, password);
                System.out.println("Customer registered successfully.");
                return true;
            } else {
                System.out.println("Password is invalid. Please enter a password that contains:");
                System.out.println("- At least one uppercase letter");
                System.out.println("- At least one number");
                System.out.println("- At least one special character");
                return false;
            }
        }
    }

    public boolean verifyCustomer(String customerId, String password) {
        if (customerDatabase.containsKey(customerId)) {
            if (customerDatabase.get(customerId).equals(password)) {
                System.out.println("Customer verified successfully.");
                return true;
            } else {
                System.out.println("Incorrect password. Please try again.");
                return false;
            }
        } else {
            System.out.println("Customer ID not found. Please register first.");
            return false;
        }
    }

    private boolean isValidPassword(String password) {
        String regex = "^(?=.*[0-9])" + "(?=.*[a-z])" + "(?=.*[A-Z])" + "(?=.*[@#$%^&-+=()])" + "(?=\\S+$).{8,20}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        boolean isValid = matcher.matches();
        System.out.println("Password being validated: " + password);
        System.out.println("Is password valid: " + isValid);
    
    return isValid;
    }
}

