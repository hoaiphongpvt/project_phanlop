
package DAL;

import BLL.Teacher;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import untils.MyDataAccess;

public class TeacherDAL {
    MyDataAccess my = new MyDataAccess("localhost","root","","school");
    public TeacherDAL(){}
    public ArrayList<Teacher> docGV() throws Exception{
        ArrayList<Teacher> list = new ArrayList<Teacher>();
        try{
                String qry = "select * from teacher";
                ResultSet rs = my.executeQuery(qry);
                while(rs.next()){
                    Teacher tc = new Teacher();
                    tc.setTeacherid(rs.getString(1));
                    tc.setName(rs.getString(2));
                    tc.setFname(rs.getString(3));
                    tc.setCourseid(rs.getString(4));
                    tc.setDepartid(rs.getString(5));
                    list.add(tc);
                }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Lỗi đọc Database");
        }
        finally{
            my.close();
        }
        return list;
    }
    
    public ArrayList<Teacher> docID() throws Exception{
        ArrayList<Teacher> list = new ArrayList<Teacher>();
        try{
                String qry = "select * from teacher";
                ResultSet rs = my.executeQuery(qry);
                while(rs.next()){
                    Teacher tc = new Teacher();
                    tc.setTeacherid(rs.getString(1));
                    list.add(tc);
                }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Lỗi đọc Database");
        }
        finally{
            my.close();
        }
        return list;
    }
    
    public boolean isValidtoAdd(Teacher tc){
       try{
           ArrayList<Teacher> arr = docGV();
           for (int i = 0; i < arr.size(); i++) {
               if(arr.get(i).getTeacherid().equals(tc.getTeacherid())){
                JOptionPane.showMessageDialog(null,"Mã Giáo viên đã tồn tại");
                return false;}
               else return true;}
        }catch(Exception e){
            return true;
        }
    return true;        
    }
    
    public int themGV(Teacher sv){
        if(isValidtoAdd(sv)){
        int res = 0;
        try{
                String qry = "insert into teacher values(";
                qry=qry+"'"+sv.getTeacherid()+"'";
                qry=qry+",'"+sv.getName()+"'";
                qry=qry+",'"+sv.getFname()+"'";
                qry=qry+",'"+sv.getCourseid()+"'";
                qry=qry+",'"+sv.getDepartid()+"')";
                res = my.executeUpdate(qry);
            }catch(Exception e){
                JOptionPane.showMessageDialog(null,"Lỗi thêm Giáo viên vào Database");
            }
        return res;
        }
        return 0;
    }
    public int suaTC(Teacher tc){
        int res = 0;
        try{
            String qry = "update teacher set ";
            qry = qry + "name='"+tc.getName()+"',";
            qry = qry + "fname='"+tc.getFname()+"',";
            qry = qry + "courseID='"+tc.getCourseid()+"',";
            qry = qry + "department='"+tc.getDepartid()+"'";
            qry = qry +" where empId ='"+tc.getTeacherid()+"'";
            res = my.executeUpdate(qry);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Lỗi sửa Giáo viên trong Database");
        }
        return res;
    }
}
