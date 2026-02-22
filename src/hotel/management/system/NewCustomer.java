package hotel.management.system;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class NewCustomer extends JFrame {
    Connection conn = null;
    private JPanel contentPane;
    private JTextField t1, t2, t3, t5, t6;
    private JComboBox<String> comboBox;
    private JRadioButton r1, r2;
    private Choice c1;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                NewCustomer frame = new NewCustomer();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public NewCustomer() throws SQLException {
        setBounds(530, 200, 850, 550);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Adding an image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("hotel/management/system/icons/fifth.png"));
        Image i3 = i1.getImage().getScaledInstance(300, 400, Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(i3);
        JLabel l1 = new JLabel(i2);
        l1.setBounds(480, 10, 300, 500);
        add(l1);

        JLabel lblTitle = new JLabel("NEW CUSTOMER FORM");
        lblTitle.setFont(new Font("Yu Mincho", Font.PLAIN, 20));
        lblTitle.setBounds(118, 11, 260, 53);
        contentPane.add(lblTitle);

        JLabel lblId = new JLabel("ID :");
        lblId.setBounds(35, 76, 200, 14);
        contentPane.add(lblId);

        comboBox = new JComboBox<>(new String[]{"Passport", "Aadhar Card", "Voter Id", "Driving License","College ID"});
        comboBox.setBounds(271, 73, 150, 20);
        contentPane.add(comboBox);

        JLabel lblNumber = new JLabel("Number :");
        lblNumber.setBounds(35, 111, 200, 14);
        contentPane.add(lblNumber);

        t1 = new JTextField();
        t1.setBounds(271, 111, 150, 20);
        contentPane.add(t1);
        t1.setColumns(10);

        // Restricting to 12 digits only (numbers only)
        t1.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) || t1.getText().length() >= 12) {
                    e.consume(); // Prevent non-digit input and limit to 12 characters
                    Toolkit.getDefaultToolkit().beep(); // Optional: Sound alert
                }
            }
        });

        JLabel lblName = new JLabel("Name :");
        lblName.setBounds(35, 151, 200, 14);
        contentPane.add(lblName);

        t2 = new JTextField();
        t2.setBounds(271, 151, 150, 20);
        contentPane.add(t2);
        t2.setColumns(10);

        JLabel lblGender = new JLabel("Gender :");
        lblGender.setBounds(35, 191, 200, 14);
        contentPane.add(lblGender);

        r1 = new JRadioButton("Male");
        r1.setBounds(271, 191, 80, 12);
        add(r1);

        r2 = new JRadioButton("Female");
        r2.setBounds(350, 191, 100, 12);
        add(r2);

        ButtonGroup bg = new ButtonGroup();
        bg.add(r1);
        bg.add(r2);

        JLabel lblCountry = new JLabel("City:");
        lblCountry.setBounds(35, 231, 200, 14);
        contentPane.add(lblCountry);

        t3 = new JTextField();
        t3.setBounds(271, 231, 150, 20);
        contentPane.add(t3);
        t3.setColumns(10);

        JLabel lblRoomNumber = new JLabel("Allocated Room Number :");
        lblRoomNumber.setBounds(35, 274, 200, 14);
        contentPane.add(lblRoomNumber);

        c1 = new Choice();
        try {
            conn c = new conn();
            ResultSet rs = c.s.executeQuery("SELECT * FROM room WHERE availability = 'Available'");
            while (rs.next()) {
                c1.add(rs.getString("roomnumber"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        c1.setBounds(271, 274, 150, 20);
        contentPane.add(c1);

        JLabel lblCheckIn = new JLabel("Checked-In :");
        lblCheckIn.setBounds(35, 316, 200, 14);
        contentPane.add(lblCheckIn);

        t5 = new JTextField();
        t5.setBounds(271, 316, 150, 20);
        contentPane.add(t5);
        t5.setColumns(10);

        JLabel lblDeposit = new JLabel("Deposit :");
        lblDeposit.setBounds(35, 359, 200, 14);
        contentPane.add(lblDeposit);

        t6 = new JTextField();
        t6.setBounds(271, 359, 150, 20);
        contentPane.add(t6);
        t6.setColumns(10);

        JButton btnAdd = new JButton("Add");
        btnAdd.setBounds(100, 430, 120, 30);
        btnAdd.setBackground(Color.BLACK);
        btnAdd.setForeground(Color.WHITE);
        contentPane.add(btnAdd);

        JButton btnBack = new JButton("Back");
        btnBack.setBounds(260, 430, 120, 30);
        btnBack.setBackground(Color.BLACK);
        btnBack.setForeground(Color.WHITE);
        contentPane.add(btnBack);

        btnAdd.addActionListener(e -> addCustomer());

        btnBack.addActionListener(e -> {
            new Reception().setVisible(true);
            setVisible(false);
        });

        getContentPane().setBackground(Color.WHITE);
    }

    private void addCustomer() {
        try {
            conn c = new conn();
            String radio = r1.isSelected() ? "Male" : (r2.isSelected() ? "Female" : "");

            String s1 = (String) comboBox.getSelectedItem();
            String s2 = t1.getText();
            String s3 = t2.getText();
            String s4 = radio;
            String s5 = t3.getText();
            String s6 = c1.getSelectedItem();
            String s7 = t5.getText();
            String s8 = t6.getText();

            if (s2.isEmpty() || s3.isEmpty() || s5.isEmpty() || s7.isEmpty() || s8.isEmpty() || s4.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (s2.length() != 12) {
                JOptionPane.showMessageDialog(null, "Number must be exactly 12 digits!", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String q1 = "INSERT INTO customer VALUES ('" + s1 + "','" + s2 + "','" + s3 + "','" + s4 + "','" + s5 + "','" + s6 + "','" + s7 + "','" + s8 + "')";
            String q2 = "UPDATE room SET availability = 'Occupied' WHERE roomnumber = '" + s6 + "'";

            c.s.executeUpdate(q1);
            c.s.executeUpdate(q2);

            SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(null, "Customer Added Successfully"));

            new Reception().setVisible(true);
            setVisible(false);
        } catch (SQLException e1) {
            JOptionPane.showMessageDialog(null, "Error: " + e1.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            e1.printStackTrace();
        }
    }
}
