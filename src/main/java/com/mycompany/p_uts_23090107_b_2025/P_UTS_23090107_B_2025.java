package com.mycompany.p_uts_23090107_b_2025;

import javax.swing.*;
import org.bson.Document;

public class P_UTS_23090107_B_2025 {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Manajemen Customer");
        frame.setSize(400, 370);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Input Fields
        JTextField tfNama = new JTextField();
        tfNama.setBounds(100, 20, 200, 25);

        JTextField tfEmail = new JTextField();
        tfEmail.setBounds(100, 60, 200, 25);

        JTextField tfTelepon = new JTextField();
        tfTelepon.setBounds(100, 100, 200, 25);

        JButton btnSimpan = new JButton("Simpan");
        btnSimpan.setBounds(100, 140, 100, 25);

        JTextArea areaList = new JTextArea();
        areaList.setBounds(100, 180, 200, 70);

        // Search Field
        JTextField tfCari = new JTextField();
        tfCari.setBounds(100, 260, 200, 25);

        JButton btnCari = new JButton("Cari");
        btnCari.setBounds(100, 290, 100, 25);

        // DAO
        CustomerDAO dao = new CustomerDAO();

        // Button Simpan Action
        btnSimpan.addActionListener(e -> {
            dao.tambahCustomer(tfNama.getText(), tfEmail.getText(), tfTelepon.getText());
            areaList.setText("");
            for (Document doc : dao.getAllCustomers()) {
                areaList.append(doc.getString("nama") + " - " + doc.getString("email") + "\n");
            }
        });

        // Button Cari Action
        btnCari.addActionListener(e -> {
            areaList.setText(""); // Bersihkan areaList
            String keyword = tfCari.getText();
            for (Document doc : dao.searchCustomers(keyword)) {
                areaList.append(doc.getString("nama") + " - " + doc.getString("email") + "\n");
            }
        });

        // Labels
        frame.add(new JLabel("Nama")).setBounds(20, 20, 100, 25);
        frame.add(tfNama);
        frame.add(new JLabel("Email")).setBounds(20, 60, 100, 25);
        frame.add(tfEmail);
        frame.add(new JLabel("Telepon")).setBounds(20, 100, 100, 25);
        frame.add(tfTelepon);
        frame.add(btnSimpan);
        frame.add(areaList);

        frame.add(new JLabel("Cari")).setBounds(20, 260, 100, 25);
        frame.add(tfCari);
        frame.add(btnCari);

        // Layout
        frame.setLayout(null);
        frame.setVisible(true);
    }
}
