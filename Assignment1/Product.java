package Assignments.Asgn1;


/**
 * @author 21985164
 * class product creates a product and stores all price information due to membership and date in it.
 * priceInfo is a list of all possible prices as clarified on the given list
 *              Post condition: priceInfo contains 4 columns corresponds to
 *              {validMembership, dateStart, dateExpire, price}
 *             and priceInfo size assigned assuming the worst case scenario
 *             (combination of 3 memberships all have different prices and a maximum of 4 date combinations) there could be 12 rows but let's say 10
 */
public class Product {
    //PRODUCT_NAME CANNOT BE CHANGED SINCE ALL PRODUCTS ARE UNIQUE
    private final String PRODUCT_NAME;
    private String[][] priceInfo = new String[10][4]; //size initialization explained above
    private int index;    //index to know how many items in the priceInfo

    /**
     * A product to group all possible prices.
     * Tell me your customer I'll tell you your price.
     * @param productName is unique and assigned just once. Keep in mind it is case sensitive
     * @param validMembership can be "gold" "silver" and "bronze" again, case sensitive
     * @param dateStart as standard format day.month.year split with dots and no white space included
     * @param dateExpire same format as with dateStart
     * @param price is the string equivalent of price.
     * Precondition: Price has to be double (ex. 12.0)
     * Post Condition: Create a Product object with the given info.
     *                 Will update the prices in market class as reading priceList with addPrice method
     */
    public Product(String productName, String validMembership, String dateStart, String dateExpire, String  price){
        this.index = 0;
        this.PRODUCT_NAME = productName;
        priceInfo[index] = new String[]{validMembership, dateStart, dateExpire, price};
    }

    /**
     * prices are unique to customer
     * @param validMembership as "gold" "silver" or "bronze"
     * @param date is customer's shopping date
     * @return the correct price for our precious customer
     */
    public double getPrice(String validMembership, String date){
        for(int i = 0; i <= this.index; i++){
            if (validMembership.equals(priceInfo[i][0]) &&
                    isDateBetween(date, priceInfo[i][1], priceInfo[i][2]))
            {
                return Double.valueOf(priceInfo[i][3]);
            }
        }
        //getPrice returns -1 for prices of out of criteria to not be reachable
        return -1;
    }//getPrice

    public void addPrice(String validMembership, String dateStart, String dateExpire, String price){
        this.index ++;
        priceInfo[index] = new String[]{validMembership, dateStart, dateExpire, price};
    }//addPrice

    public String getPRODUCT_NAME() {
        return PRODUCT_NAME;
    }//getPRODUCT_NAME

    /**
     * @param dateStart and
     * @param dateExpire for date interval !!PRECONDITION dates list has to be size 2:
     *                dates[0] for start and date[1] for end date
     * @param shopped comes from Customer object
     * @return true if the shopped date comes between given dates
     */
    private boolean isDateBetween(String shopped, String dateStart, String dateExpire) {
        int[]splitShopped =dateSplitToInteger(shopped);
        int[]splitStart =dateSplitToInteger(dateStart);
        int[]splitExpire =dateSplitToInteger(dateExpire);
        return (compareDates(splitStart, splitShopped) && compareDates(splitShopped, splitExpire));

    }// isDateBetween

    /**
     * simple date comparison
     * @param before is the date supposed to come before the
     *          @param after so if that hierarchy satisfies
     *          @return true
     */
    private boolean compareDates(int[] before, int[] after){
        if (before[2] < after[2]){
            return true; //if year is bigger @param after clearly comes after
        }else if (before[1] < after[1] && before[2] == after[2]){
            return true; //if years are equal but month is bigger
        }
        return (before[0] <= after[0] && before[1] == after[1] && before[2] == after[2] ); //years and months the same then days are concerned

    }

    /**
     * to easily compare dates
     * @param strDate is the string format (ex. "01.04.2020")
     * @return split integer list (ex. {1,4,2020} )
     */
    private int[] dateSplitToInteger(String strDate){
        String[] splitted = strDate.split("\\.");
        return new int[] {Integer.valueOf(splitted[0]),Integer.valueOf(splitted[1]),Integer.valueOf(splitted[2])};
    }
}//class
