import java.util.*;
public class HiThere {
	
    public static void main(String[] args) {
	Scanner input = new Scanner(System.in);
	System.out.println("Enter your last name: ");
	String username = input.next();
	System.out.println("Hello, Mr/Ms " + username);
	System.out.println("Enter your first name: ");
	username = input.next();
	System.out.println("Hi there " + username);
	
	for (int i = 0; i < args.length; i++) {
	    System.out.println(args[i]);
	}
    }
}
