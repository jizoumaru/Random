public class Xoshiro128ss {
	public static void main(String[] args) {
		var x = new Xoshiro128ss(1234, 5678, 91012, 3456);
		for (var i = 0; i < 100000000; i++) {
			var n = x.next();
			if (i % 1000000 == 0) {
				System.out.println(n);
			}
		}
	}

	private static final int[] JUMP = { 0x8764000b, 0xf542d2d3, 0x6fa035c3, 0x77f2db5b };
	private int[] s;

	public Xoshiro128ss(int a, int b, int c, int d) {
		this.s = new int[] { a, b, c, d };
		jump();
	}

	public int next() {
		int result = rotl(s[1] * 5, 7) * 9;
		int t = s[1] << 9;

		s[2] ^= s[0];
		s[3] ^= s[1];
		s[1] ^= s[2];
		s[0] ^= s[3];
		s[2] ^= t;
		s[3] = rotl(s[3], 11);

		return result;
	}

	private int rotl(int x, int k) {
		return (x << k) | (x >>> (32 - k));
	}

	public void jump() {
		int s0 = 0;
		int s1 = 0;
		int s2 = 0;
		int s3 = 0;

		for (int i = 0; i < JUMP.length; i++) {
			for (int b = 0; b < 32; b++) {
				if ((JUMP[i] & (1 << b)) != 0) {
					s0 ^= s[0];
					s1 ^= s[1];
					s2 ^= s[2];
					s3 ^= s[3];
				}
				next();
			}
		}

		s[0] = s0;
		s[1] = s1;
		s[2] = s2;
		s[3] = s3;
	}
}
