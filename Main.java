import java.util.Scanner;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Initialize Scanner and Admin
        Scanner scanner = new Scanner(System.in);
        Admin admin = new Admin();

        // Pre-populate menu with food items
        admin.addFoodItem("french fries", 40, "snacks", 90);
        admin.addFoodItem("chilli potato", 55, "snacks", 58);
        admin.addFoodItem("chili chicken", 130, "meal", 30);
        admin.addFoodItem("veg burger", 50, "meal", 24);
        admin.addFoodItem("red sauce pasta", 75, "meal", 300);
        admin.addFoodItem("plain maggi", 25, "snacks", 62);

        // Ask the user which interface they want to use
        int choice = JOptionPane.showOptionDialog(
                null,
                "Choose Interface",
                "helloo welcome to Byte Me!!! Your personal khana mangwana/Ordering System!",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new String[]{"CLI", "GUI"},
                "CLI"
        );

        if (choice == 0) {
            // This is for the CLI
            System.out.println("Helloo!!!! this is Byte Me! The great food ordering system, Have fun eating my g.");
            while (true) {
                // Display CLI options
                System.out.println("Userss:\n1) Admin\n2) Customer\n3) VIP Customer\n4) Exit");
                int userChoice = getValidInt(scanner);

                // Handle user input
                if (userChoice == 1) {
                    // Admin functionality
                    admin.adminInterface(scanner);
                } else if (userChoice == 2) {
                    // Regular customer functionality
                    Customer customer = new Customer("Regular");
                    customer.customerInterface(scanner, admin);
                } else if (userChoice == 3) {
                    // VIP customer functionality
                    Customer vipCustomer = new Customer("VIP");
                    vipCustomer.customerInterface(scanner, admin);
                } else if (userChoice == 4) {
                    // Exit the program
                    System.out.println("Exiting... Goodbye!");
                    break;
                } else {
                    System.out.println("Invalid choice. Please try again.");
                }
            }
            scanner.close(); // Close the Scanner when CLI is exited
        } else if (choice == 1) {
            // This is for the GUI
            SwingUtilities.invokeLater(() -> {
                try {
                    // Set the GUI look and feel
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                // Launch the GUI
                FoodOrderingGUI gui = new FoodOrderingGUI(admin);
                gui.setVisible(true);
            });
        }
    }

    // Helper method to validate integer input
    private static int getValidInt(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter an integer.");
            scanner.next();
        }
        return scanner.nextInt();
    }
}