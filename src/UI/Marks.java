package UI;

import BLL.StudentGrade;
import BUS.StudentGradeBUS;
import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

public class Marks extends JFrame implements ActionListener {
    
    DefaultTableModel modelGrade = new DefaultTableModel();
    StudentGradeBUS busGrade = new StudentGradeBUS();
    ArrayList<StudentGrade> arr = new ArrayList<StudentGrade>();
    ArrayList<StudentGrade> tempsearch = new ArrayList<StudentGrade>();


    
    String EnorllmentID;
    JButton cancel,searchs;
    JTextField txtsearch;
    JTable table;
    JComboBox cbBox;
    
    Random ran = new Random();
    long first4 = Math.abs((ran.nextLong() % 9000L) + 1000L);
    
            private void load(){
                StudentGradeBUS bus = new StudentGradeBUS();       
                try{
                   bus.docGrade();
               }catch(Exception e){
                   JOptionPane.showMessageDialog(null, "Lỗi kết nối đến Database.");
                   return;
               }
               Vector header = new Vector();
                header.add("EnrollmentID");
                header.add("CourseID");
                header.add("StudentID");
                header.add("Grade");
                    modelGrade = new DefaultTableModel(header,0){
                    public boolean isCellEditable(int row, int column)
                        {
                          return false;
                        }
               };	
               showOnTable(bus.list);
            }

            private void showOnTable(ArrayList<StudentGrade> list){
                modelGrade.setRowCount(0);
                for(StudentGrade gd:list){
                   Vector data = setVector(gd);
                   modelGrade.addRow(data);
               }
               table.setModel(modelGrade);
            }

            private Vector setVector(StudentGrade gd){
                    Vector head = new Vector();
                    head.add(gd.getEnrollID());
                    head.add(gd.getCourseID());
                    head.add(gd.getStudentID());
                    head.add(gd.getGrade());
                    return head;
            }   
    
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
        
        cbBox = new JComboBox();
        cbBox.setBounds(10, 100, 200, 30);
        cbBox.addItem("EnrollmentID");
        cbBox.addItem("CourseID");
        cbBox.addItem("StudentID");
        cbBox.addItem("Grade");

        cbBox.setFont(new Font("Tahoma", Font.BOLD, 18));
        add(cbBox);
        
        txtsearch = new JTextField();
        txtsearch.setBounds(220, 100, 300, 30);
        txtsearch.setFont(new Font("Tahoma", Font.BOLD, 18));
        add(txtsearch);
        
        searchs = new JButton("Search");
        searchs.setBounds(50, 200, 100, 50);
        searchs.setFont(new Font("Tahoma", Font.BOLD, 18));
        searchs.addActionListener(this);
        add(searchs);
 
        table = new JTable();
        
        load();
        
        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(0, 300, 600, 600);
        add(jsp);
        
        cancel = new JButton("Back");
        cancel.setBounds(375, 200, 120, 50);
        cancel.addActionListener(this);
        cancel.setFont(new Font("Tahoma", Font.BOLD, 18));
        add(cancel);
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
             if (ae.getSource() == searchs) {
                        String[] header = {"EnrollmentID", "CourseID", "StudentID", "Grade"};
                        DefaultTableModel modelsearch = new DefaultTableModel(header, 0);
                        ArrayList<StudentGrade> s;
                        s = busGrade.timkiemSanPham(String.valueOf(cbBox.getSelectedItem()), txtsearch.getText().toLowerCase().trim());
                        if (s.size() != 0) {
                            for (int i = 0; i < s.size(); i++) {
                                Object[] row = {s.get(i).getEnrollID(), s.get(i).getCourseID(), s.get(i).getStudentID(), s.get(i).getGrade()
                                };
                                modelsearch.addRow(row);
                            }
                            tempsearch.addAll(arr);
                            arr.clear();
                            arr.addAll(s);

                            table.setModel(modelsearch);
                        } else {
                            JOptionPane.showMessageDialog(null, "Không có kết quả phù hợp!");
                        }
            }else {
                setVisible(false);
            }
    }
    
    public static void main(String[] args) {
        new Marks("");
    }
}
