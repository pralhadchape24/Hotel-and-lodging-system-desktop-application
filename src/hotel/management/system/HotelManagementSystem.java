package hotel.management.system;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class HotelManagementSystem extends JFrame implements ActionListener {

    JLabel backgroundLabel, titleLabel;
    JButton nextButton;
    JPanel titlePanel;

    public HotelManagementSystem() {
        // Enable full-screen mode
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Get screen size dynamically
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        // Load and resize background image
        ImageIcon originalIcon = new ImageIcon(ClassLoader.getSystemResource("hotel/management/system/icons/logo.jpeg"));
        Image scaledImage = originalIcon.getImage().getScaledInstance(screenWidth, screenHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        // Background Label
        backgroundLabel = new JLabel(scaledIcon);
        backgroundLabel.setBounds(0, 0, screenWidth, screenHeight);
        add(backgroundLabel);

        // Title Panel (For White Background Highlight)
        titlePanel = new JPanel();
        titlePanel.setBounds((screenWidth - 800) / 2, 50, 800, 100); // Centered horizontally
        titlePanel.setBackground(Color.WHITE); // White background highlight
        titlePanel.setLayout(null);

        // Title Label (Centered inside the panel)
        titleLabel = new JLabel("HOTEL AND LODGING SYSTEM", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 50));
        titleLabel.setForeground(Color.RED);
        titleLabel.setBounds(0, 0, 800, 100); // Fill the panel
        titleLabel.setOpaque(true); // Ensure background is visible
        titleLabel.setBackground(Color.WHITE); // White background for label
        titlePanel.add(titleLabel);
        backgroundLabel.add(titlePanel);

        // Next Button (Centered at the Bottom)
        nextButton = new JButton("Next");
        nextButton.setFont(new Font("Arial", Font.BOLD, 20));
        nextButton.setBackground(Color.WHITE);
        nextButton.setForeground(Color.BLACK);
        nextButton.setBounds((screenWidth - 150) / 2, screenHeight - 120, 150, 50); // Centered horizontally
        backgroundLabel.add(nextButton);

        // Ensure button is visible & functional
        nextButton.addActionListener(this);

        // Make window visible
        setVisible(true);

        // Start Blinking Effect
        startBlinkingEffect();
    }

    // Blinking (Closing & Opening) Effect
    public void startBlinkingEffect() {
        new Thread(() -> {
            try {
                while (true) {
                    titlePanel.setVisible(false); // Hide text and background
                    Thread.sleep(500); // Wait 500ms
                    titlePanel.setVisible(true);  // Show text and background
                    Thread.sleep(500); // Wait 500ms
                }
            } catch (Exception ignored) {}
        }).start();
    }

    public void actionPerformed(ActionEvent ae) {
        new Login().setVisible(true);
        this.setVisible(false);
    }

    public static void main(String[] args) {
        new HotelManagementSystem();
    }
}
