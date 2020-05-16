public class Inpatient extends Examination {
    Inpatient(){
        super.setType("Inpatient");
        totalCost();
    }

    @Override
    public int totalCost() {
        return super.getCost() + 10;
    }
}
