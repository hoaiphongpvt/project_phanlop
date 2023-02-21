package UI;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import net.proteanit.sql.DbUtils;

public class onsitecourse extends JFrame implements ActionListener{
    
            JTextField tfname,txtlocaltion,txttimer ;
            Choice cCourse;
            JButton btnaddButton,btncancel,btnsubmit,btnsearch;
            JTable table;
            JComboBox cbdays, cbbranch, cbsemester;
    
            Random ran = new Random();
            long first4 = Math.abs((ran.nextLong() % 9000L) + 1000L);
    
    onsitecourse() {
        setSize(800, 700);
        setLocation(250, 50);
        setLayout(null);
        
        getContentPane().setBackground(Color.WHITE);
        
        JLabel heading = new JLabel("Onsite Course");
        heading.setBounds(50, 10, 400, 30);
        heading.setFont(new Font("Tahoma", Font.BOLD, 30));
        add(heading);
        
        JLabel lblname = new JLabel("Course ID");
        lblname.setBounds(50, 80, 100, 50);
        lblname.setFont(new Font("serif", Font.BOLD, 20));
        add(lblname);
        
        cCourse = new Choice();
        cCourse.setBounds(150, 90, 100, 60);
        cCourse.setFont(new Font("serif", Font.BOLD, 20));
        add(cCourse);
        
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from course");
            while(rs.next()) {
                cCourse.add(rs.getString("CourseID"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        JLabel lblurl = new JLabel("Localtion");
        lblurl.setBounds(300, 80, 100, 45);
        lblurl.setFont(new Font("serif", Font.BOLD, 20));
        add(lblurl);
        
        txtlocaltion = new JTextField();
        txtlocaltion.setBounds(400, 80, 300, 45);
        txtlocaltion.setFont(new Font("serif", Font.BOLD, 20));
        add(txtlocaltion);
        
        JLabel lblday = new JLabel("Days");
        lblday.setBounds(80, 150, 100, 45);
        lblday.setFont(new Font("serif", Font.BOLD, 20));
        add(lblday);
        
        String course[] = {"T2,T4,T6", "T3,T5,T7","T6,CN","T5","T6","T7","CN"};
        cbdays = new JComboBox(course);
        cbdays.setBounds(135, 150, 200, 45);
        cbdays.setFont(new Font("serif", Font.BOLD, 20));
        cbdays.setBackground(Color.WHITE);
        add(cbdays);
        
        JLabel lbltime = new JLabel("Time");
        lbltime.setBounds(350, 150, 100, 45);
        lbltime.setFont(new Font("serif", Font.BOLD, 20));
        add(lbltime);
        
        txttimer = new JTextField();
        txttimer.setBounds(400, 150, 200, 45);
        txttimer.setFont(new Font("serif", Font.BOLD, 20));
        add(txttimer);
        
        btnaddButton = new JButton("Add");
        btnaddButton.setBounds(50, 250, 100, 50);
        btnaddButton.addActionListener(this);
        btnaddButton.setFont(new Font("serif", Font.BOLD, 20));
        add(btnaddButton);
        
        btnsubmit = new JButton("Edit");
        btnsubmit.setBounds(200, 250, 100, 50);
        btnsubmit.addActionListener(this);
        btnsubmit.setFont(new Font("serif", Font.BOLD, 20));
        add(btnsubmit);
        
        btnsearch = new JButton("Search");
        btnsearch.setBounds(350, 250, 100, 50);
        btnsearch.addActionListener(this);
        btnsearch.setFont(new Font("serif", Font.BOLD, 20));
        add(btnsearch);
        
        btncancel = new JButton("Cancel");
        btncancel.setBounds(500, 250, 100, 50);
        btncancel.addActionListener(this);
        btncancel.setFont(new Font("serif", Font.BOLD, 20));
        add(btncancel);
        
        table = new JTable();
        
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from onsitecourse");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(0, 350, 800, 500);
        add(jsp);
        
        setVisible(true);
        
    }
    
    public void actionPerformed(ActionEvent ae) {
         if (ae.getSource() == btnsearch) {
            String query = "select * from onsitecourse where CourseID = '"+cCourse.getSelectedItem()+"'";
            try {
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }  else if (ae.getSource() == btnaddButton) {
            String courseid = cCourse.getSelectedItem();
            String localString = txtlocaltion.getText();
            String days = (String) cbdays.getSelectedItem();
            String time = txttimer.getText();
            try {
                String query = "insert into onsitecourse values('"+courseid+"', '"+localString+"', '"+days+"', '"+time+"')";

                Conn con = new Conn();
                con.s.executeUpdate(query);
                
                JOptionPane.showMessageDialog(null, "Onsitecourse details Inserted Successfully");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == btnsubmit) {
            String courseid = cCourse.getSelectedItem();
            String localString = txtlocaltion.getText();
            String days = (String) cbdays.getSelectedItem();
            String time = txttimer.getText();
            try {
                String query = "update onsitecourse set CourseID='"+courseid+"', Location='"+localString+"', Days='"+days+"', Time='"+time+"' where CourseID='"+courseid+"'";
                Conn con = new Conn();
                con.s.executeUpdate(query);
                
                JOptionPane.showMessageDialog(null, "Onsitecourse Updated Successfully");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new onsitecourse();
    }
    
}
