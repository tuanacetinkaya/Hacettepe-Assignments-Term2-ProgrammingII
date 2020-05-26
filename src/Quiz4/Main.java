package Quiz4;

/**
 * Quiz4 : Generic Collections
 * (Due: 24.05.2020 23:59)
 * (Delivered: 24.05.2020 17:10)
 * @author 21985164
 */
public class Main {
    public static void main(String[] args){

        ContactGenerator callCenter = new ContactGenerator(args[0]);
        callCenter.getAllFiles();
    }
}
