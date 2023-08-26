import java.awt.GridLayout;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

class updateWorker {

    JLabel firstnameLabel, lastnameLabel, rateLable, addressLable, phoneLable, idLable;
    JTextField firstnameTextField, lastnamTextField, rateTextField, addressTextField, phoneTextField, idTextField;
    JButton searchButton, cancleButton, updateButton;

    updateWorker() {

        JFrame updateWorkerFrame = new JFrame("Update Worker");
        JPanel updateWorkerPanel = new JPanel();
        updateWorkerPanel.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));

        firstnameLabel = new JLabel("Firstname : ");
        lastnameLabel = new JLabel("Lastname : ");
        phoneLable = new JLabel("Phone : ");
        rateLable = new JLabel("Rate : ");
        addressLable = new JLabel("Address : ");
        idLable = new JLabel("Id : ");

        firstnameTextField = new JTextField();
        lastnamTextField = new JTextField();
        phoneTextField = new JTextField();
        rateTextField = new JTextField();
        addressTextField = new JTextField();
        idTextField = new JTextField();

        searchButton = new JButton("Search");
        cancleButton = new JButton("Cancle");
        updateButton = new JButton("Update");

        updateWorkerPanel.add(idLable);
        updateWorkerPanel.add(idTextField);
        updateWorkerPanel.add(new JLabel());
        updateWorkerPanel.add(searchButton);
        updateWorkerPanel.add(firstnameLabel);
        updateWorkerPanel.add(firstnameTextField);
        updateWorkerPanel.add(lastnameLabel);
        updateWorkerPanel.add(lastnamTextField);
        updateWorkerPanel.add(phoneLable);
        updateWorkerPanel.add(phoneTextField);
        updateWorkerPanel.add(rateLable);
        updateWorkerPanel.add(rateTextField);
        updateWorkerPanel.add(addressLable);
        updateWorkerPanel.add(addressTextField);
        updateWorkerPanel.add(new JLabel());
        updateWorkerPanel.add(new JLabel());
        updateWorkerPanel.add(new JLabel());
        updateWorkerPanel.add(cancleButton);
        updateWorkerPanel.add(updateButton);

        GridLayout grid = new GridLayout(5, 4, 50, 60);
        updateWorkerPanel.setLayout(grid);
        updateWorkerFrame.setSize(900, 400);
        updateWorkerFrame.setVisible(true);
        updateWorkerFrame.setLocation(350, 230);
        updateWorkerFrame.add(updateWorkerPanel);

        // CancleButton Qwery
        cancleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == cancleButton) {
                    updateWorkerFrame.dispose();
                }
            }
        });
        // End CancleButton Qwery

        // Query for Search Id
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String url = "jdbc:mysql://localhost:3306/jagruti_construction";
                String username = "root";
                String password = "";

                try {
                    Connection connection = DriverManager.getConnection(url, username, password);
                    // java.sql.Statement statement = connection.createStatement();

                    String query1 = "select * from  `worker` where id = ?";
                    PreparedStatement preparedStatement = connection.prepareStatement(query1);
                    preparedStatement.setString(1, idTextField.getText());
                    ResultSet resultSet = preparedStatement.executeQuery();

                    if (resultSet.next()) {
                        firstnameTextField.setText(resultSet.getString("Firstname"));
                        lastnamTextField.setText(resultSet.getString("Lastname"));
                        phoneTextField.setText(resultSet.getString("phone_no"));
                        rateTextField.setText(resultSet.getString("Rate"));
                        addressTextField.setText(resultSet.getString("Address"));
                    } else {
                        JOptionPane.showMessageDialog(updateWorkerPanel, "Data not Founded");
                    }
                    resultSet.close();
                    preparedStatement.close();
                    connection.close();

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(updateWorkerPanel, "Error : Unable to Search");
                }

            }
        });

        // Update Button Qwery
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String url = "jdbc:mysql://localhost:3306/jagruti_construction";
                String username = "root";
                String password = "";

                try {
                    Connection connection = DriverManager.getConnection(url, username, password);
                    // java.sql.Statement statement = connection.createStatement();

                    String query2 = "update worker set Firstname=?,Lastname=?,Phone_no=?,Rate=?,Address=? where Id = ?";
                    PreparedStatement preparedStatement = connection.prepareStatement(query2);
                    preparedStatement.setString(1, firstnameTextField.getText());
                    preparedStatement.setString(2, lastnamTextField.getText());
                    preparedStatement.setString(3, phoneTextField.getText());
                    preparedStatement.setString(4, rateTextField.getText());
                    preparedStatement.setString(5, addressTextField.getText());
                    preparedStatement.setString(6, idTextField.getText());

                    int updateData = preparedStatement.executeUpdate();
                    if (updateData > 0) {
                        JOptionPane.showMessageDialog(updateWorkerPanel, "Date Updated SuccessFully!");
                    } else {
                        JOptionPane.showMessageDialog(updateWorkerPanel, "Data Not Updated!");
                    }
                    preparedStatement.close();
                    connection.close();

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(updateWorkerPanel, "Error : Unable to Update Data");
                }

            }
        });
    }
}
