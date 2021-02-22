public class ResearchAssistant extends Academician {
    private int ssBenefitsPercentage; //special service benefits percentage

    ResearchAssistant(String nameAndSurname, String registrationNumber, String yearOfStart){
        super(nameAndSurname, registrationNumber, "RESEARCH_ASSISTANT" , yearOfStart);
        this.ssBenefitsPercentage = 105;
    }


    /**
     * Calculated with respect to base salary, special service benefits and severance pay
     */
    public void calculateSalary(){
        super.setTotalSalary(super.calculateSalary(ssBenefitsPercentage)); //set to base salary with ssBenefits
        super.calculateSalary(); //added severance pay
    }
}
