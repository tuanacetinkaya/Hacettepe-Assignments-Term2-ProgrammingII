package Quizes.Quiz1;

public class Phone {
    private String countryCode;
    private String code;
    private int number;
    private String type;
    private static String DEFAULT_COUNTRY_CODE = "+90";

    public Phone(String countryCode, String code, int number, String type) {
        this.countryCode = countryCode;
        this.code = code;
        this.type = type;
        if (String.valueOf(number).length() != 7){
            System.out.println("Your number is not valid. Set to 0 for you to change later.");
        }else{
            this.number = number;
        }
    }
    public Phone(String code, int number, String type){
        this(DEFAULT_COUNTRY_CODE, code,number, type);
    }

    public void display(){
        System.out.format("%s Phone: %s %s %d%n ", type, countryCode, code, number);
    }
    //getter and setters
    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    //till here
}
