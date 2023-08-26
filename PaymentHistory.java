import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

class PaymentHistory{
    JFrame frame = new JFrame("View Payment Details");
    PaymentHistory(){

         frame.setVisible(true);
         frame.setSize(1000, 400);
         frame.setLocation(300, 200);

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
                tableModal.addColumn("Firstname");
                tableModal.addColumn("Lastname");
                tableModal.addColumn("Rate");
                tableModal.addColumn("Total_Attendance");
                 tableModal.addColumn("Total_Amount");

                while (resultSet.next()) {
                    //int showId = resultSet.getInt("Id");
                    String Firstname = resultSet.getString("Firstname");
                    String Lastname = resultSet.getString("Lastname");
                    int Rate = resultSet.getInt("Rate");
                    int totalAttendance = 20;
                    int totalAmount = Rate*totalAttendance;


                    tableModal.addRow(new Object[] { Firstname, Lastname, Rate, totalAttendance, totalAmount });
                    System.out.println(Firstname);
                    System.out.println(Rate);
                    System.out.println(totalAttendance);
                    System.out.println(totalAmount);

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

            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, ex);
            }
    }
}
