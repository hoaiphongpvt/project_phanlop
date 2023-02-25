
package DAL;

import BLL.CourseInstructor;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import untils.MyDataAccess;

public class CourseInstructorDAL {
    MyDataAccess my = new MyDataAccess("localhost","root","","school");
    public CourseInstructorDAL(){}
    public ArrayList<CourseInstructor> doc() throws Exception{
        ArrayList<CourseInstructor> list = new ArrayList<CourseInstructor>();
        try{
                String qry = "select * from courseinstructor";
                ResultSet rs = my.executeQuery(qry);
                while(rs.next()){
                    CourseInstructor cI = new CourseInstructor();
                    cI.setCourseID(rs.getString(1));
                    cI.setTeacherID(rs.getString(2));
                    list.add(cI);
                }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Lỗi đọc Database");
        }
        finally{
            my.close();
        }
        return list;
    }
    
    public boolean isValidtoAdd(CourseInstructor courseInstructor){
       try{
           ArrayList<CourseInstructor> arr = doc();
           for (int i = 0; i < arr.size(); i++) {
               if(arr.get(i).getCourseID().equals(courseInstructor.getCourseID())){
                JOptionPane.showMessageDialog(null,"Môn học này đã được phân công rồi");
                return true;}
               else return true;}
        }catch(Exception e){
            return true;
        }
    return true;        
    }
    
    public int them(CourseInstructor courseInstructor){
        if(isValidtoAdd(courseInstructor)){
        int res = 0;
        try{
                String qry = "insert into courseinstructor values(";
                qry=qry+"'"+courseInstructor.getCourseID()+"'";
                qry=qry+",'"+courseInstructor.getTeacherID()+"')";
                res = my.executeUpdate(qry);
            }catch(Exception e){
                JOptionPane.showMessageDialog(null,"Lỗi thêm thông tin giảng dạy vào Database");
            }
        return res;
        }
        return 0;
    }
    public int sua(CourseInstructor courseInstructor){
        int res = 0;
        try{
            String qry = "update courseinstructor set ";
            qry = qry + "PersonID='"+courseInstructor.getTeacherID()+"'";
            qry = qry +" where CourseID ='"+courseInstructor.getCourseID()+"'";
            res = my.executeUpdate(qry);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Lỗi sửa thông tin giảng dạy trong Database");
        }
        return res;
    }
}
