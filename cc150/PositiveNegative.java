public class PositiveNegative {
	public static void main(String[] args) {
		int a = 0x00000001;
		int b = 0x7FFFFFFF;
		int c = a + b;

		System.out.println("a = " + a);
		System.out.println("b = " + b);
		System.out.println("a + b = " + c);
	}
}