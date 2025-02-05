Project Structure
the key classes are:

Main: The entry point of the application, managing the user interface.
Admin: Handles the admin functionalities, including managing the menu and processing orders.
Customer: Represents a customer and manages their interactions with the system.
FoodItem: Represents individual food items available for ordering.
Order: Represents an order placed by a customer.
Menu: Manages the list of food items.
Cart: Represents a shopping cart for customers to manage their selected items.
SalesReport: Generates daily sales reports based on the orders placed.



Flow of the Application:
Start Application: When the application starts, it initializes an Admin instance and adds some sample food items to the menu.

User Selection: Users can choose from the following options:

Admin
Customer
VIP Customer
Exit
Admin Interface: Admin users can manage the menu, view pending orders, update order statuses, and generate sales reports.

Customer Interface: Customers (both regular and VIP) can:

Browse the menu
Add items to their cart
View and modify their cart
Checkout and place their order
View their order history
Class Implementations
1. Main
Functionality: Initializes the application, adds sample food items, and presents the main menu for user selection.
Key Methods:
main(): The main method that runs the application loop.
2. Admin
Functionality: Manages the canteen's menu and processes orders.
Key Methods:
adminInterface(): Displays admin options and processes their choices.
addFoodItem(): Adds new food items to the menu.
addOrder(): Adds orders to the order queue.
3. Customer
Functionality: Represents customers and their interactions with the food ordering system.
Key Methods:
customerInterface(): Displays options for customers to browse the menu, add items to the cart, and place orders.
4. FoodItem
Functionality: Represents food items, including their properties and stock management.
Key Methods:
addReview(): Adds a review to the food item.
reduceStock(): Decreases the availability of the item based on orders.
5. Order
Functionality: Represents an order made by a customer, including its status and items.
Key Methods:
addItem(): Adds a food item to the order and reduces the item's stock.
6. Menu
Functionality: Manages a collection of food items.
Key Methods:
manageMenu(): Allows the admin to add, update, or remove food items.
displayMenu(): Shows the available food items to users.
7. Cart
Functionality: Manages the items selected by a customer before checkout.
Key Methods:
addItem(): Adds an item to the cart.
checkout(): Processes the checkout, creating an order from the items in the cart.
8. SalesReport
Functionality: Generates daily sales reports based on the orders.
Key Methods:
generateDailyReport(): Iterates through the order queue and prints details of each order.