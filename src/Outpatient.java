public class Outpatient extends Examination {
    Outpatient(){
        super.setType("Outpatient");
    }

    @Override
    public int totalCost() {
        return super.getCost() + 15;
    }
}
