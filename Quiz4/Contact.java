package Quiz4;

public class Contact implements Comparable<Contact> {

    private String phoneNumber;
    private String firstName;
    private String lastName;
    private String socialSecurityNumber;

    public Contact(String phoneNumber, String firstName, String lastName, String socialSecurityNumber) {
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.socialSecurityNumber = socialSecurityNumber;
    }
    public String toString(){
        return String.join(" ", new String[] {phoneNumber, firstName, lastName, socialSecurityNumber});
    }

    @Override
    public int compareTo(Contact o) {
        return this.phoneNumber.compareTo(o.getPhoneNumber());
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
