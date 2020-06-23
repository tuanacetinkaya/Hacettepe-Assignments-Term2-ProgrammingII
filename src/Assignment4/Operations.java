package Assignment4;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
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
        ReadTextFile stackInitialFile = new ReadTextFile("stack.txt");
        ReadTextFile queueInitialFile = new ReadTextFile("queue.txt");

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
                stack = reverse(Integer.parseInt(todo[2]), stack);
                outFile.println("After reverse " + todo[2] + ":");
                outFile.println(stack);
                break;

            case("sortElements"):
                sortElements(stack);
                outFile.println("After sortElements:");
                outFile.println(stack);
                break;

            case("distinctElements"):
                outFile.println("After distinctElements:");
                outFile.println("Total distinct element=" + distinctElements(stack));
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
                queue = reverse(Integer.parseInt(todo[2]), queue);
                outFile.println("After reverse " + todo[2] + ":");
                outFile.println(queue);
                break;

            case("sortElements"):
                sortElements(queue);
                outFile.println("After sortElements:");
                outFile.println(queue);
                break;

            case("distinctElements"):
                outFile.println("After distinctElements:");
                outFile.println("Total distinct element=" + distinctElements(queue));
                break;

            default:
                System.out.println("Command formatting with queue is wrong.");
        }

    }


    /**
     * Method find the number of distinct elements using HashSet
     * @param stack or queue is the target list
     * @return the number of unique elements in list
     */
    private int distinctElements(Stack stack){
        Stack helperStack = new Stack();
        int length = stack.getSize();
        HashSet<Integer> distinct = new HashSet<>();

        for(int i = 0; i < length; i++){
            distinct.add(stack.peek().getValue());
            helperStack.push(stack.pop());
        }
        for(int i = 0; i < length; i++){
            stack.push(helperStack.pop());
        }
        return distinct.size();
    }
    private int distinctElements(Queue queue){
        Queue helperQueue = new Queue();
        int length = queue.getSize();
        HashSet<Integer> distinct = new HashSet<>();

        for(int i = 0; i < length; i++){
            distinct.add(queue.peek().getValue());
            helperQueue.add(queue.remove());
        }
        for(int i = 0; i < length; i++){
            queue.add(helperQueue.remove());
        }
        return distinct.size();
    }


    //getters in case of need
    public Stack getStack() {
        return stack;
    }
    public Queue getQueue() {
        return queue;
    }


    private Stack removeBiggerThan(int number, Stack stack){
        Stack helperStack = new Stack();
        int initialSize = stack.getSize();

        if(stack.isEmpty()){
            System.out.println("Stack is empty");
            return null;
        }

        for(int i = 0; i< initialSize; i++){
            if(stack.peek().getValue() <= number){
                helperStack.push(stack.pop());
            }else{
                stack.pop();
            }
        }
        int finalSize = helperStack.getSize();
        for (int j = 0; j < finalSize; j++){
            stack.push(helperStack.pop());
        }
        return stack;
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
        Queue helperQueue= new Queue();
        Node hold;
        Node iterator;
        int distance = 0;
        int initialSize = queue.getSize();

        //greater for loop has to be performed by the initial size of the list
        // since we will continue to popping elements and shrinking size.
        for(int i = 0; i< initialSize; i++){
            //hold the head and calculate the distance according to that
            hold = queue.getHead();
            iterator = queue.peek();
            //scan the whole queue calculating the distance for the first element
            for(int j = 0; j< queue.getSize()-1; j++){
                distance += Math.abs(iterator.getValue() - hold.getValue());
                iterator = iterator.getNextNode();
            }
            //get rid of the first element so we can continue the same operation for each element on the queue
            helperQueue.add(queue.remove()); //added head to the queue
        }

        //return the queue to it's initial position
        Node reader = helperQueue.getHead();
        for(int k = 0; k < initialSize; k++){
            queue.add(reader);
            reader = reader.getNextNode();
        }
        return distance;
    }


    /**
     * Reverse the first part of the list if the number given is no greater than the size.
     * Implementation directly reverse Stack since it's natural and use integer array for Queue.
     * @param numberOfElements to reverse from the beginning of the list
     * @param stack or queue is the list to operate
     * @return the reversed list if the number is smaller. return null otherwise
     */
    private Stack reverse(int numberOfElements, Stack stack){

        int[] reverseList = new int[numberOfElements];
        if(numberOfElements <= stack.getSize()){
            for(int i = 0; i < numberOfElements; i++){
                reverseList[i] = stack.pop().getValue();
            }
            for(int i = 0; i < numberOfElements; i++){
                stack.push(reverseList[i]);
            }
            return stack;

        }else {
            System.out.println("Please enter a value between one and the total size of the list for reverse operation.");
            return null;
        }
    }
    private Queue reverse(int numberOfElements, Queue queue){
        int initialSize = queue.getSize();
        int[] elementsToReverse = new int[numberOfElements];
        int[] keepOrder = new int[initialSize - numberOfElements];

        if(numberOfElements <= initialSize){
            for(int j = 0; j < numberOfElements; j++){
                elementsToReverse[numberOfElements- j -1] = queue.remove().getValue();
            }
            for(int i = 0; i < initialSize - numberOfElements; i++){
                keepOrder[i] = queue.remove().getValue();
            }

            for(int k = 0; k < numberOfElements; k++){
                queue.add(elementsToReverse[k]);
            }
            for(int i = 0; i < initialSize - numberOfElements; i++){
                queue.add(keepOrder[i]);
            }

            return queue;

        }else {
            System.out.println("Please enter a value between one and the total size of the list for reverse operation.");
            return null;
        }
    }


    /**
     * transfer the elements to array, sort them using Arrays.sort() and push them back with the correct order.
     * @param stack or queue to sort
     * @return the sorted stack or queue
     */
    private Stack sortElements(Stack stack){
        int length = stack.getSize();
        int[] sortedElements = new int[length];

        for(int i = 0; i< length; i++){
            sortedElements[i] = stack.pop().getValue();
        }
        Arrays.sort(sortedElements);
        for(int i = 0; i < length; i++){
            stack.push(sortedElements[length -i -1]);
        }
        return stack;
    }
    private Queue sortElements(Queue queue){
        int length = queue.getSize();
        int[] sortedElements = new int[length];

        for(int i = 0; i< length; i++){
            sortedElements[i] = queue.remove().getValue();
        }

        Arrays.sort(sortedElements);

        for(int i = 0; i < length; i++){
            queue.add(sortedElements[i]);
        }
        return queue;
    }




}
