public class Officer extends Personnel {
    private final int BASE_SALARY;
    private final int MAX_OVERWORK_HOURS, DEFAULT_WORK_HOURS;
    private int ssBenefitsPercentage, overWorkSalaryPerHour;


    Officer(String nameAndSurname, String registrationNumber, String yearOfStart){
        super(nameAndSurname, registrationNumber,"OFFICER", yearOfStart);
        this.BASE_SALARY = 2600;
        this.DEFAULT_WORK_HOURS = 40;
        this.MAX_OVERWORK_HOURS = 10;

        this.ssBenefitsPercentage = 65;
        this.overWorkSalaryPerHour = 20;
    }

    /**
     * Calculated with respect to base salary, special service benefits, overworked hours and severance pay
     */
    public void calculateSalary(){
        int ssBenefits = BASE_SALARY * ssBenefitsPercentage /100;
        super.setTotalSalary(BASE_SALARY + ssBenefits + super.getTotalSalary());  //calculated with base salary and ssBenefits
        super.calculateSalary(DEFAULT_WORK_HOURS, MAX_OVERWORK_HOURS,overWorkSalaryPerHour); //added overwork hours and severance pay
    }
}
