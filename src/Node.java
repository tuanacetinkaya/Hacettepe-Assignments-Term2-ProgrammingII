public class Node {

    private int value;
    private Node nextNode;

    Node(){
        this(-1, null);
        //when nothing is assigned the node will have the value -1
    }

    Node(int value){
        this(value, null);
    }

    Node(int value, Node nextNode){
        this.value =value;
        this.nextNode = nextNode;
    }

    public boolean hasNext(){
        return nextNode != null;
    }

    public int getValue(){
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getNextNode() {
        return nextNode;
    }

    public void setNextNode(Node nextNode) {
        this.nextNode = nextNode;
    }

    public String toString(){
        return String.valueOf(this.value);
    }
}
