import com.sun.istack.internal.NotNull;

public class Stack {
    private Node top;
    private int size;

    Stack(){
        top = null;
        size = 0;
    }

    public Node push(Node item){
        Node second = top;
        top = item;
        top.setNextNode(second);
        size++;
        return item;
    }

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

    public Node peek(){
        return top;
    }

    public boolean isEmpty(){
        return top == null;
    }


    public void initialize(int[] fileArray){
        if(fileArray == null){
            System.out.println("Stack File Array Does Not Exist");
            return;
        }
        if(fileArray.length ==  0){
            System.out.println(" Warning: 'stack.txt' is empty. Stack cannot be initialized");
            return;
        }

        top = new Node(fileArray[0]);
        size++;

        Node index = top;
        for (int i = 1; i< fileArray.length ; i++) {
            index.setNextNode(new Node(fileArray[i]));
            index = index.getNextNode();
            size++;
        }
    }

    public Node getTop() {
        return top;
    }

    public void setTop(Node top) {
        this.top = top;
    }

    public int getSize() {
        return size;
    }

    public String toString(){
        StringBuilder stack = new StringBuilder();
        Node index = top;
        for(int i = 0; i < size; i++){
            stack.append(index.getValue() + " ");
            index = index.getNextNode();
        }
        return stack.toString().trim(); //trim to get rid of the last empty space
    }

}
