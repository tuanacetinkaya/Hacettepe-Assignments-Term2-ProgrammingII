public class Test extends DecoratorExamine {
    Test(Examination examination){
        super(examination);
        super.setStrExamination("test");

    }

    @Override
    public int totalCost() {
        return super.totalCost() + 7;
    }
}
