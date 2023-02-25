package BLL;

public class OnlineCourse {
            private String CourseID,Url;

            public OnlineCourse() {
            }

            public OnlineCourse(String CourseID, String Url) {
                this.CourseID = CourseID;
                this.Url = Url;
            }

            public String getCourseID() {
                return CourseID;
            }

            public void setCourseID(String CourseID) {
                this.CourseID = CourseID;
            }

            public String getUrl() {
                return Url;
            }

            public void setUrl(String Url) {
                this.Url = Url;
            }
            
}
