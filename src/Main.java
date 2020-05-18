
public class Main {
    public static void main (String[] args){
        System.out.println("\tWarning: This program provides console information for possible errors and " +
                "extra steps taken without a request\n\t which will not be mentioned in your 'output.txt'. " +
                "So if there is a problem you might want to check below.\n\t |\n\t V\n");
        Hospital beytepeHospital = null;
        try{
            beytepeHospital = new Hospital(args[0]);

        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("You must enter the input file name with console arguments");
            System.exit(0);
        }

        beytepeHospital.performCommands();
        beytepeHospital.updateFiles();

    }

}
