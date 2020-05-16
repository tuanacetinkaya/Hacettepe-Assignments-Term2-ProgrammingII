public class DecoratorExamine implements IExamination {

    protected IExamination rootExamination;

    DecoratorExamine(IExamination examination){
        rootExamination = examination;
    }
    @Override
    public int getCost() {
        return rootExamination.getCost();
    }

    @Override
    public String getDescription() {
        return rootExamination.getDescription();
    }


}
