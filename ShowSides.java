import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

class ShowSides{
    JFrame frame = new JFrame("Show All Sides");
    ShowSides(){
        String url = "jdbc:mysql://localhost:3306/jagruti_construction";
        String username = "root";
        String password = "";

        try {
            Connection connection = DriverManager.getConnection(url, username, password);

            String query1 = "select * from  `side`";
            // java.sql.Statement statement = connection.createStatement();
            PreparedStatement preparedStatement = connection.prepareStatement(query1);

            ResultSet resultSet = preparedStatement.executeQuery();

            DefaultTableModel tableModal = new DefaultTableModel();
            tableModal.addColumn("Id");
            tableModal.addColumn("Address");
            tableModal.addColumn("Total_Amount");
            tableModal.addColumn("Constructor_Name");
            tableModal.addColumn("Contact");

            while (resultSet.next()) {
                int showId = resultSet.getInt("Id");
                String Address = resultSet.getString("Address");
                String totalAmount = resultSet.getString("Total_Amount");
                String ConstructorName = resultSet.getString("Constructor_Name");
                int contact = resultSet.getInt("Contact");

                tableModal.addRow(new Object[] { showId, Address, totalAmount, ConstructorName, contact });

            }
            JTable sideTable = new JTable(tableModal);
            JScrollPane scrollPane = new JScrollPane(sideTable);

            // Assuming 'fram' in your Frame
            frame.getContentPane().removeAll();// Remove Previos Content
            frame.getContentPane().add(scrollPane);
            frame.revalidate();// Refrash JFrame

            resultSet.close();
            preparedStatement.close();
            connection.close();
             frame.setVisible(true);
             frame.setSize(1000, 400);
             frame.setLocation(330, 230);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        }
    }
        // End ShowAllWorker Query

