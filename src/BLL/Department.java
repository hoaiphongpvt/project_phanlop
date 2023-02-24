package BLL;

public class Department {
    private String DepartID,Name,Budget,StartDate,Administrator;

    public Department() {
    }

    public Department(String DepartID, String Name, String Budget, String StartDate, String Administrator) {
        this.DepartID = DepartID;
        this.Name = Name;
        this.Budget = Budget;
        this.StartDate = StartDate;
        this.Administrator = Administrator;
    }

    public String getDepartID() {
        return DepartID;
    }

    public void setDepartID(String DepartID) {
        this.DepartID = DepartID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getBudget() {
        return Budget;
    }

    public void setBudget(String Budget) {
        this.Budget = Budget;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String StartDate) {
        this.StartDate = StartDate;
    }

    public String getAdministrator() {
        return Administrator;
    }

    public void setAdministrator(String Administrator) {
        this.Administrator = Administrator;
    }
    
}
