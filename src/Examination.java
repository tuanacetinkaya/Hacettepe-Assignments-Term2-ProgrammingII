public abstract class Examination {
    private final int MAX_OPERATION = 3;
    private int cost = 0;
    private String strExamination;
    private String type;
    private String[] operations;


    public abstract int totalCost();

    public Examination() {
        operations = new String[MAX_OPERATION]; //maximum 3 additional operations limited
    }

    public String getStrExamination() {
        return strExamination;
    }

    public void setStrExamination(String strExamination) {
        this.strExamination = strExamination;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String[] getOperations() {
        return operations;
    }

    public void setOperation(String newOperation) {
        for(int i = 0; i < operations.length; i++){
            if(operations[i] == null){
                operations[i] = newOperation;
                System.out.println(operations[i]);
                System.out.println(newOperation);
                return;
            }
        }
        System.out.println("No more additional operations accepted more than " + MAX_OPERATION);
    }

    public String toString(){
        return String.format("%s\t",type) +
                String.join(" ", operations) +
                String.format(" %d$", cost);
    }
}
