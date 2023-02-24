
package DAL;

import BLL.Department;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import untils.MyDataAccess;

public class DepartmentDAL {
    MyDataAccess my = new MyDataAccess("localhost","root","","school");
    public DepartmentDAL(){}
    public ArrayList<Department> docDepartmen() throws Exception{
        ArrayList<Department> list = new ArrayList<Department>();
        try{
                String qry = "select * from department";
                ResultSet rs = my.executeQuery(qry);
                while(rs.next()){
                    Department depart = new Department();
                    depart.setDepartID(rs.getString(1));
                    depart.setName(rs.getString(2));
                    depart.setBudget(rs.getString(3));
                    depart.setStartDate(rs.getString(4));
                    depart.setAdministrator(rs.getString(5));
                    list.add(depart);
                }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Lỗi đọc Database");
        }
        finally{
            my.close();
        }
        return list;
    }
    
    public ArrayList<Department> docID() throws Exception{
        ArrayList<Department> list = new ArrayList<Department>();
        try{
                String qry = "select * from department";
                ResultSet rs = my.executeQuery(qry);
                while(rs.next()){
                    Department course = new Department();
                    course.setDepartID(rs.getString(1));
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
    
    public boolean isValidtoAdd(Department course){
       try{
           ArrayList<Department> arr = docDepartmen();
           for (int i = 0; i < arr.size(); i++) {
               if(arr.get(i).getDepartID().equals(course.getDepartID())){
                JOptionPane.showMessageDialog(null,"Mã khoa đã tồn tại");
                return false;}
               else return true;}
        }catch(Exception e){
            return true;
        }
    return true;        
    }
    
    public int themDepartmen(Department course){
        if(isValidtoAdd(course)){
        int res = 0;
        try{
                String qry = "insert into department values(";
                qry=qry+"'"+course.getDepartID()+"'";
                qry=qry+",'"+course.getName()+"'";
                qry=qry+",'"+course.getBudget()+"'";
                qry=qry+",'"+course.getStartDate()+"'";
                qry=qry+",'"+course.getAdministrator()+")'";
                res = my.executeUpdate(qry);
            }catch(Exception e){
                JOptionPane.showMessageDialog(null,"Lỗi thêm khoa vào Database");
            }
        return res;
        }
        return 0;
    }
    public int suaDepartment(Department depart ){
        int res = 0;
        try{
            String qry = "update course set ";
            qry = qry + "Name='"+depart.getName()+"',";
            qry = qry + "Budget='"+depart.getBudget()+"',";
            qry = qry + "StartDate='"+depart.getStartDate()+"',";
            qry = qry + "Administrator='"+depart.getAdministrator()+"',";
            qry = qry +" where DepartmentID ='"+depart.getDepartID()+"'";
            res = my.executeUpdate(qry);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Lỗi sửa khoa trong Database");
        }
        return res;
    }
}
