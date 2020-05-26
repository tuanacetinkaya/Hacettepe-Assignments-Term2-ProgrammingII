public class Queue {
    private Node head;
    private Node tail;
    private int size;

    Queue(){
        head = tail = null;
        size = 0;
    }

    public boolean add(Node n){
        if(size < 100) {
            if (isEmpty()) {
                head = tail = n;
                head.setNextNode(null);
            } else {
                tail.setNextNode(n);
                tail = tail.getNextNode();
            }
            size++;
            return true;
        }
        System.out.println("Queue has reached the maximum size, cannot add more values");
        return false;
    }

    public Node remove(){
        if(!isEmpty()){
            Node queued = new Node(head.getValue());
            head = head.getNextNode();
            size--; //size update
            return queued;
        }
        else{
            System.out.println("The queue is empty, remove operation has failed!");
            return null;
        }
    }

    public void removeBiggerThan(int number){
        Node index = head;
        for(int i = 0; i< size; i++){
            if(index.getNextNode() != null) {
                if (index.getNextNode().getValue() > number) {
                    //next node now assigned to the node after and the actual next node lost it's reference,
                    // will be collected by garbage collector
                    //remember edge case: if there are 2 elements in the list, then the node after will be null and the code will not break
                    index.setNextNode(index.getNextNode().getNextNode());
                    size--; //size update
                }
            }
        }
        //I check the head last (after getting rid of every other match case in list),
        // to avoid the second element match case which will force us to remove the head element again.
        if(head.getValue() > number){
            remove();
        }
    }


    public Node peek(){
        return head;
    }

    public boolean isEmpty(){
        return head == null;
    }

    public void initialize(int[] queueFileList){
        head = new Node(queueFileList[0]);

        tail = head;
        for(int i = 1; i < queueFileList.length; i++){
            add(new Node(queueFileList[i]));
            //todo
        }
    }

    @Override
    public String toString(){
        StringBuilder queueString = new StringBuilder();
        Node current = head;
        for(int i = 0; i< size; i++) {
            queueString.append(current.getValue()).append(" ");
            current = current.getNextNode();
        }
        return queueString.toString().trim(); //trim() to get rid of the last empty space
    }

    public int getSize() {
        return size;
    }
}
