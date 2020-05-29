package Assignment4;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

public class OperationManager {
    String[] commands;
    private Stack stack;
    private Queue queue;

    /**
     * Main operation center. Stack and Queue will be held here.
     * <tt>commands</tt> is the String array to keep and execute all commands easily.
     * @param commandFileName is the file given by the user to read the commands
     */
    public OperationManager(String commandFileName) {
        ReadTextFile commandCenter = new ReadTextFile(commandFileName);
        ReadTextFile stackInitialFile = new ReadTextFile("Assignment4/stack.txt");
        ReadTextFile queueInitialFile = new ReadTextFile("Assignment4/queue.txt");

        commands = commandCenter.getListFormat();

        stack = new Stack();
        queue = new Queue();

        stack.initialize(integerConversion(stackInitialFile.getListFormat()[0]));
        queue.initialize(integerConversion(queueInitialFile.getListFormat()[0]));

    }
    //helper method to convert the read values of stack and queue to integer array
    private int[] integerConversion(String list){
        String[] split = list.split(" ");
        int[] converted = new int[split.length];
        int i = 0;
        for(String val: split){
            converted[i++] = Integer.parseInt(val);
        }
        return converted;
    }

    /**
     * updates the queue.txt and stack.txt to their latest version
     */
    public void updateFiles(){
        PrintWriter queueFile;
        PrintWriter stackFile;
        try {
            queueFile = new PrintWriter("Assignment4/queue.txt");
            stackFile = new PrintWriter("Assignment4/stack.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't find queue.txt or stack.txt");
            return;
        }
        queueFile.println(queue.toString());
        stackFile.println(stack.toString());

        queueFile.close();
        stackFile.close();
    }

    /**
     * Read the <tt>commands</tt> and execute them, write the operation and the result to output files: queueOut.txt and stackOut.txt
     */
    public void performCommands(){

        PrintWriter stackOut = null;
        PrintWriter queueOut = null;

        try {
            stackOut = new PrintWriter("Assignment4/stackOut.txt");
            queueOut = new PrintWriter("Assignment4/queueOut.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Failed to create output files");
            System.exit(0);
        }

        for(String line: commands){
            String[] todo = line.split(" ");
            switch (todo[0].trim()){

                case("S"):
                    stackPerform(todo, stackOut);
                    break;
                case("Q"):
                    queuePerform(todo, queueOut);
                    break;
                default:
                    System.out.println("Command file does not declare the operation type as stack(S) or queue(Q)!");
            }
        }

        stackOut.close();
        queueOut.close();
    }

    //stack operations
    private void stackPerform(String[] todo, PrintWriter outFile){
        switch (todo[1].trim()){
            case ("removeGreater"):
                stack.removeBiggerThan(Integer.parseInt(todo[2]));
                outFile.println("After removeGreater " + todo[2] + ":");
                outFile.println(stack);
                break;

            case("calculateDistance"):
                outFile.println("After calculateDistance:");
                outFile.println("Total distance=" + getDistance(stack.getTop()));
                break;

            case ("addOrRemove"):
                Random random = new Random();
                int howManyTimes = Integer.parseInt(todo[2]);
                if(howManyTimes > 0){
                    for(int i = 0; i < howManyTimes; i++){
                        stack.push(random.nextInt(50));
                    }
                }else {
                    for (int j = 0; j < (-1 * howManyTimes); j++){
                        stack.pop();
                    }
                }
                //update the output file
                outFile.println("After addOrRemove " + todo[2] + ":");
                outFile.println(stack);
                break;

            case ("reverse"):
                reverse(Integer.parseInt(todo[2]), stack.getSize(), stack.getTop());
                outFile.println("After reverse " + todo[2] + ":");
                outFile.println(stack);
                break;

            case("sortElements"):
                stack.sort();
                outFile.println("After sortElements:");
                outFile.println(stack);
                break;

            case("distinctElements"):
                outFile.println("After distinctElements:");
                outFile.println("Total distinct element=" + distinctElements(stack.getTop(), stack.getSize()));
                break;

            default:
                System.out.println("Command formatting with stack is wrong.");
        }

    }

    //queue operations
    private void queuePerform(String[] todo, PrintWriter outFile){
        switch (todo[1].trim()){
            case ("removeGreater"):
                queue.removeBiggerThan(Integer.parseInt(todo[2]));
                outFile.println("After removeGreater " + todo[2] + ":");
                outFile.println(queue);
                break;

            case("calculateDistance"):
                outFile.println("After calculateDistance:");
                outFile.println("Total distance=" + getDistance(queue.getHead()));
                break;

            case ("addOrRemove"):
                Random random = new Random();
                int howManyTimes = Integer.parseInt(todo[2]);
                if(howManyTimes > 0){
                    for(int i = 0; i < howManyTimes; i++){
                        queue.add(random.nextInt(50));
                    }
                }else {
                    for (int j = 0; j < (-1 * howManyTimes); j++){
                        queue.remove();
                    }
                }
                outFile.println("After addOrRemove " + todo[2] + ":");
                outFile.println(queue);
                break;

            case ("reverse"):
                reverse(Integer.parseInt(todo[2]), queue.getSize(), queue.getHead());
                outFile.println("After reverse " + todo[2] + ":");
                outFile.println(queue);
                break;

            case("sortElements"):
                queue.sort();
                outFile.println("After sortElements:");
                outFile.println(queue);
                break;

            case("distinctElements"):
                outFile.println("After distinctElements:");
                outFile.println("Total distinct element=" + distinctElements(queue.getHead(), queue.getSize()));
                break;

            default:
                System.out.println("Command formatting with queue is wrong.");
        }

    }

    /**
     * calculates the distance of each element and add them up
     * @param headNode is the list to perform since this operation is common for stack and queue, only the head node will be enough
     * @return the distance with a formula such as n1 is the first node and nE is the last node:
     *          distance = |n1 - n2| + |n1- n3| .. |n1 - nE| + |n2 - n3| + ...
     */
    private int getDistance(Node headNode){
        Node hold = headNode;
        Node move = headNode;
        int distance = 0;
        for(int i = 0; i < stack.getSize()-1; i++){
            for(int j = 0; j < stack.getSize()-i-1; j++){
                move = move.getNextNode();
                distance += Math.abs(hold.getValue() - move.getValue());
            }
            hold = hold.getNextNode();
            move = hold;
        }
        return distance;
    }

    /**
     * Reverse the first part of the list if the number given is no greater than the size.
     * Quick note: It might seem like we lost the tail on queue when we use the same method for stack and queue
     *      but the the method does not change the position of the nodes just mess with the values, so the head and the
     *      tail is still the same nodes with different values.
     * @param numberOfElements to reverse from the beginning of the list
     * @return the head of the reversed list if the number is smaller. return null otherwise
     */
    private Node reverse(int numberOfElements, int size, Node head){

        if(numberOfElements <= size && numberOfElements > 0){
            //fill the array with the values to reverse
            Node index = head;
            int[] array = new int[numberOfElements];
            for (int i = 0; i < numberOfElements; i++) {
                array[i] = index.getValue();
                index = index.getNextNode();
            }

            //reverse operation
            for (int j = 0; j < array.length / 2; j++) {
                int temp = array[j];
                array[j] = array[array.length - j - 1];
                array[array.length - j - 1] = temp;
            }

            //refresh the first <<tt>>numberOfElements<</tt>> element's value and update with the reversed version
            Node refresh = head;
            for (int k = 0; k < numberOfElements; k++) {
                refresh.setValue(array[k]);
                refresh = refresh.getNextNode();
            }
            return head;

        }else {
            System.out.println("Please enter a value between one and the total size of the list.");
            return null;
        }
    }

    /**
     * @param head is the first node since implementation for stack and queue is common
     * @param size is the total number of items in list
     * @return number of the different values in list
     */
    public int distinctElements(Node head, int size){
        ArrayList<Integer> distinctValues = new ArrayList<>();
        Node index = head;
        for(int i = 0; i < size; i++){
            if(!distinctValues.contains(index.getValue())){
                distinctValues.add(index.getValue());
            }
            index = index.getNextNode();
        }
        return distinctValues.size();
    }

    //getters and setters in case of need
    public Stack getStack() {
        return stack;
    }
    public Queue getQueue() {
        return queue;
    }

}
