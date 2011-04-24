package skilltimeline.core;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SkillEntry {

	final String Description;
	List<TimeRange> _pairs = new ArrayList<TimeRange>();

	public SkillEntry(String d) {
		Description = d;
	}

	public void addTime(Calendar[] c) {
		if (c.length != 2)
			throw new IllegalArgumentException("Expected 2 dates.");

		addTime(new TimeRange(c[0].getTimeInMillis(), c[1].getTimeInMillis()));
	}

	public void addTime(TimeRange tp) {
		_pairs.add(tp);
	}

	public List<TimeRange> getTimeRanges() {
		return _pairs;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.Description).append(": ");
		for (TimeRange tp : getTimeRanges()) {
			sb.append(tp).append(" ");
		}
		return sb.toString();
	}

	static final class TimeRange {
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
}
