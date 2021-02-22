public class Personnel {
    //personnel information assigned while creating the class
    private final String NAME;
    private final String SURNAME;
    private final String REGISTER_NUMBER;
    private String yearOfStart;
    private String position;

    //common data for all personnel
    private final int CURRENT_YEAR = 2020; //hardcoded and will not be changed
    private int[] workingHours; //will be added in Accounting

    //will be calculated
    private int totalSalary = 0;
    private int weeklySalary = 0;

    Personnel(String nameAndSurname, String registerNumber,String position, String yearOfStart){
        this.NAME = nameAndSurname.split(" ")[0];
        this.SURNAME = nameAndSurname.split(" ")[1];
        this.REGISTER_NUMBER = registerNumber;
        this.position = position;
        this.yearOfStart = yearOfStart;


    }

    /**
     * super class is calculating additional salaries of most personnel
     * PRECONDITION:  super method has to be called after adding the default salary to personnel, this method is not
     * responsible for weeks under the minimum hour.
     * Additional: Part-time employees will only use that method
     * @param minWorkHours also known as default hours in other classes, mostly assigned to 40
     * @param maxWorkHours is the limit of earning additional fee
     * @param paymentPerHour be careful; some personnel has payment per hour in addition to daily payment. For them, let it be used.
     *                       POST CONDITION: weekly salary calculated for personnel and added to totalSalary
     */
    public void calculateSalary(int minWorkHours, int maxWorkHours, int paymentPerHour){
        totalSalary += addSeverancePay();
        for(int week: workingHours){
            int addHours = week - minWorkHours;
            if(addHours >= maxWorkHours){
                weeklySalary += paymentPerHour * maxWorkHours;
            }else if(addHours > 0){
                weeklySalary += paymentPerHour * addHours;
            }
            else if(addHours != 0){
                //in condition of personnel worked under required hours
                weeklySalary = 0;
            }
            totalSalary += weeklySalary;
            weeklySalary = 0;
        }
    }

    /**
     * Method overloading
     * used for adding the severance pay only, without the additional work fees
     * for exceptional calculations of salaries in some personnel classes
     */
    public void calculateSalary(){
        totalSalary += addSeverancePay();
    }

    /**
     * @return the calculation of severance pay by the personnels' year of start
     */
    private int addSeverancePay(){
        //20* 0.8 calculated as 16 to work with integers
        return (CURRENT_YEAR- Integer.valueOf(yearOfStart)) * 16;
    }

    /**
     * Printing the personnel info in correct format
     * @return the String value as one piece, to make it easier to write in file
     */
    public String toString(){
        String output;
        output = String.format("Name : %s\n" +
                "Surname : %s\n" +
                "Registration Number : %s\n" +
                "Position : %s\n" +
                "Year of Start : %s\n" +
                "Total Salary : %d.00 TL",
                NAME, SURNAME, REGISTER_NUMBER, position, yearOfStart, totalSalary);
        return output;
    }


    //getters and setters
    public String getREGISTER_NUMBER() {
        return REGISTER_NUMBER;
    }

    public String getYearOfStart() {
        return yearOfStart;
    }

    public void setYearOfStart(String yearOfStart) {
        this.yearOfStart = yearOfStart;
    }

    public String getPosition() {
        return position;
    }

    public int[] getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(int[] workingHours){
        this.workingHours = workingHours;
    }

    public int getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(int totalSalary) {
        this.totalSalary = totalSalary;
    }
}
