import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

class appMenu extends JFrame implements ActionListener {
    JMenuItem add, update, delete, showWorker;
    JMenuItem addSide, updateSide, deleteSide, showSide;
    JMenuItem takeAttendance,viewAttendance;
    JMenuItem PaymentHistory;
    JFrame frame = new JFrame();

    appMenu() {

        frame.setTitle("Jagruti Constructors");
        frame.setSize(400, 400);
        frame.setVisible(true);

        // Strat Workers Menu
        JMenuBar mb = new JMenuBar();
        JMenu menu = new JMenu("Workers");
        add = new JMenuItem("Add Worker");
        update = new JMenuItem("Update Worker");
        delete = new JMenuItem("Delete Worker");
        showWorker = new JMenuItem("Show All Worker");

        menu.add(add);
        menu.add(update);
        menu.add(delete);
        menu.add(showWorker);

        mb.add(menu);
        frame.setJMenuBar(mb);

        add.addActionListener(this);
        update.addActionListener(this);
        delete.addActionListener(this);
        showWorker.addActionListener(this);
        // End Workers Menu

        // Sides Menu Start
        JMenu sideMenu = new JMenu("ConstructionSide");
        addSide = new JMenuItem("Add Side");
        updateSide = new JMenuItem("Update Side");
        deleteSide = new JMenuItem("Delete Side");
        showSide = new JMenuItem("Show All Sides");

        sideMenu.add(addSide);
        sideMenu.add(updateSide);
        sideMenu.add(deleteSide);
        sideMenu.add(showSide);

        mb.add(sideMenu);
        frame.setJMenuBar(mb);

        addSide.addActionListener(this);
        updateSide.addActionListener(this);
        deleteSide.addActionListener(this);
        showSide.addActionListener(this);
        // End Sides Menu

        //Attendance Menu Start
        JMenu attendancMenu = new JMenu("Attendance");
        takeAttendance = new JMenuItem("Take Attendance");
        viewAttendance = new JMenuItem("View Attendance");
        attendancMenu.add(takeAttendance);
        attendancMenu.add(viewAttendance);
        mb.add(attendancMenu);
        takeAttendance.addActionListener(this);
        viewAttendance.addActionListener(this);
        //Attendance Menu End

        //Payment Menu Start
         JMenu paymentMenu = new JMenu("Payment");
         PaymentHistory = new JMenuItem("View Details");
          PaymentHistory.addActionListener(this);
         paymentMenu.add(PaymentHistory);
         mb.add(paymentMenu);
        //Payment Menu End
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add) {
             new addworker();
        }
        if (e.getSource() == update) {
             new updateWorker();
        }
        if (e.getSource() == delete) {
             new DeleteWorker();
        }
        if (e.getSource() == addSide) {
             new addSide();
        }
        if (e.getSource() == updateSide) {
            new updateside();
        }
        if (e.getSource() == deleteSide) {
             new Deleteside();
        }
         if (e.getSource() == takeAttendance) {
             new Attendance();
        }
        if (e.getSource() == viewAttendance) {
             new viewAttendance();
        }
         if (e.getSource() == PaymentHistory) {
             new PaymentHistory();
        }
        // Start ShowAllWorker Query
        if (e.getSource() == showWorker) {
            new showWorker();
        }

        if (e.getSource() == showSide) {
            new ShowSides();
        }
    }

}

public class App {
    public static void main(String[] args) {
        new appMenu();
    }
}
