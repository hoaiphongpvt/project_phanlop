package UI;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import java.util.Random;
import net.proteanit.sql.DbUtils;

public class EnterMarks extends JFrame implements ActionListener {

    Choice crollno,ccourse,cid;
    JTable table;
    JComboBox cbsemester;
    JTextField grade;
    JButton cancel, submit,btnadd,search;
    
    Random ran = new Random();
    long first4 = Math.abs((ran.nextLong() % 9000L) + 1000L);
    
    EnterMarks() {
        
        setSize(1000, 1000);
        setLocation(0 ,0);
        setLayout(null);
        
        getContentPane().setBackground(Color.WHITE);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/exam.jpg"));
        Image i2 = i1.getImage().getScaledInstance(300, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(500, 40, 400, 300);
        add(image);
        
        JLabel heading = new JLabel("Enter Marks of Student");
        heading.setBounds(50, 0, 500, 50);
        heading.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(heading);
        
        JLabel lblrollnumber = new JLabel("Select Student Number");
        lblrollnumber.setBounds(50, 70, 150, 20);
        add(lblrollnumber);
        
        crollno = new Choice();
        crollno.setBounds(200, 70, 150, 20);
        add(crollno);
        
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from person");
            while(rs.next()) {
                crollno.add(rs.getString("PersonID"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        JLabel lblentersubject = new JLabel("Course selection");
        lblentersubject.setBounds(100, 150, 200, 40);
        add(lblentersubject);
        
        JLabel lblentermarks = new JLabel("Enter Grade");
        lblentermarks.setBounds(320, 150, 200, 40);
        add(lblentermarks);
        
        ccourse = new Choice();
        ccourse.setBounds(50, 200, 200, 20);
        add(ccourse);
        
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from course");
            while(rs.next()) {
                ccourse.add(rs.getString("CourseID"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        grade = new JTextField();
        grade.setBounds(250, 200, 200, 20);
        add(grade);
        
        JLabel lblid = new JLabel("EnrollmentID");
        lblid.setBounds(150, 250, 80, 20);
        add(lblid);
        
        cid = new Choice();
        cid.setBounds(250, 250, 200, 20);
        add(cid);
        
         try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from studentgrade");
            while(rs.next()) {
                cid.add(rs.getString("EnrollmentID"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        submit = new JButton("Edit");
        submit.setBounds(70, 300, 150, 25);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        submit.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(submit);
        
        btnadd = new JButton("Add");
        btnadd.setBounds(280, 300, 150, 25);
        btnadd.setBackground(Color.BLACK);
        btnadd.setForeground(Color.WHITE);
        btnadd.addActionListener(this);
        btnadd.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(btnadd);
        
        search = new JButton("Search");
        search.setBounds(500, 300, 150, 25);
        search.setBackground(Color.BLACK);
        search.setForeground(Color.WHITE);
        search.addActionListener(this);
        search.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(search);
        
        cancel = new JButton("Back");
        cancel.setBounds(720, 300, 150, 25);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        cancel.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(cancel);
        
        table = new JTable();
        
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from studentgrade");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(50, 400, 750, 300);
        add(jsp);
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnadd) {
            String ccourseid = ccourse.getSelectedItem();
            String crollnoid = crollno.getSelectedItem();
            String enroll = "1"+first4;
            String grades = grade.getText();
            try {
                Conn c = new Conn();
                
                String query1 = "insert into studentgrade values('"+enroll+"', '"+ccourseid+"', '"+crollnoid+"', '"+grades+"')";            
                c.s.executeUpdate(query1);
                
                JOptionPane.showMessageDialog(null, "Marks Inserted Sucessfully");
                setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
            } else if (ae.getSource() == submit) {
                        String ccourseid = ccourse.getSelectedItem();
                        String crollnoid = crollno.getSelectedItem();
                        String grades = grade.getText();
                        String enorll = cid.getSelectedItem();
                        try {
                               String query = "update studentgrade set CourseID ='"+ccourseid+"', StudentID='"+crollnoid+"', Grade='"+grades+"' where EnrollmentID='"+enorll+"'";
                               Conn con = new Conn();
                               con.s.executeUpdate(query);

                               JOptionPane.showMessageDialog(null, "Officeassignment Updated Successfully");
                                setVisible(false);
                            } catch (Exception e) {
                               e.printStackTrace();
                           }
                       }else if (ae.getSource() == search) {
                               String query = "select * from studentgrade where StudentID = '"+crollno.getSelectedItem()+"'";
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
        new EnterMarks();
    }
}
