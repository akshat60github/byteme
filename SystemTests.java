import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class SystemTests {
    private Admin admin;
    private Customer customer;
    private Cart cart;
    @BeforeEach
    public void setUp() {
        admin = new Admin();
        customer = new Customer();
        cart = new Cart();
        admin.addFoodItem("Pizza", 200, "Main Course", 10);
        admin.addFoodItem("Burger", 100, "Snack", 0); // Out of stock item
    }
    @Test
    public void testOrderOutOfStockItem() {
        FoodItem burger = admin.getMenu().getItems().get(1);  // Out-of-stock burger
        boolean orderPlaced = customer.placeOrder(burger);
        assertFalse(orderPlaced, "Order should not be placed for out-of-stock items.");
    }

    @Test
    public void testInvalidLogin() {
        boolean isLoginValid = customer.login("wrongUser", "wrongPass");
        assertFalse(isLoginValid, "Login should fail with incorrect credentials.");
    }

    @Test
    public void testAddItemToCart() {
        FoodItem pizza = admin.getMenu().getItems().get(0);  // Pizza item

        // Add item to the cart
        cart.addItem(pizza);

        // Verify total price is updated correctly
        double expectedTotal = pizza.getPrice();
        assertEquals(expectedTotal, cart.calculateTotalPrice(), "Total price should match the item's price.");
    }
    @Test
    public void testModifyItemQuantityInCart() {
        FoodItem pizza = admin.getMenu().getItems().get(0);  // Pizza item
        cart.addItem(pizza);
        cart.modifyItemQuantity(pizza, 3);
        double expectedTotal = pizza.getPrice() * 3;
        assertEquals(expectedTotal, cart.calculateTotalPrice(), "Total price should be updated to reflect the new quantity.");
    }
    @Test
    public void testSetNegativeQuantityInCart() {
        FoodItem pizza = admin.getMenu().getItems().get(0);  // Pizza item
        cart.addItem(pizza);
        boolean quantityUpdated = cart.modifyItemQuantity(pizza, -1);
        assertFalse(quantityUpdated, "Quantity should not be updated to a negative value.");
        assertEquals(pizza.getPrice(), cart.calculateTotalPrice(), "Total price should remain unchanged.");
    }
}
