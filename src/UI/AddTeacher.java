package UI;

import BLL.Teacher;
import BUS.TeacherBUS;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import com.toedter.calendar.JDateChooser;
import java.awt.event.*;
import java.sql.ResultSet;

public class AddTeacher extends JFrame implements ActionListener{
            TeacherBUS bus = new TeacherBUS();

    
            JTextField tfname, fname;
            JTextField txtmagv;
            Choice ccourse, cdepartment;
            JButton submit, cancel;

            StringBuilder sb = new StringBuilder();
            Vector vector= new Vector();
            
            Random ran = new Random();
            long first4 = Math.abs((ran.nextLong() % 9000L) + 1000L);
            
            public Teacher getText() {
                Teacher tc = new Teacher();
                tc.setName(tfname.getText().trim());
                tc.setFname(fname.getText().trim());
                tc.setTeacherid(txtmagv.getText().trim());
                tc.setCourseid(ccourse.getSelectedItem());
                tc.setDepartid(cdepartment.getSelectedItem());
                return tc;
            }

            private Vector setVector(Teacher sv){
                    Vector head = new Vector();
                    head.add(sv.getTeacherid());
                    head.add(sv.getName());
                    head.add(sv.getFname());
                    head.add(sv.getCourseid());
                    head.add(sv.getDepartid());
                    return head;
            }    
            
            public void initdata() {
                    txtmagv.setEditable(false);
            }
            
//            private Vector setID(Teacher sv){
//                    Vector head = new Vector();
//                    head.add(sv.getTeacherid());
//                    return head;
//            }   
//            
//            private void showOnTable(ArrayList<Teacher> list){
//            for(Teacher kh:list){
//                         Vector data = setVector(kh);
//                         sb.append(kh+" ");
//                         ccourse.addItem(data);
//           }
//                        tb_kh.setModel(model);
//            }
//            
//            private void load(){
//            TeacherBUS bus1 = new TeacherBUS();       
//            try{
//               bus.docID();
//           }catch(Exception e){
//               JOptionPane.showMessageDialog(null, "Lỗi kết nối đến Database.");
//               return;
//           }
//           ccourse.add(bus1.docID());
//        }
    
        AddTeacher() {
        
            setSize(900, 500);
            setLocation(350, 50);

            setLayout(null);

            JLabel heading = new JLabel("New Teacher Details");
            heading.setBounds(310, 30, 500, 50);
            heading.setFont(new Font("serif", Font.BOLD, 30));
            add(heading);

            JLabel lblname = new JLabel("Name");
            lblname.setBounds(50, 150, 100, 30);
            lblname.setFont(new Font("serif", Font.BOLD, 20));
            add(lblname);

            tfname = new JTextField();
            tfname.setBounds(200, 150, 150, 30);
            add(tfname);

            JLabel lblfname = new JLabel("First Name");
            lblfname.setBounds(400, 150, 200, 30);
            lblfname.setFont(new Font("serif", Font.BOLD, 20));
            add(lblfname);

            fname = new JTextField();
            fname.setBounds(600, 150, 150, 30);
            add(fname);

            JLabel lblempId = new JLabel("Employee Id");
            lblempId.setBounds(50, 200, 200, 30);
            lblempId.setFont(new Font("serif", Font.BOLD, 20));
            add(lblempId);

            txtmagv = new JTextField("0"+first4);
            txtmagv.setBounds(200, 200, 110, 30);
            txtmagv.setFont(new Font("serif", Font.BOLD, 20));
            add(txtmagv);

            JLabel lbldob = new JLabel("Department");
            lbldob.setBounds(400, 200, 200, 30);
            lbldob.setFont(new Font("serif", Font.BOLD, 20));
            add(lbldob);

            cdepartment = new Choice();
            cdepartment.setBounds(600, 200, 200, 50);
            cdepartment.setFont(new Font("serif", Font.BOLD, 20));
            add(cdepartment);

            try {
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery("SELECT * FROM `department` ");
                while(rs.next()) {
                    cdepartment.add(rs.getString("DepartmentID"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            JLabel lbladdress = new JLabel("Course ID");
            lbladdress.setBounds(50, 250, 100, 30);
            lbladdress.setFont(new Font("serif", Font.BOLD, 20));
            add(lbladdress);

            ccourse = new Choice();
            ccourse.setBounds(200, 250, 200, 50);
            ccourse.setFont(new Font("serif", Font.BOLD, 20));
            add(ccourse);

            try {
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery("SELECT * FROM course");
                while(rs.next()) {
                    ccourse.add(rs.getString("CourseID"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            submit = new JButton("Add");
            submit.setBounds(200, 350, 100, 50);
            submit.addActionListener(this);
            submit.setFont(new Font("serif", Font.BOLD, 20));
            add(submit);

            cancel = new JButton("Cancel");
            cancel.setBounds(400, 350, 100, 50);
            cancel.addActionListener(this);
            cancel.setFont(new Font("serif", Font.BOLD, 20));
            add(cancel);
            
            initdata();
            
        setVisible(true);
    }
    
            
    
    public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == submit) {
                        Teacher tc = getText();
                        Vector head = setVector(tc);
                        int check = bus.themTC(tc);
                        if(check == 1){ 
                                    JOptionPane.showMessageDialog(null, "Thêm thành công");
                                    setVisible(false);
                                    }else{JOptionPane.showMessageDialog(null, "Thêm thất bại");
                                    setVisible(false);}
            }else { 
                        setVisible(false);
            }
    }
    
    public static void main(String[] args) {
        new AddTeacher();
    }
}
