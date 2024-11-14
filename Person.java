public class Person {

    // Person class Attributes
    private String name;
    private String surname;
    private String email;

    // Constructor
    Person(String name, String surname, String email){

        this.name = name;
        this.surname = surname;
        this.email = email;
    }


    //Getters for Person class Attributes
    public String getName(){
        return this.name;
    }
    public String getSurname(){
        return this.surname;
    }
    public String getEmail(){
        return this.email;
    }

    //Person class methods
    public void printInfo(){
        System.out.println("Name :"+this.name+"\nSurname :"+ this.surname +"\nE-mail :"+ this.email);
    }
}
