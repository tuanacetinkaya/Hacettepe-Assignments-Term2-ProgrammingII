public class Patient{
    private int patientID;
    private String surname;
    private String name;
    private String address;
    private String phone;

    //regular constructor has address input row -> as it is used in input.txt
    public Patient(String patientID, String name, String surname, String phone, String address){
        this.patientID = Integer.parseInt(patientID);
        this.surname = surname;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }
    //initial patient constructor with input that has "Address: " on it.
    public Patient(String patientID, String nameAndSurname, String phone, String address) {
        this(
                patientID,
                nameAndSurname.split(" ")[0],
                nameAndSurname.split(" ")[1],
                phone,
                address.split(":")[1].trim()
        );
    }

    public String toString(){
        return String.format("%d\t%s %s\t%s\tAddress: %s",
                patientID,
                name, surname,
                phone,
                address);
    }

    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


}
