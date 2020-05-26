import javax.crypto.spec.PSource;

/**
 * Assignment 4: Stack and Queue Operations
 * (Due: 10.06.2020)
 * (Start: 26.05.2020 - Delivered:  )
 * @author 21985164
 */

public class Main {
    public static void main(String[] args){

        ReadTextFile que = new ReadTextFile("src\\queue.txt");
        Queue tryout = new Queue();
        tryout.initialize(integerConversion(que.getListFormat()[0]));

        System.out.println(tryout);
        System.out.println(tryout.getSize());

    }

    public static int[] integerConversion(String list){
        String[] split = list.split(" ");
        int[] converted = new int[split.length];
        int i = 0;
        for(String val: split){
            converted[i++] = Integer.parseInt(val);
        }
        return converted;
    }
}
