package Assignments.Asgn1;


public class Market {
    private String[][] shoppingList;
    private String[][] priceList;

    private Customer[] customers;
    private Product[] products;

    /**
     * Precondition: shoppingList and priceList HAS TO BE in the right format  (check initialize methods for more)
     * @param shoppingList is customer orders
     * @param priceList is products
     * Post condition: all customers took their products and all bills are ready in their objects to print, just say it
     */
    public Market(String[][] shoppingList, String[][] priceList){
        this.priceList = priceList;
        this.shoppingList = shoppingList;
        customers = new Customer[shoppingList.length]; // this is default. Every line belongs to another customer
        products = new Product[priceList.length]; //again worst case scenario, every line in list might be another product
        initializePriceList();
        initializeShoppingList();
    }

    public void printBills(){
        for(Customer joe: customers){
            joe.displayBill();
        }
    }

    /**
     * precondition: price list format has to be right as defined:
     *                 [product name] tab [type of membership] tab [start date] tab [end date] tab [price] newline
     * post condition: read price list and create Product objects and add them to products list
     *                 if the product is already exist in list add the corresponding price conditions to Product
     */
    private void initializePriceList(){
        int indexProduct = 0; //index of last product

        for(String[] unit: priceList){
            if(inList(unit)){continue;} //inList function also updates the product for new price
            products[indexProduct] = new Product(unit[0], unit[1], unit[2], unit[3], unit[4]);
            indexProduct ++;
        }
    }//initializePriceList

    /**
     * precondition: shopping list format has to be right as defined:
     *                 [customer name] space [surname] tab [type of membership] tab [shopping date] tab [product name] tab [quantity] tab [product name] tab [quantity] newline
     * post condition: read shopping list and create Customer objects and update their bills
     */
    private void initializeShoppingList(){
        int indexCustomer = 0;
        for(String[] custInfo: shoppingList){
            customers[indexCustomer] = new Customer(custInfo[0], custInfo[1], custInfo[2]);
            //i = 3 starts from products and increased  by 2 to read product as (i) and quantity as (i+1)
            for (int i = 3; i < custInfo.length ; i += 2){
                customers[indexCustomer].addToBill(findProduct(custInfo[i]), Integer.valueOf( custInfo[i+1]));
            }
            indexCustomer ++; //index increased after customer done shopping
        }
    }//initializeShoppingList

    /**
     * Find the "just right" product for customer. Modern shop assistant
     * @param productName the wanted product, they don't come with sizes tho.
     * @return the product to make happy customers
     */
    private Product findProduct(String productName){
        for(Product member: products){
            if(member.getPRODUCT_NAME().equals(productName)){
                return member;
            }
        }
        System.out.println("Customer tried to buy a non-existent product. Please check the Price List.");
        return null;
    }

    // find if the product has a previous price record
    private boolean inList(String[] product){
        for(Product member: products){
            if(member != null){
                if(member.getPRODUCT_NAME().equals(product[0])){
                    member.addPrice(product[1], product[2], product[3], product[4]);
                    return true;
                }
            } else return false;
        }
        return false;
    }

}
