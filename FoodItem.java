import java.util.ArrayList;
import java.util.List;
public class FoodItem {
    private String name;
    private double price;
    private String category;
    private int availability; // Stock quantity for the item
    private List<String> reviews;
    public FoodItem(String name, double price, String category, int availability) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.availability = availability;
        this.reviews = new ArrayList<>();
    }
    public void addReview(String review) {
        reviews.add(review);
    }
    public boolean isAvailable() {
        return availability > 0;
    }
    public void reduceStock(int quantity) {
        availability = Math.max(0, availability - quantity);
    }
    public void restock(int quantity) {
        availability += quantity;
    }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getCategory() { return category; }
    public int getAvailability() { return availability; }
    @Override
    public String toString() {
        return name + " - ₹" + price + " - " + (availability > 0 ? "Available" : "Out of Stock");
    }
}