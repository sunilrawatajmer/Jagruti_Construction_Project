import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class DeleteWorker {
    JLabel idLabel, firstnameLable, lastnameLable;
    JTextField idTextField, firstnameTextField, lastnameTextField;
    JButton searchButton, cancleButton, deleteButton;

    DeleteWorker() {
        JFrame deleteWorkerFrame = new JFrame("Delete Worker");
        JPanel deleteWorkerPanel = new JPanel();
        deleteWorkerPanel.setBorder(BorderFactory.createEmptyBorder(130, 30, 130, 30));

        idLabel = new JLabel("Id  : ");
        firstnameLable = new JLabel("FirstName  : ");
        lastnameLable = new JLabel("LastName  : ");
        idTextField = new JTextField();
        firstnameTextField = new JTextField();
        lastnameTextField = new JTextField();
        searchButton = new JButton("Search");
        cancleButton = new JButton("Cancle");
        deleteButton = new JButton("Delete");

        deleteWorkerPanel.add(idLabel);
        deleteWorkerPanel.add(idTextField);
        deleteWorkerPanel.add(new JLabel());
        deleteWorkerPanel.add(searchButton);
        deleteWorkerPanel.add(firstnameLable);
        deleteWorkerPanel.add(firstnameTextField);
        deleteWorkerPanel.add(lastnameLable);
        deleteWorkerPanel.add(lastnameTextField);
        deleteWorkerPanel.add(new JLabel());
        deleteWorkerPanel.add(cancleButton);
        deleteWorkerPanel.add(deleteButton);

        GridLayout grid = new GridLayout(3, 4, 50, 60);
        deleteWorkerPanel.setLayout(grid);
        deleteWorkerFrame.setSize(900, 500);
        deleteWorkerFrame.setVisible(true);
        deleteWorkerFrame.setLocation(350, 200);
        deleteWorkerFrame.add(deleteWorkerPanel);

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
                        lastnameTextField.setText(resultSet.getString("Lastname"));
                    } else {
                        JOptionPane.showMessageDialog(deleteWorkerPanel, "Data not Founded");
                    }
                    resultSet.close();
                    preparedStatement.close();
                    connection.close();

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(deleteWorkerPanel, "Error : Unable to Search");
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

                    String query2 = "delete from worker where Id = ?";
                    PreparedStatement preparedStatement = connection.prepareStatement(query2);

                    preparedStatement.setString(1, idTextField.getText());

                    int updateData = preparedStatement.executeUpdate();
                    if (updateData > 0) {
                        JOptionPane.showMessageDialog(deleteWorkerPanel, "Date Deleted SuccessFully!");
                    } else {
                        JOptionPane.showMessageDialog(deleteWorkerPanel, "Something Went Wrong : Data Not Deleted!");
                    }
                    preparedStatement.close();
                    connection.close();

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(deleteWorkerPanel, "Error : Unable to Update Data");
                }

            }
        });
        // End DeleteButton Qwery

        // CancleButton Qwery
        cancleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == cancleButton) {
                    deleteWorkerFrame.dispose();
                }
            }
        });
        // End CancleButton Qwery
    }
}
