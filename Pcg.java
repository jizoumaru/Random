final class Pcg implements IntSupplier {
	private final long i;
	private long s;

	Pcg() {
		SecureRandom sr = new SecureRandom();
		long i = sr.nextLong();
		long s = sr.nextLong();

		this.s = 0L;
		this.i = (i << 1L) | 1L;
		getAsInt();
		this.s += s;
		getAsInt();
	}

	@Override
	public int getAsInt() {
		long o = s;
		s = o * 6364136223846793005L + i;
		int x = (int) (((o >>> 18L) ^ o) >>> 27L);
		int r = (int) (o >>> 59L);
		return (x >>> r) | (x << ((-r) & 31));
	}
}
