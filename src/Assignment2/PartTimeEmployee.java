public class PartTimeEmployee extends Employee {

    private final int MIN_WORKING_HOUR = 10; //PER WEEK
    private final int MAX_WORKING_HOUR = 20; //PER WEEK
    private int paymentPerHour = 18;

    PartTimeEmployee(String nameAndSurname, String registrationNumber, String yearOfStart){
        super(nameAndSurname, registrationNumber, "PARTTIME_EMPLOYEE", yearOfStart);
    }

    /**
     * Calculated with respect to working hours over minimum and under maximum limit
     * then added severance pay
     */
    public void calculateSalary(){
        int[] hours = super.getWorkingHours();
        int baseTotal = 0;
        for(int work: hours){
            if(work >= MAX_WORKING_HOUR){
                baseTotal += MAX_WORKING_HOUR * paymentPerHour;
            }else if(work >= MIN_WORKING_HOUR){
                baseTotal += work * paymentPerHour;
            }
            //no payment given if work < MIN_WORKING_HOUR
        }
        super.setTotalSalary(super.getTotalSalary() + baseTotal); //total without severance pay
        super.calculateSalary(); //added severance pay
    }
}
