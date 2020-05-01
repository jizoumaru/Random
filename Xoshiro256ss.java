public class Xoshiro256ss {
	public static void main(String[] args) {
		var x = new Xoshiro256ss(1234, 5678, 91012, 3456);
		
		for (var i = 0; i < 100000000; i++) {
			var n = x.next();
			if (i % 1000000 == 0) {
				System.out.println(n);
			}
		}
	}

	private static long[] JUMP = { 0x180ec6d33cfd0abaL, 0xd5a61266f0c9392cL, 0xa9582618e03fc9aaL, 0x39abdc4529b1661cL };
	private long[] s;

	public Xoshiro256ss(long a, long b, long c, long d) {
		s = new long[] {a, b, c, d};
		jump();
	}
	
	public long next() {
		long result = rotl(s[1] * 5, 7) * 9;

		long t = s[1] << 17;

		s[2] ^= s[0];
		s[3] ^= s[1];
		s[1] ^= s[2];
		s[0] ^= s[3];

		s[2] ^= t;

		s[3] = rotl(s[3], 45);

		return result;
	}

	private long rotl(long x, int k) {
		return (x << k) | (x >>> (64 - k));
	}

	private void jump() {
		long s0 = 0;
		long s1 = 0;
		long s2 = 0;
		long s3 = 0;
		
		for(int i = 0; i < JUMP.length; i++) {
			for(int b = 0; b < 64; b++) {
				if ((JUMP[i] & (1L << b)) != 0) {
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
