package view;

import controller.PasswordController;
import model.Credential;

import java.util.List;
import java.util.Scanner;

public class PasswordUI {

    private PasswordController controller = new PasswordController();
    private Scanner scanner = new Scanner(System.in);

    public void start() {
        while (true) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    generatePassword();
                    break;
                case 2:
                    addCredential();
                    break;
                case 3:
                    viewCredentials();
                    break;
                case 4:
                    updateCredential();
                    break;
                case 5:
                    deleteCredential();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private void displayMenu() {
        System.out.println("\nPassword Manager Menu:");
        System.out.println("1. Generate Password");
        System.out.println("2. Add Credential");
        System.out.println("3. View Credentials");
        System.out.println("4. Update Credential");
        System.out.println("5. Delete Credential");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }

    private void generatePassword() {
        System.out.print("Enter password length: ");
        int len = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Use lowercase (y/n)? ");
        boolean lower = scanner.nextLine().equalsIgnoreCase("y");
        System.out.print("Use uppercase (y/n)? ");
        boolean upper = scanner.nextLine().equalsIgnoreCase("y");
        System.out.print("Use digits (y/n)? ");
        boolean digits = scanner.nextLine().equalsIgnoreCase("y");
        System.out.print("Use special characters (y/n)? ");
        boolean special = scanner.nextLine().equalsIgnoreCase("y");

        String password = controller.generatePassword(len, lower, upper, digits, special);
         if (password.isEmpty()) {
            System.out.println("Password generation failed (invalid options).");
        } else {
             System.out.println("Generated password: " + password);
            if(controller.isPasswordStrong(password)){
                System.out.println("Strong Password");
            }
            else{
                System.out.println("Weak Password");
            }
        }
    }

    private void addCredential() {
        System.out.print("Enter service name: ");
        String service = scanner.nextLine();
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (controller.addCredential(service, username, password)) {
            System.out.println("Credential added.");
        } else {
            System.out.println("Failed to add credential.");
        }
    }

    private void viewCredentials() {
        List<Credential> creds = controller.getCredentials();
        if (creds.isEmpty()) {
            System.out.println("No credentials stored.");
            return;
        }

        for (Credential c : creds) {
            System.out.println(c); 
             System.out.print("Show password (y/n)? ");
            if (scanner.nextLine().equalsIgnoreCase("y")) {
                System.out.println("Decrypted password: " + controller.getDecryptedPassword(c));
            }
        }
    }
    
     private void updateCredential()
    {
        System.out.print("Enter service name to update: ");
        String service = scanner.nextLine();
        System.out.print("Enter new username: ");
        String newUsername = scanner.nextLine();
        System.out.print("Enter new password: ");
        String newPassword = scanner.nextLine();
        if(controller.updateCredential(service, newUsername, newPassword)){
            System.out.println("Credential Updated");
        }
        else{
            System.out.println("Update Failed: Service name not found");
        }
    }
    
     private void deleteCredential()
     {
         System.out.print("Enter service name to delete: ");
        String service = scanner.nextLine();
        if(controller.deleteCredential(service)){
            System.out.println("Credential Deleted");
        }
        else{
            System.out.println("Delete Failed: Service name not found");
        }
     }
    

    public static void main(String[] args) {
        PasswordUI ui = new PasswordUI();
        ui.start();
    }
}