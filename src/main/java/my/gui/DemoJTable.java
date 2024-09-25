/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package my.gui;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class DemoJTable extends JFrame{
     private JTable tblAccounts;
    private JButton btnAdd, btnDelete;
    private JTextField txtAccountName, txtBalance;
    private DefaultTableModel model;

    public DemoJTable(String title) {
        super(title);
        createGUI();
        processEvents();
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        DemoJTable frame = new DemoJTable("Customer Account Management");
        frame.setVisible(true);
    }

    private void createGUI() {
        Object[][] data = {
            {"Lê Minh Tuấn", "16000000"},
            {"Trần Thanh Tâm", "12000000"}
        };
        String[] columnNames = {"Tên tài khoản", "Số tiền"};
        
      
        model = new DefaultTableModel(data, columnNames);
        
     
        tblAccounts = new JTable(model);
        
   
        JScrollPane scrollTable = new JScrollPane(tblAccounts);

     
        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Tên tài khoản:"));
        inputPanel.add(txtAccountName = new JTextField(15));
        inputPanel.add(new JLabel("Số tiền:"));
        inputPanel.add(txtBalance = new JTextField(10));
        inputPanel.add(btnAdd = new JButton("Thêm"));
        inputPanel.add(btnDelete = new JButton("Xóa"));

        add(scrollTable, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.NORTH);
    }

    private void processEvents() {      
        btnAdd.addActionListener((e) -> {
            String error = "";
            try {
                String accountName = txtAccountName.getText();
                String balanceText = txtBalance.getText();

                if (accountName.isEmpty()) {
                    error = "Bạn chưa nhập tên tài khoản!";
                }
                if (balanceText.isEmpty()) {
                    error = "Bạn chưa nhập số tiền!";
                }

                double balance = Double.parseDouble(balanceText);

                if (!error.isEmpty()) {
                    JOptionPane.showMessageDialog(this, error, "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

               
                model.addRow(new Object[]{accountName, balanceText});

               
                txtAccountName.setText("");
                txtBalance.setText("");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Số tiền phải là số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnDelete.addActionListener((e) -> {
            int selectedIndex = tblAccounts.getSelectedRow();
            if (selectedIndex >= 0) {     
                if (JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa tài khoản này?", "Xác nhận", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                    
                    model.removeRow(selectedIndex);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn tài khoản để xóa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
