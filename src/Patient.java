public class Patient {
    private int patientID;
    private String surname;
    private String name;
    private String address;
    private String phone;
    public Patient(String patientID, String name, String surname, String phone, String address){
        this.patientID = Integer.valueOf(patientID);
        this.surname = surname;
        this.name = name;
        this.address = address.split(": ")[1];
        this.phone = phone;
    }

    public Patient(String patientID, String nameAndSurname, String phone, String address) {
        this(patientID, nameAndSurname.split(" ")[0], nameAndSurname.split(" ")[1], phone,address);
    }

    public String toString(){
        return String.format("%d\t%s\t%s %s\t%s\tAddress: %s\n",
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
