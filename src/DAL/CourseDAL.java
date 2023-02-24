
package DAL;

import BLL.Course;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import untils.MyDataAccess;

public class CourseDAL {
    MyDataAccess my = new MyDataAccess("localhost","root","","school");
    public CourseDAL(){}
    public ArrayList<Course> docCourse() throws Exception{
        ArrayList<Course> list = new ArrayList<Course>();
        try{
                String qry = "select * from course";
                ResultSet rs = my.executeQuery(qry);
                while(rs.next()){
                    Course course = new Course();
                    course.setCourseID(rs.getString(1));
                    course.setTitle(rs.getString(2));
                    course.setCredits(rs.getString(3));
                    course.setDepartmentID(rs.getString(4));
                    list.add(course);
                }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Lỗi đọc Database");
        }
        finally{
            my.close();
        }
        return list;
    }
    
    public ArrayList<Course> docID() throws Exception{
        ArrayList<Course> list = new ArrayList<Course>();
        try{
                String qry = "select * from course";
                ResultSet rs = my.executeQuery(qry);
                while(rs.next()){
                    Course course = new Course();
                    course.setCourseID(rs.getString(1));
                    list.add(course);
                }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Lỗi đọc Database");
        }
        finally{
            my.close();
        }
        return list;
    }
    
    public boolean isValidtoAdd(Course course){
       try{
           ArrayList<Course> arr = docCourse();
           for (int i = 0; i < arr.size(); i++) {
               if(arr.get(i).getCourseID().equals(course.getCourseID())){
                JOptionPane.showMessageDialog(null,"Mã khóa học đã tồn tại");
                return false;}
               else return true;}
        }catch(Exception e){
            return true;
        }
    return true;        
    }
    
    public int themCourse(Course course){
        if(isValidtoAdd(course)){
        int res = 0;
        try{
                String qry = "insert into course values(";
                qry=qry+"'"+course.getCourseID()+"'";
                qry=qry+",'"+course.getTitle()+"'";
                qry=qry+",'"+course.getCredits()+"'";
                qry=qry+",'"+course.getDepartmentID()+")'";
                res = my.executeUpdate(qry);
            }catch(Exception e){
                JOptionPane.showMessageDialog(null,"Lỗi thêm Khóa học vào Database");
            }
        return res;
        }
        return 0;
    }
    public int suaCourse(Course tc){
        int res = 0;
        try{
            String qry = "update course set ";
            qry = qry + "Title='"+tc.getTitle()+"',";
            qry = qry + "Credits='"+tc.getCredits()+"',";
            qry = qry + "DepartmentID='"+tc.getDepartmentID()+"',";
            qry = qry +" where CourseID ='"+tc.getCourseID()+"'";
            res = my.executeUpdate(qry);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Lỗi sửa khóa học trong Database");
        }
        return res;
    }
}
