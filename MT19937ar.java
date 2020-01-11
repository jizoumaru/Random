
public class MT19937ar {
	public static void main(String[] args) {
		int[] keys = { 0x123, 0x234, 0x345, 0x456 };
		init_by_array(keys);
		System.out.println("1000 outputs of genrand_int32()");
		for (int i = 0; i < 1000; i++) {
			System.out.println(Integer.toUnsignedLong(genrand_int32()));
		}
	}

	static final int N = 624;
	static final int M = 397;
	static final int MATRIX_A = 0x9908b0df;
	static final int UPPER_MASK = 0x80000000;
	static final int LOWER_MASK = 0x7fffffff;
	static final int[] mag01 = new int[] { 0x0, MATRIX_A };
	static final int[] mt = new int[N];
	static int mti;

	static void init_genrand(int s) {
		mt[0] = s;
		
		for (var i = 1; i < N; i++) {
			mt[i] = 1812433253 * (mt[i - 1] ^ (mt[i - 1] >>> 30)) + i;
		}
		
		mti = N;
	}

	static void init_by_array(int[] keys) {
		init_genrand(19650218);
		
		var i = 1;
		var j = 0;
		
		for (var k = (N > keys.length ? N : keys.length); k > 0; k--) {
			mt[i] = (mt[i] ^ ((mt[i - 1] ^ (mt[i - 1] >>> 30)) * 1664525)) + keys[j] + j;
			i++;
			j++;
			
			if (i >= N) {
				mt[0] = mt[N - 1];
				i = 1;
			}
		
			if (j >= keys.length) {
				j = 0;
			}
		}
		
		for (var k = N - 1; k > 0; k--) {
			mt[i] = (mt[i] ^ ((mt[i - 1] ^ (mt[i - 1] >>> 30)) * 1566083941)) - i;
			i++;
			
			if (i >= N) {
				mt[0] = mt[N - 1];
				i = 1;
			}
		}

		mt[0] = 0x80000000;
	}


	static int genrand_int32() {
		if (mti >= N) {
			for (int i = 0, j = M; i < N - M; i++, j++) {
				var y = (mt[i] & UPPER_MASK) | (mt[i + 1] & LOWER_MASK);
				mt[i] = mt[j] ^ (y >>> 1) ^ mag01[y & 0x1];
			}
			
			for (int i = N - M, j = 0; i < N - 1; i++, j++) {
				var y = (mt[i] & UPPER_MASK) | (mt[i + 1] & LOWER_MASK);
				mt[i] = mt[j] ^ (y >>> 1) ^ mag01[y & 0x1];
			}
			
			var y = (mt[N - 1] & UPPER_MASK) | (mt[0] & LOWER_MASK);
			mt[N - 1] = mt[M - 1] ^ (y >>> 1) ^ mag01[y & 0x1];
			mti = 0;
		}

		var y = mt[mti++];
		y ^= (y >>> 11);
		y ^= (y << 7) & 0x9d2c5680;
		y ^= (y << 15) & 0xefc60000;
		y ^= (y >>> 18);

		return y;
	}

}
