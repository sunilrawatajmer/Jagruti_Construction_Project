import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

class showWorker{
    JFrame frame = new JFrame("Show All Workers");
    showWorker(){
        String url = "jdbc:mysql://localhost:3306/jagruti_construction";
            String username = "root";
            String password = "";

            try {
                Connection connection = DriverManager.getConnection(url, username, password);

                String query1 = "select * from  `worker`";
                // java.sql.Statement statement = connection.createStatement();
                PreparedStatement preparedStatement = connection.prepareStatement(query1);

                ResultSet resultSet = preparedStatement.executeQuery();

                DefaultTableModel tableModal = new DefaultTableModel();
                tableModal.addColumn("Id");
                tableModal.addColumn("Firstname");
                tableModal.addColumn("Lastname");
                tableModal.addColumn("Phone_no");
                tableModal.addColumn("Rate");
                tableModal.addColumn("Address");

                while (resultSet.next()) {
                    int showId = resultSet.getInt("Id");
                    String Firstname = resultSet.getString("Firstname");
                    String Lastname = resultSet.getString("Lastname");
                    int phone = resultSet.getInt("Phone_no");
                    int rate = resultSet.getInt("Rate");
                    String address = resultSet.getString("Address");

                    tableModal.addRow(new Object[] { showId, Firstname, Lastname, phone, rate, address });

                    // System.out.println(showId+" "+Firstname+" "+Lastname+" "+phone+" "+rate+"
                    // "+address);
                    // print in terminal
                }
                JTable WorkerTable = new JTable(tableModal);
                JScrollPane scrollPane = new JScrollPane(WorkerTable);

                // Assuming 'fram' in your Frame
                frame.getContentPane().removeAll();// Remove Previos Content
                frame.getContentPane().add(scrollPane);
                frame.revalidate();// Refrash JFrame

                resultSet.close();
                preparedStatement.close();
                connection.close();
                frame.setVisible(true);
                frame.setSize(1000, 400);
                frame.setLocation(330, 220);


            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
        // End ShowAllWorker Query

