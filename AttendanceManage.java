import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;

class Attendance {
    JLabel idLabel, firstnameLable, lastnameLable,dateLable;
    JTextField idTextField, firstnameTextField, lastnameTextField,dateTextField;
    JButton searchButton, cancleButton, submitButton;
    JCheckBox cb;
    String selectOption;
    // int total_attendance=0;

    Attendance() {
        JFrame AttendanceFrame = new JFrame("Take Attandance");
        JPanel AttendancePanel = new JPanel();
        AttendancePanel.setBorder(BorderFactory.createEmptyBorder(90, 30, 90, 30));

        // Combobox Qwery
        String[] comboBoxItems = { "Apsent", "Present" };
        JComboBox<String> comboBox = new JComboBox<>(comboBoxItems);
        comboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    selectOption = (String) comboBox.getSelectedItem();
                    //System.out.println(selectOption);
                    // if(selectOption == "Present"){
                    //     total_attendance++;
                    // }
                    //  int total_attendance2=0;
                    // total_attendance2=total_attendance2+total_attendance;
                    // System.out.println(total_attendance2);
                }
            }
        });
        // End ComboBox Qwery

        idLabel = new JLabel("Id  : ");
        firstnameLable = new JLabel("FirstName  : ");
        lastnameLable = new JLabel("LastName  : ");
         dateLable = new JLabel("Date  : ");
        idTextField = new JTextField();
        firstnameTextField = new JTextField();
        lastnameTextField = new JTextField();
        dateTextField = new JTextField();
        searchButton = new JButton("Search");
        cancleButton = new JButton("Cancle");
        submitButton = new JButton("Submit");

        AttendancePanel.add(idLabel);
        AttendancePanel.add(idTextField);
        AttendancePanel.add(new JLabel());
        AttendancePanel.add(searchButton);
        AttendancePanel.add(firstnameLable);
        AttendancePanel.add(firstnameTextField);
        AttendancePanel.add(lastnameLable);
        AttendancePanel.add(lastnameTextField);
        AttendancePanel.add(dateLable);
        AttendancePanel.add(dateTextField);
        AttendancePanel.add(new JLabel("Take Attendance : "));
        AttendancePanel.add(comboBox);
        AttendancePanel.add(new JPanel());
        AttendancePanel.add(cancleButton);
        AttendancePanel.add(submitButton);

        GridLayout grid = new GridLayout(4, 4, 50, 60);
        AttendancePanel.setLayout(grid);
        AttendanceFrame.setSize(900, 500);
        AttendanceFrame.setVisible(true);
         AttendanceFrame.setLocation(370, 170);
        AttendanceFrame.add(AttendancePanel);

        //get Date
        LocalDate currect = LocalDate.now();
        String currentDate = ""+currect;
        dateTextField.setText(currentDate);

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
                        JOptionPane.showMessageDialog(AttendancePanel, "Data not Founded");
                    }

                    resultSet.close();
                    preparedStatement.close();
                    connection.close();

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(AttendancePanel, "Error : Unable to Search");
                }
            }
        });
        // End Search Qwery

        // Submit button qwery
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String url = "jdbc:mysql://localhost:3306/jagruti_construction";
                String username = "root";
                String password = "";

                try {
                    Connection connection = DriverManager.getConnection(url, username, password);
                    java.sql.Statement statement = connection.createStatement();

                    String query2 = "insert into attendance(Firstname,Lastname,Date,attendance_Status)values('" + firstnameTextField.getText() + "','"
                            + lastnameTextField.getText() + "','"+dateTextField.getText()+"','" + selectOption + "')";

                    statement.executeUpdate(query2);
                    JOptionPane.showMessageDialog(null, "Attendance Submit SuccessFully!");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(AttendancePanel, ex);
                }
            }
        });
        // End submit button qwery

        // CancleButton Qwery
        cancleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == cancleButton) {
                    AttendanceFrame.dispose();
                }

            }
        });
        // End CancleButton Qwery
    }
}
