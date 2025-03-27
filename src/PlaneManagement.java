import java.util.InputMismatchException;
import java.util.*;
public class PlaneManagement {
    static Scanner input = new Scanner(System.in);
    //make 4 1D Arrays for each row ,size based on the seat count
    static int row_A[] = new int[14];
     static int row_B[] = new int[12];
    static int row_C[] = new int[12];
    static int row_D[] = new int[14];
    //Variable to track the current index in ticket_array mentioned below
    static int seat_counter=0;

    public static void main(String[] args) {
        boolean controller = true;
        int selection=0;
         //An Array to store every sold ticket information,Size is based on Total seats
         Ticket [] ticket_array=new Ticket [52];
        while (controller) {
            System.out.println("Welcome to the Plane Management Application");
            System.out.println("*".repeat(48));
            System.out.println("*                 MENU OPTIONS                  *");
            System.out.println("*".repeat(48));
            System.out.println("1) Buy a seat");
            System.out.println("2) Cancel a seat");
            System.out.println("3) Find first available seat");
            System.out.println("4) Show seating plan");
            System.out.println("5) Print tickets information and total sales");
            System.out.println("6) Search ticket");
            System.out.println("0) Quit");
            System.out.println("*".repeat(48));
            System.out.println("Please select an option:");
            System.out.println("Use an appropriate number from the Menu");

            //Validate the selection from the Menu
            try {
                selection = input.nextInt();

                if (selection > 6 || selection < 0) {

                    System.out.println("The Number You Entered is Out of Range");
                    continue;
                }
            } catch (InputMismatchException e) {
                System.out.println("Please Enter a Valid Input");
                input.next();
                continue;
            }

            switch (selection) {

                case 0:
                    System.out.println("Quiting the System");
                    controller=false;
                    break;
                case 1:
                    buy_seat(ticket_array);
                    break;
                case 2:
                    cancel_seat(ticket_array);
                    break;
                case 3:
                    find_first_available();
                    break;
                case 4:
                    show_seating_plan();
                    break;

                case 5:
                    print_tickets_info(ticket_array);
                    break;

                case 6:
                    search_ticket(ticket_array);
            }
        }
    }
     static void buy_seat(Ticket [] ticket_array){
      boolean input_validation=true;
      String row=null;
      int seatNumber=0;
      double price=0;
      boolean buy_another_validation=false;

      while (true) {
          //Check whether there are seats that haven't been booked
          if(seat_counter== ticket_array.length) {
              System.out.println("Sorry the  plane is fully booked");
              return;
          }
          //validate the inputs for the Row and the Seat Number
          while (input_validation) {

              System.out.println("Enter the  row letter for the seat you  want ");
              row = input.next();
              row = row.toUpperCase();
              if (!(row.equals("A") || row.equals("B") || row.equals("C") || row.equals("D"))) {

                  System.out.println("Please enter a Valid Row Letter");
                  continue;
              }
              System.out.println("Enter the seat number");

                  if (input.hasNextInt()) {
                      seatNumber = input.nextInt();

                      if ((seatNumber < 0 || seatNumber > 14) || ((row.equals("B") || row.equals("C")) && seatNumber > 12)) {

                          System.out.println("The Seat Number You Entered Is Out of Range");
                          continue;
                      }else {
                          input_validation=false;
                      }
                  } else {

                      System.out.println("Please enter a Corresponding Number");
                      input.next();
                      continue;
                  }
              boolean checkSpace_inRow = false;

              if (row.equals("A")) {

                  //check if there are seats that haven't been booked in the Row A

                  checkSpace_inRow=Check_Space_in_a_Row(row_A,checkSpace_inRow);
                      if (checkSpace_inRow == false) {

                          System.out.println("Sorry Every seat is booked in This Row");
                          input_validation=true;
                          continue;
                      } else if (row_A[seatNumber - 1] == 0) {
                          //Mark a seat as '1' based on the seat number, when bought -in the array row_A

                          Marking_theBought_Seat(row_A,seatNumber,row);
                      } else {
                          System.out.println("This seat is booked.Please chose another");
                          input_validation = true;
                          continue;
                      }

              } else if (row.equals("B")) {

                  //check if there are seats that haven't been booked in the Row B

                  checkSpace_inRow=Check_Space_in_a_Row(row_B,checkSpace_inRow);

                  if (checkSpace_inRow == false) {

                          System.out.println("Sorry Every seat is booked in This Row");
                          input_validation = true;
                          continue;
                  } else if (row_B[seatNumber - 1] == 0) {
                      //Mark a seat as '1' based on the seat number, when bought -in the array row_B

                      Marking_theBought_Seat(row_B,seatNumber,row);

                  } else {
                          System.out.println("This seat is booked.Please chose another");
                          input_validation = true;
                          continue;
                  }
              } else if (row.equals("C")) {

                  //check if there are seats that haven't been booked in the Row C

                  checkSpace_inRow=Check_Space_in_a_Row(row_C,checkSpace_inRow);

                  if (checkSpace_inRow == false) {

                          System.out.println("Sorry Every seat is booked in This Row");
                          input_validation = true;

                          continue;
                  }else if (row_C[seatNumber - 1] == 0) {
                      //Mark a seat as '1' based on the seat number, when bought -in the array row_C
                      Marking_theBought_Seat(row_C,seatNumber,row);

                  } else {
                          System.out.println("This seat is booked.Please chose another");
                          input_validation = true;
                          continue;
                  }

              } else {
                  //check if there are seats that haven't been booked in the Row D
                  checkSpace_inRow=Check_Space_in_a_Row(row_D,checkSpace_inRow);

                  if (checkSpace_inRow == false) {

                          System.out.println("Sorry Every seat is booked in This Row");
                          input_validation = true;
                          continue;

                  }else if (row_D[seatNumber - 1] == 0) {
                      //Mark a seat as '1' based on the seat number, when bought -in the array row_D

                      Marking_theBought_Seat(row_D,seatNumber,row);
                  } else {
                          System.out.println("This seat is booked.please chose another");
                          input_validation = true;
                          continue;
                      }
              }
          }
          //Input data about the person who booked the seat
          input.nextLine();
          System.out.println("Enter the name of the Person");
          String name = input.nextLine();


          System.out.println("Enter the surname ");
          String surname = input.nextLine();

          System.out.println("Enter the email ");
          String email = input.next();

          //define the prices for Seats
          if (seatNumber > 0 && seatNumber < 6) {
              price = 200;
          } else if (seatNumber >= 6 && seatNumber < 10) {
              price = 150;
          } else {
              price = 180;
          }

          if (seat_counter < ticket_array.length) {
              //Making an object of the Person class based on the information previously entered regarding the person who booked the seat
              Person person = new Person(name, surname, email);

              //Making an object of the ticket class based on the booked seat's row,seatNumber,price and the customer's personal information
              Ticket ticket = new Ticket(row, seatNumber, price, person);

              //Export ticket Information to the ticket array
              ticket_array[seat_counter] = ticket;

              //export the ticket information as a txt file
              ticket.save();
              seat_counter++;
          }

          //check whether user wants to buy another ticket
          while(true) {
              System.out.println("Do you want to to buy another seat?");
              System.out.println("Type 'y' for Yes and 'n' for No");
              String buy_another = input.next();

              if( !((buy_another.toLowerCase().equals("y") )|| (buy_another.toLowerCase().equals("n")))){

                  System.out.println("Please enter a valid Input");
                  continue;
              } else if (buy_another.toLowerCase().equals("y")) {
                  buy_another_validation=true;
                  break;
              }else{
                   buy_another_validation=false;
                  return;
              }
          }
          if(buy_another_validation==true){
           input_validation=true;
         }
      }

    }
      static void cancel_seat(Ticket [] ticket_array) {

          boolean input_validation = true;
          String row = null;
          int seatNumber = 0;
          boolean cancel_another_validation=false;

          while (true) {
             //check whether at least a single seat is booked
              if(seat_counter== 0) {
                  System.out.println("Sorry ,Not a single seat is Booked");
                  return;
              }
              // input validation for row and Seat number
              while (input_validation) {

                  System.out.println("Enter the  row letter for the seat  ");
                  row = input.next();
                  row = row.toUpperCase();
                  if (!(row.equals("A") || row.equals("B") || row.equals("C") || row.equals("D"))) {
                      System.out.println("Please enter a Valid Row Letter");
                      continue;
                  }

                      System.out.println("Enter the seat number");
                      if (input.hasNextInt()) {
                          seatNumber = input.nextInt();
                          if ((seatNumber < 0 || seatNumber > 14) || ((row.equals("B") || row.equals("C")) && seatNumber > 12)) {

                              System.out.println("The Seat Number You Entered Is Out of Range");

                              continue;
                          } else {

                              input_validation=false;
                          }

                      } else {

                          System.out.println("Please enter a Corresponding Number");
                          input.next();
                          continue;
                      }

                  boolean checkBooked_inRow = false;
                  if (row.equals("A")) {
                      //check if there are seats that have been booked in the Row A
                      checkBooked_inRow=Check_Booked_in_a_Row(row_A,checkBooked_inRow);
                      if (checkBooked_inRow == false) {

                          System.out.println("Sorry Not a Single seat  booked in This Row");
                          input_validation = true;
                          continue;
                      } else if (row_A[seatNumber - 1] == 0) {

                          System.out.println("Sorry this seat is already available,Can't Execute Cancel Seat");
                          input_validation = true;
                          continue;
                      } else {
                          //Mark a seat as '0' based on the seat number, when bought -in the array row_A
                          Marking_theCancelled_Seat(row_A,seatNumber,row);
                      }

                  } else if (row.equals("B")) {
                      //check if there are seats that have been booked in the Row B
                      checkBooked_inRow=Check_Booked_in_a_Row(row_B,checkBooked_inRow);
                      if (checkBooked_inRow == false) {

                          System.out.println("Sorry Not a Single  seat  booked in This Row");
                          input_validation = true;
                          continue;
                      } else if (row_B[seatNumber - 1] == 0) {

                          System.out.println("Sorry this seat is already available,Can't Execute Cancel Seat");
                          input_validation = true;
                          continue;
                      } else {
                          //Mark a seat as '0' based on the seat number, when bought -in the array row_B
                          Marking_theCancelled_Seat(row_B,seatNumber,row);
                      }

                  } else if (row.equals("C")) {
                      //check if there are seats that have been booked in the Row C

                      checkBooked_inRow=Check_Booked_in_a_Row(row_C,checkBooked_inRow);
                      if (checkBooked_inRow == false) {

                          System.out.println("Sorry not a single seat booked in This Row");
                          input_validation = true;
                          continue;
                      } else if (row_C[seatNumber - 1] == 0) {

                          System.out.println("Sorry this seat is already available,Can't Execute Cancel Seat");
                          input_validation = true;
                          continue;
                      } else {
                          //Mark a seat as '0' based on the seat number, when bought -in the array row_C
                          Marking_theCancelled_Seat(row_C,seatNumber,row);
                      }

                  } else {
                      //check if there are seats that have been booked in the Row D

                      checkBooked_inRow=Check_Booked_in_a_Row(row_D,checkBooked_inRow);
                      if (checkBooked_inRow == false) {

                          System.out.println("Sorry Not a Single seat  purchased in This Row");
                          input_validation = true;

                          continue;
                      }
                      if (row_D[seatNumber - 1] == 0) {

                          System.out.println("Sorry this seat is already available,Can't Execute Cancel Seat");
                          input_validation = true;
                          continue;
                      } else {
                          //Mark a seat as '0' based on the seat number, when bought -in the array row_D
                          Marking_theCancelled_Seat(row_D,seatNumber,row);
                      }
                  }
              }
               //Remove the information about the ticket that was cancelled from the ticket_array
              for(int i=0;i<=seat_counter;i++){

                     if(ticket_array[i]!=null) {

                         if ((ticket_array[i].getRow().equals(row)) && (ticket_array[i].getSeat() == seatNumber)) {

                             for (int j = i; j <= seat_counter; j++) {

                                 if (j == ticket_array.length - 1) {

                                     ticket_array[j] = null;
                                 }
                                 else if(ticket_array[j+1]==null){

                                     ticket_array[j] = null;
                                 }else{
                                     ticket_array[j]=ticket_array[j+1];
                                 }
                             }
                             seat_counter--;
                             break;
                         }
                     }
                 }
              //Check whether the user wants to cancel another seat
              while(true) {
                  System.out.println("Do you want to to cancel another seat?");
                  System.out.println("Type 'y' for Yes and 'n' for No");
                  String buy_another = input.next();
                  if( !((buy_another.toLowerCase().equals("y") )|| (buy_another.toLowerCase().equals("n")))){

                      System.out.println("Please enter a valid Input");
                      continue;
                  } else if (buy_another.toLowerCase().equals("y")) {
                      cancel_another_validation=true;
                      break;
                  }else{
                      cancel_another_validation=false;
                      return;
                  }
              }
              if(cancel_another_validation==true){
                  input_validation=true;
                  continue;
              }
          }
      }
      //FInd the first seat available to book
      static void find_first_available(){

        for(int i=0;i<row_A.length;i++){

            if(row_A[i]==0){
                System.out.println("The First available Seat is on :Row A Seat "+(i+1));
                return;
            }
        }
          for(int i=0;i<row_B.length;i++){

              if(row_B[i]==0){
                  System.out.println("The First available Seat is on :Row B Seat "+(i+1));
                  return;
              }
          }
          for(int i=0;i<row_C.length;i++){

              if(row_C[i]==0){
                  System.out.println("The First available Seat is on :Row C Seat "+(i+1));
                  return;
              }
          }
          for(int i=0;i<row_D.length;i++){

              if(row_D[i]==0){
                  System.out.println("The First available Seat is on :Row D Seat "+(i+1));
                  return;
              }
          }
          System.out.println("Sorry,Every seat is Booked");
      }

      //Show the booked and available seats with the characters- '0' for available and 'X'-for booked
      static void show_seating_plan(){
       printRows(row_A);
       System.out.println();
       printRows(row_B);
       System.out.println();
       printRows(row_C);
       System.out.println();
       printRows(row_D);
       System.out.println();
      }

      //Show the information about the tickets that were sold
      static void print_tickets_info(Ticket [] ticket_array){
       double total_price=0;
       boolean check_sold=true;
        for(int i=0;i<=seat_counter;i++){

           if(ticket_array[i]!=null) {
               total_price = total_price + ticket_array[i].getPrice();

               ticket_array[i].printInfo();
               System.out.println();
               check_sold=false;
           }
        }
        if(check_sold==true){
            System.out.println("Sorry not a single Ticket is sold- No information regarding tickets available");
        }
          System.out.println("Total Price of the tickets sold: "+total_price);
      }
    //Search information regarding a certain ticket
    static void search_ticket(Ticket [] ticket_array){
       boolean input_validation=true;
       String row=null;
       int seatNumber=0;

       //Input Validation for Row and Seat Number
        while (input_validation) {
            System.out.println("Enter the  row letter for the seat you  want ");
            row = input.next();
            row = row.toUpperCase();
            if (!(row.equals("A") || row.equals("B") || row.equals("C") || row.equals("D"))) {

                System.out.println("Please enter a Valid Row Letter");
                continue;
            }
            System.out.println("Enter the seat number");

            if (input.hasNextInt()) {
                seatNumber = input.nextInt();
                if ((seatNumber < 0 || seatNumber > 14) || ((row.equals("B") || row.equals("C")) && seatNumber > 12)) {

                    System.out.println("The Seat Number You Entered Is Out of Range");
                    continue;
                } else {

                    input_validation = false;
                }
            } else {

                System.out.println("Please enter a Corresponding Number");
                input.next();
                continue;
            }
        }
        //Display the Information regarding the ticket based on the Ticket and Person class
        for(int i=0;i<=seat_counter;i++){

            if(ticket_array[i]!=null) {
                if (ticket_array[i].getRow().equals(row) && ticket_array[i].getSeat() == seatNumber) {
                    System.out.println();
                    ticket_array[i].printInfo();
                    System.out.println();
                    return;
                } else if (i == seat_counter) {
                    break;
                }
            }
        }
        System.out.println("This seat is available");
    }
    //Check whether there is a seat that isn't booked in a certain row
    public  static boolean Check_Space_in_a_Row(int [] row,boolean checkSpace_inRow){
        for (int i = 0; i < row.length; i++) {
            if (row[i] == 0) {
                checkSpace_inRow = true;
                return checkSpace_inRow;
            }
        }
        return checkSpace_inRow;
    }

    //Mark a seat as '1' when bought in the arrays-row_A,row_B,row_C,row_D
    public  static  void Marking_theBought_Seat(int [] row_array,int seatNumber,String row){

        row_array[seatNumber - 1] = 1;
        System.out.println("Your seat is on row:" + row.toUpperCase() + " and your seat number is: " + seatNumber);
    }
    //Check whether there is a seat that is booked in a certain row
    public  static boolean Check_Booked_in_a_Row(int [] row,boolean checkBooked_inRow){
        for (int i = 0; i < row.length; i++) {
            if (row[i] == 1) {
                checkBooked_inRow = true;
                return checkBooked_inRow;
            }
        }
        return checkBooked_inRow;
    }
    //Mark a seat as '0' when bought in the arrays-row_A,row_B,row_C,row_D
    public  static  void Marking_theCancelled_Seat(int [] row_array,int seatNumber,String row) {
        row_array[seatNumber - 1] = 0;
        System.out.println("The  seat " + seatNumber + " of row " + row + " is available now ");
    }
    //Print Characters based on the availability of the seats
    public static void printRows(int [] row_array){
        for(int i=0;i<row_array.length;i++){
            if(row_array[i]==0){
                System.out.print("O");
            }else{

                System.out.print("X");
            }
        }
    }
}

