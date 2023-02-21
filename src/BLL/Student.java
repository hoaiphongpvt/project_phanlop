package BLL;

public class Student {
        private String masv;
        private String lastname,firstname,HireDate,EnrollmentDate;

        public Student() {
        }

        public Student(String masv, String lastname, String firstname, String HireDate, String EnrollmentDate) {
            this.masv = masv;
            this.lastname = lastname;
            this.firstname = firstname;
            this.HireDate = HireDate;
            this.EnrollmentDate = EnrollmentDate;
        }

        public String getMasv() {
            return masv;
        }

        public void setMasv(String masv) {
            this.masv = masv;
        }

        public String getLastname() {
            return lastname;
        }

        public void setLastname(String lastname) {
            this.lastname = lastname;
        }

        public String getFirstname() {
            return firstname;
        }

        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }

        public String getHireDate() {
            return HireDate;
        }

        public void setHireDate(String HireDate) {
            this.HireDate = HireDate;
        }

        public String getEnrollmentDate() {
            return EnrollmentDate;
        }

        public void setEnrollmentDate(String EnrollmentDate) {
            this.EnrollmentDate = EnrollmentDate;
        }
        
}
