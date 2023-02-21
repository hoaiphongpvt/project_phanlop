package UI;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import net.proteanit.sql.DbUtils;

public class Onlinecourse extends JFrame implements ActionListener{
    
            JTextField tfname,txturl ;
            Choice cCourse;
            JButton btnaddButton,btncancel,btnsubmit,btnsearch;
            JTable table;
    
            Random ran = new Random();
            long first4 = Math.abs((ran.nextLong() % 9000L) + 1000L);
    
    Onlinecourse() {
        setSize(800, 700);
        setLocation(250, 50);
        setLayout(null);
        
        getContentPane().setBackground(Color.WHITE);
        
        JLabel heading = new JLabel("Online course");
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
        
        JLabel lblurl = new JLabel("URL");
        lblurl.setBounds(300, 80, 100, 45);
        lblurl.setFont(new Font("serif", Font.BOLD, 20));
        add(lblurl);
        
        txturl = new JTextField();
        txturl.setBounds(350, 80, 300, 45);
        txturl.setFont(new Font("serif", Font.BOLD, 20));
        add(txturl);
        
        btnaddButton = new JButton("Add");
        btnaddButton.setBounds(50, 170, 100, 50);
        btnaddButton.addActionListener(this);
        btnaddButton.setFont(new Font("serif", Font.BOLD, 20));
        add(btnaddButton);
        
        btnsubmit = new JButton("Submit");
        btnsubmit.setBounds(200, 170, 100, 50);
        btnsubmit.addActionListener(this);
        btnsubmit.setFont(new Font("serif", Font.BOLD, 20));
        add(btnsubmit);
        
        btnsearch = new JButton("Search");
        btnsearch.setBounds(350, 170, 100, 50);
        btnsearch.addActionListener(this);
        btnsearch.setFont(new Font("serif", Font.BOLD, 20));
        add(btnsearch);
        
        btncancel = new JButton("Cancel");
        btncancel.setBounds(500, 170, 100, 50);
        btncancel.addActionListener(this);
        btncancel.setFont(new Font("serif", Font.BOLD, 20));
        add(btncancel);
        
        table = new JTable();
        
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from onlinecourse");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(0, 300, 1000, 500);
        add(jsp);
        
        setVisible(true);
        
    }
    
    public void actionPerformed(ActionEvent ae) {
         if (ae.getSource() == btnsearch) {
            String query = "select * from onlinecourse where CourseID = '"+cCourse.getSelectedItem()+"'";
            try {
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }  else if (ae.getSource() == btnaddButton) {
            String courseid = cCourse.getSelectedItem();
            String url = txturl.getText();
            try {
                String query = "insert into onlinecourse values('"+courseid+"', '"+url+"')";

                Conn con = new Conn();
                con.s.executeUpdate(query);
                
                JOptionPane.showMessageDialog(null, "Online course details Inserted Successfully");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == btnsubmit) {
            String courseid = cCourse.getSelectedItem();
            String url = txturl.getText();
            try {
                String query = "update onlinecourse set CourseID='"+courseid+"', url='"+url+"' where CourseID='"+courseid+"'";
                Conn con = new Conn();
                con.s.executeUpdate(query);
                
                JOptionPane.showMessageDialog(null, "Online course details Updated Successfully");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Onlinecourse();
    }
    
}
