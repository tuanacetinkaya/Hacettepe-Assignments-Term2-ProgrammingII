package Assignments.Asgn1;
import Assignments.Asgn1.*;


public class Customer {
    /*!Post condition for bill at an index i (i< total bought products):
          bill[i] = {[productName, unitPriceForCustomer, quantity, productTotal]} */
    private String[][] bill;

    //customer info
    private String name;
    private String membership;
    private String dateShopped;

    //unit trackers
    private double totalCost = 0.0;
    private int index = 0; //to keep track of the amount of bought products in 'bill'

    /**
     * Customer class is responsible from buying a product and calculating of it's own bill
     *      you can consider it as a thoughtful customer that cares spending
     * @param name of the customer in [Name] space [Surname] format
     * @param membership of the customer and
     * @param dateShopped will be used to calculate price
     */
    public Customer(String name, String membership, String dateShopped){
        this.name = name;
        this.membership = membership;
        this.dateShopped = dateShopped;

        //assuming the customer bought maximum 5 different products, will be initialized later if necessary
        this.bill = new String[5][4];
    }

    /**
     * Adds the product to bill and handle the price calculations
     * @param bought is the bought product
     * @param amount is quantity of product
     */
    public void addToBill(Product bought, int amount){
        //check size first to avoid IndexOutOfBoundsException
        if (isFull(this.bill)){
            this.bill = resizeArray(this.bill);
        }
        //all calculations done here for the customer
        double unitPrice = bought.getPrice(membership, dateShopped);
        double totalProductPrice = unitPrice* amount;
        this.totalCost += totalProductPrice; //totalCost updated

        //means they have the proper membership and date to buy this product
        if (unitPrice != -1){
            //bill shaped to correct order with all information
            this.bill[index] = new String[]{
                    bought.getPRODUCT_NAME(),
                    String.valueOf(unitPrice),
                    String.valueOf(amount),
                    String.valueOf(totalProductPrice)};
        }else {
            System.out.format("%s couldn't find the product they wanted: %s", this.name, bought.getPRODUCT_NAME());
            System.out.println("-> They might not have the valid membership to reach this product or the product is out of date");
        }
        //after all prices are added increase the index to keep bill updated
        index ++;
    }

    /**
     * This method prints bill formatted as below:
     *      [line][line][line][customer name] space [surname][line][line][line] newline
     *      [product name] tab [unit price] tab [quantity] tab [amount] newline
     *       Total tab [Total amount]
     */
    public void displayBill() {
        System.out.format("---%s---\n", this.name);
//        index is the index of the last item bought as mentioned above
        for(int i = 0; i < index ; i++){
            System.out.println(String.join("\t", bill[i]));
        }
        System.out.println("Total\t" + String.valueOf(this.totalCost));
    }


    /**
     * Adds 5 more slots to array if the current space is full.
     * constructed array space is limited for space efficiency since all examples has 4 or less products for customers.
     * included this method in case, consider this as buying another bag for products always there as an option :)
     * @param array for current bill array
     * @return the same array with extra free space
     */
    private String[][] resizeArray(String[][] array){
        String[][] temp = new String[array.length + 5][4];
        System.arraycopy(array,0,temp,0,array.length);
        return temp;
    }

    /**
     * @param array is the bill array
     *        but I didn't wanted to hardcode it, parameters never hurt anyone.
     * @return true if the given array is full
     */
    private boolean isFull(String[][] array){
        return (this.index == array.length-1);
        //return array[array.length-1][0] != null; //if the name of the product isn't assigned (null) the row is empty
    }


    //getters and setters in case
    public String getName() {return name;}
    public void setName(String name) { this.name = name;}
    public String getMembership() {return membership;}
    public void setMembership(String membership) {this.membership = membership;}
}
