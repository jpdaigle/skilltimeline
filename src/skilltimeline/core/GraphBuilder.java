package skilltimeline.core;

import java.util.Calendar;
import java.util.Collection;

import skilltimeline.core.SkillEntry.TimeRange;
import skilltimeline.graph.GraphObject;
import skilltimeline.graph.GraphObject.GCanvas;
import skilltimeline.graph.GraphObject.GLabel;
import skilltimeline.graph.GraphObject.GLine;
import skilltimeline.graph.GraphObject.GRectangle;
import skilltimeline.graph.GraphObject.Position;

public class GraphBuilder {

	public GraphObject buildGraph(Collection<SkillEntry> entries) {
		// Find extents of timeline and pad them on both sides by a few months
		TimeRange extents = findExtents(entries);
		Calendar ext_start = Calendar.getInstance(), ext_end = Calendar.getInstance();
		ext_start.setTimeInMillis(extents.start);
		ext_start.add(Calendar.MONTH, -3);
		ext_end.setTimeInMillis(extents.end);
		ext_end.add(Calendar.MONTH, 3);
		System.out.println(extents);

		final TimelineData timeline = new TimelineData(ext_start.getTimeInMillis(), ext_end.getTimeInMillis(), 600);

		final int TEXTWIDTH = 100;
		final int TIMELINEHEIGHT = entries.size() * Settings.SkillLineHeight;
		GraphObject canvas = new GCanvas(null);

		// Build vertical time indicators
		for (int year = ext_start.get(Calendar.YEAR) + 1; year <= ext_end.get(Calendar.YEAR); year++) {
			Calendar c = Calendar.getInstance();
			c.set(year, 0, 1, 0, 0, 0);
			int line_x = timeline.convertTimeToPixel(c.getTimeInMillis()) + TEXTWIDTH;
			GLine gline = new GLine(canvas, new Position(0, TIMELINEHEIGHT + 30), "#aaaaaa", 1);
			gline.position(new Position(line_x, 10));

			int year_fontSize = (int) ((year % 5 == 0) ? (Settings.SkillFontSize * 1.5) : (Settings.SkillFontSize));
			String year_colour = (year % 5 == 0) ? "black" : "#aaaaaa";

			GLabel lblYear = new GLabel(
					canvas, 
					String.valueOf(year), 
					year_colour, 
					Settings.YearFontFace,
					year_fontSize);
			lblYear.position(new Position(line_x, TIMELINEHEIGHT + 40));
		}

		// Build timelines
		// For each skill entry, create a Label and Rects on the timeline
		int y_off = 30;
		for (SkillEntry se : entries) {
			GLabel lbl = new GLabel(canvas, se.Description, Settings.SkillColor, Settings.FontFace,
					Settings.SkillFontSize);
			lbl.position(new Position(TEXTWIDTH, y_off));
			lbl.anchor(GLabel.END);

			for (TimeRange tr : se.getTimeRanges()) {
				Position pos, size;
				pos = new Position(timeline.convertTimeToPixel(tr.start) + TEXTWIDTH, y_off - Settings.SkillFontSize);
				size = new Position(timeline.convertTimeToPixel(tr.end) - pos.x + TEXTWIDTH, Settings.SkillFontSize);
				GRectangle rect = new GRectangle(canvas, Settings.SkillTimeColor, Settings.SkillTimeColor, size);
				rect.position(pos);
			}
			y_off += Settings.SkillLineHeight;
		}

		return canvas;
	}

	private TimeRange findExtents(Collection<SkillEntry> entries) {
		long min = Long.MAX_VALUE, max = 0;

		for (SkillEntry se : entries) {
			for (TimeRange t : se.getTimeRanges()) {
				if (t.start < min)
					min = t.start;
				if (t.end > max)
					max = t.end;
			}
		}

		return new TimeRange(min, max);
	}

	private static class TimelineData {
		final long pixelWidth;
		final long base;
		final long end;

		public TimelineData(long base, long end, long xscale) {
			this.base = base;
			this.end = end;
			this.pixelWidth = xscale;
		}

		long getTimeWidth() {
			return end - base;
		}

		int convertTimeToPixel(long t) {
			t -= base;
			return (int) (((double) t / (double) getTimeWidth()) * pixelWidth);
		}

	}
}
