package UI;
//ý tưởng tạo 3 bảng để hiển thị 3 thông tin cần thiết và để lấy dữ liệu từ 2 bảng 
//để tạo thành 1 bảng xuất dữ liệu 
import BLL.Course;
import BLL.CourseInstructor;
import BLL.Teacher;
import BUS.CourseBUS;
import BUS.CourseInstructorBUS;
import BUS.TeacherBUS;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

public class Courseinstructor extends JFrame implements ActionListener{
    
            ArrayList<CourseInstructor> arr = new ArrayList<CourseInstructor>();
            ArrayList<CourseInstructor> tempsearch = new ArrayList<CourseInstructor>();
    
            DefaultTableModel modelgv = new DefaultTableModel();
            TeacherBUS busgv = new TeacherBUS();

            DefaultTableModel modelcourse = new DefaultTableModel();
            CourseBUS buscourse = new CourseBUS();
            
            DefaultTableModel modelcourseinstructor = new DefaultTableModel();
            CourseInstructorBUS buscourseinstructor = new CourseInstructorBUS();
            
            Vector header = new Vector();

            JTextField txtCourseID,txtPersonID;
            JButton btnaddButton,btncancel,btnsubmit,btnsearch;
            JTable tb_teacher,tbcourse,tbsearch;
            JComboBox cbsearch;
            JTextField txt_search;
            
            public CourseInstructor getText() {
                CourseInstructor cs = new CourseInstructor();
                cs.setCourseID(txtCourseID.getText().trim());
                cs.setTeacherID(txtPersonID.getText().trim());
                return cs;
            }

            //load bảng teacher
            
            private void load(){
                TeacherBUS bus = new TeacherBUS();       
                try{
                   bus.docTC();
               }catch(Exception e){
                   JOptionPane.showMessageDialog(null, "Lỗi kết nối đến Database.");
                   return;
               }
                header = new Vector();
                header.add("PersonID");
                header.add("Lastname");
                header.add("Firstname");
                    modelgv = new DefaultTableModel(header,0){
                    public boolean isCellEditable(int row, int column)
                        {
                          return false;
                        }
               };	
               showOnTable(bus.list);
            }

            private void showOnTable(ArrayList<Teacher> list){
                modelgv.setRowCount(0);
                for(Teacher gv:list){
                   Vector data = setVector(gv);
                   modelgv.addRow(data);
               }
               tb_teacher.setModel(modelgv);
            }

            private Vector setVector(Teacher sv){
                    header = new Vector();
                    header.add(sv.getTeacherid());
                    header.add(sv.getName());
                    header.add(sv.getFname());
                    return header;
            }    

            //load bảng course
            
            private void loadcourse(){
                CourseBUS buscourse = new CourseBUS();       
                try{
                   buscourse.docCourse();
               }catch(Exception e){
                   JOptionPane.showMessageDialog(null, "Lỗi kết nối đến Database.");
                   return;
               }
                header = new Vector();
                header.add("Course ID");
                header.add("Title");
                    modelcourse = new DefaultTableModel(header,0){
                    public boolean isCellEditable(int row, int column)
                        {
                          return false;
                        }
               };	
               showOnTablecourse(buscourse.list);
            }

            private void showOnTablecourse(ArrayList<Course> list){
                modelcourse.setRowCount(0);
                for(Course course:list){
                   Vector data = setVectorcourse(course);
                   modelcourse.addRow(data);
               }
               tbcourse.setModel(modelcourse);
            }

            private Vector setVectorcourse(Course sv){
                    header = new Vector();
                    header.add(sv.getCourseID());
                    header.add(sv.getTitle());
                    return header;
            }    
            
           //load bảng search courseinstructor
            
            private void loadsearch(){
                CourseInstructorBUS buscourse = new CourseInstructorBUS();       
                try{
                   buscourse.doc();
               }catch(Exception e){
                   JOptionPane.showMessageDialog(null, "Lỗi kết nối đến Database.");
                   return;
               }
               Vector header = new Vector();
                header.add("Course ID");
                header.add("Person ID");
                    modelcourseinstructor = new DefaultTableModel(header,0){
                    public boolean isCellEditable(int row, int column)
                        {
                          return false;
                        }
               };	
               showOnTablecourseinstructor(buscourseinstructor.list);
            }

            private void showOnTablecourseinstructor(ArrayList<CourseInstructor> list){
                modelcourseinstructor.setRowCount(0);
                for(CourseInstructor courseinstructor:list){
                   Vector data = setVectorCourseinstructor(courseinstructor);
                   modelcourseinstructor.addRow(data);
               }
               tbsearch.setModel(modelcourseinstructor);
            }

            private Vector setVectorCourseinstructor(CourseInstructor sv){
                    Vector head = new Vector();
                    head.add(sv.getCourseID());
                    head.add(sv.getTeacherID());
                    return head;
            }    
            
            //setvalue cho từng bảng
            
            public void setModelValueCourse(Course cs, int i) {
                modelcourse.setValueAt(cs.getCourseID(), i, 0);
                tbcourse.setModel(modelcourse);
            }
            
            public void setModelValuePerson(Teacher cs, int i) {
                modelgv.setValueAt(cs.getTeacherid(), i, 0);
                tb_teacher.setModel(modelgv);
            }
            
            public void setModelValue(CourseInstructor cs, int i) {
                modelcourseinstructor.setValueAt(cs.getCourseID(), i, 0);
                modelcourseinstructor.setValueAt(cs.getTeacherID(), i, 1);
                tbsearch.setModel(modelcourseinstructor);
            }
            
    Courseinstructor() {
        setSize(1000, 700);
        setLocation(250, 50);
        setLayout(null);
        
        getContentPane().setBackground(Color.WHITE);
        
        JLabel heading = new JLabel("Course Instructor");
        heading.setBounds(50, 10, 400, 30);
        heading.setFont(new Font("Tahoma", Font.BOLD, 30));
        add(heading);
        
        JLabel lblname = new JLabel("Course ID");
        lblname.setBounds(50, 80, 100, 30);
        lblname.setFont(new Font("serif", Font.BOLD, 20));
        add(lblname);
        
        txtCourseID = new JTextField();
        txtCourseID.setBounds(150, 80, 100, 30);
        txtCourseID.setFont(new Font("serif", Font.BOLD, 20));
        add(txtCourseID);
        
        JLabel lblurl = new JLabel("Person ID");
        lblurl.setBounds(300, 80, 100, 35);
        lblurl.setFont(new Font("serif", Font.BOLD, 20));
        add(lblurl);
        
        txtPersonID = new JTextField();
        txtPersonID.setBounds(400, 80, 150, 30);
        txtPersonID.setFont(new Font("serif", Font.BOLD, 20));
        add(txtPersonID);
        
        cbsearch = new JComboBox();
        cbsearch.setBounds(50, 150, 220, 30);
        cbsearch.addActionListener(this);
        cbsearch.setFont(new Font("serif", Font.BOLD, 20));
        cbsearch.addItem("CourseID");
        add(cbsearch);
        
        txt_search = new JTextField();
        txt_search.setBounds(275, 150, 200, 30);
        txt_search.addActionListener(this);
        txt_search.setFont(new Font("serif", Font.BOLD, 20));
        add(txt_search);
        
        btnaddButton = new JButton("Add");
        btnaddButton.setBounds(50, 230, 100, 50);
        btnaddButton.addActionListener(this);
        btnaddButton.setFont(new Font("serif", Font.BOLD, 20));
        add(btnaddButton);
        
        btnsubmit = new JButton("Edit");
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
        //bảng chọn giáo viên phân công
        tb_teacher = new JTable();
        
        load();
        
        JScrollPane jsp = new JScrollPane(tb_teacher);
        jsp.setBounds(300, 300, 680, 500);
        jsp.setFont(new Font("serif", Font.BOLD, 20));
        add(jsp);
        //bảng chọn môn học giảng dạy
        tbcourse = new JTable();
        
        loadcourse();

        JScrollPane jsp1 = new JScrollPane(tbcourse);
        jsp1.setBounds(0, 300, 300, 500);
        jsp1.setFont(new Font("serif", Font.BOLD, 20));
        add(jsp1);
        
        //bảng hiển thị thông tin phân công việc giảng dạy
        tbsearch = new JTable();
        
        loadsearch();
        
        JScrollPane jsp2 = new JScrollPane(tbsearch);
        jsp2.setBounds(620, 5, 350, 280);
        jsp2.setFont(new Font("serif", Font.BOLD, 20));
        add(jsp2);
        //Lấy dữ liệu từ bảng lên form
            tb_teacher.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            tb_Teacher(evt);
                        }
            });
            tbcourse.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            tb_Course(evt);
                        }
            });
            tbsearch.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            tb_Search(evt);
                        }
            });
        
        setVisible(true);
        
    }
    
    public void actionPerformed(ActionEvent ae) {
         if (ae.getSource() == btnsearch) {
            String[] header = {"CourseID", "PersonID"};
            DefaultTableModel modelsearch = new DefaultTableModel(header, 0);
            ArrayList<CourseInstructor> s;
            s = buscourseinstructor.timkiemSanPham(String.valueOf(cbsearch.getSelectedItem()), txt_search.getText().toLowerCase().trim());
            if (s.size() != 0) {
                for (int i = 0; i < s.size(); i++) {
                    Object[] row = {s.get(i).getCourseID(), s.get(i).getTeacherID()
                    };
                    modelsearch.addRow(row);
                }
                tempsearch.addAll(arr);
                arr.clear();
                arr.addAll(s);

                tbsearch.setModel(modelsearch);
            } else {
                JOptionPane.showMessageDialog(null, "Không có kết quả phù hợp!");
            }
            }else if (ae.getSource() == btnaddButton) {
                        CourseInstructor kh = getText();
                        Vector head = setVectorCourseinstructor(kh);
                        int check = buscourseinstructor.themCourseInstructor(kh);
                        if(check == 1){ 
                            JOptionPane.showMessageDialog(null, "Thêm thành công");
                            setVisible(false);
                            }else{JOptionPane.showMessageDialog(null, "Mã đã tồn tại. Thêm thất bại");
                            setVisible(false);
                        }
            }else if(ae.getSource() == btnsubmit) {//lỗi sửa
                        int i = tb_teacher.getSelectedRow();
                        CourseInstructor s = getText();
                        int check = buscourseinstructor.suaCourseInstructor(s, i);
                        if (check == 1) {
                            setModelValue(s, i);
                            JOptionPane.showMessageDialog(null, "Sửa thành công");
                        }
                        else {
                        JOptionPane.showMessageDialog(null, "Sửa thất bại");
                    }
            } else {
                        setVisible(false);
            }
    }

    public static void main(String[] args) {
        new Courseinstructor();
    }
    
            private void tb_Teacher(java.awt.event.MouseEvent evt) {                                        
                        int i = tb_teacher.getSelectedRow();
                        if (i >= 0) {
                                    txtPersonID.setText(tb_teacher.getModel().getValueAt(i, 0).toString());
                        }
            }
            
            private void tb_Course(java.awt.event.MouseEvent evt) {                                        
                        int i = tbcourse.getSelectedRow();
                        if (i >= 0) {
                                    txtCourseID.setText(tbcourse.getModel().getValueAt(i, 0).toString());
                        }
            }
            
            private void tb_Search(java.awt.event.MouseEvent evt) {                                        
                        int i = tbsearch.getSelectedRow();
                        if (i >= 0) {
                                    txtCourseID.setText(tbsearch.getModel().getValueAt(i, 0).toString());
                                    txtPersonID.setText(tbsearch.getModel().getValueAt(i, 1).toString());
                        }
            }
    
}
