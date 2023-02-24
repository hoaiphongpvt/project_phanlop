
package DAL;

import BLL.StudentGrade;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import untils.MyDataAccess;

public class StudentGradeDAL {
    MyDataAccess my = new MyDataAccess("localhost","root","","school");
    public StudentGradeDAL(){}
    public ArrayList<StudentGrade> docGrade() throws Exception{
        ArrayList<StudentGrade> list = new ArrayList<StudentGrade>();
        try{
                String qry = "select * from studentgrade";
                ResultSet rs = my.executeQuery(qry);
                while(rs.next()){
                    StudentGrade grade = new StudentGrade();
                    grade.setEnrollID(rs.getString(1));
                    grade.setCourseID(rs.getString(2));
                    grade.setStudentID(rs.getString(3));
                    grade.setGrade(rs.getString(4));
                    list.add(grade);
                }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Lỗi đọc Database");
        }
        finally{
            my.close();
        }
        return list;
    }
    
    public boolean isValidtoAdd(StudentGrade sgrade){
       try{
           ArrayList<StudentGrade> arr = docGrade();
           for (int i = 0; i < arr.size(); i++) {
               if(arr.get(i).getEnrollID().equals(sgrade.getEnrollID())){
                JOptionPane.showMessageDialog(null,"Mã điểm đã tồn tại");
                return false;}
               else return true;}
        }catch(Exception e){
            return true;
        }
    return true;        
    }
    
    public int themGrade(StudentGrade sv){
        if(isValidtoAdd(sv)){
        int res = 0;
        try{
                String qry = "insert into studentgrade values(";
                qry=qry+"'"+sv.getEnrollID()+"'";
                qry=qry+",'"+sv.getCourseID()+"'";
                qry=qry+",'"+sv.getStudentID()+"'";
                qry=qry+",'"+sv.getGrade()+"')";
                res = my.executeUpdate(qry);
            }catch(Exception e){
                JOptionPane.showMessageDialog(null,"Lỗi thêm điểm vào Database");
            }
        return res;
        }
        return 0;
    }
    public int suaGrade(StudentGrade sgrade){
        int res = 0;
        try{
            String qry = "update studentgrade set ";
            qry = qry + "CourseID='"+sgrade.getCourseID()+"',";
            qry = qry + "StudentID='"+sgrade.getStudentID()+"',";
            qry = qry + "Grade='"+sgrade.getGrade()+"'";
            qry = qry +" where EnrollmentID ='"+sgrade.getEnrollID()+"'";
            res = my.executeUpdate(qry);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Lỗi sửa điểm trong Database");
        }
        return res;
    }
}
