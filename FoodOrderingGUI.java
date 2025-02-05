import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Vector;
public class FoodOrderingGUI extends JFrame {
    private Admin admin;
    private JTabbedPane tabbedPane;
    private JTable menuTable;
    private JTable ordersTable;
    private JTextField menuSearchField;
    private JTextField orderSearchField;
    private TableRowSorter<DefaultTableModel> menuSorter;
    private TableRowSorter<DefaultTableModel> orderSorter;

    public FoodOrderingGUI(Admin admin) {
        this.admin = admin;
        initComponents();
    }

    private void initComponents() {
        // Frame setup
        setTitle("Byte Me! Food Ordering System");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(900, 700));
        getContentPane().setBackground(new Color(240, 248, 255));
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(240, 248, 255));
        tabbedPane = new JTabbedPane();
        tabbedPane.setBackground(new Color(230, 240, 250)); // Slightly darker blue for tabs
        JPanel menuPanel = createMenuPanel();
        tabbedPane.addTab("Canteen Menu", null, menuPanel, "Browse Menu Items");

        // Orders Tab
        JPanel ordersPanel = createOrdersPanel();
        tabbedPane.addTab("Pending Orders", null, ordersPanel, "View Current Orders");

        // Navigation Buttons
        JPanel navigationPanel = createNavigationPanel();

        // Add components to main panel
        mainPanel.add(tabbedPane, BorderLayout.CENTER);
        mainPanel.add(navigationPanel, BorderLayout.SOUTH);

        // Add main panel to frame
        add(mainPanel);

        // Pack and center
        pack();
        setLocationRelativeTo(null);
    }

    private JPanel createMenuPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(240, 248, 255));

        // Column names
        Vector<String> columnNames = new Vector<>();
        columnNames.add("Name");
        columnNames.add("Price (₹)");
        columnNames.add("Category");
        columnNames.add("Availability");

        // Table data
        Vector<Vector<Object>> data = new Vector<>();
        for (FoodItem item : admin.getMenu().getAllItems()) {
            Vector<Object> row = new Vector<>();
            row.add(item.getName());
            row.add(item.getPrice());
            row.add(item.getCategory());
            row.add(item.getAvailability());
            data.add(row);
        }

        // Create table model
        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table non-editable
            }
        };
        menuTable = new JTable(model);
        menuTable.setBackground(Color.WHITE);
        menuTable.setSelectionBackground(new Color(200, 220, 255)); // Light blue selection

        // Add row sorter
        menuSorter = new TableRowSorter<>(model);
        menuTable.setRowSorter(menuSorter);

        // Search field for menu
        menuSearchField = new JTextField(20);
        menuSearchField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = menuSearchField.getText().trim();
                if (text.length() == 0) {
                    menuSorter.setRowFilter(null);
                } else {
                    menuSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }
        });

        // Reset search button
        JButton resetMenuSearchButton = new JButton("Reset Search");
        resetMenuSearchButton.setBackground(new Color(220, 230, 255));
        resetMenuSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuSearchField.setText("");
                menuSorter.setRowFilter(null);
            }
        });

        // Search panel
        JPanel searchPanel = new JPanel(new FlowLayout());
        searchPanel.setBackground(new Color(240, 248, 255));
        searchPanel.add(new JLabel("Search Menu: "));
        searchPanel.add(menuSearchField);
        searchPanel.add(resetMenuSearchButton);

        // Add components
        JScrollPane scrollPane = new JScrollPane(menuTable);
        scrollPane.getViewport().setBackground(Color.WHITE);
        panel.add(searchPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createOrdersPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(240, 248, 255));

        // Column names
        Vector<String> columnNames = new Vector<>();
        columnNames.add("Order ID");
        columnNames.add("Status");
        columnNames.add("Special Request");
        columnNames.add("Items");

        // Table data
        Vector<Vector<Object>> data = new Vector<>();
        for (Order order : admin.getPendingOrders()) {
            Vector<Object> row = new Vector<>();
            row.add(order.getOrderID());
            row.add(order.getStatus());
            row.add(order.getSpecialRequest());
            // Construct items string
            StringBuilder itemsBuilder = new StringBuilder();
            for (Map.Entry<FoodItem, Integer> entry : order.getItems().entrySet()) {
                itemsBuilder.append(entry.getKey().getName())
                        .append(" x ")
                        .append(entry.getValue())
                        .append(", ");
            }
            String items = itemsBuilder.length() > 0
                    ? itemsBuilder.substring(0, itemsBuilder.length() - 2)
                    : "No items";
            row.add(items);

            data.add(row);
        }

        // Create table model
        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table non-editable
            }
        };
        ordersTable = new JTable(model);
        ordersTable.setBackground(Color.WHITE);
        ordersTable.setSelectionBackground(new Color(200, 220, 255)); // Light blue selection

        // Add row sorter
        orderSorter = new TableRowSorter<>(model);
        ordersTable.setRowSorter(orderSorter);

        // Search field for orders
        orderSearchField = new JTextField(20);
        orderSearchField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = orderSearchField.getText().trim();
                if (text.length() == 0) {
                    orderSorter.setRowFilter(null);
                } else {
                    orderSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }
        });

        // Reset search button
        JButton resetOrderSearchButton = new JButton("Reset Search");
        resetOrderSearchButton.setBackground(new Color(220, 230, 255));
        resetOrderSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                orderSearchField.setText("");
                orderSorter.setRowFilter(null);
            }
        });

        // Search panel
        JPanel searchPanel = new JPanel(new FlowLayout());
        searchPanel.setBackground(new Color(240, 248, 255));
        searchPanel.add(new JLabel("Search Orders: "));
        searchPanel.add(orderSearchField);
        searchPanel.add(resetOrderSearchButton);

        // Add components
        JScrollPane scrollPane = new JScrollPane(ordersTable);
        scrollPane.getViewport().setBackground(Color.WHITE);
        panel.add(searchPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createNavigationPanel() {
        JPanel navigationPanel = new JPanel(new FlowLayout());
        navigationPanel.setBackground(new Color(230, 240, 250)); // Light blue background

        // Previous Tab Button
        JButton previousButton = new JButton("← Previous");
        previousButton.setBackground(new Color(220, 230, 255));
        previousButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int currentIndex = tabbedPane.getSelectedIndex();
                if (currentIndex > 0) {
                    tabbedPane.setSelectedIndex(currentIndex - 1);
                }
            }
        });

        // Next Tab Button
        JButton nextButton = new JButton("Next →");
        nextButton.setBackground(new Color(220, 230, 255));
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int currentIndex = tabbedPane.getSelectedIndex();
                if (currentIndex < tabbedPane.getTabCount() - 1) {
                    tabbedPane.setSelectedIndex(currentIndex + 1);
                }
            }
        });

        // Refresh Button
        JButton refreshButton = new JButton("Refresh Data");
        refreshButton.setBackground(new Color(220, 230, 255));
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshData();
            }
        });

        navigationPanel.add(previousButton);
        navigationPanel.add(refreshButton);
        navigationPanel.add(nextButton);

        return navigationPanel;
    }

    // Method to refresh data
    public void refreshData() {
        // Recreate table models
        Vector<String> menuColumnNames = new Vector<>();
        menuColumnNames.addAll(java.util.Arrays.asList("Name", "Price (₹)", "Category", "Availability"));

        Vector<Vector<Object>> menuData = new Vector<>();
        for (FoodItem item : admin.getMenu().getAllItems()) {
            Vector<Object> row = new Vector<>();
            row.add(item.getName());
            row.add(item.getPrice());
            row.add(item.getCategory());
            row.add(item.getAvailability());
            menuData.add(row);
        }

        Vector<String> orderColumnNames = new Vector<>();
        orderColumnNames.addAll(java.util.Arrays.asList("Order ID", "Status", "Special Request", "Items"));

        Vector<Vector<Object>> orderData = new Vector<>();
        for (Order order : admin.getPendingOrders()) {
            Vector<Object> row = new Vector<>();
            row.add(order.getOrderID());
            row.add(order.getStatus());
            row.add(order.getSpecialRequest());
            StringBuilder itemsBuilder = new StringBuilder();
            for (Map.Entry<FoodItem, Integer> entry : order.getItems().entrySet()) {
                itemsBuilder.append(entry.getKey().getName())
                        .append(" x ")
                        .append(entry.getValue())
                        .append(", ");
            }
            String items = itemsBuilder.length() > 0
                    ? itemsBuilder.substring(0, itemsBuilder.length() - 2)
                    : "No items";
            row.add(items);

            orderData.add(row);
        }
        menuSearchField.setText("");
        orderSearchField.setText("");
        DefaultTableModel menuModel = (DefaultTableModel) menuTable.getModel();
        menuModel.setDataVector(menuData, menuColumnNames);
        DefaultTableModel orderModel = (DefaultTableModel) ordersTable.getModel();
        orderModel.setDataVector(orderData, orderColumnNames);
    }
}