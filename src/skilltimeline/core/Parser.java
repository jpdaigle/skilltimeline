package skilltimeline.core;

import java.text.ParseException;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

	public SkillEntry parseLine(String s) throws ParseException {
		// skillname: start.s.s-end, start.month.day-end.month.day

		SkillEntry se = null;

		// match any characters until the ':', non-greedy
		Pattern p = Pattern.compile("(.*?):");
		Matcher m = p.matcher(s);

		if (!m.find())
			return null;
		se = new SkillEntry(m.group(1));

		// Take what's left and split it on comma separators
		s = s.substring(m.end());
		String[] chunks = s.split(",");
		for (String c : chunks) {
			se.addTime(parseTimePair(c.trim()));
		}

		return se;
	}

	public Calendar[] parseTimePair(String s) throws ParseException {
		// Example:
		// 2010.05.31-2011.03
		// month and day are optional

		String[] parts = s.split("-");
		if (parts.length != 2) {
			throw new ParseException("Could not split start/end dates.", 0);
		}

		Calendar c_start = parseDateChunk(parts[0]);
		Calendar c_end = parseDateChunk(parts[1]);

		return new Calendar[] { c_start, c_end };
	}

	public Calendar parseDateChunk(String s) {
		Pattern p = Pattern.compile("[0-9]+");
		Matcher m = p.matcher(s);
		Calendar c = Calendar.getInstance();
		int year = 0, month = 0, day = 1;
		if (m.find()) {
			year = Integer.parseInt(m.group());
		}
		if (m.find()) {
			month = Integer.parseInt(m.group());
		}
		if (m.find()) {
			day = Integer.parseInt(m.group());
		}
		c.set(year, month, day);
		return c;
	}
}
