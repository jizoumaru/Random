public final class TinyMT32 {
	public static void main(String[] args) {
		int[] param = { 1, 0x8f7011ee, 0xfc78ff1f, 0x3793fdff };
		int[] status = init(param);
		
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 5; j++) {
				System.out.println(Integer.toUnsignedString(next(status, param)));
			}
			System.out.println();
		}
	}

	static int[] init(int[] param) {
		int[] status = param.clone();
		
		for (int i = 1; i < 8; i++) {
			status[i & 3] ^= i + 1812433253
					* (status[(i - 1) & 3]
							^ (status[(i - 1) & 3] >>> 30));
		}

		for (int i = 0; i < 8; i++) {
			nextState(status, param);
		}
		
		return status;
	}

	static int next(int[] status, int[] param) {
		nextState(status, param);
		return output(status, param);
	}

	static void nextState(int[] status, int[] param) {
		int x = (status[0] & 0x7fffffff) ^ status[1] ^ status[2];
		int y = status[3];
		
		x ^= (x << 1);
		y ^= (y >>> 1) ^ x;
		
		status[0] = status[1];
		status[1] = status[2];
		status[2] = x ^ (y << 10);
		status[3] = y;
		
		status[1] ^= -(y & 1) & param[1];
		status[2] ^= -(y & 1) & param[2];
	}

	static int output(int[] status, int[] param) {
		int t0 = status[3];
		int t1 = status[0] + (status[2] >>> 8);
		t0 ^= t1;
		t0 ^= -(t1 & 1) & param[3];
		return t0;
	}

}
