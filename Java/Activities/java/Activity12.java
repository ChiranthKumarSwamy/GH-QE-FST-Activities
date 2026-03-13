// Functional Interface
interface FuncInterfaceExample {
    String display(String str);
}

public class Activity12 {
    public static void main(String[] args) {

        // Lambda expression to implement the interface method
        FuncInterfaceExample obj = (String str) -> {
            return str;
        };
        
        // Print statement calling the display method
        System.out.println(obj.display("Hello"));
    }
}