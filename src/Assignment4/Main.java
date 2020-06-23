package Assignment4;

import java.util.MissingFormatArgumentException;

/**
 * Assignment 4: Stack and Queue Operations
 * (Due: 10.06.2020)
 * (Start: 26.05.2020 - Delivered: 29.05.2020 )
 * -> Found out I made a mistake by using Node operations to navigate through Stack and Queue and rewrite the code
 * Final Submission: 07.06.2020
 * @author 21985164
 */

public class Main {
    public static void main(String[] args){

        Operations stackQueueManager = null;

        try{
            //initialize lists and create Stack and Queue objects.
            //set the command file by given name with args[0]
            stackQueueManager = new Operations(args[0]);
        }catch (ArrayIndexOutOfBoundsException i){
            System.out.println("Please enter the command file name with your arguments.");
            System.exit(0);
        }catch (MissingFormatArgumentException f){
            System.out.println("Enter the command file name with your arguments");
            System.exit(0);
        }


        stackQueueManager.performCommands(); //read command.txt, write output to queueOut.txt and stackOut.txt
        stackQueueManager.updateFiles(); //update the original stack.txt and queue.txt to their latest version

    }
}
