package UI;

import BLL.Course;
import BLL.Student;
import BLL.StudentGrade;
import BUS.CourseBUS;
import BUS.StudentBUS;
import BUS.StudentGradeBUS;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

public class EnterMarks extends JFrame implements ActionListener {

    ArrayList<StudentGrade> arr = new ArrayList<StudentGrade>();
    ArrayList<StudentGrade> tempsearch = new ArrayList<StudentGrade>();
    
    StudentGradeBUS bus = new StudentGradeBUS();
    CourseBUS buscourse = new CourseBUS();
    StudentBUS busstudent = new StudentBUS();

    
    DefaultTableModel model = new DefaultTableModel();
    
    JTextField studentid,ccourse,txt_tk,txt_enrollID;
    JTable tbMark,tbCourse,tbStudent;
    JComboBox cbsearch;
    JTextField grade;
    JButton cancel, submit,btnadd,search;
    
    
    Random ran = new Random();
    long first4 = Math.abs((ran.nextLong() % 9000L) + 1000L);
    
            public void load() {
                StudentGradeBUS bus = new StudentGradeBUS();
                try {
                    bus.docGrade();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Lỗi kết nối đến Database.");
                    return;
                }
                Vector header = new Vector();
                header.add("Enrollment ID");
                header.add("Course ID");
                header.add("Student ID");
                header.add("Grade");
                model = new DefaultTableModel(header, 0) {
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                showOnTable(bus.list);
            }
            
            public void showOnTable(ArrayList<StudentGrade> list) {
                model.setRowCount(0);
                for (StudentGrade s : list) {
                    Vector data = setVector(s);
                    model.addRow(data);
                }
                tbMark.setModel(model);
            }
            
            public Vector setVector(StudentGrade s) {
                Vector head = new Vector();
                head.add(s.getEnrollID());
                head.add(s.getCourseID());
                head.add(s.getStudentID());
                head.add(s.getGrade());
                return head;
            }
            
            public void loadCourse() {
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
                model = new DefaultTableModel(header, 0) {
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                showOnTablecourse(buscourse.list);
            }
            
            public void showOnTablecourse(ArrayList<Course> list) {
                model.setRowCount(0);
                for (Course cs : list) {
                    Vector data = setVectorcourse(cs);
                    model.addRow(data);
                }
                tbCourse.setModel(model);
            }
            
            public Vector setVectorcourse(Course s) {
                Vector head = new Vector();
                head.add(s.getCourseID());
                head.add(s.getTitle());
                return head;
            }
            
            public void loadstudent() {
                StudentBUS bus = new StudentBUS();
                try {
                    bus.docSV();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Lỗi kết nối đến Database.");
                    return;
                }
                Vector header = new Vector();
                header.add("Student ID");
                header.add("Last Name");
                header.add("First Name");
                model = new DefaultTableModel(header, 0) {
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                showOnTablestudent(busstudent.list);
            }
            
            public void showOnTablestudent(ArrayList<Student> list) {
                model.setRowCount(0);
                for (Student st : list) {
                    Vector data = setVectorstudent(st);
                    model.addRow(data);
                }
                tbStudent.setModel(model);
            }
            
            public Vector setVectorstudent(Student s) {
                Vector head = new Vector();
                head.add(s.getMasv());
                head.add(s.getLastname());
                head.add(s.getFirstname());
                return head;
            }
            
            //lấy dữ liệu
            public void setModelValuestudent(Student st, int i) {
                model.setValueAt(st.getMasv(), i, 0);
                tbStudent.setModel(model);
            }
            
            public void setModelValuecourse(Course cs, int i) {
                model.setValueAt(cs.getCourseID(), i, 0);
                tbCourse.setModel(model);
            }
            
            public void setModelValuemark(StudentGrade mark, int i) {
                model.setValueAt(mark.getEnrollID(), i, 0);
                model.setValueAt(mark.getCourseID(), i, 1);
                model.setValueAt(mark.getStudentID(), i, 2);
                model.setValueAt(mark.getGrade(), i, 3);
                tbMark.setModel(model);
            }
    
    EnterMarks() {
        
        setSize(1000, 1000);
        setLocation(0 ,0);
        setLayout(null);
        
        getContentPane().setBackground(Color.WHITE);
        
        
        tbCourse = new JTable();
        loadCourse();
        JScrollPane jsp1 = new JScrollPane(tbCourse);
        jsp1.setBounds(500, 20, 400, 100);
        add(jsp1);
        
        tbStudent = new JTable();
        loadstudent();
        JScrollPane jsp2 = new JScrollPane(tbStudent);
        jsp2.setBounds(500,150, 400, 100);
        add(jsp2);
 
        JLabel heading = new JLabel("Enter Marks of Student");
        heading.setBounds(50, 0, 500, 50);
        heading.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(heading);
        
        JLabel lblrollnumber = new JLabel("Select Student Number");
        lblrollnumber.setBounds(50, 70, 150, 20);
        add(lblrollnumber);
        
        studentid = new JTextField();
        studentid.setBounds(200, 70, 150, 20);
        add(studentid);
        
        JLabel lblenrollment = new JLabel("EnrollmentID");
        lblenrollment.setBounds(50, 120, 100, 20);
        add(lblenrollment);
        
        txt_enrollID = new JTextField(""+first4);
        txt_enrollID.setBounds(150, 120, 200, 20);
        add(txt_enrollID);
        
        JLabel lblentersubject = new JLabel("Course selection");
        lblentersubject.setBounds(100, 150, 200, 40);
        add(lblentersubject);
        
        ccourse = new JTextField();
        ccourse.setBounds(50, 200, 200, 20);
        add(ccourse);
        
        JLabel lblentermarks = new JLabel("Enter Grade");
        lblentermarks.setBounds(320, 150, 200, 40);
        add(lblentermarks);

        grade = new JTextField();
        grade.setBounds(250, 200, 200, 20);
        add(grade);
        
        cbsearch = new JComboBox();
        cbsearch.setBounds(50, 250, 150, 20);
        cbsearch.addItem("EnrollmentID");
        cbsearch.addItem("CourseID");
        cbsearch.addItem("StudentID");
        add(cbsearch);
        
        txt_tk = new JTextField();
        txt_tk.setBounds(200, 250, 200, 20);
        add(txt_tk);
        
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
        
        tbMark = new JTable();
        
        load();
        
        JScrollPane jsp = new JScrollPane(tbMark);
        jsp.setBounds(50, 400, 750, 300);
        add(jsp);
        
                        tbCourse.addMouseListener(new java.awt.event.MouseAdapter() {
                                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                tb_Course(evt);
                                    }
                        });
                        tbStudent.addMouseListener(new java.awt.event.MouseAdapter() {
                                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                tb_Student(evt);
                                    }
                        });
                        tbMark.addMouseListener(new java.awt.event.MouseAdapter() {
                                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                tb_Mark(evt);
                                    }
                        });
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == search) {
                   String[] header = {"EnrollmentID", "CourseID","StudentID","Grade"};
                   DefaultTableModel modelsearch = new DefaultTableModel(header, 0);
                   ArrayList<StudentGrade> st;
                   st = bus.timkiem(String.valueOf(cbsearch.getSelectedItem()), txt_tk.getText().toLowerCase().trim());
                   if (st.size() != 0) {
                       for (int i = 0; i < st.size(); i++) {
                           Object[] row = {st.get(i).getEnrollID(), st.get(i).getCourseID(), st.get(i).getStudentID(), st.get(i).getGrade()
                           };
                           modelsearch.addRow(row);
                       }
                       tempsearch.addAll(arr);
                       arr.clear();
                       arr.addAll(st);
                       tbMark.setModel(modelsearch);
                   } else {
                       JOptionPane.showMessageDialog(null, "Không có kết quả phù hợp!");
                   }
                   }
                        else if (ae.getSource() == btnadd) {
                               StudentGrade cs = new StudentGrade();
                               cs.setEnrollID(txt_enrollID.getText());
                               cs.setCourseID(ccourse.getText());
                               cs.setStudentID(studentid.getText());
                               cs.setGrade(grade.getText());
                               int check = bus.them(cs);
                               if(check == 1){ 
                                   JOptionPane.showMessageDialog(null, "Thêm thành công");
                                   setVisible(false);
                                   }else{JOptionPane.showMessageDialog(null, "Thêm thất bại");
                                   setVisible(false);
                               }
                   }
                        else if(ae.getSource() == submit) {
                               int i = tbMark.getSelectedRow();
                               StudentGrade s = new StudentGrade();
                               s.setEnrollID(txt_enrollID.getText());
                               s.setCourseID(ccourse.getText());
                               s.setStudentID(studentid.getText());
                               s.setGrade(grade.getText());
                               int check = bus.sua(s, i);
                               if (check == 1) {
                                   setModelValuemark(s, i);
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
                        int i = tbCourse.getSelectedRow();
                        if (i >= 0) {
                                ccourse.setText(tbCourse.getModel().getValueAt(i, 0).toString());
                        }
            }
            
            private void tb_Student(java.awt.event.MouseEvent evt) {                                        
            int i = tbStudent.getSelectedRow();
                        if (i >= 0) {
                                    studentid.setText(tbStudent.getModel().getValueAt(i, 0).toString());
                        }
            }
            
            private void tb_Mark(java.awt.event.MouseEvent evt) {                                        
            int i = tbMark.getSelectedRow();
                        if (i >= 0) {
                                    txt_enrollID.setText(tbMark.getModel().getValueAt(i, 0).toString());
                                    ccourse.setText(tbMark.getModel().getValueAt(i, 1).toString());
                                    studentid.setText(tbMark.getModel().getValueAt(i, 2).toString());
                                    grade.setText(tbMark.getModel().getValueAt(i, 3).toString());
                        }
            }

    public static void main(String[] args) {
        new EnterMarks();
    }
}
