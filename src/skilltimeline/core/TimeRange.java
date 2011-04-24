package skilltimeline.core;

final class TimeRange {
	public final long start, end;

	public TimeRange(long start, long end) {
		this.start = start;
		this.end = end;
	}

	@Override
	public String toString() {
		return start + "-" + end;
	}
}