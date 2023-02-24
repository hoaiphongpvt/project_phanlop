package BLL;

public class CourseInstructor {
            private String CourseID,TeacherID;

            public CourseInstructor() {
            }

            public CourseInstructor(String CourseID, String TeacherID) {
                this.CourseID = CourseID;
                this.TeacherID = TeacherID;
            }

            public String getCourseID() {
                return CourseID;
            }

            public void setCourseID(String CourseID) {
                this.CourseID = CourseID;
            }

            public String getTeacherID() {
                return TeacherID;
            }

            public void setTeacherID(String TeacherID) {
                this.TeacherID = TeacherID;
            }

}
