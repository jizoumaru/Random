publipublic class Xoshiro256pp {
	public static void main(String[] args) {
		var x = new Xoshiro256pp(1234, 5678, 91012, 3456);
		
		for (var i = 0; i < 100; i++) {
			var n = x.nextLong(-3, 3);
			System.out.println(i + ":" + n);
		}
		
	}

	private static long[] JUMP = { 0x180ec6d33cfd0abaL, 0xd5a61266f0c9392cL, 0xa9582618e03fc9aaL, 0x39abdc4529b1661cL };
	private long[] s;

	public Xoshiro256pp(long a, long b, long c, long d) {
		s = new long[] {a, b, c, d};
		jump();
	}
	
	public long nextLong() {
		long result = rotl(s[0] + s[3], 23) + s[0];

		long t = s[1] << 17;

		s[2] ^= s[0];
		s[3] ^= s[1];
		s[1] ^= s[2];
		s[0] ^= s[3];

		s[2] ^= t;

		s[3] = rotl(s[3], 45);

		return result;
	}
	
	public long nextLong(long min, long max) {
		if (min < 0 && min + Long.MAX_VALUE < max) {
			var n = nextLong();
			
			while (n < min || max < n) {
				n = nextLong();
			}
			
			return n;
		} else {
			if (max - min == Long.MAX_VALUE) {
				return (nextLong() & Long.MAX_VALUE) + min;
			}
			
			var r = max - min + 1L;
			var l = (Long.MAX_VALUE - r + 1L) % r;
			var n = nextLong() & Long.MAX_VALUE;
			
			while (n < l) {
				n = nextLong() & Long.MAX_VALUE;
			}
			
			return n % r + min;
		}
	}
	
	public int nextInt() {
		return (int)nextLong();
	}
	
	public int nextInt(int min, int max) {
		return (int)nextLong(min, max);
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
				nextLong();	
			}
		}
		
		s[0] = s0;
		s[1] = s1;
		s[2] = s2;
		s[3] = s3;
	}
}c class Xoshiro256pp {
	public static void main(String[] args) {
		var x = new Xoshiro256pp(1234, 5678, 91012, 3456);
		
		for (var i = 0; i < 1000000000; i++) {
			var n = x.nextInt(1, 100);
			if (i % 100000000 == 0) {
				System.out.println(i + ":" + n);
			}
		}
	}

	private static long[] JUMP = { 0x180ec6d33cfd0abaL, 0xd5a61266f0c9392cL, 0xa9582618e03fc9aaL, 0x39abdc4529b1661cL };
	private long[] s;

	public Xoshiro256pp(long a, long b, long c, long d) {
		s = new long[] {a, b, c, d};
		jump();
	}
	
	public long nextLong() {
		long result = rotl(s[0] + s[3], 23) + s[0];

		long t = s[1] << 17;

		s[2] ^= s[0];
		s[3] ^= s[1];
		s[1] ^= s[2];
		s[0] ^= s[3];

		s[2] ^= t;

		s[3] = rotl(s[3], 45);

		return result;
	}
	
	public long nextLong(long min, long max) {
		long range = max - min + 1;
		if (range <= 0) {
			while (true) {
				long n = nextLong();
				if (min <= n && n <= max) {
					return n;
				}
			}
		} else {
			long lim = (Long.MAX_VALUE - range + 1) % range;
			while (true) {
				long n = nextLong() & Long.MAX_VALUE;
				if (lim <= n) {
					return n % range + min;
				}
			}
		}
	}
	
	public int nextInt() {
		return (int)nextLong();
	}
	
	public int nextInt(int min, int max) {
		return (int)nextLong(min, max);
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
				nextLong();	
			}
		}
		
		s[0] = s0;
		s[1] = s1;
		s[2] = s2;
		s[3] = s3;
	}
}
