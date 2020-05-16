public class Imaging extends DecoratorExamine {
    Imaging(Examination examination){
        super(examination);
        super.setStrExamination("imaging");
    }

    @Override
    public int totalCost() {
        return super.totalCost() + 10;
    }
}
