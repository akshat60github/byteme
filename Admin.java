import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
public class Admin {
    private Menu menu;
    private ArrayList<Order> orderQueue;
    public Admin() {
        this.menu = new Menu();
        this.orderQueue = new ArrayList<>();
    }
    public void addFoodItem(String name, int price, String category, int stock) {
        menu.add(new FoodItem(name, price, category, stock));
    }
    public void viewMenu() {
        System.out.println("Menu:");
        for (FoodItem item : menu.getItems()) {
            System.out.println(item);
        }
    }
    public void adminInterface(Scanner scanner) {
        while (true){
            System.out.println("\nAdmin Interface: ");
            System.out.println("1) Manage Menu\n2) View Pending Orders\n3) Update Order Status\n4) Generate Sales Report\n5) Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 1) {
                menu.manageMenu(scanner);
            } else if (choice == 2) {
                System.out.println("Pending Orders:");
                orderQueue.stream()
                        .filter(order -> !order.getStatus().equals("Delivered"))
                        .forEach(System.out::println);
            } else if (choice == 3) {
                System.out.print("Enter Order ID to update: ");
                int orderId = scanner.nextInt();
                scanner.nextLine();
                Order order = findOrderById(orderId);
                if (order != null) {
                    System.out.print("Enter new status (Preparing, Out for delivery, Delivered, Denied): ");
                    order.setStatus(scanner.nextLine());
                    System.out.println("Order status updated.");
                } else {
                    System.out.println("Order not found.");
                }
            } else if (choice == 4) {
                SalesReport.generateDailyReport(orderQueue);
            } else {
                break;
            }
        }
    }
    public List<Order> getPendingOrders() {
        List<Order> pendingOrders = new ArrayList<>();
        for (Order order : orderQueue) {
            if (!order.getStatus().equals("Delivered")) {
                pendingOrders.add(order);
            }
        }
        return pendingOrders;
    }
    public void addOrder(Order order) {
        orderQueue.add(order);
    }
    public void addFoodItem(String name, double price, String category, int availability) {
        menu.addItem(new FoodItem(name, price, category, availability));
    }
    public Menu getMenu() {
        return menu;
    }
    private Order findOrderById(int id) {
        return orderQueue.stream().filter(order -> order.getOrderID() == id).findFirst().orElse(null);
    }
    // for users, to save orderhistory.
    public static void saveOrderHistory(Order order, String username) {
        String filename = username + "_OrderHistory.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write("Order ID: " + order.getOrderID() + " | Status: " + order.getStatus() + "\n");
            for (Map.Entry<FoodItem, Integer> entry : order.getItems().entrySet()) {
                FoodItem item = entry.getKey();
                int quantity = entry.getValue();
                double total = item.getPrice() * quantity;
                writer.write(item.getName() + " x " + quantity + " - ₹" + total + "\n");
            }
            writer.write("\n");
        } catch (IOException e) {
            System.out.println("Error saving order history: " + e.getMessage());
        }
    }
    // method for retrieving userdata
    public static Map<String, String> readUserData(String filename) {
        Map<String, String> users = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    users.put(parts[0], parts[1]); // Username, Password
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading user data: " + e.getMessage());
        }
        return users;
    }
    public static void registerUser(String username, String password, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(username + "," + password + "\n");
        } catch (IOException e) {
            System.out.println("Error registering user: " + e.getMessage());
        }
    }
    private void updatePendingOrdersWithRemovedItem(FoodItem removedItem) {
        for (Order order : orderQueue) {
            if (!order.getStatus().equals("Delivered") && order.getItems().containsKey(removedItem)) {
                order.setStatus("Denied");
            }
        }
    }
}