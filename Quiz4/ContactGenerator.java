package Quiz4;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class ContactGenerator {
    private String[] phoneBook;

    private ArrayList<Contact> arrayList;
    private HashSet<Contact> hashSet;
    private TreeSet<Contact> treeSet;
    private TreeSet<Contact> treeSetOrderedByLastName;
    private HashMap<String, Contact> hashMap;

    ContactGenerator(String contactFileName){
        ReadTextFile readingGlasses = new ReadTextFile(contactFileName);
        this.phoneBook = readingGlasses.getListFormat();
        arrayList = new ArrayList<>();
        hashSet = new HashSet<>();
        treeSet = new TreeSet<>();
        treeSetOrderedByLastName = new TreeSet<>(new LastNameComparator());
        hashMap = new HashMap<>();


        for(String info: phoneBook){
            String[] parts = info.split(" ");

            arrayList.add(new Contact(parts[0],parts[1], parts[2],parts[3]));
            hashSet.add(new Contact(parts[0],parts[1], parts[2],parts[3]));
            treeSet.add(new Contact(parts[0],parts[1], parts[2],parts[3]));
            treeSetOrderedByLastName.add(new Contact(parts[0],parts[1], parts[2],parts[3]));
            hashMap.put(parts[0], new Contact(parts[0],parts[1], parts[2],parts[3]));
        }


    }

    public ArrayList<Contact> getArrayList(){
        return arrayList;
    }

    public ArrayList<Contact> getArrayListOrderByLastName(){
        ArrayList<Contact> sortedList = getArrayList();
        Collections.sort(arrayList, new LastNameComparator());
        return sortedList;
    }

    public HashSet<Contact> getHashSet(){
        return hashSet;
    }

    public TreeSet<Contact> getTreeSet(){
        return treeSet;
    }

    public TreeSet<Contact> getTreeSetOrderByLastName(){
        return treeSetOrderedByLastName;
    }

    public HashMap<String, Contact> getHashMap(){
        return hashMap;
    }

    public void getAllFiles(){
        PrintWriter arrayListWriter, arrayListOrderedWriter, hashSetWriter, treeSetWriter, treeSetOrderedWriter, hashMapWriter;
        arrayListWriter = arrayListOrderedWriter = hashSetWriter = treeSetWriter = treeSetOrderedWriter = hashMapWriter = null;

        try {
            arrayListWriter = new PrintWriter("contactsArrayList.txt");
            arrayListOrderedWriter = new PrintWriter("contactsArrayListOrderByLastName.txt");
            hashSetWriter = new PrintWriter(" contactsHashSet.txt");
            treeSetWriter = new PrintWriter("contactsTreeSet.txt");
            treeSetOrderedWriter = new PrintWriter(" contactsTreeSetOrderByLastName.txt");
            hashMapWriter = new PrintWriter("contactsHashMap.txt");

        } catch (FileNotFoundException e) {
            System.out.println("System Cannot Initialize the Output Files");
            System.exit(0);
        }

        for(Contact c: arrayList){
            arrayListWriter.println(c.toString());
        }
        for(Contact c: getArrayListOrderByLastName()){
            arrayListOrderedWriter.println(c);
        }
        for(Contact c: hashSet){
            hashSetWriter.println(c);
        }
        for(Contact c: treeSet){
            treeSetWriter.println(c);
        }
        for(Contact c: treeSetOrderedByLastName){
            treeSetOrderedWriter.println(c);
        }

        for(Map.Entry<String,Contact> pair: hashMap.entrySet()){
            hashMapWriter.println(pair.getValue());
        }

        arrayListWriter.close();
        arrayListOrderedWriter.close();
        hashSetWriter.close();
        treeSetWriter.close();
        treeSetOrderedWriter.close();
        hashMapWriter.close();


    }

}
