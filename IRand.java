interface IRand {
	default boolean nextBoolean() {
		return (nextInt() & 1) == 1;
	}

	default int nextInt() {
		return (int) nextLong();
	}

	default int nextInt(int count) {
		var l = (Integer.MAX_VALUE - count + 1) % count;
		var n = nextInt() & Integer.MAX_VALUE;
		while (n < l) {
			n = nextInt() & Integer.MAX_VALUE;
		}
		return n % count;
	}

	default int nextInt(int first, int last) {
		if (first < 0 && first + Integer.MAX_VALUE < last) {
			var n = nextInt();
			while (n < first || last < n) {
				n = nextInt();
			}
			return n;
		} else {
			if (last - first == Integer.MAX_VALUE) {
				return (nextInt() & Integer.MAX_VALUE) + first;
			}
			return nextInt(last - first + 1) + first;
		}
	}

	default long nextLong() {
		return (long) nextInt() << 32 | (long) nextInt() & 0xFFFFFFFFL;
	}

	default long nextLong(long count) {
		var l = (Long.MAX_VALUE - count + 1L) % count;
		var n = nextLong() & Long.MAX_VALUE;
		while (n < l) {
			n = nextLong() & Long.MAX_VALUE;
		}
		return n % count;
	}

	default long nextLong(long first, long last) {
		if (first < 0 && first + Long.MAX_VALUE < last) {
			var n = nextLong();
			while (n < first || last < n) {
				n = nextLong();
			}
			return n;
		} else {
			if (last - first == Long.MAX_VALUE) {
				return (nextLong() & Long.MAX_VALUE) + first;
			}
			return nextLong(last - first + 1L) + first;
		}
	}

}
