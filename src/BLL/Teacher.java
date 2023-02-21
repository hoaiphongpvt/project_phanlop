package BLL;

public class Teacher {
    private String teacherid,name,fname,courseid,departid;

    public Teacher() {
    }

    public Teacher(String teacherid, String name, String fname, String courseid, String departid) {
        this.teacherid = teacherid;
        this.name = name;
        this.fname = fname;
        this.courseid = courseid;
        this.departid = departid;
    }

    public String getTeacherid() {
        return teacherid;
    }

    public void setTeacherid(String teacherid) {
        this.teacherid = teacherid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getCourseid() {
        return courseid;
    }

    public void setCourseid(String courseid) {
        this.courseid = courseid;
    }

    public String getDepartid() {
        return departid;
    }

    public void setDepartid(String departid) {
        this.departid = departid;
    }
    
    
}
