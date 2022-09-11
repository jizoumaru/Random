import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.util.random.RandomGenerator;

public final class Xoshiro256pp implements RandomGenerator
{
	public static void main(String[] args)
	{
		var x = new Xoshiro256pp();
		x.ints().limit(100).forEach(System.out::println);
	}

	private final long[] s;

	public Xoshiro256pp()
	{
		var s = new long[4];
		var b = ByteBuffer.wrap(SecureRandom.getSeed(Long.BYTES * s.length));
		for (int i = 0; i < s.length; i++)
		{
			s[i] = b.getLong();
		}
		this.s = s;
	}

	private static long rotl(long x, int k)
	{
		return (x << k) | (x >>> (64 - k));
	}

	@Override
	public long nextLong()
	{
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
}
