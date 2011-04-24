package skilltimeline.core;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SkillEntry {

	String _desc;
	List<TimePair> _pairs = new ArrayList<TimePair>();

	public SkillEntry(String d) {
		_desc = d;
	}

	public String getDescription() {
		return _desc;
	}

	public void addTime(Calendar[] c) {
		if (c.length != 2)
			throw new IllegalArgumentException("Expected 2 dates.");

		addTime(new TimePair(c[0].getTimeInMillis(), c[1].getTimeInMillis()));
	}

	public void addTime(TimePair tp) {
		_pairs.add(tp);
	}

	public List<TimePair> getTimePairs() {
		return _pairs;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getDescription()).append(": ");
		for (TimePair tp : getTimePairs()) {
			sb.append(tp.start).append("-").append(tp.end).append(" ");
		}
		return sb.toString();
	}
	
	static final class TimePair {
		public final long start, end;

		public TimePair(long start, long end) {
			this.start = start;
			this.end = end;
		}
	}
}
