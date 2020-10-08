import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int remainder;
        List<String> allValues = null;
        PrintWriter octalFile = null;

        try{
            allValues = Files.readAllLines(Paths.get(args[0]));
            octalFile = new PrintWriter("octal.txt");
        }catch (IOException io){
            System.out.println("File or command input missing");
            System.exit(0);
        }

        for(String strNumber: allValues){
            Stack octal = new Stack();
            int number = Integer.parseInt(strNumber);
            while (number != 0){
                remainder = number % 8;
                octal.push(remainder);
                number = number/8;
            }
            octalFile.println(octal);

        }

        octalFile.close();
    }
}
