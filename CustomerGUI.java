import javax.swing.*;
import java.awt.*;
public class CustomerGUI {
    public static void launchGUI() {
        JFrame frame = new JFrame("Byte Me! - Menu");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JLabel title = new JLabel("Byte Me! Menu");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(Color.BLUE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(title);
        JButton viewMenuButton = new JButton("View Menu");
        JButton placeOrderButton = new JButton("Place Order");
        JButton exitButton = new JButton("Exit");
        viewMenuButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        placeOrderButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewMenuButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Menu functionality coming soon!"));
        placeOrderButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Order placement functionality coming soon!"));
        exitButton.addActionListener(e -> System.exit(0));
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(viewMenuButton);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(placeOrderButton);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(exitButton);
        frame.add(panel);
        frame.setVisible(true);
    }
}