package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class UpdateTeacher extends JFrame implements ActionListener{
    
    JLabel labelEmpId;
    JButton submit, cancel;
    Choice cEmpId,cdepartment,ccourse;
    JTextField txtfname,txtname;
    
    UpdateTeacher() {
        
        setSize(800, 450);
        setLocation(350, 50);
        
        setLayout(null);
        
        JLabel heading = new JLabel("Update Teacher Details");
        heading.setBounds(50, 10, 500, 50);
        heading.setFont(new Font("Tahoma", Font.ITALIC, 35));
        add(heading);
        
        JLabel lblrollnumber = new JLabel("Select Employee Id");
        lblrollnumber.setBounds(50, 100, 200, 20);
        lblrollnumber.setFont(new Font("serif", Font.PLAIN, 20));
        add(lblrollnumber);
        
        cEmpId = new Choice();
        cEmpId.setBounds(250, 100, 200, 20);
        add(cEmpId);
        
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from teacher");
            while(rs.next()) {
                cEmpId.add(rs.getString("empId"));
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
        
        JLabel lblfname = new JLabel("Full Name");
        lblfname.setBounds(400, 150, 200, 30);
        lblfname.setFont(new Font("serif", Font.BOLD, 20));
        add(lblfname);
        
        txtfname = new JTextField();
        txtfname.setBounds(600, 150, 150, 30);
        txtfname.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(txtfname);
        
        JLabel lblrollno = new JLabel("Employee Id");
        lblrollno.setBounds(50, 200, 200, 30);
        lblrollno.setFont(new Font("serif", Font.BOLD, 20));
        add(lblrollno);
        
        labelEmpId = new JLabel();
        labelEmpId.setBounds(200, 200, 200, 30);
        labelEmpId.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(labelEmpId);
        
        JLabel lbldob = new JLabel("CourseID");
        lbldob.setBounds(400, 200, 200, 30);
        lbldob.setFont(new Font("serif", Font.BOLD, 20));
        add(lbldob);
        
        ccourse = new Choice();
        ccourse.setBounds(600, 200, 150, 30);
        ccourse.setFont(new Font("serif", Font.BOLD, 20));
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
        
        JLabel lbldepartment = new JLabel("Department");
        lbldepartment.setBounds(400, 250, 200, 30);
        lbldepartment.setFont(new Font("serif", Font.BOLD, 20));
        add(lbldepartment);
        
        cdepartment = new Choice();
        cdepartment.setBounds(600, 250, 150, 30);
        add(cdepartment);
        
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from department");
            while(rs.next()) {
                cdepartment.add(rs.getString("DepartmentID"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
       
        try {
            Conn c = new Conn();
            String query = "select * from teacher where empId='"+cEmpId.getSelectedItem()+"'";
            ResultSet rs = c.s.executeQuery(query);
            while(rs.next()) {
                txtname.setText(rs.getString("name"));
                txtfname.setText(rs.getString("fname"));
                labelEmpId.setText(rs.getString("empId"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        cEmpId.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                try {
                    Conn c = new Conn();
                    String query = "select * from teacher where empId='"+cEmpId.getSelectedItem()+"'";
                    ResultSet rs = c.s.executeQuery(query);
                    while(rs.next()) {
                        txtname.setText(rs.getString("name"));
                        txtfname.setText(rs.getString("fname"));
                        labelEmpId.setText(rs.getString("empId"));
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
            String empId = labelEmpId.getText();
            String course = ccourse.getSelectedItem();
            String name = txtname.getText();            
            String fname = txtfname.getText();
            String department = cdepartment.getSelectedItem();
            try {
                String query = "update teacher set name='"+name+"', fname='"+fname+"', courseID='"+course+"', department='"+department+"' where empId='"+empId+"'";
                Conn con = new Conn();
                con.s.executeUpdate(query);
                
                JOptionPane.showMessageDialog(null, "Teacher Details Updated Successfully");
                setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            setVisible(false);
        }
    }
    
    public static void main(String[] args) {
        new UpdateTeacher();
    }
}
