public class Test extends DecoratorExamine {

    Test(IExamination examination){ super(examination); }

    @Override
    public int getCost() { return rootExamination.getCost() + 7; }

    @Override
    public String getDescription() {
        return rootExamination.getDescription() + "tests ";
    }
}
