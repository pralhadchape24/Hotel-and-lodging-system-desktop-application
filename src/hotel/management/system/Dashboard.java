package hotel.management.system;
import java.awt.*;
import javax.swing.*;
public class Dashboard extends JFrame {
private JLabel titleLabel;
private JPanel titlePanel;
public static void main(String[] args) {
 new Dashboard().setVisible(true);
    }
public Dashboard() {
        super("HOTEL AND LODGING SYSTEM");
  setLayout(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Open in full-screen mode
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Get Screen Size Dynamically
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        // Background Image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("hotel/management/system/icons/front1.png"));
        Image i2 = i1.getImage().getScaledInstance(screenWidth, screenHeight, Image.SCALE_SMOOTH);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel NewLabel = new JLabel(i3);
        NewLabel.setBounds(0, 0, screenWidth, screenHeight);
        add(NewLabel);
        // Title Panel (Black Background Highlight)
        int titleWidth = 800; // Increased width to fit full text
        int titleHeight = 85;
        titlePanel = new JPanel();
        titlePanel.setBounds((screenWidth - titleWidth) / 2, 60, titleWidth, titleHeight); // Centered horizontally
        titlePanel.setBackground(Color.BLACK);
        titlePanel.setLayout(null);
        // Title Label (White Text on Black Background)
        titleLabel = new JLabel("WELCOME TO SWAMI PALACE", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 46));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(0, 0, titleWidth, titleHeight);
        titleLabel.setOpaque(true);
        titleLabel.setBackground(Color.BLACK);
        titlePanel.add(titleLabel);
        NewLabel.add(titlePanel);

        // Menu Bar
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        // Hotel Management Menu
        JMenu hotelMenu = new JMenu("HOTEL RECEPTION");
        hotelMenu.setForeground(Color.BLUE);
        menuBar.add(hotelMenu);
        JMenuItem receptionMenu = new JMenuItem("RECEPTION");
        hotelMenu.add(receptionMenu);
        receptionMenu.addActionListener(e -> new Reception());
        // Admin Menu
        JMenu adminMenu = new JMenu("ADMIN");
        adminMenu.setForeground(Color.RED);
        menuBar.add(adminMenu);
        JMenuItem addEmployee = new JMenuItem("ADD EMPLOYEE");
        adminMenu.add(addEmployee);
        addEmployee.addActionListener(e -> {
            try {
                new AddEmployee().setVisible(true);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
        JMenuItem addRoom = new JMenuItem("ADD ROOMS");
        adminMenu.add(addRoom);
        addRoom.addActionListener(e -> {
            try {
                new AddRoom().setVisible(true);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
        JMenuItem addDrivers = new JMenuItem("ADD DRIVERS");
        adminMenu.add(addDrivers);
        addDrivers.addActionListener(e -> {
            try {
                new AddDrivers().setVisible(true);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
        getContentPane().setBackground(Color.WHITE);
    }
}
