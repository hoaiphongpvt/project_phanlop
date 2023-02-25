package UI;

import BLL.Course;
import BLL.OnlineCourse;
import BUS.CourseBUS;
import BUS.OnlineCourseBUS;
import com.mysql.fabric.Response;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

public class Onlinecourse extends JFrame implements ActionListener{
    
            ArrayList<OnlineCourse> arr = new ArrayList<OnlineCourse>();
            ArrayList<OnlineCourse> tempsearch = new ArrayList<OnlineCourse>();
    
            DefaultTableModel model = new DefaultTableModel();
            OnlineCourseBUS bus = new OnlineCourseBUS();
            
            CourseBUS busc = new CourseBUS();
            
            Vector header = new Vector();
    
            JTextField tfname,txturl,txtsearch,txtCourseID;
            JButton btnaddButton,btncancel,btnsubmit,btnsearch,reload;
            JTable tableOnl,tableCourse;
            JComboBox cbsearch;
            
            String randomLink = "https://www.fineartschool.net/" + UUID.randomUUID().toString();
            
            Random ran = new Random();
            long first4 = Math.abs((ran.nextLong() % 9000L) + 1000L);
            
            private void load(){
                OnlineCourseBUS bus = new OnlineCourseBUS();       
                try{
                   bus.doc();
               }catch(Exception e){
                   JOptionPane.showMessageDialog(null, "Lỗi kết nối đến Database.");
                   return;
               }
                header = new Vector();
                header.add("Course ID");
                header.add("URL");
                    model = new DefaultTableModel(header,0){
                    public boolean isCellEditable(int row, int column)
                        {
                          return false;
                        }
               };	
               showOnTable(bus.list);
            }

            private void showOnTable(ArrayList<OnlineCourse> list){
                model.setRowCount(0);
                for(OnlineCourse onl:list){
                   Vector data = setVector(onl);
                   model.addRow(data);
               }
               tableOnl.setModel(model);
            }

            private Vector setVector(OnlineCourse sv){
                    header = new Vector();
                    header.add(sv.getCourseID());
                    header.add(sv.getUrl());
                    return header;
            }    
            
            private void loadcourse(){
                CourseBUS busc = new CourseBUS();       
                try{
                   busc.docCourse();
               }catch(Exception e){
                   JOptionPane.showMessageDialog(null, "Lỗi kết nối đến Database.");
                   return;
               }
                header = new Vector();
                header.add("Course ID");
                header.add("Title");
                    model = new DefaultTableModel(header,0){
                    public boolean isCellEditable(int row, int column)
                        {
                          return false;
                        }
               };	
               showOnTablecourse(busc.list);
            }

            private void showOnTablecourse(ArrayList<Course> list){
                model.setRowCount(0);
                for(Course cs:list){
                   Vector data = setVectorcourse(cs);
                   model.addRow(data);
               }
               tableCourse.setModel(model);
            }

            private Vector setVectorcourse(Course csCourse){
                    header = new Vector();
                    header.add(csCourse.getCourseID());
                    header.add(csCourse.getTitle());
                    return header;
            }   
            
            public void setModelValuec(Course cs, int i) {
                model.setValueAt(cs.getCourseID(), i, 0);
                tableCourse.setModel(model);
            }
            
            public void setModelValue(OnlineCourse cs, int i) {
                model.setValueAt(cs.getCourseID(), i, 0);
                model.setValueAt(cs.getUrl(), i, 1);
                tableOnl.setModel(model);
            }
    
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
        
        txtCourseID = new JTextField();
        txtCourseID.setBounds(150, 90, 100, 30);
        txtCourseID.setFont(new Font("serif", Font.BOLD, 20));
        add(txtCourseID);
        
        JLabel lblurl = new JLabel("URL");
        lblurl.setBounds(300, 80, 100, 45);
        lblurl.setFont(new Font("serif", Font.BOLD, 20));
        add(lblurl);
        
        txturl = new JTextField(randomLink);
        txturl.setBounds(350, 80, 300, 45);
        txturl.setFont(new Font("serif", Font.BOLD, 20));
        add(txturl);
        
        cbsearch = new JComboBox();
        cbsearch.setBounds(50, 170, 200, 40);
        cbsearch.setFont(new Font("serif", Font.BOLD, 20));
        cbsearch.addItem("CourseID");
        cbsearch.addItem("URL");
        add(cbsearch);
        
        txtsearch = new JTextField();
        txtsearch.setBounds(260, 170, 200, 40);
        txtsearch.setFont(new Font("serif", Font.BOLD, 20));
        add(txtsearch);
        
        reload = new JButton("Reload");
        reload.setBounds(500, 170, 100, 50);
        reload.addActionListener(this);
        reload.setFont(new Font("serif", Font.BOLD, 20));
        add(reload);
        
        btnaddButton = new JButton("Add");
        btnaddButton.setBounds(50, 230, 100, 50);
        btnaddButton.addActionListener(this);
        btnaddButton.setFont(new Font("serif", Font.BOLD, 20));
        add(btnaddButton);
        
        btnsubmit = new JButton("Submit");
        btnsubmit.setBounds(200, 230, 100, 50);
        btnsubmit.addActionListener(this);
        btnsubmit.setFont(new Font("serif", Font.BOLD, 20));
        add(btnsubmit);
        
        btnsearch = new JButton("Search");
        btnsearch.setBounds(350, 230, 100, 50);
        btnsearch.addActionListener(this);
        btnsearch.setFont(new Font("serif", Font.BOLD, 20));
        add(btnsearch);
        
        btncancel = new JButton("Cancel");
        btncancel.setBounds(500, 230, 100, 50);
        btncancel.addActionListener(this);
        btncancel.setFont(new Font("serif", Font.BOLD, 20));
        add(btncancel);
        
        tableOnl = new JTable();
        
        load();
        
        JScrollPane jsp = new JScrollPane(tableOnl);
        jsp.setBounds(200, 300, 580, 500);
        add(jsp);
        
        tableCourse = new JTable();
        
        loadcourse();
        
        JScrollPane jsp1 = new JScrollPane(tableCourse);
        jsp1.setBounds(0, 300, 200, 500);
        add(jsp1);
        
            tableCourse.addMouseListener(new java.awt.event.MouseAdapter() {
                                   public void mouseClicked(java.awt.event.MouseEvent evt) {
                                       tb_Course(evt);
                                   }
            });
            
            tableOnl.addMouseListener(new java.awt.event.MouseAdapter() {
                                   public void mouseClicked(java.awt.event.MouseEvent evt) {
                                       tb_OnlineCourse(evt);
                                   }
            });
        
        setVisible(true);
        
    }
    
    public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == btnsearch) {
                      String[] header = {"CourseID", "URL"};
                      DefaultTableModel modelsearch = new DefaultTableModel(header, 0);
                      ArrayList<OnlineCourse> s;
                      s = bus.timkiem(String.valueOf(cbsearch.getSelectedItem()), txtsearch.getText().toLowerCase().trim());
                      if (s.size() != 0) {
                          for (int i = 0; i < s.size(); i++) {
                              Object[] row = {s.get(i).getCourseID(), s.get(i).getUrl()
                              };
                              modelsearch.addRow(row);
                          }
                          tempsearch.addAll(arr);
                          arr.clear();
                          arr.addAll(s);
                          tableOnl.setModel(modelsearch);
                      } else {
                          JOptionPane.showMessageDialog(null, "Không có kết quả phù hợp!");
                      }
                      }else if (ae.getSource() == btnaddButton) {
                                  OnlineCourse cs = new OnlineCourse();
                                  cs.setCourseID(txtCourseID.getText());
                                  cs.setUrl(txturl.getText());
                                  int check = bus.them(cs);
                                  if(check == 1){ 
                                      JOptionPane.showMessageDialog(null, "Thêm thành công");
                                      setVisible(false);
                                      }else{JOptionPane.showMessageDialog(null, "Thêm thất bại");
                                      setVisible(false);
                                  }
                      }else if(ae.getSource() == btnsubmit) {
                                  int i = tableOnl.getSelectedRow();
                                  OnlineCourse s = new OnlineCourse();
                                  s.setCourseID(txtCourseID.getText());
                                  s.setUrl(txturl.getText());
                                  int check = bus.sua(s, i);
                                  if (check == 1) {
                                      setModelValue(s, i);
                                      JOptionPane.showMessageDialog(null, "Sửa thành công");
                                  }
                                  else {
                                  JOptionPane.showMessageDialog(null, "Sửa thất bại");
                                  setVisible(false);
                              }
                      } else {
                                  setVisible(false);
                        }
    }

            private void tb_Course(java.awt.event.MouseEvent evt) {                                        
                        int i = tableCourse.getSelectedRow();
                        if (i >= 0) {
                                txtCourseID.setText(tableCourse.getModel().getValueAt(i, 0).toString());
                        }
            }
            
            private void tb_OnlineCourse(java.awt.event.MouseEvent evt) {                                        
                        int i = tableOnl.getSelectedRow();
                        if (i >= 0) {
                                txtCourseID.setText(tableOnl.getModel().getValueAt(i, 0).toString());
                                txturl.setText(tableOnl.getModel().getValueAt(i, 1).toString());
                        }
            }
    
    public static void main(String[] args) {
        new Onlinecourse();
    }
    
}
