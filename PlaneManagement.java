import java.time.temporal.Temporal;
import java.util.*;

class PlaneManagement{

    public int [][] seats;
    public Ticket [] tickets =new Ticket[100];
    public int id = 0;
    public PlaneManagement(){  // Constructor

        // initiating seating Array (A, B, C, D)

        int [] A = new int[14];
        for(int i = 0; i < A.length; i++ ){
            A[i]=0;
        }

        int [] B = new int[12];
        for(int i = 0; i < B.length; i++ ){
            B[i]=0;
        }

        int [] C = new int[12];
        for(int i = 0; i < C.length; i++ ){
            C[i]=0;
        }

        int [] D = new int[14];
        for(int i = 0; i < D.length; i++ ){
            D[i]=0;
        }

        int [][] seats = {A,B,C,D};
        this.seats = seats;           /* Seat Attributes has assigned */

    }


    public void buy_seat(){   // (1) Buy Seat method
        Ticket t1 = new Ticket();
        char seatLetter;
        while(true){
            // Taking Seat Row input
            System.out.print("Enter Seat row :");
            Scanner letter = new Scanner(System.in);
            seatLetter = letter.next().charAt(0);
            if(seatLetter=='A' ||seatLetter=='B' ||seatLetter=='C' ||seatLetter=='D'){
                break;
            }
            System.out.println("Not valid seat Row!");
        }


        t1.setRow(seatLetter);

        int seatNumber;
        while (true){

            // Taking Seat number input
            System.out.print("Enter Seat Number :");
            Scanner num = new Scanner(System.in);
            seatNumber = num.nextInt() - 1;

            if(seatLetter == 'A' || seatLetter == 'D'){
                if (seatNumber < 14){
                    break;
                }
            }else{
                if(seatNumber < 12){
                    break;
                }
            }
            System.out.println("Invalid seat number");
        }

        t1.setSeat(seatNumber+1);

        double price ;
        if(seatNumber < 5 ){
            price = 200;
        }else if(seatNumber < 9){
            price = 150;
        }else {
            price = 180;
        }

        t1.setPrice(price);

        // Taking User information for tickets
        System.out.print("Enter your name :");
        Scanner name1 = new Scanner(System.in);
        String name = name1.nextLine();

        System.out.print("Enter your surname :");
        Scanner name2 = new Scanner(System.in);
        String surname = name2.nextLine();


        System.out.print("Enter your E-Mail :");
        Scanner mail = new Scanner(System.in);
        String email = mail.nextLine();


        Person p1 = new Person(name, surname, email);
        t1.setPerson(p1);


        int rowNum = 0;

        if(seatLetter == 'A' ){
            rowNum = 0;
        }else if(seatLetter == 'B'){
            rowNum = 1;
        }else if(seatLetter == 'C') {
            rowNum = 2;
        }else if(seatLetter == 'D') {
            rowNum = 3;
        }

        if (this.seats[rowNum][seatNumber] == 0){
            this.seats[rowNum][seatNumber]=1;
            System.out.println("Reserved Successfully!");
            t1.save();
            this.tickets[id] = t1;
            id++;
        }else{
            System.out.println("Seat has been Booked. Reservation Failed!");
        }

    }

    public void cancel_seat(){   // (2) Cancel Seat method

        char seatLetter;
        while(true){
            // Taking Seat Row input
            System.out.print("Enter Seat row :");
            Scanner letter = new Scanner(System.in);
            seatLetter = letter.next().charAt(0);
            if(seatLetter=='A' ||seatLetter=='B' ||seatLetter=='C' ||seatLetter=='D'){
                break;
            }
            System.out.println("Not valid seat Row!");
        }

        int seatNumber;
        while (true){
            // Taking Seat number input
            System.out.print("Enter Seat Number :");
            Scanner num = new Scanner(System.in);
            seatNumber = num.nextInt() - 1;

            if(seatLetter == 'A' || seatLetter == 'D'){
                if (seatNumber < 14){
                    break;
                }
            }else{
                if(seatNumber < 12){
                    break;
                }
            }
            System.out.println("Invalid seat number");
        }

        int rowNum = 0;

        if(seatLetter == 'A' ){
            rowNum = 0;
        }else if(seatLetter == 'B'){
            rowNum = 1;
        }else if(seatLetter == 'C') {
            rowNum = 2;
        }else if(seatLetter == 'D') {
            rowNum = 3;
        }

        if (this.seats[rowNum][seatNumber] == 1){
            for(int i=0; i<this.id; i++){
                if( this.tickets[i].getRow()==seatLetter & this.tickets[i].getSeat()==seatNumber+1 ){
                    for(int j = i; j<this.id; j++){
                        this.tickets[j] = this.tickets[j+1];
                    }
                    break;
                }
            }
            this.id--;
            this.seats[rowNum][seatNumber] = 0;
            System.out.println("Seat cancellation Successful!");
        }else{
            System.out.println("Seat cancellation Failed! Seat hasn't been bought.");
        }
    }
    public void find_first_available(){   // (3) Find first available seat method
        int row = 0;
        char [] rowcl = {'A','B','C','D'};
        for(int j = 0; j<4 ;j++){
            for(int column =0; column < this.seats[row].length; column++ ){
                if(this.seats[row][column]==0){
                    System.out.print(rowcl[row]);
                    System.out.println(column+1);
                    return ;
                }
            }
            row++;
        }
        System.out.println("No Seats are available.");
    }

    public void show_seating_plan() {   // (4) Show seating plan method
        int row = 0;
        for(int j = 0; j<4 ;j++){
            for(int column =0; column < this.seats[row].length; column++ ){
                if(this.seats[row][column]==0){
                    System.out.print("O");
                }else{
                    System.out.print("X");
                }
            }
            System.out.println();
            row++;
        }
    }

    public void ticket_info_n_total_sales() {   // (5) Print tickets information and total sales method
        double sum = 0;
        for(int i = 0; i < this.tickets.length; i++){
            if(this.tickets[i] == null){
                break;
            }
            this.tickets[i].printInfo();
            sum += this.tickets[i].getPrice();
        }
        System.out.println("Total Amount: £"+ sum);
    }

    private void search_tickets() {   // (6) Print tickets information and total sales method
        char seatLetter;
        while(true){
            // Taking Seat Row input
            System.out.print("Enter Seat row :");
            Scanner letter = new Scanner(System.in);
            seatLetter = letter.next().charAt(0);
            if(seatLetter=='A' ||seatLetter=='B' ||seatLetter=='C' ||seatLetter=='D'){
                break;
            }
            System.out.println("Not valid seat Row!");
        }

        int seatNumber;
        while (true){
            // Taking Seat number input
            System.out.print("Enter Seat Number :");
            Scanner num = new Scanner(System.in);
            seatNumber = num.nextInt() - 1;

            if(seatLetter == 'A' || seatLetter == 'D'){
                if (seatNumber < 14){
                    break;
                }
            }else{
                if(seatNumber < 12){
                    break;
                }
            }
            System.out.println("Invalid seat number");
        }

        int rowNum = 0;
        if(seatLetter == 'A' ){
            rowNum = 0;
        }else if(seatLetter == 'B'){
            rowNum = 1;
        }else if(seatLetter == 'C') {
            rowNum = 2;
        }else if(seatLetter == 'D') {
            rowNum = 3;
        }
        if (this.seats[rowNum][seatNumber] == 0){
            System.out.println("This seat is available.");
        }else{
            for(int i=0; i < this.id; i++){
                if(this.tickets[i] != null){
                    if(this.tickets[i].getRow()==seatLetter & this.tickets[i].getSeat()==seatNumber + 1 ){
                        this.tickets[i].printInfo();
                    }
                }
            }
        }
    }


    public static void main(String [] args){

        PlaneManagement plane1 = new PlaneManagement();
        System.out.println(plane1.seats[0][2]);
        System.out.println("Welcome to the Plane Management application");
        System.out.println();
        System.out.println("***************************************************");
        System.out.println("*                  MENU OPTIONS                   *");
        System.out.println("***************************************************");
        System.out.println("      1) Buy a seat                                ");
        System.out.println("      2) Cancel a seat                             ");
        System.out.println("      3) Find first available seat                 ");
        System.out.println("      4) Show seating plan                         ");
        System.out.println("      5) Print tickets information and total sales ");
        System.out.println("      6) Search ticket                             ");
        System.out.println("      0) Quit                                      ");
        System.out.println("***************************************************");

        while(true) {
            try {
                System.out.print("Please select an option :");

                Scanner input = new Scanner(System.in);
                int userInput = input.nextInt();

                // Calling appropriate method according to user input in the menu
                if (userInput == 1) {
                    plane1.buy_seat();
                } else if (userInput == 2) {
                    plane1.cancel_seat();
                } else if (userInput == 3) {
                    plane1.find_first_available();
                } else if (userInput == 4) {
                    plane1.show_seating_plan();
                } else if (userInput == 5) {
                    plane1.ticket_info_n_total_sales();
                } else if (userInput == 6) {
                    plane1.search_tickets();
                } else if (userInput == 0) {
                    break;
                } else {
                    System.out.println("Invalid Input !");
                }

            } catch (InputMismatchException e) {
                System.out.println("Input Should be a Number !");
            }
        }

    }/* This is where main method ends*/
} /* This is where class ends*/