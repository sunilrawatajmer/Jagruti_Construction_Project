import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Deleteside {
    JLabel idLabel, constructorNameLable, addressLable;
    JTextField idTextField, constructorNameTextField, addressTextField;
    JButton searchButton, cancleButton, deleteButton;

    Deleteside() {
        JFrame deletesideFrame = new JFrame("Delete side");
        JPanel deletesidePanel = new JPanel();
        deletesidePanel.setBorder(BorderFactory.createEmptyBorder(120, 30, 120, 30));

        idLabel = new JLabel("Id  : ");
        constructorNameLable = new JLabel("constructorName  : ");
        addressLable = new JLabel("address  : ");
        idTextField = new JTextField();
        constructorNameTextField = new JTextField();
        addressTextField = new JTextField();
        searchButton = new JButton("Search");
        cancleButton = new JButton("Cancle");
        deleteButton = new JButton("Delete");

        deletesidePanel.add(idLabel);
        deletesidePanel.add(idTextField);
        deletesidePanel.add(new JLabel());
        deletesidePanel.add(searchButton);
        deletesidePanel.add(constructorNameLable);
        deletesidePanel.add(constructorNameTextField);
        deletesidePanel.add(addressLable);
        deletesidePanel.add(addressTextField);
        deletesidePanel.add(new JLabel());
        deletesidePanel.add(cancleButton);
        deletesidePanel.add(deleteButton);

        GridLayout grid = new GridLayout(3, 4, 50, 60);
        deletesidePanel.setLayout(grid);
        deletesideFrame.setSize(900, 500);
        deletesideFrame.setVisible(true);
        deletesideFrame.setLocation(370, 200);
        deletesideFrame.add(deletesidePanel);

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
                        addressTextField.setText(resultSet.getString("Address"));
                    } else {
                        JOptionPane.showMessageDialog(deletesidePanel, "Data not Founded");
                    }
                    resultSet.close();
                    preparedStatement.close();
                    connection.close();

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(deletesidePanel, "Error : Unable to Search");
                }

            }
        });

        // Delete Button Qwery
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String url = "jdbc:mysql://localhost:3306/jagruti_construction";
                String username = "root";
                String password = "";

                try {
                    Connection connection = DriverManager.getConnection(url, username, password);
                    // java.sql.Statement statement = connection.createStatement();

                    String query2 = "delete from side where Id = ?";
                    PreparedStatement preparedStatement = connection.prepareStatement(query2);

                    preparedStatement.setString(1, idTextField.getText());

                    int updateData = preparedStatement.executeUpdate();
                    if (updateData > 0) {
                        JOptionPane.showMessageDialog(deletesidePanel, "Date Deleted SuccessFully!");
                    } else {
                        JOptionPane.showMessageDialog(deletesidePanel, "Something Went Wrong : Data Not Deleted!");
                    }
                    preparedStatement.close();
                    connection.close();

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(deletesidePanel, "Error : Unable to Update Data");
                }

            }
        });
        // End DeleteButton Qwery

        // CancleButton Qwery
        cancleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == cancleButton) {
                    deletesideFrame.dispose();
                }
            }
        });
        // End CancleButton Qwery
    }
}
