final class Pcg implements IntSupplier {
	private final long i;
	private long s;

	Pcg() {
		var sr = new SecureRandom();
		this.i = (sr.nextLong() << 1L) | 1L;
		this.s = this.i + sr.nextLong();
	}

	@Override
	public int getAsInt() {
		s = s * 6364136223846793005L + i;
		int x = (int) (((s >>> 18L) ^ s) >>> 27L);
		int r = (int) (s >>> 59L);
		return (x >>> r) | (x << ((-r) & 31));
	}
}
