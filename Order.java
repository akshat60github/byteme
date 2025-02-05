import java.util.HashMap;
import java.util.Map;

public class Order {
    private static int idCounter = 1;
    private int orderID;
    private String customerType;
    private Map<FoodItem, Integer> items;
    private String status;

    public Order(String customerType) {
        this.orderID = idCounter++;
        this.customerType = customerType;
        this.items = new HashMap<>();
        this.status = "Pending";  // Default status
    }

    public void addItem(FoodItem item, int quantity) {
        items.put(item, quantity);
    }

    public int getOrderID() {
        return orderID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Map<FoodItem, Integer> getItems() {
        return items;
    }

    @Override
    public String toString() {
        StringBuilder orderDetails = new StringBuilder();
        orderDetails.append("Order ID: ").append(orderID).append("\n");
        for (Map.Entry<FoodItem, Integer> entry : items.entrySet()) {
            FoodItem item = entry.getKey();
            int quantity = entry.getValue();
            orderDetails.append(item.getName()).append(" x ").append(quantity).append("\n");
        }
        orderDetails.append("Status: ").append(status).append("\n");
        return orderDetails.toString();
    }
    public Object getSpecialRequest() {
        return "Noted!";
    }
}