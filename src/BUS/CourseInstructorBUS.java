
package BUS;

import DAL.CourseInstructorDAL;
import BLL.CourseInstructor;
import java.util.ArrayList;

public class CourseInstructorBUS {
    public static ArrayList<CourseInstructor> list = new ArrayList<>();    
    public CourseInstructorBUS(){}
    public ArrayList<CourseInstructor> doc(){
        CourseInstructorDAL data = new CourseInstructorDAL();
        try{
            list = new ArrayList<CourseInstructor>();
            list = data.doc();
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
    public int themCourseInstructor(CourseInstructor ci){
        CourseInstructorDAL data = new CourseInstructorDAL();
        int check = data.them(ci);
        if(check==1)
            list.add(ci);
        return check;
    }
    public int suaCourseInstructor(CourseInstructor c,int i){
        CourseInstructorDAL data = new CourseInstructorDAL();
        int check = data.sua(c);
        if(check ==1)
            list.set(i,c);
        return check;
    }
            public static ArrayList<CourseInstructor> timkiemSanPham(String key, String query) {
                ArrayList<CourseInstructor> temp = new ArrayList<>();
                CourseInstructorDAL data = new CourseInstructorDAL();
                try{
                ArrayList<CourseInstructor> s = data.doc();
                if (key.equals("CourseID")) {
                    for (int i = 0; i < s.size(); i++) {
                        if (s.get(i).getCourseID().toLowerCase().equals(query)) {
                            temp.add(s.get(i));
                        }

                    }
                    return temp;
                }
                if (key.equals("PersonID")) {
                    for (int i = 0; i < s.size(); i++) {
                        if (s.get(i).getTeacherID().toLowerCase().equals(query)) {
                            temp.add(s.get(i));
                        }

                    }
                    return temp;
                }
                }catch(Exception e){
                    e.printStackTrace();
                }
                return null;
            }    
}
