
package BUS;

import DAL.CourseDAL;
import BLL.Course;
import java.util.ArrayList;

public class CourseBUS {
    public static ArrayList<Course> list = new ArrayList<>();      

    public CourseBUS(){}
    public ArrayList<Course> docCourse(){
        CourseDAL data = new CourseDAL();
        try{
            list = new ArrayList<Course>();
            list = data.docCourse();
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
    
    public ArrayList<Course> docID(){
        CourseDAL data = new CourseDAL();
        try{
            list = new ArrayList<Course>();
            list = data.docID();
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
   
    public int themCourse(Course course){
        CourseDAL data = new CourseDAL();
        int check = data.themCourse(course);
        if(check==1)
            list.add(course);
        return check;
    }
    public int suaCourse(Course course,int i){
        CourseDAL data = new CourseDAL();
        int check = data.suaCourse(course);
        if(check ==1)
            list.set(i,course);
        return check;
    }
    public ArrayList<Course> timkiemtongquan(String tim){
        ArrayList<Course> find = new ArrayList<>();
        for(Course course: list){
            if (  course.getCourseID().trim().toLowerCase().contains(tim.trim().toLowerCase())
               || course.getTitle().trim().toLowerCase().contains(tim.trim().toLowerCase())
               || course.getCredits().trim().toLowerCase().contains(tim.trim().toLowerCase())
               || course.getDepartmentID().trim().toLowerCase().contains(tim.trim().toLowerCase()))
                find.add(course);
        }
        return find; 
    }    
            public static ArrayList<Course> timkiem(String key, String query) {
                ArrayList<Course> temp = new ArrayList<>();
                CourseDAL data = new CourseDAL();
                try{
                ArrayList<Course> s = data.docCourse();
                if (key.equals("CourseID")) {
                    for (int i = 0; i < s.size(); i++) {
                        if (s.get(i).getCourseID().toLowerCase().equals(query)) {
                            temp.add(s.get(i));
                        }

                    }
                    return temp;
                }
                if (key.equals("Title")) {
                    for (int i = 0; i < s.size(); i++) {
                        if (s.get(i).getTitle().toLowerCase().equals(query)) {
                            temp.add(s.get(i));
                        }

                    }
                    return temp;
                }
                if (key.equals("Credits")) {
                    for (int i = 0; i < s.size(); i++) {
                        if (s.get(i).getCredits().toLowerCase().equals(query)) {
                            temp.add(s.get(i));
                        }

                    }
                    return temp;
                }
                if (key.equals("DepartmentID")) {
                    for (int i = 0; i < s.size(); i++) {
                        if (s.get(i).getDepartmentID().toLowerCase().equals(query)) {
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
