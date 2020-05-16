public class DoctorVisit extends DecoratorExamine {
    DoctorVisit(Examination examination){
        super(examination);
        super.setStrExamination("doctorvisit");
    }

    @Override
    public int totalCost() {
        return super.totalCost() + 15;
    }
}
