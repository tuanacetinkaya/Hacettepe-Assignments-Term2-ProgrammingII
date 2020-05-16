public class DoctorVisit extends DecoratorExamine {

    DoctorVisit(IExamination examination) {
        super(examination);
    }
    @Override
    public int getCost() {
        return rootExamination.getCost() + 15;
    }

    @Override
    public String getDescription() {
        return rootExamination.getDescription() + "doctorvisit ";
    }
}
