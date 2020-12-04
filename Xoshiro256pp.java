import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.util.function.LongSupplier;

public final class Xoshiro256pp implements LongSupplier {
	public static void main(String[] args) {
		var x = new Xoshiro256pp();
		
		for (var i = 0; i < 100; i++) {
			System.out.println(x.getAsLong());
		}
	}

	private final long[] s;

	public Xoshiro256pp() {
		var s = new long[4];
		var b = ByteBuffer.wrap(SecureRandom.getSeed(Long.SIZE * s.length));
		for (int i = 0; i < s.length; i++) {
			s[i] = b.getLong(Long.SIZE * i); 
		}
		this.s = s;
	}
	
	@Override
	public long getAsLong() {
		long r = rotl(s[0] + s[3], 23) + s[0];
		long t = s[1] << 17;
		s[2] ^= s[0];
		s[3] ^= s[1];
		s[1] ^= s[2];
		s[0] ^= s[3];
		s[2] ^= t;
		s[3] = rotl(s[3], 45);
		return r;
	}
	
	private static long rotl(long x, int k) {
		return (x << k) | (x >>> (64 - k));
	}
}
