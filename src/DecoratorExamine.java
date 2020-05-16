public class DecoratorExamine extends Examination {

    private Examination rootExamination;

    //will not be used.

    public DecoratorExamine(Examination examination){
        rootExamination = examination;
        super.setOperation(rootExamination.getStrExamination());
    }

    @Override
    public int totalCost() {
        return rootExamination.totalCost();
    }

    public Examination getRootExamination() {
        return rootExamination;
    }

    public void setRootExamination(Examination rootExamination) {
        this.rootExamination = rootExamination;
    }
}
