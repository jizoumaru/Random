public class Xorshift {
	public static void main(String[] args) {
		for (var i = 0; i < 10; i++) {
			System.out.println(Integer.toUnsignedString(generate()));
		}
	}
	
	static int y = 0x92D68CA2;
	
	static int generate() {
		y ^= y << 13;
		y ^= y >>> 17;
		y ^= y << 5;
		return y;
	}
}
