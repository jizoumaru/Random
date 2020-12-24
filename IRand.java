interface IRand {
	default boolean nextBoolean() {
		return (nextInt() & 1) == 1;
	}
	
	default int nextInt() {
		return (int)nextLong();
	}
	
	default int nextInt(int from, int to) {
		if (from < 0 && from + Integer.MAX_VALUE < to) {
			var n = nextInt();
			
			while (n < from || to < n) {
				n = nextInt();
			}
			
			return n;
		} else {
			if (to - from == Integer.MAX_VALUE) {
				return (nextInt() & Integer.MAX_VALUE) + from;
			}
			
			var r = to - from + 1;
			var l = (Integer.MAX_VALUE - r + 1) % r;
			var n = nextInt() & Integer.MAX_VALUE;
			
			while (n < l) {
				n = nextInt() & Integer.MAX_VALUE;
			}
			
			return n % r + from;
		}
	}

	default long nextLong() {
		return (long)nextInt() << 32 | (long)nextInt() & 0xFFFFFFFFL;
	}
	
	default long nextLong(long from, long to) {
		if (from < 0 && from + Long.MAX_VALUE < to) {
			var n = nextLong();
			
			while (n < from || to < n) {
				n = nextLong();
			}
			
			return n;
		} else {
			if (to - from == Long.MAX_VALUE) {
				return (nextLong() & Long.MAX_VALUE) + from;
			}
			
			var r = to - from + 1L;
			var l = (Long.MAX_VALUE - r + 1L) % r;
			var n = nextLong() & Long.MAX_VALUE;
			
			while (n < l) {
				n = nextLong() & Long.MAX_VALUE;
			}
			
			return n % r + from;
		}
	}

}
