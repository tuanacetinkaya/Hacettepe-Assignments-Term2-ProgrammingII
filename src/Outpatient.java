public class Outpatient implements IExamination {

    @Override
    public int getCost() {
        return 15;
    }

    @Override
    public String getDescription() {
        return "Outpatient\t";
    }
}
