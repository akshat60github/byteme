import java.util.ArrayList;
import java.util.Scanner;

public class Customer {
    private String name;
    private Cart cart;
    private ArrayList<Order> orderHistory;
    private String type;

    public Customer(String name) {
        this.name = name;
        this.type = type;
        this.cart = new Cart();  // Initialize an empty cart
        this.orderHistory = new ArrayList<>();
    }
    public void customerInterface(Scanner scanner, Admin admin) {
        while (true) {
            System.out.println("\nCustomer Interface: ");
            System.out.println("1) View Menu\n2) View Cart\n3) Checkout\n4) View Order History\n5) Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline

            if (choice == 1) {
                // Display the menu
                admin.viewMenu();  // Admin method to view menu
                System.out.println("Enter the item number to add to your cart or 'exit' to go back:");
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("exit")) {
                    continue;
                } else {
                    try {
                        int itemNumber = Integer.parseInt(input);
                        Class<? extends FoodItem[]> selectedItem = admin.getMenu().getItems().getClass();  // Get food item by number
                        cart.addItem(selectedItem);
                        System.out.println(selectedItem.getName() + " added to your cart.");
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid item number.");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Item not found.");
                    }
                }
            } else if (choice == 2) {
                cart.viewCart();
            } else if (choice == 3) {
                // Checkout (place order)
                Order order = cart.checkout(type);
                if (order != null) {
                    orderHistory.add(order);
                    System.out.println("Order placed successfully! Order ID: " + order.getOrderID());
                }
            } else if (choice == 4) {
                System.out.println("Order History:");
                for (Order order : orderHistory) {
                    System.out.println(order);
                }
            } else {
                break;  // Exit customer interface
            }
        }
    }
}