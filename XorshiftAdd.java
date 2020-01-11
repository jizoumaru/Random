
public class XorshiftAdd {
	public static void main(String[] args) {
		var xa = new XorshiftAdd(1234);
		
		for (var i = 0; i < 100; i++) {
			System.out.println(Integer.toUnsignedString(xa.generate()));
		}
	}
	
	private final int[] state;

	public XorshiftAdd(int seed) {
		state = new int[] { seed, 0, 0, 0 };

		for (int i = 1; i < 8; i++) {
			state[i & 3] ^= i + 1812433253
					* (state[(i - 1) & 3]
							^ (state[(i - 1) & 3] >>> 30));
		}

		for (int i = 0; i < 8; i++) {
			xsadd_next_state();
		}
	}

	private void xsadd_next_state() {
		var t = state[0];
		t ^= t << 15;
		t ^= t >>> 18;
		t ^= state[3] << 11;
		state[0] = state[1];
		state[1] = state[2];
		state[2] = state[3];
		state[3] = t;
	}

	public int generate() {
		xsadd_next_state();
		return state[3] + state[2];
	}
}
