public class Stack {
    private Node top;
    private int size;
    private final int MAX_CAPACITY = 100;


    /**
     * Stack implementation with LinkedList data structure
     * <tt>top</tt> is the first item in stack
     * Stack removes from and adds elements to the top of the list
     * <tt>size</tt> is the total elements in list
     */
    Stack(){
        top = null;
        size = 0;
    }

    /**
     * pushes the <tt>item/tt> to the top of the stack and makes it <tt>top</tt> element.
      * @param item to be added to stack
     * @return true if the push operation succeed
     */
    public boolean push(Node item){
        if(item != null){
            if(size <= MAX_CAPACITY){
                Node second = top;
                top = item;
                top.setNextNode(second);
                size++;
                return true;
            }
            System.out.println("Stack has reached the maximum size. Cannot add more values");
            return false;
        }

        System.out.println("Cannot push null value, operation skipped");
        return false;
    }

    //helper method to push integer values directly to stack
    public boolean push(int value){
        return push(new Node(value));
    }

    /**
     * Removes the top element from the stack and return it's value
     * @return the value of the removed Node
     */
    public Node pop(){
        if(!isEmpty()) {
            Node popped = top;
            top = top.getNextNode();
            size--;
            return popped;
        }
        System.out.println("Stack is empty, pop operation failed");
        return null;
    }

    /**
     * @return the top node without removing it
     */
    public Node peek(){
        return top;
    }

    /**
     * @return true if the stack is empty
     */
    public boolean isEmpty(){
        return top == null;
    }

    /**
     * initialize the stack by reading the values on the given array
     * @param fileArray integer array of the initial file ordered from the top element to the rest of the stack
     */
    public void initialize(int[] fileArray){
        if(fileArray == null){
            System.out.println("Stack File Array Does Not Exist");
            return;
        }
        if(fileArray.length ==  0){
            System.out.println(" Warning: 'stack.txt' is empty. Stack cannot be initialized");
            return;
        }

        if(fileArray.length <= MAX_CAPACITY){
            top = new Node(fileArray[0]);
            size++;

            Node index = top;
            for (int i = 1; i < fileArray.length; i++) {
                index.setNextNode(new Node(fileArray[i]));
                index = index.getNextNode();
                size++;
            }
        }
    }

    //getters and setters
    public Node getTop() { return top; }

    public void setTop(Node top) { this.top = top; }

    public int getSize() { return size; }

    public String toString(){
        StringBuilder stack = new StringBuilder();
        Node index = top;
        if(top != null){
            for (int i = 0; i < size; i++) {
                stack.append(index.getValue()).append(" ");
                index = index.getNextNode();
            }
            return stack.toString().trim(); //trim to get rid of the last empty space
        }
        return "";
    }

    /**
     * this method uses bubble sort technique
     * @return the head of the sorted queue
     */
    public Node sort(){
        //tale will be updated at the end of the sorting process
        Node beforeNode;
        Node biggest;
        Node restOfTheList;

        if(top == null){
            System.out.println("The queue is empty, sort process cannot be applied");
        }

        for(int i = 0; i < size -1; i++){
            biggest = beforeNode = top;
            for (int limit = 0; limit < size - i -1 ; limit++) {
                //has to run size-1 times because it compares two items at a time
                // -think the process as you are on a rope between the nodes-
                if (biggest.getNextNode().getValue() >= biggest.getValue()) {
                    beforeNode = biggest;
                    biggest = biggest.getNextNode();
                } else {
                    restOfTheList = biggest.getNextNode().getNextNode(); //holding the rest of the list safe
                    if(beforeNode != biggest){
                        beforeNode.setNextNode(biggest.getNextNode());
                        beforeNode.getNextNode().setNextNode(biggest);
                        biggest.setNextNode(restOfTheList);
                        beforeNode = beforeNode.getNextNode();
                    }else {
                        //means before is equal to the biggest and it is the beginning -the head- of the queue
                        beforeNode = biggest.getNextNode();
                        biggest.setNextNode(restOfTheList);
                        beforeNode.setNextNode(biggest);
                        top = beforeNode; //update the head of the list
                    }

                    //switched the place of the smaller value and the biggest node so far.
                }
            }
        }
        return top;
    }


}
