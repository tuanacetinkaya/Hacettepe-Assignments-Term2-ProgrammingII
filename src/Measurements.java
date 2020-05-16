public class Measurements extends DecoratorExamine {

    Measurements(Examination examination){
        super(examination);
        super.setStrExamination("measurements");

    }

    @Override
    public int totalCost() {
        return super.totalCost() + 5;
    }

}