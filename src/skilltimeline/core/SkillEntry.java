package skilltimeline.core;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class SkillEntry {

	final String Description;
	final List<TimeRange> _pairs = new ArrayList<TimeRange>();

	public SkillEntry(String d) {
		Description = d;
	}

	public void addTime(Calendar[] c) {
		if (c.length != 2)
			throw new IllegalArgumentException("Expected a range with 2 dates.");

		addTime(new TimeRange(c[0].getTimeInMillis(), c[1].getTimeInMillis()));
	}

	public void addTime(TimeRange tp) {
		_pairs.add(tp);
	}

	public List<TimeRange> getTimeRanges() {
		return Collections.unmodifiableList(_pairs);
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
}
