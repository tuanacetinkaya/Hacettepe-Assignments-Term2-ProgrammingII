package Assignments.Asgn1;



import java.util.MissingFormatArgumentException;

public class Main {
    /**
     * @author 21984164 Assignment 1 (due: 10.04.2020) (delivered: 03.04.2020)
     * @param args PRECONDITION: there has to be 2 arguments. Command written as shown:
     *             javac *.java
     *             java Assignments.Asgn2.Main shoppingList.txt priceList.txt
     */

    public static void main(String[] args) {
        String shopList, priceList;

        //when run without console arguments these are the default file names. Comment the block below and use these.
//        shopList = "shoppingList.txt";
//        priceList = "priceList.txt";

        // this block provides error messages: simply take console input and initialize the value to shopList and priceList
        shopList = priceList = "did not specified";
        try {
                shopList = args[0];
                priceList = args[1];

            } catch (MissingFormatArgumentException i) {
                System.out.println("Input Missing: Shopping List file and Price List file has to be specified ");
                System.exit(0);
            } catch (ArrayIndexOutOfBoundsException n) {
                System.out.format("Be sure to enter both lists. Here is what you have: \n " +
                        "\tShopping List: %s %n \tPrice List: %s %n", shopList,priceList);
                System.exit(0);
        }
        //end of the console input block

        //Create files by authorities
        ReadFiles files = new ReadFiles(shopList, priceList);
        //files delivered to shop officer
        Market market = new Market(files.getShopList(),files.getPriceList());
        //Hard working shop assistant delivers all bills
        market.printBills();

    }// main
}// class
