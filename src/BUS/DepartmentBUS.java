
package BUS;

import DAL.DepartmentDAL;
import BLL.Department;
import java.util.ArrayList;

public class DepartmentBUS {
    public static ArrayList<Department> listdepart = new ArrayList<>();    
    public DepartmentBUS(){}
    public ArrayList<Department> docDepartment(){
        DepartmentDAL data = new DepartmentDAL();
        try{
            listdepart = new ArrayList<Department>();
            listdepart = data.docDepartmen();
        }catch(Exception e){
            e.printStackTrace();
        }
        return listdepart;
    }
    
    public ArrayList<Department> docID(){
        DepartmentDAL data = new DepartmentDAL();
        try{
            listdepart = new ArrayList<Department>();
            listdepart = data.docID();
        }catch(Exception e){
            e.printStackTrace();
        }
        return listdepart;
    }
   
    public int themCourse(Department depart){
        DepartmentDAL data = new DepartmentDAL();
        int check = data.themDepartmen(depart);
        if(check==1)
            listdepart.add(depart);
        return check;
    }
    public int suaCourse(Department department ,int i){
        DepartmentDAL data = new DepartmentDAL();
        int check = data.suaDepartment(department );
        if(check ==1)
            listdepart.set(i,department );
        return check;
    }
    public ArrayList<Department> timkiemtongquan(String tim){
        ArrayList<Department> find = new ArrayList<>();
        for(Department depart: listdepart){
            if (  depart.getDepartID().trim().toLowerCase().contains(tim.trim().toLowerCase())
               || depart.getName().trim().toLowerCase().contains(tim.trim().toLowerCase())
               || depart.getBudget().trim().toLowerCase().contains(tim.trim().toLowerCase())
               || depart.getStartDate().trim().toLowerCase().contains(tim.trim().toLowerCase())
                || depart.getAdministrator().trim().toLowerCase().contains(tim.trim().toLowerCase()))
                find.add(depart);
        }
        return find; 
    }    
}
