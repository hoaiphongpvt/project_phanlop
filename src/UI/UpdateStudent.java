package UI;

import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class UpdateStudent extends JFrame implements ActionListener{
    
    JTextField txtname,txtfname;
    JLabel labelrollno;
    JButton submit, cancel;
    Choice crollno;
    JDateChooser Hire, Enroll;
    
    UpdateStudent() {
        
        setSize(800, 450);
        setLocation(350, 50);
        
        setLayout(null);
        
        JLabel heading = new JLabel("Update Student Details");
        heading.setBounds(50, 10, 500, 50);
        heading.setFont(new Font("Tahoma", Font.ITALIC, 35));
        add(heading);
        
        JLabel lblrollnumber = new JLabel("Select Roll Number");
        lblrollnumber.setBounds(50, 100, 200, 20);
        lblrollnumber.setFont(new Font("serif", Font.PLAIN, 20));
        add(lblrollnumber);
        
        crollno = new Choice();
        crollno.setBounds(250, 100, 200, 20);
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
        
        JLabel lblname = new JLabel("Name");
        lblname.setBounds(50, 150, 100, 30);
        lblname.setFont(new Font("serif", Font.BOLD, 20));
        add(lblname);
        
        txtname = new JTextField();
        txtname.setBounds(200, 150, 150, 30);
        txtname.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(txtname);
        
        JLabel lblfname = new JLabel("Last Name");
        lblfname.setBounds(400, 150, 200, 30);
        lblfname.setFont(new Font("serif", Font.BOLD, 20));
        add(lblfname);
        
        txtfname = new JTextField();
        txtfname.setBounds(600, 150, 150, 30);
        txtfname.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(txtfname);
        
        JLabel lblrollno = new JLabel("Studentcode");
        lblrollno.setBounds(50, 200, 200, 30);
        lblrollno.setFont(new Font("serif", Font.BOLD, 20));
        add(lblrollno);
        
        labelrollno = new JLabel();
        labelrollno.setBounds(200, 200, 200, 30);
        labelrollno.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(labelrollno);
        
        JLabel lblcourse = new JLabel("HireDate");
        lblcourse.setBounds(50, 250, 200, 30);
        lblcourse.setFont(new Font("serif", Font.BOLD, 20));
        add(lblcourse);
        
        Hire = new JDateChooser();
        Hire.setBounds(200, 250, 150, 30);
        add(Hire);
        
        JLabel lblbranch = new JLabel("EnrollmentDate");
        lblbranch.setBounds(400, 250, 200, 30);
        lblbranch.setFont(new Font("serif", Font.BOLD, 20));
        add(lblbranch);
        
        Enroll = new JDateChooser();
        Enroll.setBounds(600, 250, 150, 30);
        add(Enroll);
        
        try {
            Conn c = new Conn();
            String query = "select * from person where PersonID='"+crollno.getSelectedItem()+"'";
            ResultSet rs = c.s.executeQuery(query);
            while(rs.next()) {
                labelrollno.setText(rs.getString("PersonID"));
                txtname.setText(rs.getString("Lastname"));
                txtfname.setText(rs.getString("Firstname"));
                String dob = ((JTextField) Hire.getDateEditor().getUiComponent()).getText();
                String dobenroll = ((JTextField) Enroll.getDateEditor().getUiComponent()).getText();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        crollno.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                try {
                    Conn c = new Conn();
                    String query = "select * from person where PersonID='"+crollno.getSelectedItem()+"'";
                    ResultSet rs = c.s.executeQuery(query);
                    while(rs.next()) {
                        labelrollno.setText(rs.getString("PersonID"));
                        txtname.setText(rs.getString("Lastname"));
                        txtfname.setText(rs.getString("Firstname"));
                        String dob = ((JTextField) Hire.getDateEditor().getUiComponent()).getText();
                        String dobenroll = ((JTextField) Enroll.getDateEditor().getUiComponent()).getText();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        
        submit = new JButton("Update");
        submit.setBounds(250, 350, 120, 30);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        submit.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(submit);
        
        cancel = new JButton("Cancel");
        cancel.setBounds(450, 350, 120, 30);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        cancel.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(cancel);
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            String Studentcode = labelrollno.getText();
            String dob = ((JTextField) Hire.getDateEditor().getUiComponent()).getText();
            String dobenroll = ((JTextField) Enroll.getDateEditor().getUiComponent()).getText();
            String name = txtname.getText();
            String fname = txtfname.getText();
            
            try {
                String query = "update person set Lastname='"+name+"', Firstname='"+fname+"', HireDate='"+dob+"', EnrollmentDate='"+dobenroll+"' where PersonID='"+Studentcode+"'";
                Conn con = new Conn();
                con.s.executeUpdate(query);
                
                JOptionPane.showMessageDialog(null, "Student Details Updated Successfully");
                setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            setVisible(false);
        }
    }
    
    public static void main(String[] args) {
        new UpdateStudent();
    }
}
