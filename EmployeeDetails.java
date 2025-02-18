import java.util.*;

class Emp{
    int Id;
    String name;
    int salary;
    public Emp(int _id, String _name, int _salary){
        Id = _id;
        name = _name;
        salary = _salary;
    }
}

public class EmployeeDetails {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Emp> employees = new ArrayList<Emp>();
        boolean cont = true;
        while(cont) {
            System.out.println("\nSelect your choice: ");
            System.out.println("1. Add a new Employee");
            System.out.println("2. Remove an Employee");
            System.out.println("3. Search an Employee");
            System.out.println("4. Show list of Employees");
            System.out.println("5. Exit");
            int choice = sc.nextInt();
            switch (choice) {
                case 1: // adding an employee
                    System.out.print("Enter Name, id and salary of employee: ");
                    String name = sc.next();
                    int id = sc.nextInt();
                    int salary = sc.nextInt();
                    employees.add(new Emp(id, name, salary));
                    System.out.printf("Employee with id %d is added!\n", id);
                    break;
                case 2: // removing an employee
                    System.out.print("Enter the id of employee you want to remove: ");
                    int id1 = sc.nextInt();
                    Iterator i1 = employees.iterator();
                    Emp e1 = null;
                    boolean found = false;
                    while (i1.hasNext()) {
                        e1 = (Emp) i1.next();
                        if (e1.Id == id1) {
                            found = true;
                            break;
                        }
                    }
                    if(found) {
                        employees.remove(e1);
                        System.out.printf("Employee with id %d is removed!\n", id1);
                    }
                    else{
                        System.out.println("Employee not found!!");
                    }
                    break;
                case 3: // searching employee
                    System.out.print("Enter the id: ");
                    int id2 = sc.nextInt();
                    boolean flag = false;
                    Iterator i2 = employees.iterator();
                    while (i2.hasNext()) {
                        Emp e = (Emp) i2.next();
                        if (e.Id == id2) {
                            System.out.println("Id: " + e.Id + "\tName: " + e.name + "\tSalary: " + e.salary);
                            flag = true;
                            break;
                        }
                    }
                    if(!flag) System.out.println("Employee not found");
                    break;
                case 4: // employee details
                    Iterator i = employees.iterator();
                    while (i.hasNext()) {
                        Emp e = (Emp) i.next();
                        System.out.println("Id: " + e.Id + "\tName: " + e.name + "\tSalary: " + e.salary);
                    }
                    break;
                default: // exit
                    cont = false;
            }
        }
    }
}
