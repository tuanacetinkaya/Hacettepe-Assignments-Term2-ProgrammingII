package Assignments.Asgn1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author 21985164
 */
public class ReadFiles{
    //expected file names as String
    private String strShoppingList;
    private String strPriceList;
    //scanners initialized for related files
    private Scanner scanShop, scanPrice;
    //will use to parse data to main
    private String[][] shopList, priceList;

    /**
     * File reading class
     * @param strShoppingList and
     * @param strPriceList will be parsed from console
     *        Post Condition: calling the class reads the files and create proper String lists from given files
     *                     remember lists are not yet initialized in constructor,
     *                     use getPriceList and getShopList methods to create and use them
     */
    public ReadFiles(String strShoppingList, String strPriceList ){
        this.strShoppingList = strShoppingList;
        this.strPriceList = strPriceList;

        setScanner(); //to understand the file size to initialize the lists
        int shopX, shopY, priceX, priceY; //list sizes
        //
        int[] sizeShop = findSize(scanShop);
        int[] sizePrice = findSize(scanPrice);
        shopY = sizeShop[0];
        shopX = sizeShop[1];
        priceY = sizePrice[0];
        priceX = sizePrice[1];
        this.shopList = new String[shopY][shopX]; //created list with correct max size
        this.priceList = new String[priceY][priceX]; //created list with correct max size
        setScanner(); //again to reset the scanner indexes
    }//constructor

    //getters and setters
    public String[][] getShopList(){
        return readList(scanShop, shopList);
    }//getShopList
    public String[][] getPriceList(){
        return readList(scanPrice, priceList);
    }//getPriceList

    /**
     * added to be able to read more efficiently
     * @param scan as the file's scanner
     * @param list is the list to be written
     * @return the file in nested list
     */
    private String[][] readList(Scanner scan, String[][] list){
        int index = 0;
        while(scan.hasNextLine()){
            list[index] = scan.nextLine().split("\t");
            index++;
        }
        return list;
    }//readList

    /**
     *to be able to create the lists
     * @param scan local scanner
     * @return the maximum size of the given list as (y -row , x- column)
     * Post condition: when initializing the array x and y has to be used as they are (no need to care index zero)
     */
    private int[] findSize(Scanner scan){
        int x, y, temp;
        x = y =0;
        while(scan.hasNextLine()){
            y +=1;
            String str = scan.nextLine();
            temp = str.split("\t").length;
            if (temp >= x) {x = temp;}
        }
        return new int[] {y, x};
    }//findSize

    /**
     * Precondition: files defined has to be proper txt files
     * Post condition: error-free scanners ready to use
     */
    private void setScanner(){
        //conditions check to provide incorrect inputs
       if(fileCheck()) {
           // problemFile provides exception handling for file names.
            String problemFile = strShoppingList;    //for catching the failed file assigned as the first file by default
            try{
                File fileShopList = new File (strShoppingList);
                problemFile = strPriceList;          //if reads the first file successfully passes to the second file
                File filePriceList = new File(strPriceList);
                problemFile = "or some error occurred while reading files"; //to know the problem is not finding the file but reading it

                //assigned scanners to freely read and split the file inputs
                this.scanShop = new Scanner(fileShopList);
                this.scanPrice = new Scanner(filePriceList);
            } catch (FileNotFoundException f){
                System.out.format("Cannot find the file %s \n Please be sure your files exist" , problemFile );
                System.exit(-1);
            }//try catch
        }
       else {
           System.out.println("Be sure your files is in the right format. (Ex: a.txt)");
           System.exit(0);
       }//if
    }//setScanner

    /**
     * Be sure the given files in txt format and has a proper name
     * @return true if nothing's wrong
     */
    private boolean fileCheck(){
        return  (strShoppingList.endsWith(".txt") && strPriceList.endsWith(".txt") &&
                    strShoppingList.length() > 4 && strPriceList.length() > 4);
                    //since the shortest input is a.txt (length 5)
    }//fileCheck
}//class
