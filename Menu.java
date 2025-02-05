import java.util.Map;
import java.util.TreeMap;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
public class Menu {
    private Map<String, FoodItem> items;
    public Menu() {
        this.items = new TreeMap<>();
    }
    public void addItem(FoodItem item) {
        items.put(item.getName(), item);
    }
    public FoodItem getItem(String name) {
        return items.get(name);
    }
    public List<FoodItem> getAllItems() {
        return new ArrayList<>(items.values());
    }
    public void removeItem(String name) {
        items.remove(name);
    }
    public void manageMenu(Scanner scanner) {
        System.out.println("1) Add Item\n2) Update Item\n3) Remove Item");
        int choice = scanner.nextInt();
        scanner.nextLine();
        if (choice == 1) {
            System.out.print("Enter name, price, category, availability(int/num, amount of dishes of the same): ");
            addItem(new FoodItem(scanner.next(), scanner.nextDouble(), scanner.next(), scanner.nextInt()));
        } else if (choice == 2) {
            System.out.print("Enter item name to update: ");
            String name = scanner.next();
            FoodItem item = getItem(name);
            if (item != null) {
                System.out.print("Enter new availability: ");
                item.restock(scanner.nextInt());
            }
        } else if (choice == 3) {
            System.out.print("Enter item name to remove: ");
            items.remove(scanner.next());
        }
    }
    public void add(FoodItem foodItem) {
        items.put(foodItem.getName(), foodItem);
    }
    public FoodItem[] getItems() {
        return items.values().toArray(new FoodItem[0]);
    }
}