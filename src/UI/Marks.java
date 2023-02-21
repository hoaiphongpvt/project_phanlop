package UI;

import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;
import java.util.Random;
import net.proteanit.sql.DbUtils;

public class Marks extends JFrame implements ActionListener {
    
    String EnorllmentID;
    JButton cancel,searchc,searchs;
    Choice cStudentID,cCourse;
    JTable table;
    
    Random ran = new Random();
    long first4 = Math.abs((ran.nextLong() % 9000L) + 1000L);
    
    Marks(String EnorllmentiD) {
        this.EnorllmentID = EnorllmentiD;
        
        setSize(600, 600);
        setLocation(500, 150);
        setLayout(null);
        
        getContentPane().setBackground(Color.WHITE);
        
        JLabel heading = new JLabel("Delhi Technical Univeristy");
        heading.setBounds(100, 10, 500, 25);
        heading.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(heading);
        
        JLabel subheading = new JLabel("Result of Examination");
        subheading.setBounds(100, 50, 500, 20);
        subheading.setFont(new Font("Tahoma", Font.BOLD, 18));
        add(subheading);
        
        JLabel Enrollmentid = new JLabel("EnrollmentID");
        Enrollmentid.setBounds(25, 100, 500, 20);
        Enrollmentid.setFont(new Font("Tahoma", Font.BOLD, 18));
        add(Enrollmentid);
        
        JLabel enroll = new JLabel("1"+first4);
         enroll.setBounds(200, 100, 500, 20);
        enroll.setFont(new Font("Tahoma", Font.BOLD, 18));
        add(enroll);
        
        JLabel courseid = new JLabel("Course ID");
        courseid.setBounds(25, 150, 100, 20);
        courseid.setFont(new Font("Tahoma", Font.BOLD, 18));
        add(courseid);
        
        searchc = new JButton("Search CourseID");
        searchc.setBounds(375, 50, 200, 50);
        searchc.setFont(new Font("Tahoma", Font.BOLD, 18));
        searchc.addActionListener(this);
        add(searchc);
        
        cCourse = new Choice();
        cCourse.setBounds(200, 150, 200, 100);
        cCourse.setFont(new Font("Tahoma", Font.BOLD, 18));
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
        
        JLabel studentid = new JLabel("Student ID");
         studentid.setBounds(25, 200, 175, 20);
        studentid.setFont(new Font("Tahoma", Font.BOLD, 18));
        add(studentid);
        
        searchs = new JButton("Search StudentID");
        searchs.setBounds(375, 120, 200, 50);
        searchs.setFont(new Font("Tahoma", Font.BOLD, 18));
        searchs.addActionListener(this);
        add(searchs);
        
        cStudentID = new Choice();
        cStudentID.setBounds(200, 200, 200, 100);
        cStudentID.setFont(new Font("Tahoma", Font.BOLD, 18));
        add(cStudentID);
        
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from person");
            while(rs.next()) {
                cStudentID.add(rs.getString("PersonID"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        table = new JTable();
        
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from studentgrade");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(0, 300, 600, 600);
        add(jsp);
        
        cancel = new JButton("Back");
        cancel.setBounds(375, 200, 120, 25);
        cancel.addActionListener(this);
        cancel.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(cancel);
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == searchc) {
            String query = "select * from studentgrade where CourseID = '"+cCourse.getSelectedItem()+"'";
            try {
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == searchs) {
            String query = "select * from studentgrade where StudentID = '"+cStudentID.getSelectedItem()+"'";
            try {
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            setVisible(false);
        }
    }
    
    public static void main(String[] args) {
        new Marks("");
    }
}
