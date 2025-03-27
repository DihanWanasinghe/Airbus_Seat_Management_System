public class Person {
    //attributes
    private String name;
    private String surname;
    private String email;

    //constructor
    public Person(String name,String surname,String email){


        this.name=name;
        this.surname=surname;
        this.email=email;

    }

    public String getName(){

        return name;

    }
    public String getSurname(){

        return surname;
    }
    public String getEmail(){

        return email;
    }


    public void setName(String name){

        this.name=name;
    }
    public void setSurname(String surname){

        this.surname=surname;
    }
    public void setEmail(String email){

        this.email=email;
    }


    public void printInfo(){

        System.out.println("Name of the Customer:"+getName());
        System.out.println("Surname of the Customer:"+getSurname());
        System.out.println("Email of the Customer:"+getEmail());
    }
}
