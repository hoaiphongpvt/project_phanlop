
package BUS;

import DAL.StudentGradeDAL;
import BLL.StudentGrade;
import java.util.ArrayList;

public class StudentGradeBUS {
    public static ArrayList<StudentGrade> list = new ArrayList<>();    
    public StudentGradeBUS(){}
    public ArrayList<StudentGrade> docGrade(){
        StudentGradeDAL data = new StudentGradeDAL();
        try{
            list = new ArrayList<StudentGrade>();
            list = data.docGrade();
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
    public int them(StudentGrade sv){
        StudentGradeDAL data = new StudentGradeDAL();
        int check = data.themGrade(sv);
        if(check==1)
            list.add(sv);
        return check;
    }
    public int sua(StudentGrade sv,int i){
        StudentGradeDAL data = new StudentGradeDAL();
        int check = data.suaGrade(sv);
        if(check ==1)
            list.set(i,sv);
        return check;
    }
            public static ArrayList<StudentGrade> timkiem(String key, String query) {
                ArrayList<StudentGrade> temp = new ArrayList<>();
                StudentGradeDAL data = new StudentGradeDAL();
                try{
                ArrayList<StudentGrade> s = data.docGrade();
                if (key.equals("EnrollmentID")) {
                    for (int i = 0; i < s.size(); i++) {
                        if (s.get(i).getEnrollID().toLowerCase().equals(query)) {
                            temp.add(s.get(i));
                        }

                    }
                    return temp;
                }
                if (key.equals("CourseID")) {
                    for (int i = 0; i < s.size(); i++) {
                        if (s.get(i).getCourseID().toLowerCase().equals(query)) {
                            temp.add(s.get(i));
                        }

                    }
                    return temp;
                }
                if (key.equals("StudentID")) {
                    for (int i = 0; i < s.size(); i++) {
                        if (s.get(i).getStudentID().toLowerCase().equals(query)) {
                            temp.add(s.get(i));
                        }

                    }
                    return temp;
                }
                if (key.equals("Grade")) {
                    for (int i = 0; i < s.size(); i++) {
                        if (s.get(i).getGrade().toLowerCase().equals(query)) {
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
