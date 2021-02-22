package Assignment3;

public class Imaging extends DecoratorExamine {
    Imaging(IExamination examination){
        super(examination);
    }

    @Override
    public int getCost() {
        return rootExamination.getCost() + 10;
    }

    @Override
    public String getDescription() {
        return rootExamination.getDescription() + "imaging ";
    }
}
