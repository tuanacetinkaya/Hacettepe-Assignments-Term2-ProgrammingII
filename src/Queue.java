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

    public boolean add(int value){
        return add(new Node(value));
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
        for (int value : queueFileList) {
            add(new Node(value));
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

    public Node getHead() {
        return head;
    }

    public Node getTail() {
        return tail;
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

        if(head == null){
            System.out.println("The queue is empty, sort process cannot be applied");
        }

        for(int i = 0; i < size -1; i++){
            biggest = beforeNode = head;
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
                        head = beforeNode; //update the head of the list
                    }

                    //switched the place of the smaller value and the biggest node so far.
                    if (restOfTheList == null) {
                        tail = biggest;
                        //means we just switched with the last item on the list and it has to be the tale from now on.
                        //note: this only happens fow the first turn
                    }
                }
            }
        }
        return head;
    }
}
