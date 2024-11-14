import java.io.File;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors
public class Ticket {

    // Initiating Ticket Attributes
    private char row;
    private double price;
    private int seat;

    private Person person ;

    //Setters for Ticket class
    public void setRow(char row){
        this.row = row;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public void setSeat(int seat){
        this.seat = seat;
    }

    public void setPerson(Person person){
        this.person = person;
    }

    // Getters for Ticket class
    public char getRow(){
        return this.row;
    }

    public double getPrice(){
        return this.price;
    }

    public int getSeat(){
        return this.seat;
    }

    public Person getPerson(){
        return this.person;
    }


    // methods that Ticket class perform
    public void printInfo(){
        person.printInfo();
        System.out.println("Seat :" + this.row + this.seat + " and the Price :£" + this.price);
        System.out.println();

    }

    //Ticket printing method
    public void save(){
        String fileName = this.getRow() + String.valueOf(this.getSeat())+".txt";
        try {
            File file = new File(fileName);
            FileWriter myWriter = new FileWriter(fileName);
            myWriter.write("Name :"+ this.getPerson().getName()+"\nSurname :"+ this.getPerson().getSurname()+"\nEmail :"+ this.getPerson().getEmail()+ "\nSeat :" + this.row + this.seat + "\nPrice :£" + this.price);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }

    }

}
