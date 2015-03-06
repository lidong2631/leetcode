

public class divideTwoNumber {
	public static void main(String[] args) {
		int min = Integer.MIN_VALUE;
		int max = Integer.MAX_VALUE;
		System.out.println("min = " + min);
		System.out.println("max = " + max);

		int a = min + -1;
		int b = max + 1;

		System.out.println(min + " + " + "-1" + " = " + a);
		System.out.println(max + " + " + "1" + " = " + b);
	}
}