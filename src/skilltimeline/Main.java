package skilltimeline;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import skilltimeline.core.Io;
import skilltimeline.core.SkillParser;
import skilltimeline.core.SkillEntry;
import skilltimeline.graph.GraphObject;
import skilltimeline.graph.SvgRenderer;
import skilltimeline.graph.GraphObject.GCanvas;
import skilltimeline.graph.GraphObject.GLabel;
import skilltimeline.graph.GraphObject.GLine;
import skilltimeline.graph.GraphObject.Position;

/**
 * Main entry point to the application: parses command line and provides app
 * runtime.
 */
public class Main {

	public static void main(String[] args) {

		// 1 arg: input filename
		if (args.length == 0)
			throw new IllegalArgumentException("Usage: skilltimeline.Main FILENAME");

		try {
			String[] skillfile = Io.readFile(args[0]);
			List<SkillEntry> parsed_skills = new ArrayList<SkillEntry>();
			SkillParser sparser = new SkillParser();
			for (String sk : skillfile) {
				try {
					SkillEntry se = sparser.parseLine(sk);
					parsed_skills.add(se);
					System.out.println(se);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void test() {
		// ************
		// Just some stupid temporary tests
		// ************

		GraphObject canvas = new GCanvas(null);
		GLine line = new GLine(canvas, new Position(100, 100), "red", 5);
		line.position(new Position(50, 50));
		GLabel lbl = new GLabel(canvas, "the quick brown fox", "black", "Geneva, Tahoma", 12);
		lbl.position(new Position(40, 40));

		StringBuilder sb = new StringBuilder();
		SvgRenderer.getRenderer(canvas).render(canvas, sb);
		System.out.println(sb);

		SkillParser parser = new SkillParser();
		try {
			SkillEntry se = parser.parseLine("Hello world : 2009.05-2010.06.06");
			System.out.println(se);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
