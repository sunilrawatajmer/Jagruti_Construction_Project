import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.sql.DriverManager;
import javax.swing.*;

class addworker {
    JLabel firstnameLabel, lastnameLabel, rateLable, addressLable, phoneLable;
    JTextField firstnameTextField, lastnamTextField, rateTextField, addressTextField, phoneTextField;
    JButton cancleButton, saveButton;

    addworker() {

        JFrame addWorkerFrame = new JFrame("Add Worker");
        JPanel addWorkerPanel = new JPanel();
        addWorkerPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        firstnameLabel = new JLabel("Firstname : ");
        lastnameLabel = new JLabel("Lastname : ");
        phoneLable = new JLabel("Phone : ");
        rateLable = new JLabel("Rate : ");
        addressLable = new JLabel("Address : ");

        firstnameTextField = new JTextField();
        lastnamTextField = new JTextField();
        phoneTextField = new JTextField();
        rateTextField = new JTextField();
        addressTextField = new JTextField();

        cancleButton = new JButton("Cancle");
        saveButton = new JButton("Save");

        addWorkerPanel.add(firstnameLabel);
        addWorkerPanel.add(firstnameTextField);
        addWorkerPanel.add(lastnameLabel);
        addWorkerPanel.add(lastnamTextField);
        addWorkerPanel.add(phoneLable);
        addWorkerPanel.add(phoneTextField);
        addWorkerPanel.add(rateLable);
        addWorkerPanel.add(rateTextField);
        addWorkerPanel.add(addressLable);
        addWorkerPanel.add(addressTextField);
        addWorkerPanel.add(new JLabel());
        addWorkerPanel.add(new JLabel());
        addWorkerPanel.add(new JLabel());
        addWorkerPanel.add(cancleButton);
        addWorkerPanel.add(saveButton);

        GridLayout grid = new GridLayout(4, 4, 50, 60);
        addWorkerPanel.setLayout(grid);
        addWorkerFrame.setSize(800, 400);
        addWorkerFrame.setVisible(true);
        addWorkerFrame.setLocation(400, 230);
        addWorkerFrame.add(addWorkerPanel);

        // CancleButton Qwery
        cancleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == cancleButton) {
                    addWorkerFrame.dispose();
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

                    String query1 = "INSERT INTO `worker`(`Firstname`, `Lastname`, `Phone_no`, `Rate`, `Address`) VALUES ('"
                            + firstnameTextField.getText() + "','" + lastnamTextField.getText() + "','"
                            + Integer.parseInt(phoneTextField.getText()) + "','"
                            + Integer.parseInt(rateTextField.getText()) + "','" + addressTextField.getText() + "')";
                    statement.executeUpdate(query1);
                    JOptionPane.showMessageDialog(null, "Data Insert SuccessFully!");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });
        // End SaveButton Qwery
    }

    public static void addActionListener(ActionListener actionListener) {
    }
}
