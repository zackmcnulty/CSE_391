public class Backwards{
	public static void main(String[] args) {
		for (String param : args) {
			for (int i = param.length() - 1; i >= 0; i--) {
				System.out.print(param.charAt(i));
			}
			System.out.println();
		}
	}
}

