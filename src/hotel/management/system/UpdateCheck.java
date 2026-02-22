package hotel.management.system;

import java.awt.*;
import java.sql.*;	
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UpdateCheck extends JFrame {
    Connection conn = null;
    private JPanel contentPane;
    private JTextField txt_ID, txt_Room, txt_Status, txt_Date, txt_Time, txt_Payment;
    Choice c1;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                UpdateCheck frame = new UpdateCheck();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public UpdateCheck() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 200, 950, 500);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblUpdateCheckStatus = new JLabel("Check-In Details");
        lblUpdateCheckStatus.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblUpdateCheckStatus.setBounds(124, 11, 222, 25);
        contentPane.add(lblUpdateCheckStatus);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("hotel/management/system/icons/nine.jpg"));
        JLabel l1 = new JLabel(i1);
        l1.setBounds(450, 70, 476, 270);
        add(l1);
        
        JLabel lblNewLabel = new JLabel("ID:");
        lblNewLabel.setBounds(25, 88, 46, 14);
        contentPane.add(lblNewLabel);
        
        c1 = new Choice();
        try {
            conn c = new conn();
            ResultSet rs = c.s.executeQuery("SELECT number FROM customer");
            while (rs.next()) {
                c1.add(rs.getString("number"));    
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        c1.setBounds(248, 85, 140, 20);
        contentPane.add(c1);

        JLabel lblNewLabel_1 = new JLabel("Room Number:");
        lblNewLabel_1.setBounds(25, 129, 107, 14);
        contentPane.add(lblNewLabel_1);

        txt_ID = new JTextField();
        txt_ID.setBounds(248, 126, 140, 20);
        contentPane.add(txt_ID);

        JLabel lblNewLabel_2 = new JLabel("Name:");
        lblNewLabel_2.setBounds(25, 174, 97, 14);
        contentPane.add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("Checked-in:");
        lblNewLabel_3.setBounds(25, 216, 107, 14);
        contentPane.add(lblNewLabel_3);

        JLabel lblNewLabel_4 = new JLabel("Amount Paid (Rs):");
        lblNewLabel_4.setBounds(25, 261, 107, 14);
        contentPane.add(lblNewLabel_4);

        JLabel lblNewLabel_5 = new JLabel("Pending Amount (Rs):");
        lblNewLabel_5.setBounds(25, 302, 150, 14);
        contentPane.add(lblNewLabel_5);

        txt_Status = new JTextField();
        txt_Status.setBounds(248, 171, 140, 20);
        contentPane.add(txt_Status);
        
        txt_Date = new JTextField();
        txt_Date.setBounds(248, 216, 140, 20);
        contentPane.add(txt_Date);
        
        txt_Time = new JTextField();
        txt_Time.setBounds(248, 258, 140, 20);
        contentPane.add(txt_Time);
        
        txt_Payment = new JTextField();
        txt_Payment.setBounds(248, 299, 140, 20);
        contentPane.add(txt_Payment);

        // Check Button
        JButton btnCheck = new JButton("Check");
        btnCheck.addActionListener(e -> {
            try {
                String s1 = c1.getSelectedItem();
                conn c = new conn();
                
                String query = "SELECT * FROM customer WHERE number = ?";
                PreparedStatement pst = c.c.prepareStatement(query);
                pst.setString(1, s1);
                ResultSet rs1 = pst.executeQuery();
                
                if (rs1.next()) {
                    txt_ID.setText(rs1.getString("room"));    
                    txt_Status.setText(rs1.getString("name"));    
                    txt_Date.setText(rs1.getString("checkintime"));    
                    txt_Time.setText(rs1.getString("deposit"));    
                } else {
                    JOptionPane.showMessageDialog(null, "No record found!");
                    return;
                }

                // Calculate Pending Amount
                String total = "";
                query = "SELECT price FROM room WHERE roomnumber = ?";
                pst = c.c.prepareStatement(query);
                pst.setString(1, txt_ID.getText());
                ResultSet rs2 = pst.executeQuery();

                if (rs2.next()) {
                    total = rs2.getString("price");
                }

                if (!total.isEmpty() && !txt_Time.getText().isEmpty()) {
                    int pending = Integer.parseInt(total) - Integer.parseInt(txt_Time.getText());
                    txt_Payment.setText(String.valueOf(pending));
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        });
        btnCheck.setBounds(56, 378, 89, 23);
        btnCheck.setBackground(Color.BLACK);
        btnCheck.setForeground(Color.WHITE);
        contentPane.add(btnCheck);

        // Update Button
        JButton btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(ae -> {
            try {
                conn c = new conn();

                String s1 = c1.getSelectedItem();
                String s2 = txt_ID.getText();
                String s3 = txt_Status.getText();
                String s4 = txt_Date.getText();
                String s5 = txt_Time.getText();

                String query = "UPDATE customer SET room = ?, name = ?, checkintime = ?, deposit = ? WHERE number = ?";
                PreparedStatement pst = c.c.prepareStatement(query);
                pst.setString(1, s2);
                pst.setString(2, s3);
                pst.setString(3, s4);
                pst.setString(4, s5);
                pst.setString(5, s1);

                int updatedRows = pst.executeUpdate();
                if (updatedRows > 0) {
                    JOptionPane.showMessageDialog(null, "Data Updated Successfully");
                    new Reception().setVisible(true);
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Update failed! No matching record found.");
                }

            } catch (Exception ee) {
                ee.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: " + ee.getMessage());
            }
        });
        btnUpdate.setBounds(168, 378, 89, 23);
        btnUpdate.setBackground(Color.BLACK);
        btnUpdate.setForeground(Color.WHITE);
        contentPane.add(btnUpdate);

        // Back Button
        JButton btnExit = new JButton("Back");
        btnExit.addActionListener(e -> {
            new Reception().setVisible(true);
            setVisible(false);
        });
        btnExit.setBounds(281, 378, 89, 23);
        btnExit.setBackground(Color.BLACK);
        btnExit.setForeground(Color.WHITE);
        contentPane.add(btnExit);

        getContentPane().setBackground(Color.WHITE);
    }
}
