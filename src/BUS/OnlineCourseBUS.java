
package BUS;

import DAL.OnlineCourseDAL;
import BLL.OnlineCourse;
import java.util.ArrayList;

public class OnlineCourseBUS {
    public static ArrayList<OnlineCourse> list = new ArrayList<>();    
    public OnlineCourseBUS(){}
    public ArrayList<OnlineCourse> doc(){
        OnlineCourseDAL data = new OnlineCourseDAL();
        try{
            list = new ArrayList<OnlineCourse>();
            list = data.doc();
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
    public int themkhoa(OnlineCourse ci){
        OnlineCourseDAL data = new OnlineCourseDAL();
        int check = data.them(ci);
        if(check==1)
            list.add(ci);
        return check;
    }
    public int suaCourseInstructor(OnlineCourse c,int i){
        OnlineCourseDAL data = new OnlineCourseDAL();
        int check = data.sua(c);
        if(check ==1)
            list.set(i,c);
        return check;
    }
            public static ArrayList<OnlineCourse> timkiemSanPham(String key, String query) {
                ArrayList<OnlineCourse> temp = new ArrayList<>();
                OnlineCourseDAL data = new OnlineCourseDAL();
                try{
                ArrayList<OnlineCourse> s = data.doc();
                if (key.equals("CourseID")) {
                    for (int i = 0; i < s.size(); i++) {
                        if (s.get(i).getCourseID().toLowerCase().equals(query)) {
                            temp.add(s.get(i));
                        }

                    }
                    return temp;
                }
                if (key.equals("url")) {
                    for (int i = 0; i < s.size(); i++) {
                        if (s.get(i).getUrl().toLowerCase().equals(query)) {
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
