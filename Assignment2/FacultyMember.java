public class FacultyMember extends Academician {
    private int addCourseFeePerHour; //for hours overworked
    private final int MAX_ADD_COURSE_HOUR_PER_WEEK; //limit of additional course hours
    private int ssBenefitsPercentage; //extra payment for academicians

    FacultyMember(String nameAndSurname, String registrationNumber, String yearOfStart){
        super(nameAndSurname, registrationNumber,"FACULTY_MEMBER" ,yearOfStart);
        this.addCourseFeePerHour = 20;
        this.MAX_ADD_COURSE_HOUR_PER_WEEK = 8;
        this.ssBenefitsPercentage = 135;

    }

    /**
     * Calculate with respect to additional course fee, base salary and severance pay
     */
    public void calculateSalary() {
        int salaryWithoutAddCourse = super.calculateSalary(ssBenefitsPercentage); //using Academician super class
        super.setTotalSalary(super.getTotalSalary() + salaryWithoutAddCourse);
        super.calculateSalary(super.getDEFAULT_WORK_HOURS(),MAX_ADD_COURSE_HOUR_PER_WEEK,addCourseFeePerHour); //calculated additional salary
    }
}
