public class FullTimeEmployee extends Employee {
    private int dayOfWork = 5; //Full time employees do not work on weekends
    private int paymentPerDay; //Assigned by position
    private int overWorkSalary; //Assigned by position

    private final int MAX_OVERWORK_HOUR, DEFAULT_WORK_HOURS; //these are stable and will not change, thus final variables

    FullTimeEmployee(String nameAndSurname, String registrationNumber, String position, String yearOfStart, int maxOverWorkHours, int overWorkSalary, int paymentPerDay){
        super(nameAndSurname, registrationNumber, position, yearOfStart);
        DEFAULT_WORK_HOURS = 40;

        this.MAX_OVERWORK_HOUR = maxOverWorkHours;
        this.overWorkSalary = overWorkSalary;
        this.paymentPerDay = paymentPerDay;
    }

    /**
     * Calculated with respect to weekly working hours, additional hours over default and under max limit and severance pay
     * Precondition: No Assignments.Asgn2.FullTimeEmployee will work under their default working hour. This condition is ignored
     */
    public void calculateSalary(){
        super.setTotalSalary(super.getTotalSalary() + (4 * dayOfWork * paymentPerDay)); //monthly minimum payment assigned
        super.calculateSalary(DEFAULT_WORK_HOURS,MAX_OVERWORK_HOUR,overWorkSalary); //additional fee and severance pay added
    }
}
