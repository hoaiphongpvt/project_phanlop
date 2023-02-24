package BLL;

public class StudentGrade {
            private String EnrollID,CourseID,StudentID,Grade;

            public StudentGrade() {
            }

            public StudentGrade(String EnrollID, String CourseID, String StudentID, String Grade) {
                this.EnrollID = EnrollID;
                this.CourseID = CourseID;
                this.StudentID = StudentID;
                this.Grade = Grade;
            }

            public String getEnrollID() {
                return EnrollID;
            }

            public void setEnrollID(String EnrollID) {
                this.EnrollID = EnrollID;
            }

            public String getCourseID() {
                return CourseID;
            }

            public void setCourseID(String CourseID) {
                this.CourseID = CourseID;
            }

            public String getStudentID() {
                return StudentID;
            }

            public void setStudentID(String StudentID) {
                this.StudentID = StudentID;
            }

            public String getGrade() {
                return Grade;
            }

            public void setGrade(String Grade) {
                this.Grade = Grade;
            }

}
