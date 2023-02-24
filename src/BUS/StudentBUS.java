
package BUS;

import DAL.StudentDAL;
import BLL.Student;
import java.util.ArrayList;

public class StudentBUS {
    public static ArrayList<Student> list = new ArrayList<>();    
    public StudentBUS(){}
    public ArrayList<Student> docSV(){
        StudentDAL data = new StudentDAL();
        try{
            list = new ArrayList<Student>();
            list = data.docSV();
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
    public int themSV(Student sv){
        StudentDAL data = new StudentDAL();
        int check = data.themSV(sv);
        if(check==1)
            list.add(sv);
        return check;
    }
    public int suaKh(Student sv,int i){
        StudentDAL data = new StudentDAL();
        int check = data.suaSV(sv);
        if(check ==1)
            list.set(i,sv);
        return check;
    }
    public ArrayList<Student> timkiemtongquan(String tim){
        ArrayList<Student> find = new ArrayList<>();
        for(Student sv: list){
            if (  sv.getMasv().trim().toLowerCase().contains(tim.trim().toLowerCase())
               || sv.getLastname().trim().toLowerCase().contains(tim.trim().toLowerCase())
               || sv.getFirstname().trim().toLowerCase().contains(tim.trim().toLowerCase())
               || sv.getHireDate().trim().toLowerCase().contains(tim.trim().toLowerCase())
               || sv.getEnrollmentDate().trim().toLowerCase().contains(tim.trim().toLowerCase()))
                find.add(sv);
        }
        return find; 
    }    
}
