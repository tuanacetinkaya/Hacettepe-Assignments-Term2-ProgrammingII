public class Academician extends Personnel {
    private final int BASE_SALARY; //2600 by default
    private final int DEFAULT_WORK_HOURS; //40 by default these employees will never work under that limit

    Academician(String nameAndSurname, String registrationNumber, String position, String yearOfStart){
        super(nameAndSurname, registrationNumber, position,yearOfStart);
        this.BASE_SALARY = 2600;
        this.DEFAULT_WORK_HOURS = 40;
    }

    /**
     * PRECONDITION: All academicians need to work minimum 40 hour by default. This method does not handle that and will
     *          pay salary to anyone under 40 hours if exist.
     * returned value will be set to super.totalSalary after setting the additional fee.
     * @param ssBenefitsPercentage depends on personnel
     * @return the salary with base salary and ssBenefits
     */
    public int calculateSalary(int ssBenefitsPercentage){
        int ssBenefits = BASE_SALARY * ssBenefitsPercentage /100;
        return (BASE_SALARY + ssBenefits + super.getTotalSalary());
    }

    //get default work hours used to calculate salary from children classes Assignments.Asgn2.FacultyMember and Assignments.Asgn2.ResearchAssistant
    public int getDEFAULT_WORK_HOURS() {
        return DEFAULT_WORK_HOURS;
    }
}
