package Assignment3;

public class Measurements extends DecoratorExamine {

    Measurements(IExamination examination){
        super(examination);
    }
    @Override
    public int getCost() {
        return rootExamination.getCost() + 5;
    }

    @Override
    public String getDescription() {
        return rootExamination.getDescription() + "measurements ";
    }


}