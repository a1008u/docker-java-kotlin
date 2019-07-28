class Person {
    String name;
    public Person(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
}

public class SalesPerson extends Person {
    int empID;
    public SalesPerson(int empID, String name){
        super(name);
        this.empID = empID;
    }
    public int getEmpID(){
        return empID;
    }
}
