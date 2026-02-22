package hotel.management.system;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import net.proteanit.sql.DbUtils;

public class SearchRoom extends JFrame {
    private JPanel contentPane;
    private JTable table;
    private JComboBox<String> c1; // Changed from Choice to JComboBox
    private JCheckBox checkRoom;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                SearchRoom frame = new SearchRoom();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public SearchRoom() throws SQLException {
        setTitle("Search Room");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(530, 200, 700, 500);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.setBackground(Color.WHITE);

        JLabel lblSearchForRoom = new JLabel("Search For Room");
        lblSearchForRoom.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblSearchForRoom.setBounds(250, 10, 200, 30);
        contentPane.add(lblSearchForRoom);

        JLabel lblRoomBedType = new JLabel("Room Bed Type:");
        lblRoomBedType.setBounds(50, 73, 100, 14);
        contentPane.add(lblRoomBedType);

        String[] bedTypes = {"Single Bed", "Double Bed"};
        c1 = new JComboBox<>(bedTypes);
        c1.setBounds(160, 70, 120, 22);
        c1.setBackground(Color.WHITE);
        contentPane.add(c1);

        checkRoom = new JCheckBox("Only display Available");
        checkRoom.setBounds(400, 69, 205, 23);
        checkRoom.setBackground(Color.WHITE);
        contentPane.add(checkRoom);

        // Table headers
        String[] columnNames = {"Room Number", "Availability", "Clean Status", "Price", "Bed Type"};

        JLabel lblRoomNumber = new JLabel("Room Number");
        lblRoomNumber.setBounds(20, 140, 100, 14);
        contentPane.add(lblRoomNumber);

        JLabel lblAvailability = new JLabel("Availability");
        lblAvailability.setBounds(150, 140, 100, 14);
        contentPane.add(lblAvailability);

        JLabel lblCleanStatus = new JLabel("Clean Status");
        lblCleanStatus.setBounds(280, 140, 100, 14);
        contentPane.add(lblCleanStatus);

        JLabel lblPrice = new JLabel("Price");
        lblPrice.setBounds(420, 140, 50, 14);
        contentPane.add(lblPrice);

        JLabel lblBedType = new JLabel("Bed Type");
        lblBedType.setBounds(540, 140, 100, 14);
        contentPane.add(lblBedType);

        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 170, 650, 200);
        contentPane.add(scrollPane);

        // Search Button
        JButton btnSearch = new JButton("Search");
        btnSearch.setBounds(200, 400, 120, 30);
        btnSearch.setBackground(Color.BLACK);
        btnSearch.setForeground(Color.WHITE);
        contentPane.add(btnSearch);

        btnSearch.addActionListener(e -> searchRooms());

        // Back Button
        JButton btnBack = new JButton("Back");
        btnBack.setBounds(380, 400, 120, 30);
        btnBack.setBackground(Color.BLACK);
        btnBack.setForeground(Color.WHITE);
        contentPane.add(btnBack);

        btnBack.addActionListener(e -> {
            new Reception().setVisible(true);
            dispose();
        });
    }

    private void searchRooms() {
        try {
            conn c = new conn();
            String selectedBedType = (String) c1.getSelectedItem();
            String sql;

            if (checkRoom.isSelected()) {
                sql = "SELECT * FROM room WHERE availability = 'Available' AND bed_type = ?";
            } else {
                sql = "SELECT * FROM room WHERE bed_type = ?";
            }

            PreparedStatement pst = c.c.prepareStatement(sql);
            pst.setString(1, selectedBedType);
            ResultSet rs = pst.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));

            rs.close();
            pst.close();
            c.s.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
