public class Stack {
    private Node top;
    private int size;
    private final int MAX_CAPACITY = 10;


    public Stack() {
        size = 0;
    }
    /**
     * pushes the <tt>item/tt> to the top of the stack and makes it <tt>top</tt> element.
     * @param item to be added to stack
     * @return true if the push operation succeed
     */
    public boolean push(Node item){
        if(item != null){
            if(!isFull()){
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

    public boolean isFull(){
        return size == MAX_CAPACITY;
    }

    public String toString(){
        StringBuilder stack = new StringBuilder();
        Node index = top;
        if(top != null){
            for (int i = 0; i < size; i++) {
                stack.append(index.getValue());
                index = index.getNextNode();
            }
            return stack.toString().trim(); //trim to get rid of the last empty space
        }
        return "";
    }


}
