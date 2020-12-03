final class Pcg {
	public static void main(String[] args) {
		Pcg pcg = new Pcg();
		for (int i = 0; i < 100; i++) {
			System.out.println(pcg.next());
		}
	}
	
	private long state;
	private long inc;
	
	Pcg() {
		SecureRandom sr = new SecureRandom();
		init(sr.nextLong(), sr.nextLong());
	}
	
	Pcg(long initstate, long initseq) {
		init(initstate, initseq);
	}
	
	private void init(long initstate, long initseq) {
	    state = 0L;
	    inc = (initseq << 1L) | 1L;
	    next();
	    state += initstate;
	    next();
	}
	
	public int next() {
	    long oldstate = state;
	    state = oldstate * 6364136223846793005L + inc;
	    int xorshifted = (int)(((oldstate >>> 18L) ^ oldstate) >>> 27L);
	    int rot = (int)(oldstate >>> 59L);
	    return (xorshifted >>> rot) | (xorshifted << ((-rot) & 31));
	}
}
