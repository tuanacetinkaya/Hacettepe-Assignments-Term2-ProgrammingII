public class Inpatient implements IExamination {
    @Override
    public int getCost() {
        return 10;
    }

    @Override
    public String getDescription() {
        return "Inpatient\t";
    }
}
