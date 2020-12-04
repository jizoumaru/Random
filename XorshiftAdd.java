import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.util.function.IntSupplier;

public class XorshiftAdd implements IntSupplier {
	private final int[] s;

	public XorshiftAdd() {
		var s = new int[4];
		var b = ByteBuffer.wrap(SecureRandom.getSeed(Integer.SIZE * s.length));
		for (var i = 0; i < s.length; i++) {
			s[i] = b.getInt(Integer.SIZE * i);
		}
		this.s = s;
	}

	@Override
	public int getAsInt() {
		var t = s[0];
		t ^= t << 15;
		t ^= t >>> 18;
		t ^= s[3] << 11;
		s[0] = s[1];
		s[1] = s[2];
		s[2] = s[3];
		s[3] = t;
		return s[3] + s[2];
	}
}
