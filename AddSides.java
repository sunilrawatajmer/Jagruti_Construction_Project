import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.sql.DriverManager;
import javax.swing.*;

class addSide {
    JLabel addressLabel, amountLabel, nameLable, contactLable;
    JTextField addressTextField, amountTextField, nameTextField, contactTextField;
    JButton cancleButton, saveButton;

    addSide() {

        JFrame addSideFrame = new JFrame("Add Side");
        JPanel addSidePanel = new JPanel();
        addSidePanel.setBorder(BorderFactory.createEmptyBorder(75, 40, 75, 40));

        addressLabel = new JLabel("Address : ");
        amountLabel = new JLabel("Total Amount : ");
        contactLable = new JLabel("Contact no ");
        nameLable = new JLabel("Constructor Name :  ");

        addressTextField = new JTextField();
        amountTextField = new JTextField();
        contactTextField = new JTextField();
        nameTextField = new JTextField();

        cancleButton = new JButton("Cancle");
        saveButton = new JButton("Save");

        addSidePanel.add(addressLabel);
        addSidePanel.add(addressTextField);
        addSidePanel.add(amountLabel);
        addSidePanel.add(amountTextField);
        addSidePanel.add(contactLable);
        addSidePanel.add(contactTextField);
        addSidePanel.add(nameLable);
        addSidePanel.add(nameTextField);
        addSidePanel.add(new JLabel());
        addSidePanel.add(cancleButton);
        addSidePanel.add(saveButton);

        GridLayout grid = new GridLayout(3, 4, 50, 60);
        addSidePanel.setLayout(grid);
        addSideFrame.setSize(800, 400);
        addSideFrame.setVisible(true);
        addSideFrame.setLocation(400, 230);
        addSideFrame.add(addSidePanel);

        // CancleButton Qwery
        cancleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == cancleButton) {
                    addSideFrame.dispose();
                }

            }
        });
        // End CancleButton Qwery

        // SaveButton Qwery
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String url = "jdbc:mysql://localhost:3306/jagruti_construction";
                String username = "root";
                String password = "";

                try {
                    Connection connection = DriverManager.getConnection(url, username, password);
                    java.sql.Statement statement = connection.createStatement();

                    String query1 = "INSERT INTO `side`(`Address`, `Total_Amount`, `Constructor_Name`, `Contact`) VALUES ('"
                            + addressTextField.getText() + "','" + Integer.parseInt(amountTextField.getText()) + "','"
                            + nameTextField.getText() + "','" + Integer.parseInt(contactTextField.getText()) + "')";
                    statement.executeUpdate(query1);
                    JOptionPane.showMessageDialog(null, "Data Insert SuccessFully!");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(addSideFrame, addressTextField);
                }

            }
        });
        // End SaveButton Qwery
    }
}
