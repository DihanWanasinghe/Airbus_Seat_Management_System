import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
public class Ticket {

    //attributes
    private String row;
    private int seat;
    private double price;

    private Person person;

    //constructor
    public Ticket(String row,int seat,double price,Person person){

        this.row=row;
        this.seat=seat;
        this.price=price;
        this.person=person;
    }



    public String getRow(){

        return row;

    }
    public  int getSeat(){

        return seat;
    }
    public double getPrice(){

        return price;
    }

    public Person getPerson(){

        return person;
    }




    public void setRow(String row){

        this.row=row;
    }
    public void setSeat(int seat){

        this.seat=seat;
    }
    public void setPrice(double price){

        this.price=price;
    }

    public void setPerson(Person person){

        this.person=person;
    }



    public void printInfo(){

        System.out.println("Row of the Seat:"+getRow());
        System.out.println("Seat Number:"+getSeat());
        System.out.println("Price of the Seat:"+getPrice());
        getPerson().printInfo();


    }
    public void save(){

         try{

          FileWriter file=new FileWriter(this.row+this.seat+".txt");
          file.write("Row is:"+this.row+"\n");
          file.write("Seat is:"+this.seat+"\n");
          file.write("Price is:"+this.price+"\n");
          file.write("Name of the purchaser:"+this.person.getName()+"\n");
          file.write("Surname of the purchaser:"+this.person.getSurname()+"\n");
          file.write("Email of the purchaser:"+this.person.getEmail()+"\n");
          file.close();



         }catch (IOException e){

               System.out.println("Error Occurred While Writing the File");
               e.printStackTrace();



         }





    }
}
