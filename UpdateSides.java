import java.awt.GridLayout;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

class updateside {

    JLabel constructorNameLabel, amountLable, addressLable, contactLable, idLable;
    JTextField constructorNameTextField, amountTextField, addressTextField, contactTextField, idTextField;
    JButton searchButton, cancleButton, updateButton;

    updateside() {

        JFrame updatesideFrame = new JFrame("Update side");
        JPanel updatesidePanel = new JPanel();
        updatesidePanel.setBorder(BorderFactory.createEmptyBorder(25, 20, 25, 20));

        constructorNameLabel = new JLabel("constructorName : ");
        contactLable = new JLabel("contact : ");
        amountLable = new JLabel("amount : ");
        addressLable = new JLabel("Address : ");
        idLable = new JLabel("Id : ");

        constructorNameTextField = new JTextField();
        contactTextField = new JTextField();
        amountTextField = new JTextField();
        addressTextField = new JTextField();
        idTextField = new JTextField();

        searchButton = new JButton("Search");
        cancleButton = new JButton("Cancle");
        updateButton = new JButton("Update");

        updatesidePanel.add(idLable);
        updatesidePanel.add(idTextField);
        updatesidePanel.add(new JLabel());
        updatesidePanel.add(searchButton);
        updatesidePanel.add(constructorNameLabel);
        updatesidePanel.add(constructorNameTextField);
        updatesidePanel.add(contactLable);
        updatesidePanel.add(contactTextField);
        updatesidePanel.add(amountLable);
        updatesidePanel.add(amountTextField);
        updatesidePanel.add(addressLable);
        updatesidePanel.add(addressTextField);
        updatesidePanel.add(new JLabel());
        updatesidePanel.add(cancleButton);
        updatesidePanel.add(updateButton);

        GridLayout grid = new GridLayout(4, 4, 50, 60);
        updatesidePanel.setLayout(grid);
        updatesideFrame.setSize(800, 400);
        updatesideFrame.setVisible(true);
         updatesideFrame.setLocation(400, 220);
        updatesideFrame.add(updatesidePanel);

        // CancleButton Qwery
        cancleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == cancleButton) {
                    updatesideFrame.dispose();
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

                    String query1 = "select * from  `side` where id = ?";
                    PreparedStatement preparedStatement = connection.prepareStatement(query1);
                    preparedStatement.setString(1, idTextField.getText());
                    ResultSet resultSet = preparedStatement.executeQuery();

                    if (resultSet.next()) {
                        constructorNameTextField.setText(resultSet.getString("Constructor_Name"));
                        contactTextField.setText(resultSet.getString("Contact"));
                        amountTextField.setText(resultSet.getString("Total_Amount"));
                        addressTextField.setText(resultSet.getString("Address"));
                    } else {
                        JOptionPane.showMessageDialog(updatesidePanel, "Data not Founded");
                    }
                    resultSet.close();
                    preparedStatement.close();
                    connection.close();

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(updatesidePanel, "Error : Unable to Search");
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

                    String query2 = "update side set Constructor_Name=?,Contact=?,Total_Amount=?,Address=? where Id = ?";
                    PreparedStatement preparedStatement = connection.prepareStatement(query2);
                    preparedStatement.setString(1, constructorNameTextField.getText());
                    preparedStatement.setString(2, contactTextField.getText());
                    preparedStatement.setString(3, amountTextField.getText());
                    preparedStatement.setString(4, addressTextField.getText());
                    preparedStatement.setString(5, idTextField.getText());

                    int updateData = preparedStatement.executeUpdate();
                    if (updateData > 0) {
                        JOptionPane.showMessageDialog(updatesidePanel, "Date Updated SuccessFully!");
                    } else {
                        JOptionPane.showMessageDialog(updatesidePanel, "Data Not Updated!");
                    }
                    preparedStatement.close();
                    connection.close();

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(updatesidePanel, "Error : Unable to Update Data");
                }

            }
        });
    }
}
