package Quiz4;

import java.util.Comparator;

public class LastNameComparator implements Comparator<Contact> {

    @Override
    public int compare(Contact o1, Contact o2) {
        return o1.getLastName().compareTo(o2.getLastName());
    }
}
