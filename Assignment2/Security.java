public class Security extends Personnel {
    private int workingDays = 6;

    private final int MAX_WORK_HOUR, MIN_WORK_HOUR;
    private int transMoney, foodMoney;
    private int minHourPerWeek, maxHourPerWeek, transMoneyPerWeek, foodMoneyPerWeek;
    private int paymentPerHour;

    Security(String nameAndSurname, String registrationNumber, String yearOfStart){
        super(nameAndSurname, registrationNumber, "SECURITY",yearOfStart);
        transMoney = 5;
        foodMoney = 10;
        MAX_WORK_HOUR = 9;
        MIN_WORK_HOUR = 5;
        paymentPerHour = 10;

        //weekly transitions with respect to 6 days of work defined to Assignments.Asgn2.Security
        transMoneyPerWeek = transMoney * workingDays; //30
        foodMoneyPerWeek = foodMoney * workingDays; //60
        maxHourPerWeek = MAX_WORK_HOUR * workingDays; //54
        minHourPerWeek = MIN_WORK_HOUR * workingDays; //30

    }


    /**
     * Calculated with respect to weekly payment of food and transportation money, weekly working hours and severance pay
     *  overwritten implementation to weekly payment
     */
    @Override
    public void calculateSalary(){
        int extraPaymentPerWeek = foodMoneyPerWeek + transMoneyPerWeek;
        int monthlyEarning = 0;
        int[] workData = super.getWorkingHours();
        for(int week: workData){
            if(week >= maxHourPerWeek){
                monthlyEarning += (maxHourPerWeek * paymentPerHour) + extraPaymentPerWeek;
            }else if(week >= minHourPerWeek){
                monthlyEarning += (week * paymentPerHour) + extraPaymentPerWeek;
            }
        }
        super.setTotalSalary(monthlyEarning);
        super.calculateSalary(); //for severance pay
    }
}
