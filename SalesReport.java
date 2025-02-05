import java.util.ArrayList;
import java.util.Map;

public class SalesReport {
    public static void generateDailyReport(ArrayList<Order> orders) {
        double totalSales = 0;
        System.out.println("Today's generated sales report:");
        for (Order order : orders) {
            double orderTotal = 0;
            System.out.println("\nOrder ID: " + order.getOrderID() + " | Status: " + order.getStatus() +
                    " | Special Request: " + order.getSpecialRequest());
            for (Map.Entry<FoodItem, Integer> entry : order.getItems().entrySet()) {
                FoodItem item = entry.getKey();
                int quantity = entry.getValue();
                double itemTotal = item.getPrice() * quantity;
                orderTotal += itemTotal;
                System.out.println(item.getName() + " x " + quantity + " - ₹" + itemTotal);
            }
            System.out.println("Order Total: ₹" + orderTotal);
            totalSales += orderTotal;
        }
        System.out.println("\nTotal sales today(Daily sales report): ₹" + totalSales);
    }
}