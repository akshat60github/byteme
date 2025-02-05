import java.util.ArrayList;
public class Cart {
    private ArrayList<FoodItem> items;
    public Cart() {
        items = new ArrayList<>();
    }
    public void addItem(FoodItem item) {
        items.add(item);
    }
    public void viewCart() {
        System.out.println("Your Cart:");
        for (FoodItem item : items) {
            System.out.println(item);
        }
    }

    public Order checkout(String customerType) {
        if (items.isEmpty()) {
            System.out.println("Your cart is empty! Add items to your cart before checking out.");
            return null;
        }
        Order newOrder = new Order(customerType);
        for (FoodItem item : items) {
            newOrder.addItem(item, 1);
        }
        items.clear();
        return newOrder;
    }

    public ArrayList<FoodItem> addItem(Class<? extends FoodItem[]> selectedItem) {
        return items;
    }
}