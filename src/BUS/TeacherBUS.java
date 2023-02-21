
package BUS;

import DAL.TeacherDAL;
import BLL.Teacher;
import java.util.ArrayList;

public class TeacherBUS {
    public static ArrayList<Teacher> list = new ArrayList<>();    
    public TeacherBUS(){}
    public ArrayList<Teacher> docTC(){
        TeacherDAL data = new TeacherDAL();
        try{
            list = new ArrayList<Teacher>();
            list = data.docGV();
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
    
    public ArrayList<Teacher> docID(){
        TeacherDAL data = new TeacherDAL();
        try{
            list = new ArrayList<Teacher>();
            list = data.docID();
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
   
    public int themTC(Teacher tc){
        TeacherDAL data = new TeacherDAL();
        int check = data.themGV(tc);
        if(check==1)
            list.add(tc);
        return check;
    }
    public int suaTC(Teacher tc,int i){
        TeacherDAL data = new TeacherDAL();
        int check = data.suaTC(tc);
        if(check ==1)
            list.set(i,tc);
        return check;
    }
    public ArrayList<Teacher> timkiemtongquan(String tim){
        ArrayList<Teacher> find = new ArrayList<>();
        for(Teacher tc: list){
            if (  tc.getTeacherid().trim().toLowerCase().contains(tim.trim().toLowerCase())
               || tc.getName().trim().toLowerCase().contains(tim.trim().toLowerCase())
               || tc.getFname().trim().toLowerCase().contains(tim.trim().toLowerCase())
               || tc.getCourseid().trim().toLowerCase().contains(tim.trim().toLowerCase())
               || tc.getDepartid().trim().toLowerCase().contains(tim.trim().toLowerCase()))
                find.add(tc);
        }
        return find; 
    }    
}
