package skilltimeline;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import skilltimeline.core.GraphBuilder;
import skilltimeline.core.Io;
import skilltimeline.core.SkillEntry;
import skilltimeline.core.SkillParser;
import skilltimeline.graph.GraphObject;
import skilltimeline.graph.SvgRenderer;

/**
 * Main entry point to the application: parses command line and provides app
 * runtime.
 */
public class Main {

	public static void main(String[] args) {

		// 1 arg: input filename
		if (args.length == 0 || args[0].equals("-h")) {
			String errMsg = "Usage: skilltimeline.Main INPUTFILE \n";
			errMsg += "Output file generated is INPUTFILE.svg \n\n";
			errMsg += "Format of input file:\n";
			errMsg += "Some skill: 2008.04-2009.09, 2010.01-2011.03 \n";
			errMsg += "Some other skill: 2006-2010.06";
			System.out.println(errMsg);
			System.exit(1);
		}

		final String filename = args[0];
		try {
			String[] skillfile = Io.readFile(filename);
			List<SkillEntry> parsed_skills = new ArrayList<SkillEntry>();
			SkillParser sparser = new SkillParser();
			for (String sk : skillfile) {
				if (sk.startsWith("#"))
					continue; // comment line
				try {
					SkillEntry se = sparser.parseLine(sk);
					parsed_skills.add(se);
				} catch (ParseException e) {
					System.err.println("Error parsing input file... skipping line.");
					e.printStackTrace();
				}
			}
			System.out.printf("Parsed %s entries. About to draw scene...\n", parsed_skills.size());
			GraphObject canvas = GraphBuilder.buildGraph(parsed_skills);
			StringBuilder outputBuffer = new StringBuilder();
			SvgRenderer.getRenderer(canvas).render(canvas, outputBuffer);
			Io.writeFile(filename + ".svg", outputBuffer.toString());
			System.out.println("Done.");
		} catch (IOException e) {
			System.err.println("Error occurred, exiting.");
			e.printStackTrace();
		}

	}

}
