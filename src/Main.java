import java.util.MissingFormatArgumentException;

/**
 * Assignment 4: Stack and Queue Operations
 * (Due: 10.06.2020)
 * (Start: 26.05.2020 - Delivered:  )
 * @author 21985164
 */

public class Main {
    public static void main(String[] args){
        OperationManager stackQueueManager = null;

        try{
            stackQueueManager = new OperationManager(args[0]);
        }catch (MissingFormatArgumentException f){
            System.out.println("Please enter the command file name with your arguments.");
            System.exit(0);
        }

        stackQueueManager.performCommands();
        stackQueueManager.updateFiles();

    }
}
