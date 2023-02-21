package UI;

import com.toedter.calendar.JDateChooser;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import net.proteanit.sql.DbUtils;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class officeassignment extends JFrame implements ActionListener{
    
            JTextField tfname,txtlocal ;
            Choice cInstructorID;
            JButton btnaddButton,btncancel,btnsubmit,btnsearch;
            JTable table;
            JDateChooser dtime;
            JLabel lblid;
            
            Random ran = new Random();
            long first4 = Math.abs((ran.nextLong() % 9000L) + 1000L);
            
            officeassignment() {
                        setSize(800, 700);
                        setLocation(250, 50);
                        setLayout(null);

                        getContentPane().setBackground(Color.WHITE);

                        JLabel heading = new JLabel("Officeassignment");
                        heading.setBounds(50, 10, 400, 30);
                        heading.setFont(new Font("Tahoma", Font.BOLD, 30));
                        add(heading);

                        JLabel lblname = new JLabel("Instructor ID");
                        lblname.setBounds(50, 80, 120, 50);
                        lblname.setFont(new Font("serif", Font.BOLD, 20));
                        add(lblname);

                        cInstructorID = new Choice();
                        cInstructorID.setBounds(170, 90, 100, 60);
                        cInstructorID.setFont(new Font("serif", Font.BOLD, 20));
                        add(cInstructorID);

                        try {
                            Conn c = new Conn();
                            ResultSet rs = c.s.executeQuery("select * from officeassignment");
                            while(rs.next()) {
                                cInstructorID.add(rs.getString("InstructorID"));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        
                        lblid = new JLabel("1"+first4);
                        lblid.setBounds(50, 135, 120, 50);
                        lblid.setFont(new Font("serif", Font.BOLD, 20));
                        add(lblid);

                        JLabel lblurl = new JLabel("Location");
                        lblurl.setBounds(300, 80, 100, 45);
                        lblurl.setFont(new Font("serif", Font.BOLD, 20));
                        add(lblurl);

                        txtlocal = new JTextField();
                        txtlocal.setBounds(400, 80, 300, 45);
                        txtlocal.setFont(new Font("serif", Font.BOLD, 20));
                        add(txtlocal);
                        
                        JLabel lbltime = new JLabel("Time stamp");
                        lbltime.setBounds(300, 135, 200, 35);
                        lbltime.setFont(new Font("serif", Font.BOLD, 20));
                        add(lbltime);
                        
                        dtime = new JDateChooser();
                        dtime.setBounds(400, 135, 300, 45);
                        dtime.setFont(new Font("serif", Font.BOLD, 20));
                        add(dtime);

                        btnaddButton = new JButton("Add");
                        btnaddButton.setBounds(50, 200, 100, 50);
                        btnaddButton.addActionListener(this);
                        btnaddButton.setFont(new Font("serif", Font.BOLD, 20));
                        add(btnaddButton);

                        btnsubmit = new JButton("Edit");
                        btnsubmit.setBounds(200, 200, 100, 50);
                        btnsubmit.addActionListener(this);
                        btnsubmit.setFont(new Font("serif", Font.BOLD, 20));
                        add(btnsubmit);

                        btnsearch = new JButton("Search");
                        btnsearch.setBounds(350, 200, 100, 50);
                        btnsearch.addActionListener(this);
                        btnsearch.setFont(new Font("serif", Font.BOLD, 20));
                        add(btnsearch);

                        btncancel = new JButton("Cancel");
                        btncancel.setBounds(500, 200, 100, 50);
                        btncancel.addActionListener(this);
                        btncancel.setFont(new Font("serif", Font.BOLD, 20));
                        add(btncancel);

                        table = new JTable();

                        try {
                            Conn c = new Conn();
                            ResultSet rs = c.s.executeQuery("select * from officeassignment");
                            table.setModel(DbUtils.resultSetToTableModel(rs));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                        JScrollPane jsp = new JScrollPane(table);
                        jsp.setBounds(0, 300, 800, 500);
                        add(jsp);

                        setVisible(true);
            }

            public void actionPerformed(ActionEvent ae) {
                        if (ae.getSource() == btnsearch) {
                           String query = "select * from officeassignment where InstructorID = '"+cInstructorID.getSelectedItem()+"'";
                           try {
                               Conn c = new Conn();
                               ResultSet rs = c.s.executeQuery(query);
                               table.setModel(DbUtils.resultSetToTableModel(rs));
                           } catch (Exception e) {
                               e.printStackTrace();
                           }
                       }  else if (ae.getSource() == btnaddButton) {
                           String courseid = lblid.getText();
                           String local = txtlocal.getText();
                           String time = ((JTextField) dtime.getDateEditor().getUiComponent()).getText();
                           try {
                               String query = "insert into officeassignment values('"+courseid+"', '"+local+"', '"+time+"')";

                               Conn con = new Conn();
                               con.s.executeUpdate(query);

                               JOptionPane.showMessageDialog(null, "Officeassignment Inserted Successfully");
                           } catch (Exception e) {
                               e.printStackTrace();
                           }
                       } else if (ae.getSource() == btnsubmit) {
                           String InstructorString = lblid.getText();
                           String local = txtlocal.getText();
                           String time = ((JTextField) dtime.getDateEditor().getUiComponent()).getText();
                           try {
                               String query = "update officeassignment set Location='"+local+"', Timestamp='"+time+"' where InstructorID='"+InstructorString+"'";
                               Conn con = new Conn();
                               con.s.executeUpdate(query);

                               JOptionPane.showMessageDialog(null, "Officeassignment Updated Successfully");
                           } catch (Exception e) {
                               e.printStackTrace();
                           }
                       } else {
                           setVisible(false);
                       }
            }
            
        public static void main(String[] args) {
                new officeassignment();
        }
    
    
}
