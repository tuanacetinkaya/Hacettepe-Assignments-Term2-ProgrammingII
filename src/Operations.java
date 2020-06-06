import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

public class Operations {
    String[] commands;
    private Stack stack;
    private Queue queue;

    /**
     * Main operation center. Stack and Queue will be held here.
     * <tt>commands</tt> is the String array to keep and execute all commands easily.
     * @param commandFileName is the file given by the user to read the commands
     */
    public Operations(String commandFileName) {
        ReadTextFile commandCenter = new ReadTextFile(commandFileName);
        ReadTextFile stackInitialFile = new ReadTextFile("src\\stack.txt");//todo
        ReadTextFile queueInitialFile = new ReadTextFile("src\\queue.txt");//todo

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
            queueFile = new PrintWriter("queue.txt");
            stackFile = new PrintWriter("stack.txt");
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
            stackOut = new PrintWriter("stackOut.txt");
            queueOut = new PrintWriter("queueOut.txt");
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
                stack = removeBiggerThan(Integer.parseInt(todo[2]), stack);
                outFile.println("After removeGreater " + todo[2] + ":");
                outFile.println(stack);
                break;

            case("calculateDistance"):
                outFile.println("After calculateDistance:");
                outFile.println("Total distance=" + calculateDistance(stack));
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
                queue = removeBiggerThan(Integer.parseInt(todo[2]), queue);
                outFile.println("After removeGreater " + todo[2] + ":");
                outFile.println(queue);
                break;

            case("calculateDistance"):
                outFile.println("After calculateDistance:");
                outFile.println("Total distance=" + calculateDistance(queue));
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
            System.out.println("Please enter a value between one and the total size of the list for reverse operation.");
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


    private Stack removeBiggerThan(int number, Stack stack){
        Stack helperStack = new Stack();
        Stack finalStack = new Stack();
        Node iterate = stack.getTop();

        for(int i = 0; i< stack.getSize(); i++){
            if(iterate.getValue() <= number){
                helperStack.push(iterate);
            }
        }

        for (int j = 0; j < helperStack.getSize(); j++){
            finalStack.push(helperStack.pop());
        }
        return finalStack;
    }

    private Queue removeBiggerThan(int number, Queue queue){
        Queue helperQueue = new Queue();
        Node iterate = queue.getHead();

        for(int i = 0; i< queue.getSize(); i++){
            if(iterate.getValue() <= number){
                helperQueue.add(iterate);
            }
            iterate = iterate.getNextNode();
        }
        return helperQueue;
    }

    /**
     * calculates the distance of each element and add them up
     * @param stack or queue is the list to perform since this operation
     * @return the distance with a formula such as n1 is the first node and nE is the last node:
     *          distance = |n1 - n2| + |n1- n3| .. |n1 - nE| + |n2 - n3| + ...
     */
    private int calculateDistance(Stack stack){
        Stack helperStack = new Stack();
        Node iterator;
        int distance = 0;
        int holdValue;
        int initialSize = stack.getSize();

        //greater for loop has to be performed by the initial size of the list
        // since we will continue to popping elements and shrinking size.
        for(int i = 0; i< initialSize; i++){
            iterator = stack.getTop();
            holdValue = stack.peek().getValue();
            //scan the whole stack calculating the distance for the first element
            for(int j = 0; j< stack.getSize()-1; j++){
                distance += Math.abs(iterator.getValue() - holdValue);
                iterator = iterator.getNextNode();
            }
            //get rid of the first element so we can continue the same operation for each element on the stack
            helperStack.push(stack.pop());
        }

        //return the stack to it's initial position
        for(int k = 0; k < initialSize; k++){
            stack.push(helperStack.pop());
        }
        return distance;
    }

    private int calculateDistance(Queue queue){
        Stack helperStack= new Stack();
        Node hold;
        Node iterator;
        int distance = 0;
        int initialSize = queue.getSize();

        //greater for loop has to be performed by the initial size of the list
        // since we will continue to popping elements and shrinking size.
        for(int i = 0; i< initialSize; i++){
            //hold the tail and calculate the distance according to that
            hold = queue.getTail();
            iterator = queue.peek();
            //scan the whole queue calculating the distance for the first element
            for(int j = 0; j< queue.getSize()-1; j++){
                distance += Math.abs(iterator.getValue() - hold.getValue());
                iterator = iterator.getNextNode();
            }
            //get rid of the last element so we can continue the same operation for each element on the queue
            helperStack.push(queue.remove());
        }

        //return the queue to it's initial position
        for(int k = 0; k < initialSize; k++){
            queue.push(helperQueue.remove());
        }
        return distance;
    }


}
