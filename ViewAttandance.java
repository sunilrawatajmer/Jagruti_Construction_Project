import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

class viewAttendance{
    JFrame frame = new JFrame("View All Attendance");
    viewAttendance(){
        String url = "jdbc:mysql://localhost:3306/jagruti_construction";
            String username = "root";
            String password = "";

            try {
                Connection connection = DriverManager.getConnection(url, username, password);

                String query1 = "select * from  `attendance`";
                // java.sql.Statement statement = connection.createStatement();
                PreparedStatement preparedStatement = connection.prepareStatement(query1);

                ResultSet resultSet = preparedStatement.executeQuery();

                DefaultTableModel tableModal = new DefaultTableModel();
                tableModal.addColumn("Firstname");
                tableModal.addColumn("Lastname");
                tableModal.addColumn("Date");
                tableModal.addColumn("Status");

                while (resultSet.next()) {
                    //int showId = resultSet.getInt("Id");
                    String Firstname = resultSet.getString("Firstname");
                    String Lastname = resultSet.getString("Lastname");
                    String Date = resultSet.getString("Date");
                    String Status = resultSet.getString("attendance_Status");

                    tableModal.addRow(new Object[] { Firstname, Lastname, Date, Status });

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
                 frame.setLocation(320, 220);
                 frame.setSize(1000, 400);

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
    }
}
