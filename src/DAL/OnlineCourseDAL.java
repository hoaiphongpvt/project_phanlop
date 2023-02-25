
package DAL;

import BLL.OnlineCourse;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import untils.MyDataAccess;

public class OnlineCourseDAL {
    MyDataAccess my = new MyDataAccess("localhost","root","","school");
    public OnlineCourseDAL(){}
    public ArrayList<OnlineCourse> doc() throws Exception{
        ArrayList<OnlineCourse> list = new ArrayList<OnlineCourse>();
        try{
                String qry = "select * from onlinecourse";
                ResultSet rs = my.executeQuery(qry);
                while(rs.next()){
                    OnlineCourse cI = new OnlineCourse();
                    cI.setCourseID(rs.getString(1));
                    cI.setUrl(rs.getString(2));
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
    
    public boolean isValidtoAdd(OnlineCourse courseInstructor){
       try{
           ArrayList<OnlineCourse> arr = doc();
           for (int i = 0; i < arr.size(); i++) {
               if(arr.get(i).getCourseID().equals(courseInstructor.getCourseID())){
                JOptionPane.showMessageDialog(null,"Mã môn đã tồn tại");
                return false;}
               else return true;}
        }catch(Exception e){
            return true;
        }
    return true;        
    }
    
    public int them(OnlineCourse onl){
        if(isValidtoAdd(onl)){
        int res = 0;
        try{
                String qry = "insert into onlinecourse values(";
                qry=qry+"'"+onl.getCourseID()+"'";
                qry=qry+",'"+onl.getUrl()+"')";
                res = my.executeUpdate(qry);
            }catch(Exception e){
                JOptionPane.showMessageDialog(null,"Lỗi thêm khóa học online vào Database");
            }
        return res;
        }
        return 0;
    }
    public int sua(OnlineCourse onl){
        int res = 0;
        try{
            String qry = "update onlinecourse set ";
            qry = qry + "url='"+onl.getUrl()+"',";
            qry = qry +" where CourseID ='"+onl.getCourseID()+"'";
            res = my.executeUpdate(qry);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Lỗi sửa khóa học online trong Database");
        }
        return res;
    }
}
