package UI;

import BLL.Course;
import BLL.Department;
import BUS.CourseBUS;
import BUS.DepartmentBUS;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.awt.Button;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Management_Course extends JFrame implements ActionListener{
    
            ArrayList<Course> arr = new ArrayList<Course>();
            ArrayList<Course> tempsearch = new ArrayList<Course>();
            CourseBUS bus = new CourseBUS();
    
            JLabel lblcourseID,lblTitlle,lblCredits,lblDepartment;
            JTextField txt_courseid,txt_Title,txt_credits,txt_departmentid,txt_tk;
            Button add,edit,cancel,search;
            DefaultTableModel model = new DefaultTableModel();
            JTable tb_course,tb_depart;
            JComboBox cbsearch;

            Random ran = new Random();
            long first4 = Math.abs((ran.nextLong() % 9000L) + 1000L);
            int rd = (int) Math.abs((ran.nextInt() % 9L) + 1L);
            Random r  = new Random(first4);
            
            
            public void init() {
                txt_courseid.setEditable(false);
                txt_departmentid.setEditable(false);
            }
            
            public void load() {
                CourseBUS bus = new CourseBUS();
                try {
                    bus.docCourse();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Lỗi kết nối đến Database.");
                    return;
                }
                Vector header = new Vector();
                header.add("Course ID");
                header.add("Title");
                header.add("Credits");
                header.add("DepartmentID");
                model = new DefaultTableModel(header, 0) {
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                showOnTable(bus.list);
            }
            
            public void showOnTable(ArrayList<Course> list) {
                model.setRowCount(0);
                for (Course s : list) {
                    Vector data = setVector(s);
                    model.addRow(data);
                }
                tb_course.setModel(model);
            }
            
            public Vector setVector(Course s) {
                Vector head = new Vector();
                head.add(s.getCourseID());
                head.add(s.getTitle());
                head.add(s.getCredits());
                head.add(s.getDepartmentID());
                return head;
            }
            
            //load dữ liệu lên table
            public void loadDepart() {
                DepartmentBUS bus = new DepartmentBUS();
                try {
                    bus.docDepartment();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Lỗi kết nối đến Database.");
                    return;
                }
                Vector header = new Vector();
                header.add("Department ID");
                header.add("Name");
                model = new DefaultTableModel(header, 0) {
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                showOnTableDepartment(bus.listdepart);
            }
            
            public void showOnTableDepartment(ArrayList<Department> list) {
                model.setRowCount(0);
                for (Department depart : list) {
                    Vector data = setVectorDepartment(depart);
                    model.addRow(data);
                }
                tb_depart.setModel(model);
            }
            
            public Vector setVectorDepartment(Department depart) {
                Vector head = new Vector();
                head.add(depart.getDepartID());
                head.add(depart.getName());
                return head;
            }
            
            public Course getText() {
                Course s = new Course();
                s.setCourseID(txt_courseid.getText().trim());
                s.setTitle(txt_Title.getText().trim());
                s.setCredits(txt_credits.getText().trim());
                s.setDepartmentID(txt_departmentid.getText().trim());
                return s;
            }
            
            //lấy dữ liệu từ table lên
            public void setModelValue(Course cs, int i) {
                model.setValueAt(cs.getCourseID(), i, 0);
                model.setValueAt(cs.getTitle(), i, 1);
                model.setValueAt(cs.getCredits(), i, 2);
                model.setValueAt(cs.getDepartmentID(), i, 3);
                tb_course.setModel(model);
            }
            
            public void setModelValueDepart(Department cs, int i) {
                model.setValueAt(cs.getDepartID(), i, 0);
                tb_depart.setModel(model);
            }
    
            Management_Course() {
                        setSize(1300, 800);
                        setLocation(50, 10);

                        setLayout(null);
                        
                        JLabel heading = new JLabel("New Course Details");
                        heading.setBounds(500, 20, 500, 50);
                        heading.setFont(new Font("serif", Font.BOLD, 50));
                        add(heading);
                        
                        lblcourseID = new JLabel("Course ID");
                        lblcourseID.setBounds(50, 100, 150, 50);
                        lblcourseID.setFont(new Font("serif", Font.BOLD, 30));
                        add(lblcourseID);
                        
                        txt_courseid = new JTextField("0"+first4);
                        txt_courseid.setBounds(200, 100, 100, 50);
                        txt_courseid.setFont(new Font("serif", Font.ITALIC, 30));
                        add(txt_courseid);
                        
                        lblCredits = new JLabel("Credits");
                        lblCredits.setBounds(550, 100, 150, 50);
                        lblCredits.setFont(new Font("serif", Font.BOLD, 30));
                        add(lblCredits);
                        
                        txt_credits = new JTextField("0"+rd);
                        txt_credits.setBounds(700, 100, 50, 50);
                        txt_credits.setFont(new Font("serif", Font.BOLD, 30));
                        add(txt_credits);
                        
                        lblTitlle = new JLabel("Title Course");
                        lblTitlle.setBounds(50,170,200, 50);
                        lblTitlle.setFont(new Font("serif", Font.BOLD, 30));
                        add(lblTitlle);
                        
                        txt_Title = new JTextField();
                        txt_Title.setBounds(250,170,250, 50);
                        txt_Title.setFont(new Font("serif", Font.PLAIN, 30));
                        add(txt_Title);
                        
                        lblDepartment = new JLabel("Department ID");
                        lblDepartment.setBounds(550, 170, 250, 50);
                        lblDepartment.setFont(new Font("serif", Font.BOLD, 30));
                        add(lblDepartment);
                        
                        txt_departmentid = new JTextField();
                        txt_departmentid.setBounds(760, 170, 50, 50);
                        txt_departmentid.setFont(new Font("serif", Font.PLAIN, 30));
                        add(txt_departmentid);
                        
                        cbsearch = new JComboBox();
                        cbsearch.setBounds(50, 230, 200, 50);
                        cbsearch.setFont(new Font("serif", Font.BOLD, 30));
                        cbsearch.addItem("CourseID");
                        cbsearch.addItem("DepartmentID");
                        cbsearch.addItem("Title");
                        cbsearch.addItem("Credits");
                        add(cbsearch);
                        
                        search = new Button("Search");
                        search.setBounds(550, 230, 150, 50);
                        search.addActionListener(this);
                        search.setFont(new Font("serif", Font.BOLD, 30));
                        add(search);
                        
                        txt_tk = new JTextField();
                        txt_tk.setBounds(250, 230,250, 50);
                        txt_tk.setFont(new Font("serif", Font.BOLD, 30));
                        add(txt_tk);
                        
                        add = new Button("Add");
                        add.setBounds(900, 100, 70, 50);
                        add.addActionListener(this);
                        add.setFont(new Font("serif", Font.BOLD, 30));
                        add(add);
                        
                        edit = new Button("Edit");
                        edit.setBounds(900, 170, 70, 50);
                        edit.addActionListener(this);
                        edit.setFont(new Font("serif", Font.BOLD, 30));
                        add(edit);
                        
                        cancel = new Button("Cancel");
                        cancel.setBounds(900, 240, 100, 50);
                        cancel.addActionListener(this);
                        cancel.setFont(new Font("serif", Font.BOLD, 30));
                        add(cancel);
                        
                        JLabel lbltable = new JLabel("Bảng khóa học");
                        lbltable.setBounds(100, 300, 300, 50);
                        lbltable.setFont(new Font("serif", Font.BOLD, 30));
                        add(lbltable);
                        
                        tb_course = new JTable();
                        load();
                        JScrollPane jsp = new JScrollPane(tb_course);
                        jsp.setBounds(50, 350, 700, 410);
                        add(jsp);
                        
                        JLabel lbldepart = new JLabel("Bảng Khoa");
                        lbldepart.setBounds(800, 300, 300, 50);
                        lbldepart.setFont(new Font("serif", Font.BOLD, 30));
                        add(lbldepart);
                        
                        tb_depart = new JTable();
                        loadDepart();
                        JScrollPane jsp1 = new JScrollPane(tb_depart);
                        jsp1.setBounds(800, 350, 470, 410);
                        add(jsp1);
                        
                        tb_course.addMouseListener(new java.awt.event.MouseAdapter() {
                                public void mouseClicked(java.awt.event.MouseEvent evt) {
                                    tb_Course(evt);
                                }
                        });
                        
                        tb_depart.addMouseListener(new java.awt.event.MouseAdapter() {
                                public void mouseClicked(java.awt.event.MouseEvent evt) {
                                    tb_Depart(evt);
                                }
                        });
                    
                        
                        init();
                        setVisible(true);
            }
            
            public void searchtk(){
                model.setRowCount(0);
                for(Course cs:bus.timkiemtongquan(txt_tk.getText())){
                    model.addRow(new Object[]{
                        cs.getCourseID(),cs.getCredits(),cs.getTitle(),cs.getDepartmentID()
                    });
                }
            }      
            
            private void reload() {
                txt_Title.setText("");
                txt_credits.setText("");
                txt_departmentid.setText("");
                txt_tk.setText("");
                txt_courseid.setText("0"+first4);
            }
                        
            public void actionPerformed(ActionEvent ae) {
                if (ae.getSource() == search) {
                   String[] header = {"CourseID", "Title","Credits","DepartmentID"};
                   DefaultTableModel modelsearch = new DefaultTableModel(header, 0);
                   ArrayList<Course> s;
                   s = bus.timkiem(String.valueOf(cbsearch.getSelectedItem()), txt_tk.getText().toLowerCase().trim());
                   if (s.size() != 0) {
                       reload();
                       for (int i = 0; i < s.size(); i++) {
                           Object[] row = {s.get(i).getCourseID(), s.get(i).getTitle(), s.get(i).getCredits(), s.get(i).getDepartmentID()
                           };
                           modelsearch.addRow(row);
                       }
                       tempsearch.addAll(arr);
                       arr.clear();
                       arr.addAll(s);
                       tb_course.setModel(modelsearch);
                   } else {
                       JOptionPane.showMessageDialog(null, "Không có kết quả phù hợp!");
                   }
                   }else if (ae.getSource() == add) {
                               Course cs = getText();
                               Vector head = setVector(cs);
                               int check = bus.themCourse(cs);
                               if(check == 1){ 
                                   JOptionPane.showMessageDialog(null, "Thêm thành công");
                                   reload();
                                   setVisible(false);
                                   }else{JOptionPane.showMessageDialog(null, "Thêm thất bại");
                                   reload();
                                   setVisible(false);
                               }
                   }else if(ae.getSource() == edit) {
                               int i = tb_course.getSelectedRow();
                               Course s = getText();
                               int check = bus.suaCourse(s, i);
                               if (check == 1) {
                                   setModelValue(s, i);
                                   JOptionPane.showMessageDialog(null, "Sửa thành công");
                                   reload();
                               }
                               else {
                               JOptionPane.showMessageDialog(null, "Sửa thất bại");
                               reload();
                               setVisible(false);
                           }
                   } else {
                               setVisible(false);
                   }
           }
            
            private void tb_Course(java.awt.event.MouseEvent evt) {                                        
            int i = tb_course.getSelectedRow();
            if (i >= 0) {
                    txt_courseid.setText(tb_course.getModel().getValueAt(i, 0).toString());
                    txt_Title.setText(tb_course.getModel().getValueAt(i, 1).toString());
                    txt_credits.setText(tb_course.getModel().getValueAt(i, 2).toString());
                    txt_departmentid.setText(tb_course.getModel().getValueAt(i, 3).toString());
                    }
            }
            
            private void tb_Depart(java.awt.event.MouseEvent evt) {                                        
            int i = tb_depart.getSelectedRow();
            if (i >= 0) {
                    txt_departmentid.setText(tb_depart.getModel().getValueAt(i, 0).toString());
                    }
            }
            
            public static void main(String[] args) {
                      new Management_Course();
            }
    
}
