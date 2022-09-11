import java.nio.ByteBuffer;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.random.RandomGenerator;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.ChaCha20ParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class ChaCha20Random implements RandomGenerator
{
	private byte[] s = new byte[4096];
	private final ByteBuffer b = ByteBuffer.allocate(s.length);
	
	public ChaCha20Random() {
		try
		{
			SecureRandom.getInstanceStrong().nextBytes(s);

			for (var i = 0; i < 1000; i++)
			{
				refill();
			}
		}
		catch (NoSuchAlgorithmException e)
		{
			throw new RuntimeException(e);
		}
	}

	void refill()
	{
		var k = new SecretKeySpec(s, 0, 32, "ChaCha20");
		var n = Arrays.copyOfRange(s, 32, 32 + 12);
		s = encrypt(s, k, n, 0);
		b.clear();
		b.put(s);
		b.flip();
	}

	@Override
	public long nextLong()
	{
		if (b.remaining() < Long.BYTES)
		{
			refill();
		}
		return b.getLong();
	}

	static byte[] encrypt(byte[] src, SecretKey key, byte[] nonce, int counter)
	{
		try
		{
			var c = Cipher.getInstance("ChaCha20");
			c.init(Cipher.ENCRYPT_MODE, key, new ChaCha20ParameterSpec(nonce, counter));
			return c.doFinal(src);
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	static byte[] decrypt(byte[] src, SecretKey key, byte[] nonce, int counter)
	{
		try
		{
			var c = Cipher.getInstance("ChaCha20");
			c.init(Cipher.DECRYPT_MODE, key, new ChaCha20ParameterSpec(nonce, counter));
			return c.doFinal(src);
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}

}
