package Assignment3;

public class DecoratorExamine implements IExamination {

    protected IExamination rootExamination;

    /**
     * Decorator class of the decorator design pattern. All operations will inherit this class.
     * @param examination takes all the Examination classes
     */
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
