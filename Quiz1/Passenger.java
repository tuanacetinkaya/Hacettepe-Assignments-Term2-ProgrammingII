package Quizes.Quiz1;

public class Passenger {
    private String name, surname, gender;
    private Phone phone;

    public Passenger(String name, String surname, String gender, Phone phone) {
        this.phone = phone;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
    }
    //getter and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }
    //till here

    public void displayPassenger(){
        System.out.format("%s %s (%s) \n ",name, surname, gender);
        phone.display();
    }
}
